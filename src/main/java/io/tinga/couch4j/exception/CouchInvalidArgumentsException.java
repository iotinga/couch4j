package io.tinga.couch4j.exception;

import io.tinga.couch4j.dto.CouchErrorResponse;

public class CouchInvalidArgumentsException extends CouchException {
    public CouchInvalidArgumentsException(CouchErrorResponse response) {
        super("provided request is invalid", response);
    }

    public CouchInvalidArgumentsException() {
        this(null);
    }
}
