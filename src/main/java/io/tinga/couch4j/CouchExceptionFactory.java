package io.tinga.couch4j;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.tinga.couch4j.dto.CouchErrorResponse;
import io.tinga.couch4j.exception.CouchConflictException;
import io.tinga.couch4j.exception.CouchException;
import io.tinga.couch4j.exception.CouchInvalidArgumentsException;
import io.tinga.couch4j.exception.CouchNotAuthorizedException;
import io.tinga.couch4j.exception.CouchNotFoundException;
import io.tinga.couch4j.exception.CouchPreconditionFailed;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class CouchExceptionFactory {
    private static final Logger log = LoggerFactory.getLogger(CouchExceptionFactory.class);

    public static CouchException getExceptionFromResponse(Response response) {
        CouchErrorResponse errorResponse = null;
        ResponseBody body = response.body();
        String bodyAsString = "NO_BODY";
        if (body != null) {
            try {
                bodyAsString = body.string();
                errorResponse = HttpUtil.getJsonResponseBody(bodyAsString, CouchErrorResponse.class);
            } catch (IOException e) {
                // ignored
            }
        }

        log.warn("request failed with code {} {}", response.code(), bodyAsString);

        switch (response.code()) {
            case 400:
                return new CouchInvalidArgumentsException(errorResponse);
            case 401:
                return new CouchNotAuthorizedException(errorResponse);
            case 404:
                return new CouchNotFoundException(errorResponse);
            case 409:
                return new CouchConflictException(errorResponse);
            case 412:
                return new CouchPreconditionFailed(errorResponse);
            default:
                return new CouchException("couchdb response with unexpected status " + response.code(), errorResponse);
        }
    }
}
