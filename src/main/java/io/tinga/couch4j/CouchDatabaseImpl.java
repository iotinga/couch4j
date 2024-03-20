package io.tinga.couch4j;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.tinga.couch4j.changes.CouchChangesRequest;
import io.tinga.couch4j.changes.CouchChangesResponse;
import io.tinga.couch4j.dto.CouchBulkGetRequest;
import io.tinga.couch4j.dto.CouchBulkGetResponse;
import io.tinga.couch4j.dto.CouchBulkUpdateRequest;
import io.tinga.couch4j.dto.CouchBulkUpdateResponseItem;
import io.tinga.couch4j.dto.CouchDesignDocument;
import io.tinga.couch4j.dto.CouchDocument;
import io.tinga.couch4j.dto.CouchOkErrorResponse;
import io.tinga.couch4j.dto.CouchPutResponse;
import io.tinga.couch4j.exception.CouchConflictException;
import io.tinga.couch4j.exception.CouchException;
import io.tinga.couch4j.exception.CouchPreconditionFailed;
import io.tinga.couch4j.find.CouchFindQuery;
import io.tinga.couch4j.find.CouchFindResponse;
import io.tinga.couch4j.view.CouchViewQuery;
import io.tinga.couch4j.view.CouchViewResponse;
import okhttp3.HttpUrl;

class CouchDatabaseImpl implements CouchDatabase {

    private CouchServerImpl server;
    private HttpUrl baseUrl;
    private String name;

    /**
     * Note that this method is not public, since it's meant to
     * be created by CouchServerImpl only!
     */
    CouchDatabaseImpl(CouchServerImpl server, String name) {
        this.server = server;
        this.name = name;
        this.baseUrl = server.getBaseUrl().newBuilder().addPathSegment(name).build();
    }

    @Override
    public void createDbIfNotExists() throws CouchException {
        HttpUrl url = baseUrl.newBuilder()
                .addPathSegment(name)
                .build();

        try {
            server.jsonPut(url, Map.of(), Object.class);
        } catch (CouchPreconditionFailed e) {
            // db already exists. This is not a failure.
        }
    }

    @Override
    public <K> CouchDocument get(K id) throws CouchException {
        return get(id, CouchDocument.class);
    }

    @Override
    public <K, T extends CouchDocument> T get(K id, Class<T> classOfT) throws CouchException {
        return get(id, null, classOfT);
    }

    @Override
    public <K, T extends CouchDocument> T get(K id, String rev, Class<T> classOfT) throws CouchException {
        HttpUrl.Builder urlBuilder = baseUrl.newBuilder()
                .addPathSegment(id.toString());
        if (rev != null) {
            urlBuilder.addQueryParameter("rev", rev);
        }

        return HttpUtil.getJsonResponseBody(server.get(urlBuilder.build()), classOfT);
    }

    @Override
    public <K> byte[] getAttachment(K docId, String id) throws CouchException {
        HttpUrl url = baseUrl.newBuilder()
                .addPathSegment(docId.toString())
                .addPathSegment(id)
                .build();

        return HttpUtil.getBinaryResponseBody(server.get(url));
    }

    @Override
    public String put(CouchDocument doc) throws CouchException {
        CouchPutResponse response;
        if (doc.getId() == null) {
            response = server.jsonPost(baseUrl, doc, CouchPutResponse.class);
        } else {
            // a document update/creation with ID
            HttpUrl.Builder urlBuilder = baseUrl.newBuilder()
                    .addPathSegment(doc.getId());
            if (doc.getRev() != null) {
                urlBuilder.addQueryParameter("rev", doc.getRev());
            }
            response = server.jsonPost(urlBuilder.build(), doc, CouchPutResponse.class);
        }

        doc.setId(response.getId());
        doc.setRev(response.getRev());

        return response.getRev();
    }

    @Override
    public <K> String putAttachment(K docId, String id, byte[] content) throws CouchException {
        HttpUrl url = baseUrl.newBuilder()
                .addPathSegment(docId.toString())
                .addPathSegment(id)
                .build();

        CouchPutResponse response = HttpUtil.getJsonResponseBody(server.put(url, HttpUtil.getBinaryBody(content)),
                CouchPutResponse.class);

        return response.getRev();
    }

    @Override
    public <K> String putAttachment(K docId, String docRev, String id, byte[] content) throws CouchException {
        HttpUrl url = baseUrl.newBuilder()
                .addPathSegment(docId.toString())
                .addPathSegment(id)
                .addQueryParameter("rev", docRev)
                .build();

        CouchPutResponse response = HttpUtil.getJsonResponseBody(server.put(url, HttpUtil.getBinaryBody(content)),
                CouchPutResponse.class);

        return response.getRev();
    }

    @Override
    public String delete(CouchDocument doc) throws CouchException {
        HttpUrl url = baseUrl
                .newBuilder()
                .addPathSegment(doc.getId())
                .addQueryParameter("rev", doc.getRev())
                .build();

        CouchPutResponse response = server.jsonDelete(url, CouchPutResponse.class);

        doc.setRev(response.getRev());
        doc.setDeleted(true);

        return response.getRev();
    }

