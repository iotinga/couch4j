package io.tinga.couch4j.dto;

import jakarta.annotation.Nullable;

/**
 * An error response returned by CouchDb
 */
public class CouchErrorResponse {
    private String id;
    private String rev;
    private String error;
    private String reason;

    /**
     * Get the id of the document that was trying to modify
     * 
     * @return
     */
    @Nullable
    public String getId() {
        return id;
    }

    /**
     * Get the revision of the document, if present
     * 
     * @return
     */
    @Nullable
    public String getRev() {
        return rev;
    }

    /**
     * Get the error string
     * 
     * @return
     */
    public String getError() {
        return error;
    }

    /**
     * Get the reason string
     * 
     * @return
     */
    public String getReason() {
        return reason;
    }
}
