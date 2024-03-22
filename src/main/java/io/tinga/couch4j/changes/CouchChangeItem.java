package io.tinga.couch4j.changes;

import java.util.List;

/**
 * An item of the changes feed
 */
public class CouchChangeItem {
    /**
     * A change item
     */
    public static class Change {
        private String rev;

        /**
         * Get the document revision
         * 
         * @return the document revision
         */
        public String getRev() {
            return rev;
        }

        @Override
        public String toString() {
            return "Change [rev=" + rev + "]";
        }
    }

    private String id;
    private String seq;
    private boolean deleted;
    private List<Change> changes;

    /**
     * Get the id of the document
     * 
     * @return id of the document
     */
    public String getId() {
        return id;
    }

    /**
     * Get the sequence number
     * 
     * @return sequence number
     */
    public String getSeq() {
        return seq;
    }

    /**
     * Get if the document is deleted
     * 
     * @return true if deleted
     */
    public boolean isDeleted() {
        return deleted;
    }

    /**
     * Get a list of changes
     * 
     * @return a list of changes
     */
    public List<Change> getChanges() {
        return changes;
    }

    @Override
    public String toString() {
        return "CouchChangeItem [id=" + id + ", seq=" + seq + ", deleted=" + deleted + ", changes=" + changes + "]";
    }
}
