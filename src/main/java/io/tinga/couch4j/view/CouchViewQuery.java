package io.tinga.couch4j.view;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonSetter;

public class CouchViewQuery<K, V> {
    private String designDoc;
    private String name;

    /**
     * Include conflicts information in response. Ignored if include_docs isn’t
     * true. Default is false.
     */
    private Boolean conflicts;

    /**
     * Return the documents in descending order by key. Default is false.
     */
    private Boolean descending;

    /**
     * Stop returning records when the specified key is reached.
     */
    private K endKey;

    /**
     * Stop returning records when the specified document ID is reached.
     * Ignored if endkey is not set.
     */
    private String endKeyDocId;

    /**
     * Group the results using the reduce function to a group or single row.
     * Implies reduce is true and the maximum group_level. Default is false.
     */
    private Boolean group;

    /**
     * Specify the group level to be used. Implies group is true.
     */
    private Integer groupLevel;

    /**
     * Include the associated document with each row. Default is false.
     */
    private Boolean includeDocs;

    /**
     * Include the Base64-encoded content of attachments in the documents that
     * are included if include_docs is true. Ignored if include_docs isn’t true.
     */
    private Boolean attachments;

    /**
     * Include encoding information in attachment stubs if include_docs is true
     * and the particular attachment is compressed. Ignored if include_docs isn’t
     * true.
     */
    private Boolean attEncodingInfo;

    /**
     * Specifies whether the specified end key should be included in the result.
     */
    private Boolean inclusiveEnd;

    /**
     * Return only documents that match the specified key.
     */
    private K key;

    /**
     * Return only documents where the key matches one of the keys specified in the
     * array.
     */
    private List<K> keys;

    /**
     * Limit the number of the returned documents to the specified number.
     */
    private Integer limit;

    /**
     * Use the reduction function. Default is true when a reduce function is
     * defined.
     */
    private Boolean reduce;

    /**
     * Skip this number of records before starting to return the results. Default is
     * 0.
     */
    private Integer skip;

    /**
     * Sort returned
     */
    private Boolean sorted;

    /**
     * Setting this to false offers a performance boost. The total_rows and offset
     * fields are not available when this is set to false. Default is true.
     */
    private Boolean rows = true;

    /**
     * Whether or not the view results should be returned from a stable set of
     * shards. Default is false.
     */
    private Boolean stable;

    /**
     * Return records starting with the specified key.
     */
    private K startKey;

    /**
     * Return records starting with the specified document ID.
     * Ignored if startkey is not set.
     */
    private String startKeyDocId;

    /**
     * Whether or not the view in question should be updated prior to responding
     * to the user. Supported values: true, false, lazy. Default is true.
     */
    private String update;

    /**
     * Whether to include in the response an update_seq value indicating
     * the sequence id of the database the view reflects. Default is false.
     */
    private Boolean updateSeq;

    private Class<?> classOfV = Object.class;
    private Class<?> classOfK = Object.class;

    public Class<?> getClassOfV() {
        return classOfV;
    }

    public CouchViewQuery<K, V> setClassOfV(Class<?> classOfV) {
        this.classOfV = classOfV;
        return this;
    }

    public Class<?> getClassOfK() {
        return classOfK;
    }

    public CouchViewQuery<K, V> setClassOfK(Class<?> classOfK) {
        this.classOfK = classOfK;
        return this;
    }

    public CouchViewQuery(String designDoc, String name) {
        this.designDoc = designDoc;
        this.name = name;
    }

    @JsonIgnore
    public String getDesignDoc() {
        return designDoc;
    }

    @JsonIgnore
    public String getName() {
        return name;
    }

    @JsonInclude(Include.NON_NULL)
    public Boolean getConflicts() {
        return conflicts;
    }

    public CouchViewQuery<K, V> setConflicts(Boolean conflicts) {
        this.conflicts = conflicts;
        return this;
    }

    @JsonInclude(Include.NON_NULL)
    public Boolean getDescending() {
        return descending;
    }

    public CouchViewQuery<K, V> setDescending(Boolean descending) {
        this.descending = descending;
        return this;
    }

    @JsonGetter("end_key")
    @JsonInclude(Include.NON_NULL)
    public K getEndKey() {
        return endKey;
    }

    @JsonSetter("end_key")
    public CouchViewQuery<K, V> setEndKey(K endKey) {
        if (classOfK == null) {
            classOfK = endKey.getClass();
        }
        this.endKey = endKey;
        return this;
    }

    @JsonGetter("end_key_doc_id")
    @JsonInclude(Include.NON_NULL)
    public String getEndKeyDocId() {
        return endKeyDocId;
    }

    @JsonSetter("end_key_doc_id")
    public CouchViewQuery<K, V> setEndKeyDocId(String endKeyDocId) {
        this.endKeyDocId = endKeyDocId;
        return this;
    }

    @JsonInclude(Include.NON_NULL)
    public Boolean getGroup() {
        return group;
    }

