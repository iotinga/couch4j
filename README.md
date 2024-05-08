# couch4j

A simple CouchDb client in Java

## Installation

Install the libary with Maven. Add to your pom.xml the following:

```xml
<dependency>
    <groupId>io.tinga.couch4j</groupId>
    <artifactId>couch4j</artifactId>
    <version>0.9.1</version>
</dependency>
```

## Basic usage

```java
class TestCouchDoc extends CouchDocument {
    private String test;

    TestCouchDoc() {
    }

    TestCouchDoc(String id) {
        super(id);
    }

    String getTest() {
        return this.test;
    }

    void setTest(String test) {
        this.test = test;
    }
}

CouchServer server = new CouchBuilder()
  .setUri("http://localhost:5984")
  .setBasicAuth("admin", "password")
  .build();
CouchDatabase db = server.db("test");
db.createDbIfNotExists();

TestCouchDoc doc = new TestCouchDoc();
doc.setTest("TEST");

// put document
db.put(doc);

// get document
TestCouchDoc doc2 = db.get(doc.getId(), TestCouchDoc.class);

// delete document
db.delete(doc);

// see README.md for more examples
```
