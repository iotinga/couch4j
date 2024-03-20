package io.tinga.couch4j.dto;

import java.util.List;

public class CouchBulkUpdateRequest {
    private List<CouchDocument> docs;

    public CouchBulkUpdateRequest(List<CouchDocument> docs) {
        this.docs = docs;
    }

    public List<CouchDocument> getDocs() {
        return docs;
    }
}
