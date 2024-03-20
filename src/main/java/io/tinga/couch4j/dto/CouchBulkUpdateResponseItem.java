package io.tinga.couch4j.dto;

import jakarta.annotation.Nullable;

/**
 * A response item to a POST /{db}/_bulk_docs
 */
public class CouchBulkUpdateResponseItem {
    private String id;

    @Nullable
    private String rev;

    private Boolean ok;

    @Nullable
    private String error;

    @Nullable
    private String reason;

    /**
     * Get the id of the document
     * 
     * @return id of the document
     */
    public String getId() {
        return id;
    }

    /**
     * Get the revision of the document
     * 
     * @return revision of the document
     */
    @Nullable
    public String getRev() {
        return rev;
    }

    /**
     * Check if the request is successful
     * 
     * @return true if request is ok
     */
    public boolean isOk() {
        return ok != null && ok;
    }

    /**
     * Get a string representing the error, if any
     * 
     * @return a string representing the error
     */
    @Nullable
    public String getError() {
        return error;
    }

    /**
     * Get a string representing the reason, if any
     * 
     * @return a string representing the reason
     */
    @Nullable
    public String getReason() {
        return reason;
    }
}
