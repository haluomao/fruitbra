package com.money.mongodbservice.client;

import com.money.common.util.LogHelper;
import com.money.common.util.SystemUtility;
import com.money.mongodbservice.common.MongoDB;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.HashMap;
import java.util.Map;

/**
 * [Description]
 * <p/>
 * date: 2016/7/4
 *
 * @author maofagui
 * @version 1.0
 */
public class MongoDBClient {


    public static void main(String[] args) throws Exception {

        MongoDBClient pro = new MongoDBClient();

//        System.out.println("增加：");
//        pro.testInsertPost();
//        pro.testFindPost();
//
//        System.out.println("更新：");
//        pro.testUpdatePost();
//        pro.testFindPost();
//
//        System.out.println("删除：");
//        pro.testDeletePost();
        pro.testFindPost();

    }

    public void testUpdatePost() throws Exception {
        String url = "http://localhost:8080/mongodbservice/demo/update";
        StringBuffer stringBuffer = new StringBuffer();
        Map<String, String> map = new HashMap<String, String>();
        map.put("ip", "127.0.0.1");
        map.put("port", "27017");
        map.put("db", "db1");
        map.put("username", "db1");
        map.put("password", "db1");
        map.put("collection", "joke2");

        Document bson = new Document();
        bson.append("likes", 250);
        map.put("bson", bson.toJson());

        Document bson2 = new Document();
        bson2.append("likes", 450);
        map.put("bson2", bson2.toJson());

        String res = SystemUtility.postRequest(url, map);
        System.out.println(res);
    }

    public void testDeletePost() throws Exception {
        String url = "http://localhost:8080/mongodbservice/demo/delete";
        StringBuffer stringBuffer = new StringBuffer();
        Map<String, String> map = new HashMap<String, String>();
        map.put("ip", "127.0.0.1");
        map.put("port", "27017");
        map.put("db", "db1");
        map.put("username", "db1");
        map.put("password", "db1");
        map.put("collection", "joke2");
        Document bson = new Document();
        bson.append("likes", 450);
        map.put("bson", bson.toJson());
        String res = SystemUtility.postRequest(url, map);
        System.out.println(res);
    }

    public void testFindPost() throws Exception {
        String url = "http://localhost:8080/mongodbservice/demo/find";
        StringBuffer stringBuffer = new StringBuffer();
        Map<String, String> map = new HashMap<String, String>();
        map.put("ip", "127.0.0.1");
        map.put("port", "27017");
        map.put("db", "db1");
        map.put("username", "db1");
        map.put("password", "db1");
        map.put("collection", "joke2");
        map.put("collection", "joke2");
        Document bson = new Document();
        bson.append("likes", 300);

        String res = SystemUtility.postRequest(url, map);
        System.out.println(res);
    }

    public void testInsertPost() throws Exception {
        String url = "http://localhost:8080/mongodbservice/demo/insert";
        StringBuffer stringBuffer = new StringBuffer();
        Map<String, String> map = new HashMap<String, String>();
        map.put("ip", "127.0.0.1");
        map.put("port", "27017");
        map.put("db", "db1");
        map.put("username", "db1");
        map.put("password", "db1");
        map.put("collection", "joke2");
        Document bson = new Document();
        bson.append("likes", 250);
        map.put("bson", bson.toJson());
        String res = SystemUtility.postRequest(url, map);
        System.out.println(res);
    }

    public void testFind() throws Exception {
        String url = "http://localhost:8888/mongodbservice/demo/find";
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("ip=").append("127.0.0.1");
        stringBuffer.append("&");
        stringBuffer.append("port=").append(27017);
        stringBuffer.append("&");
        stringBuffer.append("db=").append("db1");
        stringBuffer.append("&");
        stringBuffer.append("collection=").append("joke2");

//        BsonDocument bson = MongoDB.eq("likes", 300);
        Document bson = new Document();
        bson.append("likes", 300);
//        stringBuffer.append("&");
//        stringBuffer.append("bson=").append(bson.toJson().getBytes());

        String paramsStr = stringBuffer.toString();

        System.out.println("paramsStr:" + paramsStr);

        String res = SystemUtility.getRequest(url, paramsStr);

        LogHelper.info("res:{0}", res);
    }


    public void testInsert() throws Exception {
        String url = "http://localhost:8888/mongodbservice/demo/insert";
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("ip=").append("127.0.0.1");
        stringBuffer.append("&");
        stringBuffer.append("port=").append(27017);
        stringBuffer.append("&");
        stringBuffer.append("db=").append("db1");
        stringBuffer.append("&");
        stringBuffer.append("collection=").append("joke2");

        //Bson bson = MongoDB.eq("likes", 300);
        Document document = new Document();
        document.append("some", "any");
        document.append("true", "false");

        System.out.println(document.toJson());

        stringBuffer.append("&");
        stringBuffer.append("bson=").append(document.toJson());

        String paramsStr = stringBuffer.toString();

        System.out.println("paramsStr:" + paramsStr);

        String res = SystemUtility.getRequest(url, paramsStr);

        LogHelper.info("res:{0}", res);
    }

    public void testUpdate() throws Exception {
        String url = "http://localhost:8888/mongodbservice/demo/update";
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("ip=").append("127.0.0.1");
        stringBuffer.append("&");
        stringBuffer.append("port=").append(27017);
        stringBuffer.append("&");
        stringBuffer.append("db=").append("db1");
        stringBuffer.append("&");
        stringBuffer.append("collection=").append("joke2");

        Bson bson = MongoDB.eq("likes", 300);
        stringBuffer.append("&");
        stringBuffer.append("bson=").append(bson);

        String paramsStr = stringBuffer.toString();

        System.out.println("paramsStr:" + paramsStr);

        String res = SystemUtility.getRequest(url, paramsStr);

        LogHelper.info("res:{0}", res);
    }

}
