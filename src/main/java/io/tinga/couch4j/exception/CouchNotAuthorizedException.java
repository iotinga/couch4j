package io.tinga.couch4j.exception;

import io.tinga.couch4j.dto.CouchErrorResponse;

public class CouchNotAuthorizedException extends CouchException {
    public CouchNotAuthorizedException(CouchErrorResponse response) {
        super("authentication error", response);
    }

    public CouchNotAuthorizedException() {
        this(null);
    }
}
