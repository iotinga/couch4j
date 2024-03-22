package io.tinga.couch4j.changes;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;

import io.tinga.couch4j.CouchDatabase;

public class CouchNormalChangesFeed implements CouchChangesFeed, Runnable {
    private final ExecutorService executorService;
    private final CouchDatabase db;
    private final CouchChangesRequest initialParams;
    private final int pollIntervalMs;
    private Consumer<CouchChangeItem> onItem;
    private Consumer<Exception> onError;
    private ChangesFeedState state;
    private CouchChangesRequest currentParams;

    public CouchNormalChangesFeed(CouchDatabase db, CouchChangesRequest params) {
        this(db, params, 1000);
    }

    public CouchNormalChangesFeed(CouchDatabase db, CouchChangesRequest params, int pollIntervalMs) {
        this(db, params, pollIntervalMs, Executors.newSingleThreadExecutor());
    }

    public CouchNormalChangesFeed(CouchDatabase db, CouchChangesRequest params, int pollIntervalMs,
            ExecutorService executorService) {
        this.state = ChangesFeedState.IDLE;
        this.initialParams = params.clone();
        this.executorService = executorService;
        this.pollIntervalMs = pollIntervalMs;
        this.db = db;
        this.currentParams = this.initialParams.clone();

        // override the parameters for this polling
        this.initialParams.setFeed(CouchChangesRequest.FEED_NORMAL);
    }

    @Override
    public ChangesFeedState getState() {
        return this.state;
    }

    @Override
    public void start(Consumer<CouchChangeItem> onItem, Consumer<Exception> onError) {
        if (state != ChangesFeedState.IDLE) {
            throw new IllegalStateException("feed is already started");
        }

        state = ChangesFeedState.STARTING;
        this.onItem = onItem;
        this.onError = onError;
        currentParams = this.initialParams.clone();
        executorService.submit(this);
    }

    @Override
    public void resume() {
        if (state != ChangesFeedState.PAUSED) {
            throw new IllegalStateException("feed is not paused. Use start to start it!");
        }

        state = ChangesFeedState.STARTING;
        executorService.submit(this);
    }

    @Override
    public void pause() {
        if (state != ChangesFeedState.RUNNING) {
            throw new IllegalStateException("feed is not running");
        }
        state = ChangesFeedState.PAUSING;
    }

    @Override
    public void stop() {
        if (state != ChangesFeedState.RUNNING) {
            throw new IllegalStateException("feed is not running");
        }
        state = ChangesFeedState.STOPPING;
        onItem = null;
        onError = null;
    }

    public void run() {
        state = ChangesFeedState.RUNNING;

        while (state == ChangesFeedState.RUNNING) {
            try {
                CouchChangesResponse changes = db.changes(this.currentParams);
                if (onItem != null && changes != null) {
                    for (CouchChangeItem change : changes.getResults()) {
                        onItem.accept(change);
                    }
                }

                // set the since of the request as the last since
                currentParams.setSince(changes.getLastSeq());

                Thread.sleep(pollIntervalMs);
            } catch (Exception e) {
                if (onError != null) {
                    onError.accept(e);
                }
            }
        }

        switch (state) {
            case ChangesFeedState.STOPPING:
                state = ChangesFeedState.IDLE;
                break;
            case ChangesFeedState.PAUSING:
                state = ChangesFeedState.PAUSED;
                break;
            default:
                throw new IllegalStateException("unreachable state");
        }
    }
}
