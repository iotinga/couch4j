package io.tinga.couch4j.exception;

import io.tinga.couch4j.dto.CouchErrorStatus;

public class CouchPreconditionFailed extends CouchException {
    public CouchPreconditionFailed(CouchErrorStatus response) {
        super("not found", response);
    }

    public CouchPreconditionFailed() {
        this(null);
    }
}
