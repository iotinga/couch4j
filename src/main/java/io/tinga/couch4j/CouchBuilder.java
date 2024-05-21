package io.tinga.couch4j;

import java.time.Duration;

import io.tinga.couch4j.auth.CouchAuthentication;
import io.tinga.couch4j.auth.CouchAuthenticationBasic;
import io.tinga.couch4j.auth.CouchAuthenticationJwt;
import io.tinga.couch4j.auth.CouchAuthenticationNone;

/**
 * Builder to create the CouchDb server instance
 */
public class CouchBuilder {
    private String uri = "http://localhost:5984";
    private Duration callTimeout = Duration.ofSeconds(30);
    private CouchAuthentication auth = new CouchAuthenticationNone();

    /**
     * Set the CouchDb base HTTP uri
     * 
     * @param uri CouchDb uri
     * @return this object
     */
    public CouchBuilder setUri(String uri) {
        this.uri = uri;
        return this;
    }

    /**
     * Set the timeout for a request. The
     * default is 30 seconds
     * 
     * @param callTimeout The timeout duration
     * @return this object
     */
    public CouchBuilder setCallTimeout(Duration callTimeout) {
        this.callTimeout = callTimeout;
        return this;
    }

    /**
     * Configure CouchDb with HTTP authentication
     * 
     * @param username username
     * @param password password
     * @return this object
     */
    public CouchBuilder setBasicAuth(String username, String password) {
        auth = new CouchAuthenticationBasic(username, password);
        return this;
    }

    /**
     * Configure CouchDb with JWT authentication
     * 
     * @param jwt jwt token
     * @return this object
     */
    public CouchBuilder setJwt(String jwt) {
        auth = new CouchAuthenticationJwt(jwt);
        return this;
    }

    /**
     * Constructs an instance of CouchDb server
     * 
     * @return CouchDb server instance
     */
    public CouchServer build() {
        return new CouchServerImpl(uri, callTimeout, auth);
    }
}
