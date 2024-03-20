package io.tinga.couch4j.exception;

import io.tinga.couch4j.dto.CouchErrorStatus;

public class CouchInvalidArgumentsException extends CouchException {
    public CouchInvalidArgumentsException(CouchErrorStatus response) {
        super("provided request is invalid", response);
    }

    public CouchInvalidArgumentsException() {
        this(null);
    }
}
