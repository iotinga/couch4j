package io.tinga.couch4j;

import java.io.IOException;

import io.tinga.couch4j.auth.CouchAuthentication;
import io.tinga.couch4j.exception.CouchException;
import io.tinga.couch4j.exception.CouchExceptionFactory;
import io.tinga.couch4j.exception.CouchNetworkError;
import jakarta.annotation.Nullable;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class CouchServerImpl implements CouchServer {
    private HttpUrl baseUrl;
    private CouchAuthentication auth;
    private OkHttpClient client = new OkHttpClient();

    /**
     * @param uri  uri of the server
     * @param auth authentication information
     */
    CouchServerImpl(String uri, CouchAuthentication auth) {
        this.baseUrl = HttpUrl.get(uri);
        this.auth = auth;
    }

    @Nullable
    private ResponseBody request(Request.Builder request) throws CouchException {
        Request requestWithAuth = request
                .addHeader("Authorization", auth.getAuthorizationHeader())
                .build();

        try {
            Response response = client.newCall(requestWithAuth).execute();
            if (!response.isSuccessful()) {
                throw CouchExceptionFactory.getExceptionFromResponse(response);
            }

            return response.body();
        } catch (IOException e) {
            throw new CouchNetworkError();
        }
    }

    <T> T jsonRequest(Request.Builder requestBuilder, Class<?> classOfT, Class<?>... otherT) throws CouchException {
        return HttpUtil.getJsonResponseBody(request(requestBuilder), classOfT, otherT);
    }

    @Nullable
    ResponseBody get(HttpUrl url) throws CouchException {
        Request.Builder req = new Request.Builder()
                .get()
                .url(url);

        return request(req);
    }

    <T> T jsonGet(HttpUrl url, Class<?> classOfT, Class<?>... otherT) throws CouchException {
        Request.Builder req = new Request.Builder()
                .get()
                .url(url);

        return jsonRequest(req, classOfT, otherT);
    }

    @Nullable
    ResponseBody delete(HttpUrl url) throws CouchException {
        Request.Builder req = new Request.Builder()
                .delete()
                .url(url);

        return request(req);
    }

    <T> T jsonDelete(HttpUrl url, Class<?> classOfT, Class<?>... otherT) throws CouchException {
        Request.Builder req = new Request.Builder()
                .delete()
                .url(url);

        return jsonRequest(req, classOfT, otherT);
    }

    @Nullable
    ResponseBody post(HttpUrl url, RequestBody body) throws CouchException {
        Request.Builder req = new Request.Builder()
                .post(body)
                .url(url);

        return request(req);
    }

    <T> T jsonPost(HttpUrl url, Object body, Class<?> classOfT, Class<?>... otherT) throws CouchException {
        Request.Builder req = new Request.Builder()
                .post(HttpUtil.getJsonBody(body))
                .url(url);

        return jsonRequest(req, classOfT, otherT);
    }

    @Nullable
    ResponseBody put(HttpUrl url, RequestBody body) throws CouchException {
        Request.Builder req = new Request.Builder()
                .put(body)
                .url(url);

        return request(req);
    }

    <T> T jsonPut(HttpUrl url, Object body, Class<?> classOfT, Class<?>... otherT) throws CouchException {
        Request.Builder req = new Request.Builder()
                .put(HttpUtil.getJsonBody(body))
                .url(url);

        return jsonRequest(req, classOfT, otherT);
    }

    HttpUrl getBaseUrl() {
        return baseUrl;
    }

    @Override
    public CouchDatabase db(String name) {
        return new CouchDatabaseImpl(this, name);
    }

    @Override
    public String toString() {
        return baseUrl.toString();
    }
}