package io.tinga.couch4j.view;

import java.util.List;

public class CouchViewQueriesResponse<K, V, D> {
    private List<CouchViewResponse<K, V, D>> results;

    public List<CouchViewResponse<K, V, D>> getResults() {
        return results;
    }

    public void setResults(List<CouchViewResponse<K, V, D>> results) {
        this.results = results;
    }
}
