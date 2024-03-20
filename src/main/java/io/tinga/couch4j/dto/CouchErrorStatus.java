package io.tinga.couch4j.dto;

import jakarta.annotation.Nullable;

public class CouchErrorStatus {
    private String id;
    private String error;
    private String reason;

    @Nullable
    public String getId() {
        return id;
    }

    public String getError() {
        return error;
    }

    public String getReason() {
        return reason;
    }
}
