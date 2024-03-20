package io.tinga.couch4j.exception;

import io.tinga.couch4j.dto.CouchErrorResponse;

public class CouchPreconditionFailed extends CouchException {
    public CouchPreconditionFailed(CouchErrorResponse response) {
        super("not found", response);
    }

    public CouchPreconditionFailed() {
        this(null);
    }
}
