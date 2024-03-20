package io.tinga.couch4j.auth;

import okhttp3.Credentials;

/**
 * Authenticate with HTTP basic auth
 */
public class CouchAuthenticationBasic implements CouchAuthentication {
    private String authorizationHeader;

    /**
     * Construct the authentication with username and password
     * 
     * @param username
     * @param password
     */
    public CouchAuthenticationBasic(String username, String password) {
        authorizationHeader = Credentials.basic(username, password);
    }

    @Override
    public String getAuthorizationHeader() {
        return authorizationHeader;
    }
}
