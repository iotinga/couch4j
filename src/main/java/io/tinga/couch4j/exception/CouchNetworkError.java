package io.tinga.couch4j.exception;

public class CouchNetworkError extends CouchException {
    public CouchNetworkError() {
        super("network error");
    }
}
