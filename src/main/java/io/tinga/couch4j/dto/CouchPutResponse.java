package io.tinga.couch4j.dto;

import jakarta.annotation.Nullable;

public class CouchPutResponse {
    private boolean ok;

    @Nullable
    private String id;

    @Nullable
    private String rev;

    public boolean isOk() {
        return ok;
    }

    @Nullable
    public String getId() {
        return id;
    }

    @Nullable
    public String getRev() {
        return rev;
    }

    @Override
    public String toString() {
        return String.format("Response(ok=%b, id=%s, rev=%s)", ok, id, rev);
    }
}
