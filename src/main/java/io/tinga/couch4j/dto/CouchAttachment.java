package io.tinga.couch4j.dto;

import jakarta.annotation.Nullable;

/**
 * A document attachment
 */
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

    /**
     * Get the MIME type of the attachment
     */
    public String getContentType() {
        return content_type;
    }

    /**
     * Get an hash of the attachment
     */
    public String getDigest() {
        return digest;
    }

    /**
     * Get the length of the attachment
     */
    public Integer getLength() {
        return length;
    }

    /**
     * Get the rev position
     */
    public Integer getRevpos() {
        return revpos;
    }

    /**
     * True if the attachment contains the content
     */
    public boolean hasData() {
        return stub == null;
    }

    /**
     * Return the payload of the document
     */
    public byte[] getData() {
        return data;
    }

    /**
     * Return the encoded (compressed) length of the document.
     * May be null if the attachment was not requested with this information.
     */
    @Nullable
    public Integer getEncodedLength() {
        return encoded_length;
    }

    /**
     * Get the encoding (compression format) used for this document
     */
    @Nullable
    public String getEncoding() {
        return encoding;
    }
}
