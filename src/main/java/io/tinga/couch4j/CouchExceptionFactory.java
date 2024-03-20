package io.tinga.couch4j;

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

    public static CouchException getExceptionFromResponse(Response response) {
        CouchErrorResponse errorResponse = null;
        ResponseBody body = response.body();
        if (body != null) {
            try {
                errorResponse = HttpUtil.getJsonResponseBody(body, CouchErrorResponse.class);
            } catch (Exception e) {
                // ignored
            }
        }

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
