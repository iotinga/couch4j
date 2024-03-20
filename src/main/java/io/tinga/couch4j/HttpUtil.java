package io.tinga.couch4j;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;

import io.tinga.couch4j.exception.CouchException;
import io.tinga.couch4j.exception.CouchNetworkError;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

public class HttpUtil {
    private static final MediaType JSON_MEDIA_TYPE = MediaType.get("application/json");
    private static final MediaType BINARY_MEDIA_TYPE = MediaType.get("application/octet-stream");

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    private static final TypeFactory TYPE_FACTORY = OBJECT_MAPPER.getTypeFactory();

    public static RequestBody getJsonBody(Object object) throws CouchException {
        try {
            return RequestBody.create(OBJECT_MAPPER.writeValueAsString(object), JSON_MEDIA_TYPE);
        } catch (JsonProcessingException e) {
            throw new CouchException(
                    "document serialization failed. Check if document type is correctly serializable!");
        }
    }

    public static RequestBody getBinaryBody(byte[] bytes) {
        return RequestBody.create(bytes, BINARY_MEDIA_TYPE);
    }

    public static <T> T getJsonResponseBody(ResponseBody body, Class<?> classOfT, Class<?>... parameterClasses)
            throws CouchException {
        if (body == null) {
            throw new CouchException("response body is null. This is not expected here!");
        }

        try {
            return OBJECT_MAPPER.readValue(body.string(),
                    TYPE_FACTORY.constructParametricType(classOfT, parameterClasses));
        } catch (IOException e) {
            throw new CouchException("response is not a valid JSON. This is not expected here!");
        } finally {
            body.close();
        }
    }

    public static byte[] getBinaryResponseBody(ResponseBody body) throws CouchException {
        if (body == null) {
            throw new CouchException("response body is null. This is not expected here!");
        }

        try {
            byte[] bytes = body.bytes();
            body.close();

            return bytes;
        } catch (IOException e) {
            throw new CouchNetworkError();
        } finally {
            body.close();
        }
    }
}