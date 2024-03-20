package io.tinga.couch4j.exception;

import io.tinga.couch4j.dto.CouchErrorResponse;

public class CouchNotFoundException extends CouchException {
    public CouchNotFoundException(CouchErrorResponse response) {
        super("not found", response);
    }

    public CouchNotFoundException() {
        this(null);
    }
}
