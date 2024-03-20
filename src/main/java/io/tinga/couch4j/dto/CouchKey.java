package io.tinga.couch4j.dto;

public interface CouchKey {
    /**
     * The id of the document (_id field).
     * 
     * @return the id of the document
     */
    String getId();

    /**
     * Revision code of the document (_rev field).
     * May be null for documents not yet created.
     * 
     * @return revision code of the document
     */
    String getRev();
}
