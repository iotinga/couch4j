package io.tinga.couch4j.auth;

import jakarta.annotation.Nullable;

public interface CouchAuthentication {
    /**
     * Gets the value for the Authorization header
     * 
     * @return authorization header value, or null to not set any header
     */
    @Nullable
    String getAuthorizationHeader();
}
