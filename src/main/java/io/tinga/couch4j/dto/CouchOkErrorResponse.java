package io.tinga.couch4j.dto;

import jakarta.annotation.Nullable;

/**
 * A response that can be either an ok response or an error
 */
public class CouchOkErrorResponse<T> {
    private T ok;
    private CouchErrorResponse error;

    /**
     * The request is successful
     * 
     * @return true if request is successful
     */
    public boolean isOk() {
        return ok != null;
    }

    /**
     * Get the document, only if get is successful
     * 
     * @return the document type
     */
    @Nullable
    public T getOk() {
        return ok;
    }

    /**
     * Get the error code, if the get failed
     * 
     * @return the error document
     */
    @Nullable
    public CouchErrorResponse getError() {
        return error;
    }
}