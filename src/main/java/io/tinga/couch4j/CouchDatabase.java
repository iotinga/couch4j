package io.tinga.couch4j;

import java.util.List;
import java.util.Map;

import io.tinga.couch4j.changes.CouchChangesRequest;
import io.tinga.couch4j.changes.CouchChangesResponse;
import io.tinga.couch4j.dto.CouchBulkUpdateResponseItem;
import io.tinga.couch4j.dto.CouchDesignDocument;
import io.tinga.couch4j.dto.CouchDocument;
import io.tinga.couch4j.exception.CouchException;
import io.tinga.couch4j.find.CouchFindQuery;
import io.tinga.couch4j.view.CouchViewQuery;
import io.tinga.couch4j.view.CouchViewResponse;

/**
 * A CouchDb Database
 */
public interface CouchDatabase {
        /**
         * Creates the database if it does not exists
         * 
         * @throws CouchException
         */
        void createDbIfNotExists() throws CouchException;

        /**
         * Gets the document with the specified id
         * 
         * @param <K> type of the key object. The id of the document is the result
         *            of the toString() method
         * @param id  of the document to get
         * @return the document
         * @throws CouchException
         */
        <K> CouchDocument get(K id) throws CouchException;

        /**
         * Gets the document with the specified id
         * 
         * @param <K>      type of the key object. The id of the document is the result
         *                 of the toString() method
         * @param <T>      type of the document
         * @param id       of the document to get
         * @param classOfT class to deserialize document into
         * @return the document
         * @throws CouchException
         */
        <K, T extends CouchDocument> T get(K id, Class<T> classOfT) throws CouchException;

        /**
         * Gets the document with the specified id
         * 
         * @param <K>      type of the key object. The id of the document is the result
         *                 of the toString() method
         * @param <T>      type of the document
         * @param id       of the document to get
         * @param rev      gets the document with specific revision
         * @param classOfT class to deserialize document into
         * @return the document
         * @throws CouchException
         */
        <K, T extends CouchDocument> T get(K id, String rev, Class<T> classOfT) throws CouchException;

        /**
         * Gets a list of documents
         * 
         * @param <K>  type of the key object. The id of the document is the result
         *             of the toString() method
         * @param keys keys to get
         * @return a map containing the document got from the db.
         * @throws CouchException if the get of the document fails. If only
         *                        the get of a single document fails it will not throw,
         *                        but that document will not be in the result map.
         */
        <K> Map<String, CouchDocument> getMany(List<K> keys) throws CouchException;

        /**
         * Gets a list of documents
         * 
         * @param <K>      type of the key object. The id of the document is the result
         *                 of the toString() method
         * @param <T>      type of the document
         * @param keys     keys to get
         * @param classOfT class to deserialize document into
         * @return a map containing the document got from the db.
         * @throws CouchException if the get of the document fails. If only
         *                        the get of a single document fails it will not throw,
         *                        but that document will not be in the result map.
         */
        <K, T extends CouchDocument> Map<String, T> getMany(List<K> keys, Class<T> classOfT) throws CouchException;

        /**
         * Get the attachment of a document
         * 
         * @param docId id of the document
         * @param id    id of the attachment
         * @return the attachment raw content
         * @throws CouchException
         */
        <K> byte[] getAttachment(K docId, String id) throws CouchException;

        /**
         * Puts a document in the database.
         * If the document does not exist it's created.
         * If the document exists, it's updated. In this case the
         * document needs to contain the _rev field, otherwise it will
         * return an error.
         * If the document id is null, a new ID is automatically generated by couchdb.
         * Updates the provided doc revision and id, modifying the object in place.
         * 
         * @param doc the document to put
         * @return the new document revision code
         * @throws CouchException
         */
        String put(CouchDocument doc) throws CouchException;

        /**
         * Puts, updates or deletes a list of documents in the couchdb.
         * Updates the doc revision updating the document in-place.
         * 
         * @param docs the document to put, update or delete
         * @return a map containing the response of the operation for each document
         * @throws CouchException if the update of all documents fails.
         */
        Map<String, CouchBulkUpdateResponseItem> putMany(List<CouchDocument> docs) throws CouchException;

        /**
         * Put the attachment of a document. Use this method only to
         * create a new attachment.
         * 
         * @param docId   id of the document
         * @param id      id of the attachment
         * @param content content of the attachment
         * @return the updated document revision (putting attachment updates it)
         * @throws CouchException
         */
        <K> String putAttachment(K docId, String id, byte[] content) throws CouchException;

        /**
         * Put the attachment of a document. If a docRev is specified,
         * the existing attachment is updated
         * 
         * @param docId   id of the document
         * @param docRev  revision of the document
         * @param id      id of the attachment
         * @param content content of the attachment
         * @return the updated document revision (putting attachment updates it)
         * @throws CouchException
         */
        <K> String putAttachment(K docId, String docRev, String id, byte[] content) throws CouchException;

        /**
         * Deletes the specified document
         * 
         * @param doc the document to delete. Shall include _id and _rev field
         * @return the id of the deleted document in case of success
         * @throws CouchException
         */
        String delete(CouchDocument doc) throws CouchException;

        /**
         * Deletes the specified document
         * 
         * @param id  id of the document to delete
         * @param rev revision code of the document to delete
         * @return the id of the deleted document in case of success
         * @throws CouchException
         */
        <K> String delete(K id, String rev) throws CouchException;

        /**
         * Deletes the specified document attachment
         * 
         * @param docId  id of the document to delete
         * @param docRev revision code of the document to delete
         * @param id     id of the attachment to delete
         * @return the id of the deleted document in case of success
         * @throws CouchException
         */
        <K> String deleteAttachment(K docId, String docRev, String id) throws CouchException;

        /**
         * Executes a find query
         * 
         * @param <T>      type of the resulting document
         * @param query    the query to execute
         * @param classOfT class of the T type
         * @return a list of document
         * @throws CouchException
         */
        <T extends CouchDocument> List<T> find(CouchFindQuery query, Class<T> classOfT) throws CouchException;

        /**
         * Perform a query on a view
         * 
         * @param <K>   type of the view key (typically either String)
         * @param <V>   type of the view value
         * @param query query to perform on the view
         * @return the couchdb view result
         * @throws CouchException
         */
        <K, V> CouchViewResponse<K, V, CouchDocument> view(CouchViewQuery<K, V> query)
                        throws CouchException;

        /**
         * Perform a query on a view
         * 
         * @param <K>      type of the view key (typically a String)
         * @param <V>      type of the view value
         * @param <T>      type of the document returned of the view (if queried with
         *                 includeDocs)
         * @param query    query to perform on the view
         * @param classOfT class of the T type
         * @return the couchdb view result
         * @throws CouchException
         */
        <K, V, T extends CouchDocument> CouchViewResponse<K, V, T> view(CouchViewQuery<K, V> query, Class<T> classOfT)
                        throws CouchException;

        /**
         * Queries the changes feed
         * 
         * @param request
         * @return
         * @throws CouchException
         */
        CouchChangesResponse changes(CouchChangesRequest request) throws CouchException;

        /**
         * Returns the design document with the specified id
         * 
         * @param id id of the design document
         * @throws CouchException
         */
        <K> CouchDesignDocument getDesignDocument(K id) throws CouchException;

        /**
         * Creates or updates the specified design document
         * 
         * @param doc the design document to update
         * @throws CouchException
         */
        void createOrUpdateDesignDocument(CouchDesignDocument doc) throws CouchException;
}
