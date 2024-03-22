package io.tinga.couch4j.changes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class CouchChangesRequest implements Cloneable {
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

    public List<String> getDocIds() {
        return docIds;
    }

    public void setDocIds(List<String> doc_ids) {
        this.docIds = doc_ids;
    }

    public Boolean getConflicts() {
        return conflicts;
    }

    public void setConflicts(Boolean conflicts) {
        this.conflicts = conflicts;
    }

    public Boolean getDescending() {
        return descending;
    }

    public void setDescending(Boolean descending) {
        this.descending = descending;
    }

    public String getFeed() {
        return feed;
    }

    public void setFeed(String feed) {
        this.feed = feed;
    }

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }

    public Integer getHeartbeat() {
        return heartbeat;
    }

    public void setHeartbeat(Integer heartbeat) {
        this.heartbeat = heartbeat;
    }

    public Boolean getIncludeDocs() {
        return includeDocs;
    }

    public void setIncludeDocs(Boolean include_docs) {
        this.includeDocs = include_docs;
    }

    public Boolean getAttachments() {
        return attachments;
    }

    public void setAttachments(Boolean attachments) {
        this.attachments = attachments;
    }

    public Boolean getAttEncodingInfo() {
        return attEncodingInfo;
    }

    public void setAttEncodingInfo(Boolean att_encoding_info) {
        this.attEncodingInfo = att_encoding_info;
    }

    public Integer getLastEventId() {
        return lastEventId;
    }

    public void setLastEventId(Integer last_event_id) {
        this.lastEventId = last_event_id;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public String getSince() {
        return since;
    }

    public void setSince(String since) {
        this.since = since;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public Integer getTimeout() {
        return timeout;
    }

    public void setTimeout(Integer timeout) {
        this.timeout = timeout;
    }

    public String getView() {
        return view;
    }

    public void setView(String view) {
        this.view = view;
    }

    public Integer getSeqInterval() {
        return seqInterval;
    }

    public void setSeqInterval(Integer seq_interval) {
        this.seqInterval = seq_interval;
    }

    @Override
    protected CouchChangesRequest clone() {
        try {
            return (CouchChangesRequest) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("internal error. Shall never have happened!", e);
        }
    }

    public Map<String, String> getQueryParams() {
        Map<String, Object> items = new HashMap<>();

        items.put("conflicts", conflicts);
        items.put("descending", descending);
        items.put("feed", feed);
        items.put("filter", filter);
        items.put("hreartbeat", heartbeat);
        items.put("include_docs", includeDocs);
        items.put("attachments", attachments);
        items.put("att_encoding_info", attEncodingInfo);
        items.put("last_event_id", lastEventId);
        items.put("limit", limit);
        items.put("since", since);
        items.put("style", style);
        items.put("timeout", timeout);
        items.put("view", view);
        items.put("seq_interval", seqInterval);

        Map<String, String> result = new HashMap<>();
        for (Entry<String, Object> item : items.entrySet()) {
            if (item.getValue() != null) {
                result.put(item.getKey(), item.getValue().toString());
            }
        }

        return result;
    }

    public Map<String, Object> getBody() {
        if (docIds != null) {
            return Map.of("doc_ids", docIds);
        }

        return Map.of();
    }

    @Override
    public String toString() {
        return "CouchChangesRequest [docIds=" + docIds + ", conflicts=" + conflicts + ", descending=" + descending
                + ", feed=" + feed + ", filter=" + filter + ", heartbeat=" + heartbeat + ", includeDocs=" + includeDocs
                + ", attachments=" + attachments + ", attEncodingInfo=" + attEncodingInfo + ", lastEventId="
                + lastEventId + ", limit=" + limit + ", since=" + since + ", style=" + style + ", timeout=" + timeout
                + ", view=" + view + ", seqInterval=" + seqInterval + "]";
    }
}
