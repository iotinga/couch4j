package io.tinga.couch4j.exception;

import io.tinga.couch4j.HttpUtil;
import io.tinga.couch4j.dto.CouchErrorStatus;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class CouchExceptionFactory {

    public static CouchException getExceptionFromResponse(Response response) {
        CouchErrorStatus errorResponse = null;
        ResponseBody body = response.body();
        if (body != null) {
            try {
                errorResponse = HttpUtil.getJsonResponseBody(body, CouchErrorStatus.class);
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
