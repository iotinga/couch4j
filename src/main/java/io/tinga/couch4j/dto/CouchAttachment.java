package io.tinga.couch4j.dto;

import jakarta.annotation.Nullable;

public class CouchAttachment {
    private String content_type;
    private String digest;
    private Integer length;
    private Integer revpos;

    @Nullable
    private Boolean stub;

    @Nullable
    private byte[] data;

    @Nullable
    private Integer encoded_length;

    @Nullable
    private String encoding;

    public String getContentType() {
        return content_type;
    }

    public String getDigest() {
        return digest;
    }

    public Integer getLength() {
        return length;
    }

    public Integer getRevpos() {
        return revpos;
    }

    public boolean getHasData() {
        return stub == null;
    }

    public byte[] getData() {
        return data;
    }

    @Nullable
    public Integer getEncodedLength() {
        return encoded_length;
    }

    @Nullable
    public String getEncoding() {
        return encoding;
    }
}
