package io.tinga.couch4j.dto;

import java.util.List;

import jakarta.annotation.Nullable;

public class CouchBulkGetResponse<T> {
    public static class Error {
        private String id;
        private String rev;
        private String error;
        private String reason;

        public String getId() {
            return id;
        }

        public String getRev() {
            return rev;
        }

        public String getError() {
            return error;
        }

        public String getReason() {
            return reason;
        }
    }

    public static class Docs<T> {
        private T ok;
        private Error error;

        public boolean isOk() {
            return ok != null;
        }

        @Nullable
        public T getOk() {
            return ok;
        }

        @Nullable
        public Error getError() {
            return error;
        }
    }

    public static class Item<T> {
        private String id;
        private List<Docs<T>> docs;

        public String getId() {
            return id;
        }

        public List<Docs<T>> getDocs() {
            return docs;
        }
    }

    private List<Item<T>> results;

    public List<Item<T>> getResults() {
        return results;
    }
}
