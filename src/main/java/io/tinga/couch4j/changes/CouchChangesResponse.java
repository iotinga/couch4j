package io.tinga.couch4j.changes;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonGetter;

/**
 * Changes feed response
 */
public class CouchChangesResponse {
    private Long lastSeq;
    private Integer pending;
    private List<CouchChangeItem> results;

    /**
     * Get last sequence
     * 
     * @return
     */
    @JsonGetter("last_seq")
    public long getLastSeq() {
        return lastSeq;
    }

    /**
     * Get the results
     * 
     * @return
     */
    public List<CouchChangeItem> getResults() {
        return results;
    }

    /**
     * Get pending
     * 
     * @return
     */
    public Integer getPending() {
        return pending;
    }
}
