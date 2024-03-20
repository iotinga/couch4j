package io.tinga.couch4j.view;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonSetter;

/**
 * A view query request
 * 
 * @param <K> type of the key
 * @param <V> type of the value
 */
public class CouchViewQuery<K, V> {
    private String designDoc;
    private String name;
    private Boolean conflicts;
    private Boolean descending;
    private K endKey;
    private String endKeyDocId;
    private Boolean group;
    private Integer groupLevel;
    private Boolean includeDocs;
    private Boolean attachments;
    private Boolean attEncodingInfo;
    private Boolean inclusiveEnd;
    private K key;
    private List<K> keys;
    private Integer limit;
    private Boolean reduce;
    private Integer skip;
    private Boolean sorted;
    private Boolean rows;
    private Boolean stable;
    private K startKey;
    private String startKeyDocId;
    private String update;
    private Boolean updateSeq;
    private Class<?> classOfV = Object.class;
    private Class<?> classOfK = Object.class;

    /**
     * Get the class of the value generic type
     * 
     * @return
     */
    public Class<?> getClassOfV() {
        return classOfV;
    }

    /**
     * Set the class of the value generic type
     * 
     * @param classOfV
     * @return
     */
    public CouchViewQuery<K, V> setClassOfV(Class<?> classOfV) {
        this.classOfV = classOfV;
        return this;
    }

    /**
     * Get the class of the key generic type
     * 
     * @return
     */
    public Class<?> getClassOfK() {
        return classOfK;
    }

    /**
     * Set the value of the key generic type. This shouldn't normally needed since
     * it's deduced from setKey or setStartKey/setEndKey.
     * 
     * @param classOfK
     * @return
     */
    public CouchViewQuery<K, V> setClassOfK(Class<?> classOfK) {
        this.classOfK = classOfK;
        return this;
    }

    /**
     * Create a new view query
     * 
     * @param designDoc id of the design document
     * @param name      name of the view
     */
    public CouchViewQuery(String designDoc, String name) {
        this.designDoc = designDoc;
        this.name = name;
    }

    /**
     * Get the name of the design document
     * 
     * @return
     */
    @JsonIgnore
    public String getDesignDoc() {
        return designDoc;
    }

    /**
     * Get the name of thew view
     * 
     * @return
     */
    @JsonIgnore
    public String getName() {
        return name;
    }

    /**
     * Include conflicts information in response. Ignored if include_docs isn’t
     * true. Default is false.
     */
    @JsonInclude(Include.NON_NULL)
    public Boolean getConflicts() {
        return conflicts;
    }

    /**
     * Include conflicts information in response. Ignored if include_docs isn’t
     * true. Default is false.
     */
    public CouchViewQuery<K, V> setConflicts(Boolean conflicts) {
        this.conflicts = conflicts;
        return this;
    }

    /**
     * Return the documents in descending order by key. Default is false.
     */
    @JsonInclude(Include.NON_NULL)
    public Boolean getDescending() {
        return descending;
    }

    /**
     * Return the documents in descending order by key. Default is false.
     */
    public CouchViewQuery<K, V> setDescending(Boolean descending) {
        this.descending = descending;
        return this;
    }

    /**
     * Stop returning records when the specified key is reached.
     */
    @JsonGetter("end_key")
    @JsonInclude(Include.NON_NULL)
    public K getEndKey() {
        return endKey;
    }

    /**
     * Stop returning records when the specified key is reached.
     */
    @JsonSetter("end_key")
    public CouchViewQuery<K, V> setEndKey(K endKey) {
        if (classOfK == null) {
            classOfK = endKey.getClass();
        }
        this.endKey = endKey;
        return this;
    }

    /**
     * Stop returning records when the specified document ID is reached.
     * Ignored if endkey is not set.
     */
    @JsonGetter("end_key_doc_id")
    @JsonInclude(Include.NON_NULL)
    public String getEndKeyDocId() {
        return endKeyDocId;
    }

    /**
     * Stop returning records when the specified document ID is reached.
     * Ignored if endkey is not set.
     */
    @JsonSetter("end_key_doc_id")
    public CouchViewQuery<K, V> setEndKeyDocId(String endKeyDocId) {
        this.endKeyDocId = endKeyDocId;
        return this;
    }

