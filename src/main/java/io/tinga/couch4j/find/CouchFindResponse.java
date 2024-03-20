package io.tinga.couch4j.find;

import java.util.List;

import jakarta.annotation.Nullable;

public class CouchFindResponse<T> {
    private List<T> docs;

    @Nullable
    private String warning;

    @Nullable
    private CouchFindExecutionStats execution_stats;

    @Nullable
    private String bookmark;

    public List<T> getDocs() {
        return docs;
    }

    @Nullable
    public String getWarning() {
        return warning;
    }

    @Nullable
    public CouchFindExecutionStats getExecutionStats() {
        return execution_stats;
    }

    @Nullable
    public String getBookmark() {
        return bookmark;
    }
}
