package io.tinga.couch4j.dto;

import jakarta.annotation.Nullable;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class CouchDocument implements CouchKey {
    @Nullable
    private String id;

    @Nullable
    private String rev;

    @Nullable
    private Boolean deleted;

    public CouchDocument() {
    }

    public CouchDocument(String id) {
        this.id = id;
    }

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

    public boolean isDeleted() {
        return deleted != null && deleted;
    }

    @JsonGetter("_deleted")
    @JsonInclude(Include.NON_NULL)
    public Boolean getDeleted() {
        return deleted;
    }

    @JsonSetter("_deleted")
    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    @Override
    public String toString() {
        return id;
    }
}
