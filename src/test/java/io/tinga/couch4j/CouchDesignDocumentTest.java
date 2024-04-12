package io.tinga.couch4j;

import java.util.Map;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.github.javafaker.Faker;

import io.tinga.couch4j.dto.CouchDesignDocument;
import io.tinga.couch4j.dto.CouchDocument;
import io.tinga.couch4j.exception.CouchException;
import io.tinga.couch4j.view.CouchViewQuery;
import io.tinga.couch4j.view.CouchViewResponse;

class CouchDesignDocumentTest {
    Faker faker = new Faker();
    CouchServer server = new CouchBuilder()
            .setUri("http://localhost:5984")
            .setBasicAuth("admin", "admin")
            .build();;
    CouchDatabase db;

    @BeforeEach
    void setUp() throws CouchException {
        db = server.db("test" + faker.random().hex(10).toLowerCase());
        db.createDbIfNotExists();
    }

    @AfterEach
    void tearDown() throws CouchException {
        db.dropDb();
    }

    @Test
    void updateExistingDesignDoc() throws CouchException {
        CouchDesignDocument dd = new CouchDesignDocument("test");

        dd.setViews(Map.of("view1", new CouchDesignDocument.View("function (doc) { emit(doc._id, 1) }")));

        db.createOrUpdateDesignDocument(dd);

        CouchDesignDocument dd2 = new CouchDesignDocument("test");

        dd2.setViews(Map.of("view1", new CouchDesignDocument.View("function (doc) { emit(doc._id, 1) }")));

        db.createOrUpdateDesignDocument(dd2);
    }

    @Test
    void queryDesignDoc() throws CouchException {
        CouchDesignDocument dd = new CouchDesignDocument("test");

        dd.setViews(Map.of("view1", new CouchDesignDocument.View("function (doc) { emit(doc._id, 1) }")));

        db.createOrUpdateDesignDocument(dd);

        db.put(new CouchDesignDocument());

        db.view(new CouchViewQuery<String, Integer>("test", "view1").setIncludeDocs(true));
    }
}
