package io.tinga.couch4j.dto;

import java.util.List;

/**
 * Request for a POST /{db}/_bulk_docs request
 */
public class CouchBulkUpdateRequest<T extends CouchDocument> {
    private List<T> docs;

    /**
     * Initialize with a list of document
     * 
     * @param docs a list of documents
     */
    public CouchBulkUpdateRequest(List<T> docs) {
        this.docs = docs;
    }

    /**
     * Get the documents of the request
     * 
     * @return a list of document
     */
    public List<T> getDocs() {
        return docs;
    }
}
