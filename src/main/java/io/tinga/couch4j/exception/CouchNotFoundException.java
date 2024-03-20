package io.tinga.couch4j.exception;

import io.tinga.couch4j.dto.CouchErrorStatus;

public class CouchNotFoundException extends CouchException {
    public CouchNotFoundException(CouchErrorStatus response) {
        super("not found", response);
    }

    public CouchNotFoundException() {
        this(null);
    }
}
