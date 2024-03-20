package io.tinga.couch4j.changes;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;
import java.util.function.Function;

import io.tinga.couch4j.CouchDatabase;

public class CouchNormalChangesFeed implements CouchChangesFeed, Runnable {
    private final ExecutorService executorService;
    private final CouchDatabase db;
    private final CouchChangesRequest params;
    private Consumer<CouchChangeItem> onItem;
    private Function<CouchChangesResponse, Boolean> onResponse;
    private Consumer<Exception> onError;
    private ChangesFeedState state;
    private String currentSince;
    private CouchChangesRequest currentParams;

    public CouchNormalChangesFeed(CouchDatabase db, CouchChangesRequest params) {
        this.state = ChangesFeedState.IDLE;
        this.currentSince = params.getSince();
        this.params = params;
        this.currentParams = this.params.clone();
        this.executorService = Executors.newSingleThreadExecutor();
        this.db = db;
    }

    public CouchNormalChangesFeed(CouchDatabase db, CouchChangesRequest params,
            ExecutorService executorService) {
        this.state = ChangesFeedState.IDLE;
        this.currentSince = params.getSince();
        this.params = params;
        this.currentParams = this.params.clone();
        this.executorService = executorService;
        this.db = db;
    }

    @Override
    public ChangesFeedState state() {
        return this.state;
    }

    @Override
    public void start(Consumer<CouchChangeItem> onItem, Consumer<Exception> onError) {
        if (this.state != ChangesFeedState.IDLE) {
            return;
        }

        this.state = ChangesFeedState.SETUP;
        this.onResponse = null;
        this.onItem = onItem == null ? this.onItem : onItem;
        this.onError = onError == null ? this.onError : onError;

        this.executorService.submit(this);
    }

    @Override
    public void pause() {
        if (this.state != ChangesFeedState.RUNNING) {
            return;
        }
        this.state = ChangesFeedState.HALTING;
    }

    @Override
    public void stop() {
        if (this.state != ChangesFeedState.RUNNING) {
            return;
        }
        this.state = ChangesFeedState.HALTING;
        this.onItem = null;
        this.onResponse = null;
        this.onError = null;
        this.currentSince = this.params.getSince();
        this.currentParams = this.params.clone();
    }

    public void run() {
        this.state = ChangesFeedState.RUNNING;
        var params = this.currentParams.clone();
        this.params.setSince(this.currentSince);
        while (this.state == ChangesFeedState.RUNNING) {
            try {
                var changes = this.db.changes(params);
                if (this.onItem != null && changes != null) {
                    for (CouchChangeItem change : changes.getResults()) {
                        this.onItem.accept(change);
                    }
                }
                if (this.onResponse != null && changes != null) {
                    for (CouchChangeItem change : changes.getResults()) {
                        this.onItem.accept(change);
                    }
                }
            } catch (Exception e) {
                if (this.onError != null) {
                    this.onError.accept(e);
                }
            }
        }
        this.state = ChangesFeedState.IDLE;
    }

    @Override
    public void start(Function<CouchChangesResponse, Boolean> onResponse, Consumer<Exception> onError) {
        if (this.state != ChangesFeedState.IDLE) {
            return;
        }

        this.state = ChangesFeedState.SETUP;
        this.onItem = null;
        this.onResponse = onResponse == null ? this.onResponse : onResponse;
        this.onError = onError == null ? this.onError : onError;

        this.executorService.submit(this);
    }
}
