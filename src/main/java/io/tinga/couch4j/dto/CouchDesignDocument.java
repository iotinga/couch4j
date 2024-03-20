package io.tinga.couch4j.dto;

import java.util.HashMap;
import java.util.Map;

import jakarta.annotation.Nullable;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * A CouchDb design document
 */
public class CouchDesignDocument extends CouchDocument {
    /**
     * The definition of a CouchDb view
     */
    public static class View {
        /** Set reduce to _approx_count_distinct */
        public static final String REDUCE_APPROX_COUNT_DISTINCT = "_approx_count_distinct";

        /** Set reduce to return the element count */
        public static final String REDUCE_COUNT = "_count";

        /** Set reduce to return stats of the object */
        public static final String REDUCE_STATS = "_stats";

        /** Set reduce to return the sum of the values */
        public static final String REDUCE_SUM = "_sum";

        private String map;

        @Nullable
        private String reduce;

        /**
         * Construct a view with a map function
         * 
         * @param map the map function source code
         */
        public View(String map) {
            this.map = map;
        }

        /**
         * Construct the view with a map and reduce function
         * 
         * @param map    the map function JS code
         * @param reduce a reduce function JS code, or a string corresponding to one of
         *               the REDUCE_ predefined functions
         */
        public View(String map, String reduce) {
            this.map = map;
            this.reduce = reduce;
        }

        /**
         * Get the map function
         * 
         * @return the map function
         */
        public String getMap() {
            return map;
        }

        /**
         * Set the map function
         * 
         * @param map the map function
         */
        public void setMap(String map) {
            this.map = map;
        }

        /**
         * Get the reduce function, if any
         * 
         * @return
         */
        @Nullable
        public String getReduce() {
            return reduce;
        }

        /**
         * Set the reduce function, if any
         * 
         * @param reduce
         */
        @Nullable
        public void setReduce(String reduce) {
            this.reduce = reduce;
        }
    }

    /** JavaScript language for the design document */
    public static final String LANGUAGE_JAVASCRIPT = "javascript";

    private String language = LANGUAGE_JAVASCRIPT;

    @Nullable
    private String validateDocUpdate;

    private Boolean autoUpdate = true;

    private Map<String, View> views = new HashMap<>();
    private Map<String, String> filters = new HashMap<>();
    private Map<String, String> updates = new HashMap<>();

    /**
     * Creates a design document with the specified id
     * 
     * @param id
     */
    public CouchDesignDocument(String id) {
        super(id);
    }

    /**
     * Get the language of the document, used for the various function
     * 
     * @return
     */
    public String getLanguage() {
        return language;
    }

    /**
     * Set the language of the document, used for the functions
     * 
     * @param language
     */
    public void setLanguage(String language) {
        this.language = language;
    }

    /**
     * Get the function to validate a document update
     * 
     * @return
     */
    @JsonGetter("validate_doc_update")
    @JsonInclude(Include.NON_NULL)
    public String getValidateDocUpdate() {
        return validateDocUpdate;
    }

    /**
     * Set a function that is used to validate the document update
     * 
     * @param validateDocUpdate the function source code in the design document
     *                          language
     */
    @JsonSetter("validate_doc_update")
    public void setValidateDocUpdate(String validateDocUpdate) {
        this.validateDocUpdate = validateDocUpdate;
    }

    /**
     * Get the fact that the design doc to automatically update
     * 
     * @return
     */
    @JsonGetter("autoupdate")
    @JsonInclude(Include.NON_NULL)
    public Boolean getAutoUpdate() {
        return autoUpdate;
    }

    /**
     * Set the design document to automatically update
     * 
     * @param autoUpdate
     */
    @JsonSetter("autoupdate")
    public void setAutoUpdate(Boolean autoUpdate) {
        this.autoUpdate = autoUpdate;
    }

    /**
     * Get thew view in this design document
     * 
     * @return
     */
    @JsonInclude(Include.NON_NULL)
    public Map<String, View> getViews() {
        return views;
    }

    /**
     * Set the view in this design document
     * 
     * @param views
     */
    public void setViews(Map<String, View> views) {
        this.views = views;
    }

    /**
     * Get the filters defined in the design document
     * 
     * @return
     */
    @JsonInclude(Include.NON_NULL)
    public Map<String, String> getFilters() {
        return filters;
    }

    /**
     * Set the filters of the design document
     * 
     * @param filters
     */
    public void setFilters(Map<String, String> filters) {
        this.filters = filters;
    }

    /**
     * Get the update functions of the design document
     * 
     * @return
     */
    @JsonInclude(Include.NON_NULL)
    public Map<String, String> getUpdates() {
        return updates;
    }

    /**
     * Set the update functions of the design document
     * 
     * @param updates
     */
    public void setUpdates(Map<String, String> updates) {
        this.updates = updates;
    }
}
