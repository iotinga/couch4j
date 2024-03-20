package io.tinga.couch4j.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * Request to get bulk documents
 */
public class CouchBulkGetRequest {
    /**
     * A document in the request
     */
    public static class Doc {
        private String id;

        @JsonInclude(Include.NON_NULL)
        private String rev;

        /**
         * Construct with id and rev
         * 
         * @param id
         * @param rev
         */
        Doc(String id, String rev) {
            this.id = id;
            this.rev = rev;
        }

        /**
         * Construct with only the id
         * 
         * @param id
         */
        Doc(String id) {
            this.id = id;
        }

        /**
         * Get the document id
         * 
         * @return
         */
        public String getId() {
            return id;
        }

        /**
         * Get the document revision
         * 
         * @return
         */
        public String getRev() {
            return rev;
        }
    }

    private List<Doc> docs = new ArrayList<>();

    /**
     * Get a list of document
     * 
     * @return
     */
    public List<Doc> getDocs() {
        return docs;
    }

    /**
     * Add a document to the request
     * 
     * @param <K>
     * @param id
     */
    public <K> void addDoc(K id) {
        docs.add(new Doc(id.toString()));
    }

    /**
     * Add a document with its revision to the request
     * 
     * @param <K>
     * @param id
     * @param rev
     */
    public <K> void addDoc(K id, String rev) {
        docs.add(new Doc(id.toString(), rev));
    }
}
