import com.money.mongodbservice.common.MongoDB;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * MongoDB Tester.
 *
 * @author maofagui
 * @version 1.0
 * @since 2016年7月2日
 */
public class MongoDBTest {
    //    MongoDB mongoDB = new MongoDB("127.0.0.1", 27017, "db1");
    MongoDB mongoDB = new MongoDB("127.0.0.1", 27017, "foo",
            "qidian", "qidian");

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: createCollection(String str)
     */
    @Test
    public void testCreateCollection() throws Exception {
        int res = mongoDB.createCollection("joke");
        System.out.println("创建集合：" + res);
    }

    /**
     * Method: getCollection(String str)
     */
    @Test
    public void testGetCollection() throws Exception {
        MongoCollection<Document> res = mongoDB.getCollection("joke");
        System.out.println("获取集合:" + res);
    }

    /**
     * Method: insertMany(String collectionName, List<Document> documents)
     */
    @Test
    public void testInsertMany() throws Exception {

    }

    /**
     * Method: insertOne(String collectionName, Document document)
     */
    @Test
    public void testInsertOne() throws Exception {
        Document document = new Document("title", "MongoDB").
                append("description", "database2").
                append("likes", 100).
                append("by", "Fly");
        mongoDB.insertOne("joke", document);
    }

    /**
     * Method: updateMany(String collectionName, Document document1, Document document2)
     */
    @Test
    public void testUpdateMany() throws Exception {
    }

    /**
     * Method: updateOne(String collectionName, Document document1, Document document2)
     */
    @Test
    public void testUpdateOne() throws Exception {
    }

    /**
     * Method: getDocuments(String collectionName)
     */
    @Test
    public void testGetDocuments() throws Exception {
        List<Document> docs = mongoDB.getDocuments("joke");
        for (Document doc : docs)
            System.out.println(doc);
    }

    /**
     * Method: findOne(String collectionName, String fieldName, TItem value)
     */
    @Test
    public void testFindOne() throws Exception {
        Document doc = mongoDB.findOne("joke", "likes", 100);
        System.out.println(doc);
    }

    /**
     * Method: findAll(String collectionName, String fieldName, TItem value)
     */
    @Test
    public void testFindAll() throws Exception {
        List<Document> docs = mongoDB.findAll("joke", "likes", 100);
        for (Document doc : docs) {
            System.out.println(doc);
        }
    }

    @Test
    public void testFindAll2() throws Exception {
        List<Document> docs = mongoDB.findAll("joke2", new Document());
        System.out.println(docs);
        for (Document doc : docs) {
            System.out.println(doc.toJson());
        }
    }

    /**
     * Method: deleteOne(String collectionName, Bson bson)
     */
    @Test
    public void testDeleteOne() throws Exception {
    }

    /**
     * Method: deleteMany(String collectionName, Bson bson)
     */
    @Test
    public void testDeleteMany() throws Exception {
    }

    @Test
    public void testFindOneAndReplace() throws Exception {
        Document document = new Document("title", "MongoDB2").
                append("description", "database2").
                append("likes", 200).
                append("by", "Fly2");

        mongoDB.insertOne("joke2", document);

        MongoCollection<Document> collection = mongoDB.getCollection("joke2");
        System.out.println("collection size:" + collection.count());

        Document document3 = new Document("title", "MongoDB3").
                append("description", "database3").
                append("likes", 300).
                append("by", "Fly3");

        mongoDB.findOneAndReplace("joke2", "likes", 200, document3);

        List<Document> docs = mongoDB.getDocuments("joke2");
        System.out.println("size:" + docs.size());
        for (Document doc : docs)
            System.out.println(doc);

    }

    @Test
    public void testFindAllWithGT() throws Exception {
        List<Document> docs = mongoDB.findAll("joke2", mongoDB.gte("likes", 200));
        for (Document doc : docs)
            System.out.println(doc);
    }

} 
