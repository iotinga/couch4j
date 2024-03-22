package io.tinga.couch4j.find;

import java.util.List;
import java.util.Map;

/**
 * A set of convenience operators to perform find queries
 */
public class CouchFindOperators {
    /** type string for null to use with type() */
    public static final String TYPE_NULL = "null";

    /** type string for boolean to use with type() */
    public static final String TYPE_BOOLEAN = "boolean";

    /** type string for number to use with type() */
    public static final String TYPE_NUMBER = "number";

    /** type string for string to use with type() */
    public static final String TYPE_STRING = "string";

    /** type string for array to use with type() */
    public static final String TYPE_ARRAY = "array";

    /** type string for object to use with type() */
    public static final String TYPE_OBJECT = "object";

    /**
     * Matches if all the selectors in the array match.
     * 
     * @param selectors
     * @return
     */
    public static Map<String, Object> and(Object... selectors) {
        return Map.of("$and", List.of(selectors));
    }

    /**
     * Matches if any of the selectors in the array match.
     * All selectors must use the same index.
     * 
     * @param selectors
     * @return
     */
    public static Map<String, Object> or(Object... selectors) {
        return Map.of("$or", List.of(selectors));
    }

    /**
     * Matches if the given selector does not match.
     * 
     * @param selector
     * @return
     */
    public static Map<String, Object> not(Object selector) {
        return Map.of("$not", selector);
    }

    /**
     * Matches if none of the selectors in the array match.
     * 
     * @param selectors
     * @return
     */
    public static Map<String, Object> nor(Object... selectors) {
        return Map.of("$nor", List.of(selectors));
    }

    /**
     * Matches an array value if it contains all the elements of the argument array.
     * 
     * @param selectors
     * @return
     */
    public static Map<String, Object> all(Object... selectors) {
        return Map.of("$all", List.of(selectors));
    }

    /**
     * Matches and returns all documents that contain an array field with at least
     * one element that matches all the specified query criteria.
     * 
     * @param selector
     * @return
     */
    public static Map<String, Object> elemMatch(Object selector) {
        return Map.of("$elemMatch", selector);
    }

    /**
     * Matches and returns all documents that contain an array field with all
     * its elements matching all the specified query criteria.
     * 
     * @param selector
     * @return
     */
    public static Map<String, Object> allMatch(Object selector) {
        return Map.of("$allMatch", selector);
    }

    /**
     * Matches and returns all documents that contain a map that contains at
     * least one key that matches all the specified query criteria.
     * 
     * @param selector
     * @return
     */
    public static Map<String, Object> keyMapMatch(Object selector) {
        return Map.of("$keyMapMatch", selector);
    }

    /**
     * The field is less than the argument
     * 
     * @param field
     * @param argument
     * @return
     */
    public static Map<String, Object> lt(String field, Object argument) {
        return Map.of(field, Map.of("$lt", argument));
    }

    /**
     * The field is less than or equal to the argument.
     * 
     * @param field
     * @param argument
     * @return
     */
    public static Map<String, Object> lte(String field, Object argument) {
        return Map.of(field, Map.of("$lte", argument));
    }

    /**
     * The field is equal to the argument
     * 
     * @param field
     * @param argument
     * @return
     */
    public static Map<String, Object> eq(String field, Object argument) {
        return Map.of(field, Map.of("$eq", argument));
    }

    /**
     * The field is not equal to the argument.
     * 
     * @param field
     * @param argument
     * @return
     */
    public static Map<String, Object> ne(String field, Object argument) {
        return Map.of(field, Map.of("$ne", argument));
    }

    /**
     * The field is greater than the to the argument.
     * 
     * @param field
     * @param argument
     * @return
     */
    public static Map<String, Object> gt(String field, Object argument) {
        return Map.of(field, Map.of("$gt", argument));
    }

    /**
     * The field is greater than or equal to the argument.
     * 
     * @param field
     * @param argument
     * @return
     */
    public static Map<String, Object> gte(String field, Object argument) {
        return Map.of(field, Map.of("$gte", argument));
    }

    /**
     * Check whether the field exists or not, regardless of its value.
     * 
     * @param field
     * @param exists
     * @return
     */
    public static Map<String, Object> exists(String field, Boolean exists) {
        return Map.of(field, Map.of("$exists", exists));
    }

    /**
     * Check the document field’s type.
     * Valid values are "null", "boolean", "number", "string", "array", and
     * "object". These are defined in the TYPE_ constants in this class.
     * 
     * @param field
     * @param type
     * @return
     */
    public static Map<String, Object> type(String field, String type) {
        return Map.of(field, Map.of("$type", type));
    }

    /**
     * The document field must exist in the list provided.
     * 
     * @param field
     * @param values
     * @return
     */
    public static Map<String, Object> in(String field, Object... values) {
        return Map.of(field, Map.of("$in", values));
    }

    /**
     * The document field not must exist in the list provided.
     * 
     * @param field
     * @param values
     * @return
     */
    public static Map<String, Object> nin(String field, Object... values) {
        return Map.of(field, Map.of("$nin", values));
    }

    /**
     * Special condition to match the length of an array field in a document.
     * Non-array fields cannot match this condition.
     * 
     * @param field
     * @param size
     * @return
     */
    public static Map<String, Object> size(String field, Integer size) {
        return Map.of(field, Map.of("$size", size));
    }

    /**
     * Divisor is a non-zero integer, Remainder is any integer. Non-integer values
     * result in a 404. Matches documents where field % Divisor == Remainder is
     * true, and only when the document field is an integer.
     * 
     * @param field
     * @param divisor
     * @param reminder
     * @return
     */
    public static Map<String, Object> mod(String field, Integer divisor, Integer reminder) {
        return Map.of(field, Map.of("$mod", List.of(divisor, reminder)));
    }

    /**
     * A regular expression pattern to match against the document field. Only
     * matches when the field is a string value and matches the supplied regular
     * expression. The matching algorithms are based on the Perl Compatible Regular
     * Expression (PCRE) library. For more information about what is implemented,
     * see the see the Erlang Regular Expression
     * 
     * @param field
     * @param regex
     * @return
     */
    public static Map<String, Object> regex(String field, String regex) {
        return Map.of(field, Map.of("$regex", regex));
    }
}
