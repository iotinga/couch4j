package io.tinga.couch4j.dto;

import jakarta.annotation.Nullable;

public class CouchBulkUpdateResponseItem {
    private String id;

    @Nullable
    private String rev;

    private Boolean ok;

    @Nullable
    private String error;

    @Nullable
    private String reason;

    public String getId() {
        return id;
    }

    public String getRev() {
        return rev;
    }

    public boolean isOk() {
        return ok != null && ok;
    }

    public String getError() {
        return error;
    }

    public String getReason() {
        return reason;
    }
}
