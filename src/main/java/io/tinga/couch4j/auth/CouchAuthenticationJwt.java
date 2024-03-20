package io.tinga.couch4j.auth;

public class CouchAuthenticationJwt implements CouchAuthentication {
    private String authorizationHeader;

    public CouchAuthenticationJwt(String token) {
        authorizationHeader = "Bearer " + token;
    }

    @Override
    public String getAuthorizationHeader() {
        return authorizationHeader;
    }
}