    /**
     * Group the results using the reduce function to a group or single row.
     * Implies reduce is true and the maximum group_level. Default is false.
     */
    @JsonInclude(Include.NON_NULL)
    public Boolean getGroup() {
        return group;
    }

    /**
     * Group the results using the reduce function to a group or single row.
     * Implies reduce is true and the maximum group_level. Default is false.
     */
    public CouchViewQuery<K, V> setGroup(Boolean group) {
        this.group = group;
        return this;
    }

    /**
     * Specify the group level to be used. Implies group is true.
     */
    @JsonGetter("group_level")
    @JsonInclude(Include.NON_NULL)
    public Integer getGroupLevel() {
        return groupLevel;
    }

    /**
     * Specify the group level to be used. Implies group is true.
     */
    @JsonSetter("group_level")
    public CouchViewQuery<K, V> setGroupLevel(Integer groupLevel) {
        this.groupLevel = groupLevel;
        return this;
    }

    /**
     * Include the associated document with each row. Default is false.
     */
    @JsonGetter("include_docs")
    @JsonInclude(Include.NON_NULL)
    public Boolean getIncludeDocs() {
        return includeDocs;
    }

    /**
     * Include the associated document with each row. Default is false.
     */
    @JsonSetter("include_docs")
    public CouchViewQuery<K, V> setIncludeDocs(Boolean includeDocs) {
        this.includeDocs = includeDocs;
        return this;
    }

    /**
     * Include the Base64-encoded content of attachments in the documents that
     * are included if include_docs is true. Ignored if include_docs isn’t true.
     */
    @JsonInclude(Include.NON_NULL)
    public Boolean getAttachments() {
        return attachments;
    }

    /**
     * Include the Base64-encoded content of attachments in the documents that
     * are included if include_docs is true. Ignored if include_docs isn’t true.
     */
    public CouchViewQuery<K, V> setAttachments(Boolean attachments) {
        this.attachments = attachments;
        return this;
    }

    /**
     * Include encoding information in attachment stubs if include_docs is true
     * and the particular attachment is compressed. Ignored if include_docs isn’t
     * true.
     */
    @JsonGetter("att_encoding_info")
    @JsonInclude(Include.NON_NULL)
    public Boolean getAttEncodingInfo() {
        return attEncodingInfo;
    }

    /**
     * Include encoding information in attachment stubs if include_docs is true
     * and the particular attachment is compressed. Ignored if include_docs isn’t
     * true.
     */
    @JsonSetter("att_encoding_info")
    public CouchViewQuery<K, V> setAttEncodingInfo(Boolean attEncodingInfo) {
        this.attEncodingInfo = attEncodingInfo;
        return this;
    }

    /**
     * Specifies whether the specified end key should be included in the result.
     */
    @JsonGetter("inclusive_end")
    @JsonInclude(Include.NON_NULL)
    public Boolean getInclusiveEnd() {
        return inclusiveEnd;
    }

    /**
     * Specifies whether the specified end key should be included in the result.
     */
    @JsonSetter("inclusive_end")
    public CouchViewQuery<K, V> setInclusiveEnd(Boolean inclusiveEnd) {
        this.inclusiveEnd = inclusiveEnd;
        return this;
    }

    /**
     * Return only documents that match the specified key.
     */
    @JsonInclude(Include.NON_NULL)
    public K getKey() {
        return key;
    }

    /**
     * Return only documents that match the specified key.
     */
    public CouchViewQuery<K, V> setKey(K key) {
        if (classOfK == null) {
            classOfK = key.getClass();
        }
        this.key = key;
        return this;
    }

    /**
     * Return only documents where the key matches one of the keys specified in the
     * array.
     */
    @JsonInclude(Include.NON_NULL)
    public List<K> getKeys() {
        return keys;
    }

    /**
     * Return only documents where the key matches one of the keys specified in the
     * array.
     */
    public CouchViewQuery<K, V> setKeys(List<K> keys) {
        this.keys = keys;
        return this;
    }

    /**
     * Limit the number of the returned documents to the specified number.
     */
    @JsonInclude(Include.NON_NULL)
    public Integer getLimit() {
        return limit;
    }

