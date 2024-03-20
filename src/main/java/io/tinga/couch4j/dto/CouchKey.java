package io.tinga.couch4j.dto;

import jakarta.annotation.Nullable;

/**
 * A key for a CouchDb document
 */
public interface CouchKey {
    /**
     * The id of the document (_id field).
     * 
     * @return the id of the document
     */
    @Nullable
    String getId();

    /**
     * Revision code of the document (_rev field).
     * May be null for documents not yet created.
     * 
     * @return revision code of the document
     */
    @Nullable
    String getRev();
}
