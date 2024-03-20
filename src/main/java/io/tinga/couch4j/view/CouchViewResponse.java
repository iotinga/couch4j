package io.tinga.couch4j.view;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonGetter;

/**
 * Response to a view query
 * 
 * @param <K> type of the key
 * @param <V> type of the value
 * @param <D> type of the document
 * 
 */
public class CouchViewResponse<K, V, D> {
    private long updateSeq;
    private long offset;
    private long totalRows;
    private List<CouchViewItem<K, V, D>> rows;

    /**
     * Offset where the document list started.
     * 
     * @return
     */
    public long getOffset() {
        return offset;
    }

    /**
     * Number of documents in the database/view.
     * 
     * @return
     */
    @JsonGetter("total_rows")
    public long getTotalRows() {
        return totalRows;
    }

    /**
     * Array of view row objects. By default the information returned contains only
     * the document ID and revision.
     * 
     * @return
     */
    public List<CouchViewItem<K, V, D>> getRows() {
        return rows;
    }

    /**
     * Current update sequence for the database.
     * 
     * @return
     */
    @JsonGetter("update_seq")
    public long getUpdateSeq() {
        return updateSeq;
    }
}
