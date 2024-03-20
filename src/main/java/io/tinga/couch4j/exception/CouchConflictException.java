package io.tinga.couch4j.exception;

import io.tinga.couch4j.dto.CouchErrorStatus;

public class CouchConflictException extends CouchException {
    public CouchConflictException(CouchErrorStatus response) {
        super("conflict while performing the operation", response);
    }

    public CouchConflictException() {
        this(null);
    }
}
