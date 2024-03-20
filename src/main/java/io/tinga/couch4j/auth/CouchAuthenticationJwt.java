package io.tinga.couch4j.auth;

/**
 * Authenticate trough JWT tokens
 */
public class CouchAuthenticationJwt implements CouchAuthentication {
    private String authorizationHeader;

    /**
     * Construct authentication with a JWT token
     * 
     * @param token
     */
    public CouchAuthenticationJwt(String token) {
        authorizationHeader = "Bearer " + token;
    }

    @Override
    public String getAuthorizationHeader() {
        return authorizationHeader;
    }
}
