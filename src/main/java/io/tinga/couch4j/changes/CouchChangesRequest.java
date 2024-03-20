package io.tinga.couch4j.changes;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class CouchChangesRequest {
    /**
     * All past changes are returned immediately
     */
    public static final String FEED_NORMAL = "normal";

    /**
     * Specifies Long Polling Mode. Waits until at least one change has
     * occurred, sends the change, then closes the connection. Most commonly used
     * in conjunction with since=now, to wait for the next change.
     */
    public static final String FEED_LONGPOLL = "longpoll";

    /**
     * Sends a line of JSON per event. Keeps the
     * socket open until timeout
     */
    public static final String FEED_CONTINUOS = "continuous";

    /**
     * Works the same as Continuous Mode, but sends the
     * events in EventSource format.
     */
    public static final String FEED_EVENTSOURCE = "eventsource";

    private List<String> docIds;
    private Boolean conflicts;
    private Boolean descending;
    private String feed;
    private String filter;
    private Integer heartbeat;
    private Boolean includeDocs;
    private Boolean attachments;
    private Boolean attEncodingInfo;
    private Integer lastEventId;
    private Integer limit;
    private String since;
    private String style;
    private Integer timeout;
    private String view;
    private Integer seqInterval;

    @JsonGetter("doc_ids")
    @JsonInclude(Include.NON_NULL)
    public List<String> getDocIds() {
        return docIds;
    }

    @JsonSetter("doc_ids")
    public void setDocIds(List<String> doc_ids) {
        this.docIds = doc_ids;
    }

    @JsonInclude(Include.NON_NULL)
    public Boolean getConflicts() {
        return conflicts;
    }

    public void setConflicts(Boolean conflicts) {
        this.conflicts = conflicts;
    }

    @JsonInclude(Include.NON_NULL)
    public Boolean getDescending() {
        return descending;
    }

    public void setDescending(Boolean descending) {
        this.descending = descending;
    }

    @JsonInclude(Include.NON_NULL)
    public String getFeed() {
        return feed;
    }

    public void setFeed(String feed) {
        this.feed = feed;
    }

    @JsonInclude(Include.NON_NULL)
    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }

    @JsonInclude(Include.NON_NULL)
    public Integer getHeartbeat() {
        return heartbeat;
    }

    public void setHeartbeat(Integer heartbeat) {
        this.heartbeat = heartbeat;
    }

    @JsonInclude(Include.NON_NULL)
    @JsonGetter("include_docs")
    public Boolean getIncludeDocs() {
        return includeDocs;
    }

    @JsonSetter("include_docs")
    public void setIncludeDocs(Boolean include_docs) {
        this.includeDocs = include_docs;
    }

    @JsonInclude(Include.NON_NULL)
    public Boolean getAttachments() {
        return attachments;
    }

    public void setAttachments(Boolean attachments) {
        this.attachments = attachments;
    }

    @JsonInclude(Include.NON_NULL)
    @JsonGetter("att_encoding_info")
    public Boolean getAttEncodingInfo() {
        return attEncodingInfo;
    }

    @JsonSetter("att_encoding_info")
    public void setAttEncodingInfo(Boolean att_encoding_info) {
        this.attEncodingInfo = att_encoding_info;
    }

    @JsonInclude(Include.NON_NULL)
    @JsonGetter("last_event_id")
    public Integer getLastEventId() {
        return lastEventId;
    }

    @JsonSetter("last_event_id")
    public void setLastEventId(Integer last_event_id) {
        this.lastEventId = last_event_id;
    }

    @JsonInclude(Include.NON_NULL)
    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    @JsonInclude(Include.NON_NULL)
    public String getSince() {
        return since;
    }

    public void setSince(String since) {
        this.since = since;
    }

    @JsonInclude(Include.NON_NULL)
    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    @JsonInclude(Include.NON_NULL)
    public Integer getTimeout() {
        return timeout;
    }

    public void setTimeout(Integer timeout) {
        this.timeout = timeout;
    }

    @JsonInclude(Include.NON_NULL)
    public String getView() {
        return view;
    }

    public void setView(String view) {
        this.view = view;
    }

    @JsonInclude(Include.NON_NULL)
    @JsonGetter("seq_interval")
    public Integer getSeqInterval() {
        return seqInterval;
    }

    @JsonSetter("seq_interval")
    public void setSeqInterval(Integer seq_interval) {
        this.seqInterval = seq_interval;
    }

    protected CouchChangesRequest clone() {
        try {
            return (CouchChangesRequest) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }
}
