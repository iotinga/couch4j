package io.tinga.couch4j.dto;

import jakarta.annotation.Nullable;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * A base CouchDb document. Must be extended by the document
 * that are used in the application.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CouchDocument implements CouchKey {
    @Nullable
    private String id;

    @Nullable
    private String rev;

    @Nullable
    private Boolean deleted;

    @Nullable
    private List<CouchAttachment> attachments;

    /**
     * Construct a document without the id
     */
    public CouchDocument() {
    }

    /**
     * Construct a document with a specified id
     */
    public CouchDocument(String id) {
        this.id = id;
    }

    /**
     * Construct a document with specified id and revision number
     * 
     * @param id
     * @param rev
     */
    public CouchDocument(String id, String rev) {
        this(id);
        this.rev = rev;
    }

    @Nullable
    @JsonGetter("_id")
    @JsonInclude(Include.NON_NULL)
    public String getId() {
        return id;
    }

    @JsonSetter("_id")
    public void setId(String id) {
        this.id = id;
    }

    @Nullable
    @JsonGetter("_rev")
    @JsonInclude(Include.NON_NULL)
    public String getRev() {
        return rev;
    }

    @JsonSetter("_rev")
    public void setRev(String rev) {
        this.rev = rev;
    }

    /**
     * True if the document is deleted
     * 
     * @return
     */
    @JsonGetter("_deleted")
    @Nullable
    @JsonInclude(Include.NON_NULL)
    public Boolean getDeleted() {
        return deleted;
    }

    /**
     * Set the document for deletion
     * 
     * @param deleted
     */
    @JsonSetter("_deleted")
    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    @Override
    public String toString() {
        return id;
    }

    /**
     * Return the attachment of this document
     * 
     * @return
     */
    @Nullable
    @JsonGetter("_attachments")
    @JsonInclude(Include.NON_NULL)
    public List<CouchAttachment> getAttachments() {
        return attachments;
    }
}
