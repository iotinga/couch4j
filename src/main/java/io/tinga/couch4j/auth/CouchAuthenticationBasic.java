package io.tinga.couch4j.auth;

import okhttp3.Credentials;

public class CouchAuthenticationBasic implements CouchAuthentication {
    private String authorizationHeader;

    public CouchAuthenticationBasic(String username, String password) {
        authorizationHeader = Credentials.basic(username, password);
    }

    @Override
    public String getAuthorizationHeader() {
        return authorizationHeader;
    }
}
