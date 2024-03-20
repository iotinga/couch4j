package io.tinga.couch4j;

/**
 * Represents a CouchDb server
 */
public interface CouchServer {
    /**
     * Returns the database with the specified name
     * 
     * @param name name of the database
     * @return a CouchDb database instance
     */
    CouchDatabase db(String name);
}
