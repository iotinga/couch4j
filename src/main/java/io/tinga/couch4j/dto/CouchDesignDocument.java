package io.tinga.couch4j.dto;

import java.util.HashMap;
import java.util.Map;

import jakarta.annotation.Nullable;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

public class CouchDesignDocument extends CouchDocument {
    public static class View {
        public static final String APPROX_COUNT_DISTINCT = "_approx_count_distinct";
        public static final String COUNT = "_count";
        public static final String STATS = "_stats";
        public static final String SUM = "_sum";

        private String map;

        @Nullable
        private String reduce;

        public View(String map) {
            this.map = map;
        }

        public View(String map, String reduce) {
            this.map = map;
            this.reduce = reduce;
        }

        public String getMap() {
            return map;
        }

        public void setMap(String map) {
            this.map = map;
        }

        public String getReduce() {
            return reduce;
        }

        public void setReduce(String reduce) {
            this.reduce = reduce;
        }
    }

    private String language = "javascript";

    @Nullable
    private String validateDocUpdate;

    private Boolean autoUpdate = true;

    private Map<String, View> views = new HashMap<>();
    private Map<String, String> filters = new HashMap<>();
    private Map<String, String> updates = new HashMap<>();

    public CouchDesignDocument(String id) {
        super(id);
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @JsonGetter("validate_doc_update")
    public String getValidateDocUpdate() {
        return validateDocUpdate;
    }

    @JsonSetter("validate_doc_update")
    public void setValidateDocUpdate(String validateDocUpdate) {
        this.validateDocUpdate = validateDocUpdate;
    }

    @JsonGetter("autoupdate")
    public Boolean getAutoUpdate() {
        return autoUpdate;
    }

    @JsonSetter("autoupdate")
    public void setAutoUpdate(Boolean autoUpdate) {
        this.autoUpdate = autoUpdate;
    }

    public Map<String, View> getViews() {
        return views;
    }

    public void setViews(Map<String, View> views) {
        this.views = views;
    }

    public Map<String, String> getFilters() {
        return filters;
    }

    public void setFilters(Map<String, String> filters) {
        this.filters = filters;
    }

    public Map<String, String> getUpdates() {
        return updates;
    }

    public void setUpdates(Map<String, String> updates) {
        this.updates = updates;
    }
}
