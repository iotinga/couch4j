package io.tinga.couch4j.find;

import java.util.HashMap;
import java.util.List;

public class CouchFindOperator extends HashMap<String, Object> {
    private static CouchFindOperator build(String op, Object selector) {
        CouchFindOperator o = new CouchFindOperator();
        o.put(op, selector);

        return o;
    }

    public static CouchFindOperator and(CouchFindSelector... selectors) {
        return build("$and", List.of(selectors));
    }

    public static CouchFindOperator or(CouchFindSelector... selectors) {
        return build("$or", List.of(selectors));
    }

    public static CouchFindOperator not(CouchFindSelector selector) {
        return build("$not", selector);
    }

    public static CouchFindOperator nor(CouchFindSelector... selectors) {
        return build("$nor", List.of(selectors));
    }

    public static CouchFindOperator all(CouchFindSelector... selectors) {
        return build("$all", List.of(selectors));
    }

    public static CouchFindOperator elemMatch(CouchFindSelector selector) {
        return build("$elemMatch", selector);
    }

    public static CouchFindOperator allMatch(CouchFindSelector selector) {
        return build("$allMatch", selector);
    }

    public static CouchFindOperator keyMapMatch(CouchFindSelector selector) {
        return build("$keyMapMatch", selector);
    }
}
