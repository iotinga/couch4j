package io.tinga.couch4j;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.verify;

import java.util.function.Consumer;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.github.javafaker.Faker;

import io.tinga.couch4j.changes.CouchChangeItem;
import io.tinga.couch4j.changes.CouchChangesFeed;
import io.tinga.couch4j.changes.CouchChangesRequest;
import io.tinga.couch4j.changes.CouchLongPollingChangesFeed;
import io.tinga.couch4j.dto.CouchDocument;
import io.tinga.couch4j.exception.CouchException;

public class CouchChangesTest {
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
    void longPollingTest() throws CouchException, InterruptedException {
        CouchChangesRequest request = new CouchChangesRequest();
        request.setSince("now");
        CouchChangesFeed changes = new CouchLongPollingChangesFeed(db, request);

        Consumer<CouchChangeItem> onItem = mock(Consumer.class);
        Consumer<Exception> onException = mock(Consumer.class);

        changes.start(onItem, onException);

        db.put(new CouchDocument("doc1"));

        verify(onItem, timeout(1000)).accept(any());
    }
}
