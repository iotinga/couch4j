package io.tinga.couch4j;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;

import io.tinga.couch4j.exception.CouchException;
import io.tinga.couch4j.exception.CouchNetworkError;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

class HttpUtil {
    private static final Logger log = LoggerFactory.getLogger(HttpUtil.class);

    private static final MediaType JSON_MEDIA_TYPE = MediaType.get("application/json");
    private static final MediaType BINARY_MEDIA_TYPE = MediaType.get("application/octet-stream");

    private static final ObjectMapper OBJECT_MAPPER = JsonMapper.builder()
            .findAndAddModules()
            .build();
    private static final TypeFactory TYPE_FACTORY = OBJECT_MAPPER.getTypeFactory();

    public static RequestBody getJsonBody(Object object) throws CouchException {
        try {
            String jsonString = OBJECT_MAPPER.writeValueAsString(object);
            return RequestBody.create(jsonString, JSON_MEDIA_TYPE);
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
            return getJsonResponseBody(body.string(), classOfT, parameterClasses);
        } catch (IOException e) {
            log.error("error deserializing JSON body", e);
            throw new CouchException("response is not a valid JSON. This is not expected here!");
        } finally {
            body.close();
        }
    }

    public static <T> T getJsonResponseBody(String body, Class<?> classOfT, Class<?>... parameterClasses)
            throws IOException {
        return OBJECT_MAPPER.readValue(body,
                TYPE_FACTORY.constructParametricType(classOfT, parameterClasses));
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
            log.error("error reading binary body response", e);
            throw new CouchNetworkError();
        } finally {
            body.close();
        }
    }
}
