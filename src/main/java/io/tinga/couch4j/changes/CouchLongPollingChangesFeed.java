package io.tinga.couch4j.changes;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.tinga.couch4j.CouchDatabase;

public class CouchLongPollingChangesFeed implements CouchChangesFeed, Runnable {
    private static final Logger log = LoggerFactory.getLogger(CouchLongPollingChangesFeed.class);
    private static final int RETRY_MS = 1000;

    private final ExecutorService executorService;
    private final CouchDatabase db;
    private final CouchChangesRequest initialParams;
    private Consumer<CouchChangeItem> onItem;
    private Consumer<Exception> onError;
    private ChangesFeedState state;
    private CouchChangesRequest currentParams;

    public CouchLongPollingChangesFeed(CouchDatabase changesService, CouchChangesRequest params) {
        this(changesService, params, Executors.newSingleThreadExecutor());
    }

    public CouchLongPollingChangesFeed(CouchDatabase changesService, CouchChangesRequest params,
            ExecutorService executorService) {
        this.state = ChangesFeedState.IDLE;
        this.initialParams = params.clone();

        // override the parameters for this polling
        this.initialParams.setFeed(CouchChangesRequest.FEED_LONGPOLL);

        this.executorService = executorService;
        this.db = changesService;
        this.currentParams = this.initialParams.clone();
    }

    @Override
    public ChangesFeedState getState() {
        return this.state;
    }

    @Override
    public void start(Consumer<CouchChangeItem> onItem, Consumer<Exception> onError) {
        if (this.state != ChangesFeedState.IDLE) {
            throw new IllegalStateException("feed is already started");
        }

        this.state = ChangesFeedState.STARTING;
        this.onItem = onItem;
        this.onError = onError;
        this.currentParams = this.initialParams.clone();
        this.executorService.submit(this);
    }

    @Override
    public void resume() {
        if (this.state != ChangesFeedState.PAUSED) {
            throw new IllegalStateException("feed is not paused. Use start to start it!");
        }

        this.state = ChangesFeedState.STARTING;
        this.executorService.submit(this);
    }

    @Override
    public void pause() {
        if (this.state != ChangesFeedState.RUNNING) {
            throw new IllegalStateException("feed is not running");
        }
        this.state = ChangesFeedState.PAUSING;
    }

    @Override
    public void stop() {
        if (this.state != ChangesFeedState.RUNNING) {
            throw new IllegalStateException("feed is not running");
        }
        this.state = ChangesFeedState.STOPPING;
        this.onItem = null;
        this.onError = null;
    }

    public void run() {
        this.state = ChangesFeedState.RUNNING;

        while (this.state == ChangesFeedState.RUNNING) {
            try {
                log.debug("requesting changes in {} mode since {}", this.currentParams.getFeed(),
                        this.currentParams.getSince());
                CouchChangesResponse changes = this.db.changes(this.currentParams);
                log.debug("got changes {}", changes);

                if (this.onItem != null && changes != null) {
                    for (CouchChangeItem change : changes.getResults()) {
                        log.debug("got change {}", change);
                        this.onItem.accept(change);
                    }
                }

                // set the since of the request as the last since
                this.currentParams.setSince(changes.getLastSeq());
            } catch (Exception e) {
                log.warn("CouchDb request failed", e);
                if (this.onError != null) {
                    this.onError.accept(e);
                }

                log.debug("try again in {}", RETRY_MS);
                try {
                    Thread.sleep(RETRY_MS);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }
        }

        switch (this.state) {
            case ChangesFeedState.STOPPING:
                this.state = ChangesFeedState.IDLE;
                break;
            case ChangesFeedState.PAUSING:
                this.state = ChangesFeedState.PAUSED;
                break;
            default:
                throw new IllegalStateException("unreachable state");
        }
    }
}
