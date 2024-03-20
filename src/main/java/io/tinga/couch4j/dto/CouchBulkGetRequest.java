package io.tinga.couch4j.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class CouchBulkGetRequest {
    public static class Doc {
        private String id;

        @JsonInclude(Include.NON_NULL)
        private String rev;

        Doc(String id, String rev) {
            this.id = id;
            this.rev = rev;
        }

        Doc(String id) {
            this.id = id;
        }

        public String getId() {
            return id;
        }

        public String getRev() {
            return rev;
        }
    }

    private List<Doc> docs = new ArrayList<>();

    public List<Doc> getDocs() {
        return docs;
    }

    public <K> void addDoc(K id) {
        docs.add(new Doc(id.toString()));
    }

    public <K> void addDoc(K id, String rev) {
        docs.add(new Doc(id.toString(), rev));
    }
}
