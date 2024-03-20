package io.tinga.couch4j.dto;

import java.util.List;

public class CouchBulkGetResponse<T> {
    /**
     * An item in the response
     */
    public static class Item<T> {
        private String id;
        private List<CouchOkErrorResponse<T>> docs;

        /**
         * Get the id of the document
         * 
         * @return the id of the document
         */
        public String getId() {
            return id;
        }

        /**
         * Get the requested documents. More document may be returned
         * if all the revisions are asked
         * 
         * @return list of document
         */
        public List<CouchOkErrorResponse<T>> getDocs() {
            return docs;
        }
    }

    private List<Item<T>> results;

    /**
     * Get the request result
     * 
     * @return a list of items
     */
    public List<Item<T>> getResults() {
        return results;
    }
}
