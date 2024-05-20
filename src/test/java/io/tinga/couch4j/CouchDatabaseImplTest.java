package io.tinga.couch4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.github.javafaker.Faker;

import io.tinga.couch4j.dto.CouchDocument;
import io.tinga.couch4j.exception.CouchConflictException;
import io.tinga.couch4j.exception.CouchException;
import io.tinga.couch4j.exception.CouchNotFoundException;

class BasicCouchDoc extends CouchDocument {
    private String s1;
    private int i2;
    private boolean b3;
    private List<Integer> l4;

    BasicCouchDoc() {
    }

    BasicCouchDoc(String id) {
        super(id);
    }

    public String getS1() {
        return s1;
    }

    public void setS1(String s1) {
        this.s1 = s1;
    }

    public int getI2() {
        return i2;
    }

    public void setI2(int i2) {
        this.i2 = i2;
    }

    public boolean isB3() {
        return b3;
    }

    public void setB3(boolean b3) {
        this.b3 = b3;
    }

    public List<Integer> getL4() {
        return l4;
    }

    public void setL4(List<Integer> l4) {
        this.l4 = l4;
    }
}

class CouchDatabaseImplTest {
    Faker faker = new Faker();
    CouchServer server = new CouchBuilder()
            .setUri("http://localhost:5984")
            .setBasicAuth("admin", "admin")
            .build();
    CouchDatabase db;

    List<BasicCouchDoc> getRandomDocs(int howMany) {
        List<BasicCouchDoc> result = new ArrayList<>();
        for (int i = 0; i < howMany; i++) {
            result.add(new BasicCouchDoc(faker.random().hex(20)));
        }

        return result;
    }

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
    void testDocNotFound() {
        Assertions.assertThrows(CouchNotFoundException.class, () -> {
            db.get(faker.backToTheFuture().character());
        });
    }

    @Test
    void testUpdateConflict() throws CouchException {
        CouchDocument doc = new CouchDocument(faker.backToTheFuture().character());
        db.put(doc);
        Assertions.assertThrows(CouchConflictException.class, () -> {
            db.put(new CouchDocument(doc.getId()));
        });
    }

    @Test
    void testCrudOperations() throws CouchException {
        BasicCouchDoc doc = new BasicCouchDoc();
        doc.setS1(faker.backToTheFuture().character());

        db.put(doc);
        Assertions.assertNotNull(doc.getId());
        Assertions.assertNotNull(doc.getRev());

        BasicCouchDoc doc2 = db.get(doc.getId(), BasicCouchDoc.class);
        Assertions.assertEquals(doc.getRev(), doc2.getRev());
        Assertions.assertEquals(doc.getS1(), doc2.getS1());

        doc2.setS1(faker.hacker().adjective());
        db.put(doc2);
        Assertions.assertNotEquals(doc.getRev(), doc2.getRev());

        db.delete(doc2);
        Assertions.assertTrue(doc2.getDeleted());
    }

    @Test
    void testBulkGet() throws CouchException {
        List<BasicCouchDoc> docs = getRandomDocs(10);
        for (BasicCouchDoc doc : docs) {
            db.put(doc);
        }

        List<String> ids = new ArrayList<>(docs.stream()
                .map(doc -> {
                    return doc.getId();
                })
                .toList());
        String fakeId = faker.random().hex(20);
        ids.add(fakeId);
        Map<String, BasicCouchDoc> gotDocs = db.getMany(ids, BasicCouchDoc.class);

        for (BasicCouchDoc doc : docs) {
            Assertions.assertEquals(doc.getId(), gotDocs.get(doc.getId()).getId());
            Assertions.assertEquals(doc.getRev(), gotDocs.get(doc.getId()).getRev());
            Assertions.assertNull(gotDocs.get(doc.getId()).getS1());
            Assertions.assertEquals(gotDocs.get(doc.getId()).getI2(), 0);
        }

        Assertions.assertNull(gotDocs.get(fakeId));
    }

    @Test
    void testBulkUpdate() throws CouchException {
        List<BasicCouchDoc> docs = getRandomDocs(10);
        db.putMany(docs);

        for (BasicCouchDoc doc : docs) {
            Assertions.assertNotNull(doc.getRev());
        }

        docs.getFirst().setDeleted(true);
        db.putMany(docs);

        Assertions.assertThrows(CouchNotFoundException.class, () -> {
            db.get(docs.getFirst());
        });
    }

    @Test
    void testAttachments() throws CouchException {
        byte[] content = new byte[] { 1, 2, 3, 4, 5, 6 };
        CouchDocument doc = new CouchDocument();
        db.put(doc);

        String oldRev = doc.getRev();
        String attName = faker.file().fileName();
        db.putAttachment(doc, attName, content);
        Assertions.assertNotEquals(doc.getRev(), oldRev, "shall update doc revision");

        byte[] newContent = db.getAttachment(doc, attName);
        Assertions.assertArrayEquals(newContent, content);

        db.deleteAttachment(doc, attName);
        Assertions.assertThrows(CouchNotFoundException.class, () -> {
            db.getAttachment(doc, attName);
        });
    }
}
