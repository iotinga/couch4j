package io.tinga.couch4j.exception;

import io.tinga.couch4j.dto.CouchErrorStatus;

public class CouchNotAuthorizedException extends CouchException {
    public CouchNotAuthorizedException(CouchErrorStatus response) {
        super("authentication error", response);
    }

    public CouchNotAuthorizedException() {
        this(null);
    }
}
