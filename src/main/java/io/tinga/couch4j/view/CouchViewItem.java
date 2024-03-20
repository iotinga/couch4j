package io.tinga.couch4j.view;

import jakarta.annotation.Nullable;

/**
 * Result of a view query
 */
public class CouchViewItem<K, V, D> {
    private String id;
    private K key;
    private V value;

    @Nullable
    private D doc;

    /**
     * Get the document in the view response.
     * This is present only if the setIncludeDocs(true) was specified in the view
     * query.
     * 
     * @return
     */
    public D getDoc() {
        return doc;
    }

    /**
     * Get the id of the document that was emitted
     * 
     * @return
     */
    public String getId() {
        return id;
    }

    /**
     * Get the view key that was emitted
     * 
     * @return
     */
    public K getKey() {
        return key;
    }

    /**
     * Get the view value that was emitted
     * 
     * @return
     */
    public V getValue() {
        return value;
    }
}
