package io.tinga.couch4j.view;

import jakarta.annotation.Nullable;

public class CouchViewItem<K, V, D> {
    private String id;
    private K key;
    private V value;

    @Nullable
    private D doc;

    public D getDoc() {
        return doc;
    }

    public void setDoc(D doc) {
        this.doc = doc;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }
}
