package io.tinga.couch4j.find;

import java.util.List;
import java.util.Map;

import jakarta.annotation.Nullable;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * A query using the Mango interface (/{db}/_find)
 */
public class CouchFindQuery {
    /** Sort in ascending order */
    public static final String SORT_ASC = "asc";

    /** Sort in descending order */
    public static final String SORT_DESC = "desc";

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
    private Boolean executionStats;

    /**
     * Constructs a query with a selector
     * 
     * @param selector
     */
    public CouchFindQuery(Map<String, Object> selector) {
        this.selector = selector;
    }

    /**
     * Gets the selector, i.e. the query itself
     * 
     * @return
     */
    public Map<String, Object> getSelector() {
        return selector;
    }

    /**
     * Sets the query selector
     * 
     * @param selector
     * @return
     */
    public CouchFindQuery setSelector(Map<String, Object> selector) {
        this.selector = selector;
        return this;
    }

    /**
     * Gets the query limit
     * 
     * @return
     */
    @JsonInclude(Include.NON_NULL)
    public Integer getLimit() {
        return limit;
    }

    /**
     * Sets the query limit. At most {limit} elements are returned.
     * 
     * @param limit
     * @return
     */
    public CouchFindQuery setLimit(Integer limit) {
        this.limit = limit;
        return this;
    }

    /**
     * Get the elements to skip
     * 
     * @return
     */
    @JsonInclude(Include.NON_NULL)
    public Integer getSkip() {
        return skip;
    }

    /**
     * The first {skip} elements on the result set are skipped.
     * You may want to use the bookmark to do pagination, for efficiency.
     * 
     * @param skip
     * @return
     */
    public CouchFindQuery setSkip(Integer skip) {
        this.skip = skip;
        return this;
    }

    /**
     * Get the sort setting for the query
     * 
     * @return
     */
    @JsonInclude(Include.NON_NULL)
    public List<Map<String, String>> getSort() {
        return sort;
    }

    /**
     * Set the sort parameter for the query, use one of the SORT_ constants in this
     * class.
     * <b>WARNING</b>: sort works only if the column has an index!
     * 
     * @param sort
     * @return
     */
    public CouchFindQuery setSort(List<Map<String, String>> sort) {
        this.sort = sort;
        return this;
    }

    /**
     * Get the fields to return
     * 
     * @return
     */
    @JsonInclude(Include.NON_NULL)
    public List<String> getFields() {
        return fields;
    }

    /**
     * Set the fields that the query has to get
     * 
     * @param fields
     * @return
     */
    public CouchFindQuery setFields(List<String> fields) {
        this.fields = fields;
        return this;
    }

    /**
     * Get the index that is used
     * 
     * @return
     */
    @JsonInclude(Include.NON_NULL)
    @JsonGetter("use_index")
    public String getUseIndex() {
        return useIndex;
    }

    /**
     * Set the index to use
     * 
     * @param useIndex
     * @return
     */
    @JsonSetter("use_index")
    public CouchFindQuery setUseIndex(String useIndex) {
        this.useIndex = useIndex;
        return this;
    }

    /**
     * 
     * 
     * @return
     */
    @JsonInclude(Include.NON_NULL)
    public Boolean getConflicts() {
        return conflicts;
    }

    /**
     * 
     * 
     * @param conflicts
     * @return
     */
    public CouchFindQuery setConflicts(Boolean conflicts) {
        this.conflicts = conflicts;
        return this;
    }

    /**
     * Get the read quorum
     * 
     * @return
     */
    @JsonInclude(Include.NON_NULL)
    public Integer getReadQuorum() {
        return r;
    }

    /**
     * Set the read quorum
     * 
     * @param r
     * @return
     */
    public CouchFindQuery setReadQuorum(Integer r) {
        this.r = r;
        return this;
    }

    /**
     * Get the bookmark
     * 
     * @return
     */
    @JsonInclude(Include.NON_NULL)
    public String getBookmark() {
        return bookmark;
    }

    /**
     * Set the bookmark. This is essential for doing pagination: by setting the
     * bookmark returned by a previous find a new page of data is returned.
     * 
     * @param bookmark
     * @return
     */
    public CouchFindQuery setBookmark(String bookmark) {
        this.bookmark = bookmark;
        return this;
    }

    /**
     * 
     * @return
     */
    @JsonInclude(Include.NON_NULL)
    public Boolean getUpdate() {
        return update;
    }

    /**
     * 
     * @param update
     * @return
     */
    public CouchFindQuery setUpdate(Boolean update) {
        this.update = update;
        return this;
    }

    /**
     * 
     * @return
     */
    @JsonInclude(Include.NON_NULL)
    public String getStale() {
        return stale;
    }

    /**
     * 
     * @param stale
     * @return
     */
    public CouchFindQuery setStale(String stale) {
        this.stale = stale;
        return this;
    }

    /**
     * Get if the execution stats are included in the query result
     * 
     * @return
     */
    @JsonInclude(Include.NON_NULL)
    @JsonGetter("execution_stats")
    public Boolean getIncludeExecutionStats() {
        return executionStats;
    }

    /**
     * Set the execution stats to be included in the query result
     * 
     * @param executionStats
     * @return
     */
    @JsonSetter("execution_stats")
    public CouchFindQuery setIncludeExecutionStats(Boolean executionStats) {
        this.executionStats = executionStats;
        return this;
    }
}
