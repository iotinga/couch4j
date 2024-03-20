package io.tinga.couch4j.changes;

import java.util.function.Consumer;
import java.util.function.Function;

public interface CouchChangesFeed {

    public enum ChangesFeedState {
        IDLE,
        SETUP,
        RUNNING,
        HALTING
    }

    public ChangesFeedState state();

    public void start(Consumer<CouchChangeItem> onItem, Consumer<Exception> onError);

    public void start(Function<CouchChangesResponse, Boolean> onResponse, Consumer<Exception> onError);

    public void pause();

    public void stop();
}