    @Override
    public <K> String delete(K id, String rev) throws CouchException {
        return delete(new CouchDocument(id.toString(), rev));
    }

    @Override
    public <K> String deleteAttachment(K docId, String docRev, String id) throws CouchException {
        HttpUrl url = baseUrl.newBuilder()
                .addPathSegment(docId.toString())
                .addPathSegment(id)
                .build();

        CouchPutResponse response = server.jsonDelete(url, CouchPutResponse.class);

        return response.getRev();
    }

    @Override
    public <T extends CouchDocument> List<T> find(CouchFindQuery query, Class<T> classOfT) throws CouchException {
        HttpUrl url = baseUrl.newBuilder()
                .addPathSegment("_find")
                .build();

        CouchFindResponse<T> response = server.jsonPost(url, query, CouchFindResponse.class, classOfT);

        return response.getDocs();
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public <K, T extends CouchDocument> Map<String, T> getMany(List<K> keys, Class<T> classOfT) throws CouchException {
        HttpUrl url = baseUrl.newBuilder()
                .addPathSegment("_bulk_get")
                .build();

        CouchBulkGetRequest request = new CouchBulkGetRequest();
        for (K key : keys) {
            request.addDoc(key);
        }

        CouchBulkGetResponse<T> response = server.jsonPost(url, request, CouchBulkGetResponse.class, classOfT);
        Map<String, T> docs = new HashMap<>();
        for (CouchBulkGetResponse.Item<T> result : response.getResults()) {
            for (CouchOkErrorResponse<T> doc : result.getDocs()) {
                if (doc.isOk()) {
                    docs.put(result.getId(), doc.getOk());
                }
            }
        }

        return docs;
    }

    @Override
    public <K> Map<String, CouchDocument> getMany(List<K> keys) throws CouchException {
        return getMany(keys, CouchDocument.class);
    }

    @Override
    public <T extends CouchDocument> Map<String, CouchBulkUpdateResponseItem> putMany(List<T> docs)
            throws CouchException {
        HttpUrl url = baseUrl.newBuilder()
                .addPathSegment("_bulk_docs")
                .build();

        CouchBulkUpdateRequest<T> request = new CouchBulkUpdateRequest<>(docs);
        List<CouchBulkUpdateResponseItem> response = server.jsonPost(url, request, ArrayList.class,
                CouchBulkUpdateResponseItem.class);
        Map<String, CouchBulkUpdateResponseItem> result = new HashMap<>();
        for (CouchBulkUpdateResponseItem item : response) {
            result.put(item.getId(), item);
        }

        return result;
    }

    @Override
    public <K, V, T extends CouchDocument> CouchViewResponse<K, V, T> view(CouchViewQuery<K, V> query,
            Class<T> classOfT)
            throws CouchException {
        HttpUrl url = baseUrl.newBuilder()
                .addPathSegment("_design")
                .addPathSegment(query.getDesignDoc())
                .addPathSegment("_view")
                .addPathSegment(query.getName())
                .build();

        return server.jsonPost(url, query, CouchViewResponse.class, query.getClassOfK(), query.getClassOfV(), classOfT);
    }

    @Override
    public <K, V> CouchViewResponse<K, V, CouchDocument> view(CouchViewQuery<K, V> query) throws CouchException {
        return view(query, CouchDocument.class);
    }

    @Override
    public CouchChangesResponse changes(CouchChangesRequest request) throws CouchException {
        HttpUrl url = baseUrl.newBuilder()
                .addPathSegment("_changes")
                .build();

        CouchChangesResponse response = server.jsonPost(url, request, CouchChangesResponse.class);

        return response;
    }

    @Override
    public <K> CouchDesignDocument getDesignDocument(K id) throws CouchException {
        HttpUrl.Builder urlBuilder = baseUrl.newBuilder()
                .addPathSegment("_design")
                .addPathSegment(id.toString());

        return server.jsonGet(urlBuilder.build(), CouchDesignDocument.class);
    }

    @Override
    public void createOrUpdateDesignDocument(CouchDesignDocument doc) throws CouchException {
        // a document update/creation with ID
        HttpUrl.Builder urlBuilder = baseUrl.newBuilder()
                .addPathSegment("_design")
                .addPathSegment(doc.getId());
        if (doc.getRev() != null) {
            urlBuilder.addQueryParameter("rev", doc.getRev());
        }
        try {
            CouchPutResponse response = server.jsonPut(urlBuilder.build(), doc, CouchPutResponse.class);
            doc.setRev(response.getRev());
        } catch (CouchConflictException e) {
            if (doc.getRev() == null && doc.getId() != null) {
                // a conflict may be generate by the document already existing. Load it to get
                // the _rev field.
                CouchDesignDocument oldDoc = getDesignDocument(doc.getId());
                doc.setRev(oldDoc.getId());

                // recursively call the document creation.
                createOrUpdateDesignDocument(doc);
            } else {
                // otherwise, surely this is some kind of unexplainable error.
                throw e;
            }
        }
    }
}
