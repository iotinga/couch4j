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

    /**
     * Get the MIME type of the attachment
     * 
     * @return content type string
     */
    public String getContentType() {
        return content_type;
    }

    /**
     * Get an hash of the attachment
     * 
     * @return the hash string
     */
    public String getDigest() {
        return digest;
    }

    /**
     * Get the length of the attachment
     * 
     * @return the length of the attachment
     */
    public Integer getLength() {
        return length;
    }

    /**
     * Get the rev position
     * 
     * @return
     */
    public Integer getRevpos() {
        return revpos;
    }

    /**
     * True if the attachment contains the content
     * 
     * @return
     */
    public boolean hasData() {
        return stub == null;
    }

    /**
     * Return the payload of the document
     * 
     * @return
     */
    public byte[] getData() {
        return data;
    }

    /**
     * Return the encoded (compressed) length of the document.
     * May be null if the attachment was not requested with this information.
     * 
     * @return
     */
    @Nullable
    public Integer getEncodedLength() {
        return encoded_length;
    }

    /**
     * Get the encoding (compression format) used for this document
     * 
     * @return
     */
    @Nullable
    public String getEncoding() {
        return encoding;
    }
}
