package io.tinga.couch4j.find;

import java.util.HashMap;
import java.util.List;

public class CouchFindSelector extends HashMap<String, Object> {
    private HashMap<String, Object> inner;

    private CouchFindSelector(String name) {
        inner = new HashMap<>();
        put(name, inner);
    }

    public static CouchFindSelector field(String name) {
        return new CouchFindSelector(name);
    }

    public CouchFindSelector lt(Object other) {
        inner.put("$lt", other);
        return this;
    }

    public CouchFindSelector lte(Object other) {
        inner.put("$lte", other);
        return this;
    }

    public CouchFindSelector eq(Object other) {
        inner.put("$eq", other);
        return this;
    }

    public CouchFindSelector ne(Object other) {
        inner.put("$ne", other);
        return this;
    }

    public CouchFindSelector gt(Object other) {
        inner.put("$gt", other);
        return this;
    }

    public CouchFindSelector gte(Object other) {
        inner.put("$gte", other);
        return this;
    }

    public CouchFindSelector exists(Boolean exists) {
        inner.put("$exists", exists);
        return this;
    }

    public CouchFindSelector type(String type) {
        inner.put("$type", type);
        return this;
    }

    public CouchFindSelector in(Object... values) {
        inner.put("$in", values);
        return this;
    }

    public CouchFindSelector nin(Object... values) {
        inner.put("$nin", values);
        return this;
    }

    public CouchFindSelector size(Integer size) {
        inner.put("$size", size);
        return this;
    }

    public CouchFindSelector mod(Integer divisor, Integer reminder) {
        inner.put("$mod", List.of(divisor, reminder));
        return this;
    }

    public CouchFindSelector regex(String regex) {
        inner.put("$regex", regex);
        return this;
    }
}