    public CouchViewQuery<K, V> setGroup(Boolean group) {
        this.group = group;
        return this;
    }

    @JsonGetter("group_level")
    @JsonInclude(Include.NON_NULL)
    public Integer getGroupLevel() {
        return groupLevel;
    }

    @JsonSetter("group_level")
    public CouchViewQuery<K, V> setGroupLevel(Integer groupLevel) {
        this.groupLevel = groupLevel;
        return this;
    }

    @JsonGetter("include_docs")
    @JsonInclude(Include.NON_NULL)
    public Boolean getIncludeDocs() {
        return includeDocs;
    }

    @JsonSetter("include_docs")
    public CouchViewQuery<K, V> setIncludeDocs(Boolean includeDocs) {
        this.includeDocs = includeDocs;
        return this;
    }

    @JsonInclude(Include.NON_NULL)
    public Boolean getAttachments() {
        return attachments;
    }

    public CouchViewQuery<K, V> setAttachments(Boolean attachments) {
        this.attachments = attachments;
        return this;
    }

    @JsonGetter("att_encoding_info")
    @JsonInclude(Include.NON_NULL)
    public Boolean getAttEncodingInfo() {
        return attEncodingInfo;
    }

    @JsonSetter("att_encoding_info")
    public CouchViewQuery<K, V> setAttEncodingInfo(Boolean attEncodingInfo) {
        this.attEncodingInfo = attEncodingInfo;
        return this;
    }

    @JsonGetter("inclusive_end")
    @JsonInclude(Include.NON_NULL)
    public Boolean getInclusiveEnd() {
        return inclusiveEnd;
    }

    @JsonSetter("inclusive_end")
    public CouchViewQuery<K, V> setInclusiveEnd(Boolean inclusiveEnd) {
        this.inclusiveEnd = inclusiveEnd;
        return this;
    }

    @JsonInclude(Include.NON_NULL)
    public K getKey() {
        return key;
    }

    public CouchViewQuery<K, V> setKey(K key) {
        if (classOfK == null) {
            classOfK = key.getClass();
        }
        this.key = key;
        return this;
    }

    @JsonInclude(Include.NON_NULL)
    public List<K> getKeys() {
        return keys;
    }

    public CouchViewQuery<K, V> setKeys(List<K> keys) {
        this.keys = keys;
        return this;
    }

    @JsonInclude(Include.NON_NULL)
    public Integer getLimit() {
        return limit;
    }

    public CouchViewQuery<K, V> setLimit(Integer limit) {
        this.limit = limit;
        return this;
    }

    @JsonInclude(Include.NON_NULL)
    public Boolean getReduce() {
        return reduce;
    }

    public CouchViewQuery<K, V> setReduce(Boolean reduce) {
        this.reduce = reduce;
        return this;
    }

    @JsonInclude(Include.NON_NULL)
    public Integer getSkip() {
        return skip;
    }

    public CouchViewQuery<K, V> setSkip(Integer skip) {
        this.skip = skip;
        return this;
    }

    @JsonInclude(Include.NON_NULL)
    public Boolean getSorted() {
        return sorted;
    }

    public CouchViewQuery<K, V> setSorted(Boolean sorted) {
        this.sorted = sorted;
        return this;
    }

    @JsonInclude(Include.NON_NULL)
    public Boolean getRows() {
        return rows;
    }

    public CouchViewQuery<K, V> setRows(Boolean rows) {
        this.rows = rows;
        return this;
    }

    @JsonInclude(Include.NON_NULL)
    public Boolean getStable() {
        return stable;
    }

    public CouchViewQuery<K, V> setStable(Boolean stable) {
        this.stable = stable;
        return this;
    }

    @JsonGetter("start_key")
    @JsonInclude(Include.NON_NULL)
    public K getStartKey() {
        return startKey;
    }

    @JsonSetter("start_key")
    public CouchViewQuery<K, V> setStartKey(K startKey) {
        if (classOfK == null) {
            classOfK = startKey.getClass();
        }
        this.startKey = startKey;
        return this;
    }

    @JsonGetter("start_key_doc_id")
    @JsonInclude(Include.NON_NULL)
    public String getStartKeyDocId() {
        return startKeyDocId;
    }

    @JsonSetter("start_key_doc_id")
    public CouchViewQuery<K, V> setStartKeyDocId(String startKeyDocId) {
        this.startKeyDocId = startKeyDocId;
        return this;
    }

    @JsonInclude(Include.NON_NULL)
    public String getUpdate() {
        return update;
    }

    public CouchViewQuery<K, V> setUpdate(String update) {
        this.update = update;
        return this;
    }

    @JsonGetter("update_seq")
    @JsonInclude(Include.NON_NULL)
    public Boolean getUpdateSeq() {
        return updateSeq;
    }

    @JsonSetter("update_seq")
    public CouchViewQuery<K, V> setUpdateSeq(Boolean updateSeq) {
        this.updateSeq = updateSeq;
        return this;
    }
}
