package io.tinga.couch4j.find;

import java.util.List;

import jakarta.annotation.Nullable;

/**
 * The result of a find query
 */
public class CouchFindResponse<T> {
    private List<T> docs;

    @Nullable
    private String warning;

    @Nullable
    private CouchFindExecutionStats execution_stats;

    @Nullable
    private String bookmark;

    /**
     * Get the returned documents
     * 
     * @return
     */
    public List<T> getDocs() {
        return docs;
    }

    /**
     * Get an eventual warning about not optimal query execution
     * 
     * @return
     */
    @Nullable
    public String getWarning() {
        return warning;
    }

    /**
     * Get the execution stats. This is populated only if
     * setExecutionStats(true) was called on the query
     * 
     * @return
     */
    @Nullable
    public CouchFindExecutionStats getExecutionStats() {
        return execution_stats;
    }

    /**
     * Get the bookmark, that is needed to fetch the next data page.
     * <b>WARNING</b>: the sole presence of a bookmark doesn't indicate that there
     * is another data page!
     * 
     * @return
     */
    @Nullable
    public String getBookmark() {
        return bookmark;
    }
}
