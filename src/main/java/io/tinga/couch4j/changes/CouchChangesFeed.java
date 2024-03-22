package io.tinga.couch4j.changes;

import java.util.function.Consumer;

/**
 * CouchDb changes feed
 */
public interface CouchChangesFeed {
    /**
     * State of the changes feed
     */
    enum ChangesFeedState {
        /** Not running */
        IDLE,

        /** Feed is starting up */
        STARTING,

        /** Feed is running */
        RUNNING,

        /** Feed is */
        STOPPING,

        /** Feed is pausing */
        PAUSING,

        /** Feed is paused */
        PAUSED,
    }

    /**
     * Get the state of the changes feed
     * 
     * @return the current state
     */
    ChangesFeedState getState();

    /**
     * Start the changes feed
     * 
     * @param onItem  callback called on each item
     * @param onError callback called in case of an error
     * @throws IllegalStateException if the feed is not idle
     */
    void start(Consumer<CouchChangeItem> onItem, Consumer<Exception> onError);

    /**
     * Resume the changes feed, after pausing it
     * 
     * @throws IllegalStateException if the feed is not paused
     */
    void resume();

    /**
     * Pause the changes feed
     * 
     * @throws IllegalStateException if the feed is not running
     */
    void pause();

    /**
     * Stop the changes feed
     * 
     * @throws IllegalStateException if the feed is not running
     */
    void stop();
}
