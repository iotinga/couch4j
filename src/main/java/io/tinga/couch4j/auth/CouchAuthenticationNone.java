package io.tinga.couch4j.auth;

public class CouchAuthenticationNone implements CouchAuthentication {
    @Override
    public String getAuthorizationHeader() {
        return null;
    }
}
