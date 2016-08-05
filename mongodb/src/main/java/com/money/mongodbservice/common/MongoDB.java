package com.money.mongodbservice.common;

import com.money.common.util.Config;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.money.common.util.LogHelper;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.List;

/**
 * java连接mongodb
 * date: 2016/7/2
 *
 * @author maofagui
 * @version 1.0
 */
public class MongoDB {
    MongoClient mongoClient = null;
    MongoDatabase mongoDatabase = null;

    /**
     * 使用身份验证连接DB
     */
    public MongoDB() {
        String address = Config.getConfig("mongodb.ip");
        int port = Config.getConfig("mongodb.port", 27017);
        String username = Config.getConfig("mongodb.username");
        String password = Config.getConfig("mongodb.password");
        String database = Config.getConfig("mongodb.database");
        ServerAddress serverAddress = new ServerAddress(address, port);
        List<ServerAddress> addrs = new ArrayList<ServerAddress>();
        addrs.add(serverAddress);

        //MongoCredential.createScramSha1Credential()三个参数分别为 用户名 数据库名称 密码
        MongoCredential credential = MongoCredential.createScramSha1Credential(
                username, database, password.toCharArray());
        List<MongoCredential> credentials = new ArrayList<MongoCredential>();
        credentials.add(credential);

        //通过连接认证获取MongoDB连接
        this.mongoClient = new MongoClient(addrs, credentials);

        //连接到数据库
        this.mongoDatabase = mongoClient.getDatabase(database);
        LogHelper.info("Connect to database successfully");
    }

    /**
     * 不使用身份验证连接DB
     *
     * @param address
     * @param port
     * @param database
     */
    public MongoDB(String address, int port, String database) {
        mongoClient = new MongoClient(address, port);
        mongoDatabase = mongoClient.getDatabase(database);
    }


    public MongoDB(String address, int port, String database, String username, String password) {
        mongoClient = new MongoClient(address, port);
        mongoDatabase = mongoClient.getDatabase(database);

        ServerAddress serverAddress = new ServerAddress(address, port);
        List<ServerAddress> addrs = new ArrayList<ServerAddress>();
        addrs.add(serverAddress);

        MongoCredential credential = MongoCredential.createScramSha1Credential(
                username, database, password.toCharArray());
        List<MongoCredential> credentials = new ArrayList<MongoCredential>();
        credentials.add(credential);

        //通过连接认证获取MongoDB连接
        this.mongoClient = new MongoClient(addrs, credentials);
        //连接到数据库
        this.mongoDatabase = mongoClient.getDatabase(database);
        LogHelper.info("Connect to database successfully");
    }

    /**
     * 创建文档集合
     *
     * @param collectionName
     * @return
     */
    public int createCollection(String collectionName) {
        mongoDatabase.createCollection(collectionName);
        return 0;
    }

    /**
     * 获取文档集合
     *
     * @param collectionName
     * @return
     */
    public MongoCollection<Document> getCollection(String collectionName) throws Exception {
        return mongoDatabase.getCollection(collectionName);
    }


    /**
     * 返回符合集合中的文档
     *
     * @param collectionName
     * @return
     */
    public List<Document> getDocuments(String collectionName) throws Exception {
        MongoCollection<Document> collection = mongoDatabase.getCollection(collectionName);
        FindIterable<Document> findIterable = collection.find();
        MongoCursor<Document> mongoCursor = findIterable.iterator();
        List<Document> res = new ArrayList<Document>();
        while (mongoCursor.hasNext()) {
            res.add(mongoCursor.next());
        }
        return res;
    }

    /**
     * 插入单个文档
     *
     * @param collectionName
     * @param document
     */
    public void insertOne(String collectionName, Document document) throws Exception {
        MongoCollection<Document> collection = mongoDatabase.getCollection(collectionName);
        collection.insertOne(document);
    }

    /**
     * 将文档集合插入数据库集合中
     *
     * @param collectionName
     * @param documents
     */
    public void insertMany(String collectionName, List<Document> documents) throws Exception {
        MongoCollection<Document> collection = mongoDatabase.getCollection(collectionName);
        collection.insertMany(documents);
    }

    /**
     * 返回符合条件的第一个文档
     *
     * @param collectionName
     * @param fieldName
     * @param value
     * @param <TItem>
     * @return
     */
    public <TItem> Document findOne(String collectionName, String fieldName, TItem value) throws Exception {
        MongoCollection<Document> collection = mongoDatabase.getCollection(collectionName);

        FindIterable<Document> findIterable = collection.find(Filters.eq(fieldName, value));
        MongoCursor<Document> mongoCursor = findIterable.iterator();
        while (mongoCursor.hasNext()) {
            return mongoCursor.next();
        }
        return null;
    }

    /**
     * 返回符合条件的所有文档
     *
     * @param collectionName
     * @param fieldName
     * @param value
     * @param <TItem>
     * @return
     */
    public <TItem> List<Document> findAll(String collectionName, String fieldName, TItem value) throws Exception {
        MongoCollection<Document> collection = mongoDatabase.getCollection(collectionName);
        FindIterable<Document> findIterable = collection.find(Filters.eq(fieldName, value));
        MongoCursor<Document> mongoCursor = findIterable.iterator();
        List<Document> res = new ArrayList<Document>();
        while (mongoCursor.hasNext()) {
            res.add(mongoCursor.next());
        }
        return res;
    }

