package io.tinga.couch4j.view;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

public class CouchViewResponse<K, V, D> {
    private long updateSeq;
    private long offset;
    private long totalRows;
    private List<CouchViewItem<K, V, D>> rows;

    public CouchViewResponse() {
    }

    public long getOffset() {
        return offset;
    }

    public void setOffset(long offset) {
        this.offset = offset;
    }

    @JsonGetter("total_rows")
    public long getTotalRows() {
        return totalRows;
    }

    @JsonSetter("total_rows")
    public void setTotalRows(long totalRows) {
        this.totalRows = totalRows;
    }

    public List<CouchViewItem<K, V, D>> getRows() {
        return rows;
    }

    public void setRows(List<CouchViewItem<K, V, D>> rows) {
        this.rows = rows;
    }

    @JsonGetter("update_seq")
    public long getUpdateSeq() {
        return updateSeq;
    }

    @JsonSetter("update_seq")
    public void setUpdateSeq(long update_seq) {
        this.updateSeq = update_seq;
    }
}
