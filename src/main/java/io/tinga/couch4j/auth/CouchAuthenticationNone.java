package io.tinga.couch4j.auth;

/**
 * Dummy authentication for CouchDb
 */
public class CouchAuthenticationNone implements CouchAuthentication {
    @Override
    public String getAuthorizationHeader() {
        return null;
    }
}
