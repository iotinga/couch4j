package io.tinga.couch4j.changes;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

public class CouchChangesResponse {
    private Long lastSeq;
    private Integer pending;
    private List<CouchChangeItem> results;

    @JsonGetter("last_seq")
    public long getLastSeq() {
        return lastSeq;
    }

    @JsonSetter("last_seq")
    public void setLast_seq(long last_seq) {
        this.lastSeq = last_seq;
    }

    public List<CouchChangeItem> getResults() {
        return results;
    }

    public void setResults(List<CouchChangeItem> results) {
        this.results = results;
    }

    public Integer getPending() {
        return pending;
    }

    public void setPending(Integer pending) {
        this.pending = pending;
    }
}