    /**
     * Limit the number of the returned documents to the specified number.
     */
    public CouchViewQuery<K, V> setLimit(Integer limit) {
        this.limit = limit;
        return this;
    }

    /**
     * Use the reduction function. Default is true when a reduce function is
     * defined.
     */
    @JsonInclude(Include.NON_NULL)
    public Boolean getReduce() {
        return reduce;
    }

    /**
     * Use the reduction function. Default is true when a reduce function is
     * defined.
     */
    public CouchViewQuery<K, V> setReduce(Boolean reduce) {
        this.reduce = reduce;
        return this;
    }

    /**
     * Skip this number of records before starting to return the results. Default is
     * 0.
     */
    @JsonInclude(Include.NON_NULL)
    public Integer getSkip() {
        return skip;
    }

    /**
     * Skip this number of records before starting to return the results. Default is
     * 0.
     */
    public CouchViewQuery<K, V> setSkip(Integer skip) {
        this.skip = skip;
        return this;
    }

    /**
     * Sort returned
     */
    @JsonInclude(Include.NON_NULL)
    public Boolean getSorted() {
        return sorted;
    }

    /**
     * Sort returned
     */
    public CouchViewQuery<K, V> setSorted(Boolean sorted) {
        this.sorted = sorted;
        return this;
    }

    /**
     * Setting this to false offers a performance boost. The total_rows and offset
     * fields are not available when this is set to false. Default is true.
     */
    @JsonInclude(Include.NON_NULL)
    public Boolean getRows() {
        return rows;
    }

    /**
     * Setting this to false offers a performance boost. The total_rows and offset
     * fields are not available when this is set to false. Default is true.
     */
    public CouchViewQuery<K, V> setRows(Boolean rows) {
        this.rows = rows;
        return this;
    }

    /**
     * Whether or not the view results should be returned from a stable set of
     * shards. Default is false.
     */
    @JsonInclude(Include.NON_NULL)
    public Boolean getStable() {
        return stable;
    }

    /**
     * Whether or not the view results should be returned from a stable set of
     * shards. Default is false.
     */
    public CouchViewQuery<K, V> setStable(Boolean stable) {
        this.stable = stable;
        return this;
    }

    /**
     * Return records starting with the specified key.
     */
    @JsonGetter("start_key")
    @JsonInclude(Include.NON_NULL)
    public K getStartKey() {
        return startKey;
    }

    /**
     * Return records starting with the specified key.
     */
    @JsonSetter("start_key")
    public CouchViewQuery<K, V> setStartKey(K startKey) {
        if (classOfK == null) {
            classOfK = startKey.getClass();
        }
        this.startKey = startKey;
        return this;
    }

    /**
     * Return records starting with the specified document ID.
     * Ignored if startkey is not set.
     */
    @JsonGetter("start_key_doc_id")
    @JsonInclude(Include.NON_NULL)
    public String getStartKeyDocId() {
        return startKeyDocId;
    }

    /**
     * Return records starting with the specified document ID.
     * Ignored if startkey is not set.
     */
    @JsonSetter("start_key_doc_id")
    public CouchViewQuery<K, V> setStartKeyDocId(String startKeyDocId) {
        this.startKeyDocId = startKeyDocId;
        return this;
    }

    /**
     * Whether or not the view in question should be updated prior to responding
     * to the user. Supported values: true, false, lazy. Default is true.
     */
    @JsonInclude(Include.NON_NULL)
    public String getUpdate() {
        return update;
    }

    /**
     * Whether or not the view in question should be updated prior to responding
     * to the user. Supported values: true, false, lazy. Default is true.
     */
    public CouchViewQuery<K, V> setUpdate(String update) {
        this.update = update;
        return this;
    }

    /**
     * Whether to include in the response an update_seq value indicating
     * the sequence id of the database the view reflects. Default is false.
     */
    @JsonGetter("update_seq")
    @JsonInclude(Include.NON_NULL)
    public Boolean getUpdateSeq() {
        return updateSeq;
    }

    /**
     * Whether to include in the response an update_seq value indicating
     * the sequence id of the database the view reflects. Default is false.
     */
    @JsonSetter("update_seq")
    public CouchViewQuery<K, V> setUpdateSeq(Boolean updateSeq) {
        this.updateSeq = updateSeq;
        return this;
    }
}
