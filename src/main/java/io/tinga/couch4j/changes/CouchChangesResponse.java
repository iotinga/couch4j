package io.tinga.couch4j.changes;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonGetter;

/**
 * Changes feed response
 */
public class CouchChangesResponse {
    private String lastSeq;
    private Integer pending;
    private List<CouchChangeItem> results;

    /**
     * Get last sequence
     * 
     * @return
     */
    @JsonGetter("last_seq")
    public String getLastSeq() {
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

    @Override
    public String toString() {
        return "CouchChangesResponse [lastSeq=" + lastSeq + ", pending=" + pending + ", results=" + results + "]";
    }
}
