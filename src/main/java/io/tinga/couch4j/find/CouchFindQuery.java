package io.tinga.couch4j.find;

import java.util.List;
import java.util.Map;

import jakarta.annotation.Nullable;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class CouchFindQuery {
    private Map<String, Object> selector;

    @Nullable
    private Integer limit;

    @Nullable
    private Integer skip;

    @Nullable
    private List<Map<String, String>> sort;

    @Nullable
    private List<String> fields;

    @Nullable
    private String useIndex;

    @Nullable
    private Boolean conflicts;

    @Nullable
    private Integer r;

    @Nullable
    private String bookmark;

    @Nullable
    private Boolean update;

    @Nullable
    private String stale;

    @Nullable
    private Boolean execution_stats;

    public Map<String, Object> getSelector() {
        return selector;
    }

    public CouchFindQuery setSelector(Map<String, Object> selector) {
        this.selector = selector;
        return this;
    }

    @JsonInclude(Include.NON_NULL)
    public Integer getLimit() {
        return limit;
    }

    public CouchFindQuery setLimit(Integer limit) {
        this.limit = limit;
        return this;
    }

    @JsonInclude(Include.NON_NULL)
    public Integer getSkip() {
        return skip;
    }

    public CouchFindQuery setSkip(Integer skip) {
        this.skip = skip;
        return this;
    }

    @JsonInclude(Include.NON_NULL)
    public List<Map<String, String>> getSort() {
        return sort;
    }

    public CouchFindQuery setSort(List<Map<String, String>> sort) {
        this.sort = sort;
        return this;
    }

    @JsonInclude(Include.NON_NULL)
    public List<String> getFields() {
        return fields;
    }

    public CouchFindQuery setFields(List<String> fields) {
        this.fields = fields;
        return this;
    }

    @JsonInclude(Include.NON_NULL)
    @JsonGetter("use_index")
    public String getUseIndex() {
        return useIndex;
    }

    @JsonSetter("use_index")
    public CouchFindQuery setUseIndex(String useIndex) {
        this.useIndex = useIndex;
        return this;
    }

    @JsonInclude(Include.NON_NULL)
    public Boolean getConflicts() {
        return conflicts;
    }

    public CouchFindQuery setConflicts(Boolean conflicts) {
        this.conflicts = conflicts;
        return this;
    }

    @JsonInclude(Include.NON_NULL)
    public Integer getReadQuorum() {
        return r;
    }

    public CouchFindQuery setReadQuorum(Integer r) {
        this.r = r;
        return this;
    }

    @JsonInclude(Include.NON_NULL)
    public String getBookmark() {
        return bookmark;
    }

    public CouchFindQuery setBookmark(String bookmark) {
        this.bookmark = bookmark;
        return this;
    }

    @JsonInclude(Include.NON_NULL)
    public Boolean getUpdate() {
        return update;
    }

    public CouchFindQuery setUpdate(Boolean update) {
        this.update = update;
        return this;
    }

    @JsonInclude(Include.NON_NULL)
    public String getStale() {
        return stale;
    }

    public CouchFindQuery setStale(String stale) {
        this.stale = stale;
        return this;
    }

    @JsonInclude(Include.NON_NULL)
    public Boolean getIncludeExecutionStats() {
        return execution_stats;
    }

    public CouchFindQuery setIncludeExecutionStats(Boolean execution_stats) {
        this.execution_stats = execution_stats;
        return this;
    }
}
