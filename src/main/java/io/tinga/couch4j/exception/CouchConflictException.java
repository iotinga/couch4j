package io.tinga.couch4j.exception;

import io.tinga.couch4j.dto.CouchErrorResponse;

public class CouchConflictException extends CouchException {
    public CouchConflictException(CouchErrorResponse response) {
        super("conflict while performing the operation", response);
    }

    public CouchConflictException() {
        this(null);
    }
}
