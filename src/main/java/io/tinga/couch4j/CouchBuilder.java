package io.tinga.couch4j;

import io.tinga.couch4j.auth.CouchAuthentication;
import io.tinga.couch4j.auth.CouchAuthenticationBasic;
import io.tinga.couch4j.auth.CouchAuthenticationNone;

public class CouchBuilder {
    private String uri = "http://localhost:5984";
    private CouchAuthentication auth = new CouchAuthenticationNone();

    public CouchBuilder setUri(String uri) {
        this.uri = uri;
        return this;
    }

    public CouchBuilder setBasicAuth(String username, String password) {
        auth = new CouchAuthenticationBasic(username, password);
        return this;
    }

    public CouchServer build() {
        return new CouchServerImpl(uri, auth);
    }
}
