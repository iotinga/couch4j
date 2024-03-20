package io.tinga.couch4j.exception;

import io.tinga.couch4j.dto.CouchErrorResponse;
import jakarta.annotation.Nullable;

public class CouchException extends Exception {
    private String reason;
    private String error;

    public CouchException(String message) {
        super(message);
    }

    public CouchException(String message, CouchErrorResponse response) {
        this(message);
        this.reason = response.getReason();
        this.error = response.getError();
    }

    /**
     * Return the CouchDb error code.
     * See CouchDb documentation for the list of error codes.
     * 
     * @return CouchDb error code
     */
    @Nullable
    public String getErrorCode() {
        return error;
    }

    /**
     * Returns the CouchDb reason code.
     * See CouchDb documentation for the list of reason codes.
     * 
     * @return Couchdb reason code
     */
    @Nullable
    public String getReason() {
        return reason;
    }
}
