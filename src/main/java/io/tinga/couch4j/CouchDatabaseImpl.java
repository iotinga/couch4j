package io.tinga.couch4j;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
import io.tinga.couch4j.view.CouchViewQueriesResponse;
import io.tinga.couch4j.view.CouchViewQuery;
import io.tinga.couch4j.view.CouchViewResponse;
import okhttp3.HttpUrl;
import okhttp3.RequestBody;

class CouchDatabaseImpl implements CouchDatabase {
    private static final Logger log = LoggerFactory.getLogger(CouchServerImpl.class);

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
        try {
            server.put(baseUrl, RequestBody.create(new byte[] {})).close();
        } catch (CouchPreconditionFailed e) {
            log.debug("db already exists. This is not a failure.");
        }
    }

    @Override
    public void dropDb() throws CouchException {
        server.delete(baseUrl).close();
        log.info("dropped database {}", name);
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
    public byte[] getAttachment(CouchDocument doc, String id) throws CouchException {
        HttpUrl url = baseUrl.newBuilder()
                .addPathSegment(doc.getId())
                .addPathSegment(id)
                .build();

        return HttpUtil.getBinaryResponseBody(server.get(url));
    }

    @Override
    public void put(CouchDocument doc) throws CouchException {
        CouchPutResponse response;
        if (doc.getId() == null) {
            response = server.jsonPost(baseUrl, doc, CouchPutResponse.class);
            doc.setId(response.getId());
        } else {
            // a document update/creation with ID
            HttpUrl.Builder urlBuilder = baseUrl.newBuilder()
                    .addPathSegment(doc.getId());
            if (doc.getRev() != null) {
                urlBuilder.addQueryParameter("rev", doc.getRev());
            }
            response = server.jsonPut(urlBuilder.build(), doc, CouchPutResponse.class);

        }

        if (doc.getRev() == null) {
            log.debug("created doc {} {}", response.getId(), response.getRev());
        } else {
            log.debug("updated doc {} {}", response.getId(), response.getRev());

        }

        doc.setRev(response.getRev());
    }

    @Override
    public void putAttachment(CouchDocument doc, String id, byte[] content) throws CouchException {
        HttpUrl.Builder url = baseUrl.newBuilder()
                .addPathSegment(doc.getId())
                .addPathSegment(id);
        if (doc.getRev() != null) {
            url.addQueryParameter("rev", doc.getRev());
        }

        CouchPutResponse response = HttpUtil.getJsonResponseBody(
                server.put(url.build(), HttpUtil.getBinaryBody(content)),
                CouchPutResponse.class);

        doc.setRev(response.getRev());

        log.debug("updated att {} {} {}", doc.getId(), doc.getRev(), id);
    }

    @Override
    public void delete(CouchDocument doc) throws CouchException {
        HttpUrl url = baseUrl
                .newBuilder()
                .addPathSegment(doc.getId())
                .addQueryParameter("rev", doc.getRev())
                .build();

        CouchPutResponse response = server.jsonDelete(url, CouchPutResponse.class);

        doc.setRev(response.getRev());
        doc.setDeleted(true);

        log.debug("deleted doc {} {}", doc.getId(), doc.getRev());
    }

    @Override
    public void deleteAttachment(CouchDocument doc, String id) throws CouchException {
        HttpUrl.Builder url = baseUrl.newBuilder()
                .addPathSegment(doc.getId())
                .addPathSegment(id);
        if (doc.getRev() != null) {
            url.addQueryParameter("rev", doc.getRev());
        }

        CouchPutResponse response = server.jsonDelete(url.build(), CouchPutResponse.class);
        doc.setRev(response.getRev());

        log.debug("deleted att {} {} {}", doc.getId(), doc.getRev(), id);
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

        Map<String, CouchDocument> docsMap = new HashMap<>();
        for (CouchDocument doc : docs) {
            docsMap.put(doc.getId(), doc);
        }

        CouchBulkUpdateRequest<T> request = new CouchBulkUpdateRequest<>(docs);
        List<CouchBulkUpdateResponseItem> response = server.jsonPost(url, request, ArrayList.class,
                CouchBulkUpdateResponseItem.class);
        Map<String, CouchBulkUpdateResponseItem> result = new HashMap<>();
        for (CouchBulkUpdateResponseItem item : response) {
            result.put(item.getId(), item);
            if (item.isOk()) {
                CouchDocument oldDoc = docsMap.get(item.getId());
                if (oldDoc != null) {
                    docsMap.get(item.getId()).setRev(item.getRev());
                }
            }
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
        HttpUrl.Builder url = baseUrl.newBuilder()
                .addPathSegment("_changes");

        for (Entry<String, String> entry : request.getQueryParams().entrySet()) {
            url.addQueryParameter(entry.getKey(), entry.getValue());
        }

        CouchChangesResponse response = server.jsonPost(url.build(), request.getBody(), CouchChangesResponse.class);

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
                doc.setRev(oldDoc.getRev());

                // recursively call the document creation.
                createOrUpdateDesignDocument(doc);
            } else {
                // otherwise, surely this is some kind of unexplainable error.
                throw e;
            }
        }
    }

    @Override
    public <K, V> List<CouchViewResponse<K, V, CouchDocument>> view(List<CouchViewQuery<K, V>> queries)
            throws CouchException {
        return viewMany(queries, CouchDocument.class);
    }

    @Override
    public <K, V, T extends CouchDocument> List<CouchViewResponse<K, V, T>> viewMany(List<CouchViewQuery<K, V>> queries,
            Class<T> classOfT) throws CouchException {
        HttpUrl url = baseUrl.newBuilder()
                .addPathSegment("_design")
                .addPathSegment(queries.getFirst().getDesignDoc())
                .addPathSegment("_view")
                .addPathSegment(queries.getFirst().getName())
                .addPathSegment("queries")
                .build();

        CouchViewQueriesResponse<K, V, T> response = server.jsonPost(url, Map.of("queries", queries),
                CouchViewQueriesResponse.class,
                queries.getFirst().getClassOfK(),
                queries.getFirst().getClassOfV(), classOfT);

        return response.getResults();
    }
}