    /**
     * 返回符合条件的所有文档
     *
     * @param collectionName
     * @param bson           查询条件
     * @return
     */
    public List<Document> findAll(String collectionName, Bson bson) throws Exception {
        MongoCollection<Document> collection = mongoDatabase.getCollection(collectionName);
        FindIterable<Document> findIterable = collection.find(bson);
        MongoCursor<Document> mongoCursor = findIterable.iterator();
        List<Document> res = new ArrayList<Document>();
        while (mongoCursor.hasNext()) {
            res.add(mongoCursor.next());
        }
        return res;
    }

    /**
     * 返回符合条件的所有文档, 可分页
     *
     * @param collectionName
     * @param bson
     * @param pageSize
     * @param pageIndex
     * @return
     */
    public List<Document> findAll(String collectionName, Bson bson, int pageSize, int pageIndex) throws Exception {
        MongoCollection<Document> collection = mongoDatabase.getCollection(collectionName);

        FindIterable<Document> findIterable = collection.find(bson).limit(pageSize).skip(pageIndex);
        MongoCursor<Document> mongoCursor = findIterable.iterator();
        List<Document> res = new ArrayList<Document>();
        while (mongoCursor.hasNext()) {
            res.add(mongoCursor.next());
        }
        return res;
    }

    /**
     * 返回查询数量
     *
     * @param collectionName
     * @return
     */
    public long count(String collectionName) {
        return mongoDatabase.getCollection(collectionName).count();
    }

    /**
     * 返回查询数量
     *
     * @param collectionName
     * @param bson           查询条件
     * @return
     */
    public long count(String collectionName, Bson bson) throws Exception {
        return mongoDatabase.getCollection(collectionName).count(bson);
    }

    /**
     * 更新符合条件的单个文档 (若出错使用replaceOne, 3.0以前，使用updateOne，3.0以后使用replaceOne)
     *
     * @param collectionName
     * @param bson
     * @param updateBson
     */
    @Deprecated
    public void updateOne(String collectionName, Bson bson, Bson updateBson) throws Exception {
        MongoCollection<Document> collection = mongoDatabase.getCollection(collectionName);
        collection.updateOne(bson, updateBson);
    }

    /**
     * 更新符合条件的单个文档
     *
     * @param collectionName
     * @param bson
     * @param updateDoc
     */
    public void replaceOne(String collectionName, Bson bson, Document updateDoc) throws Exception {
        MongoCollection<Document> collection = mongoDatabase.getCollection(collectionName);
        collection.replaceOne(bson, updateDoc);
    }

    /**
     * 更新符合条件的多个文档
     *
     * @param collectionName
     * @param bson
     * @param updateBson
     */
    public void updateMany(String collectionName, Bson bson, Bson updateBson) throws Exception {
        MongoCollection<Document> collection = mongoDatabase.getCollection(collectionName);
        collection.updateMany(bson, updateBson);
    }

    /**
     * 删除符合条件的第一个文档
     *
     * @param collectionName
     * @param bson
     */
    public void deleteOne(String collectionName, Bson bson) throws Exception {
        MongoCollection<Document> collection = mongoDatabase.getCollection(collectionName);
        collection.deleteOne(bson);
    }

    /**
     * 删除所有符合条件的文档
     *
     * @param collectionName
     * @param bson
     */
    public void deleteMany(String collectionName, Bson bson) throws Exception {
        MongoCollection<Document> collection = mongoDatabase.getCollection(collectionName);
        collection.deleteMany(bson);
    }

    /**
     * 查找第一个并替换
     *
     * @param collectionName
     * @param fieldName
     * @param value
     * @param document1
     * @param <TItem>
     * @return
     */

    public <TItem> Document findOneAndReplace(String collectionName, String fieldName, TItem value, Document document1)
            throws Exception {
        MongoCollection<Document> collection = mongoDatabase.getCollection(collectionName);
        return collection.findOneAndReplace(Filters.eq(fieldName, value), document1);
    }

    /**
     * equal 等于
     *
     * @param fieldName
     * @param value
     * @param <TItem>
     * @return
     */
    public static <TItem> Bson eq(String fieldName, TItem value) {
        return Filters.eq(fieldName, value);
    }

    /**
     * 存在字段
     *
     * @param fieldName
     * @return
     */
    public static Bson exists(String fieldName) {
        return Filters.exists(fieldName);
    }

    /**
     * greater 大于
     *
     * @param fieldName
     * @param <TItem>
     * @return
     */
    public static <TItem> Bson gt(String fieldName, TItem value) {
        return Filters.gt(fieldName, value);
    }

    /**
     * greater or equal 大于或等于
     *
     * @param fieldName
     * @param <TItem>
     * @return
     */
    public static <TItem> Bson gte(String fieldName, TItem value) {
        return Filters.gte(fieldName, value);
    }

    /**
     * less 小于
     *
     * @param fieldName
     * @param <TItem>
     * @return
     */
    public static <TItem> Bson lt(String fieldName, TItem value) {
        return Filters.lt(fieldName, value);
    }

    /**
     * less or equal 小于或等于
     *
     * @param fieldName
     * @param <TItem>
     * @return
     */
    public static <TItem> Bson lte(String fieldName, TItem value) {
        return Filters.lte(fieldName, value);
    }
}
