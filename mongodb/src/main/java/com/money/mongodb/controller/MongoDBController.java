package com.money.mongodb.controller;

import com.money.common.util.LogHelper;
import com.money.mongodb.common.Consts;
import com.money.mongodb.domain.ConnectInfo;
import com.money.mongodb.service.JsonService;
import com.money.mongodbservice.common.MongoDB;
import org.bson.Document;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


@Controller
public class MongoDBController {

    @ResponseBody
    @RequestMapping(value = "demo/insert", produces = "application/json; charset=utf-8")
    public String insert(ConnectInfo info, final Model model,
                         final HttpServletRequest request) {
        String res = "";
        //根据连接信息获取连接
        MongoDB mongoDB = connectMongoDB(info);
        if (null == mongoDB)
            return errorMsg();

        String collectionName = request.getParameter(Consts.COLLECTION_NAME);
        if (null != collectionName) {
            if (null != request.getParameter(Consts.BSON)) {
                try {
                    Document document = Document.parse(request.getParameter(Consts.BSON));
                    mongoDB.insertOne(collectionName, document);
                } catch (Exception e) {
                    return errorMsg(e.toString());
                }
            }
            res = successMsg();
        }
        return res;
    }

    @ResponseBody
    @RequestMapping(value = "demo/delete", produces = "application/json; charset=utf-8")
    public String delete(ConnectInfo info, final Model model,
                         final HttpServletRequest request) {
        String res = "";
        //根据连接信息获取连接
        MongoDB mongoDB = connectMongoDB(info);
        if (null == mongoDB)
            return errorMsg();

        String collectionName = request.getParameter(Consts.COLLECTION_NAME);
        if (null != collectionName) {
            if (null != request.getParameter(Consts.BSON)) {
                try {
                    Document document = Document.parse(request.getParameter(Consts.BSON));
                    mongoDB.deleteOne(collectionName, document);
                } catch (Exception e) {
                    return errorMsg(e.toString());
                }
            }
            res = successMsg();
        }
        return res;
    }

    @ResponseBody
    @RequestMapping(value = "demo/deleteMany", produces = "application/json; charset=utf-8")
    public String deleteMany(ConnectInfo info, final Model model,
                             final HttpServletRequest request) {
        String res = "";
        //根据连接信息获取连接
        MongoDB mongoDB = connectMongoDB(info);
        if (null == mongoDB)
            return errorMsg();

        String collectionName = request.getParameter(Consts.COLLECTION_NAME);
        if (null != collectionName) {
            if (null != request.getParameter(Consts.BSON)) {
                try {
                    Document document = Document.parse(request.getParameter(Consts.BSON));
                    mongoDB.deleteMany(collectionName, document);
                } catch (Exception e) {
                    return errorMsg(e.toString());
                }
            }
            res = successMsg();
        }
        return res;
    }


    @ResponseBody
    @RequestMapping(value = "demo/update", produces = "application/json; charset=utf-8")
    public String update(ConnectInfo info, final Model model,
                         final HttpServletRequest request) {
        String res = "";
        //根据连接信息获取连接
        MongoDB mongoDB = connectMongoDB(info);
        if (null == mongoDB)
            return errorMsg();

        String collectionName = request.getParameter(Consts.COLLECTION_NAME);
        if (null != collectionName) {
            if (null != request.getParameter(Consts.BSON) && null != request.getParameter(Consts.BSON_2)) {
                try {
                    Document document = Document.parse(request.getParameter(Consts.BSON));
                    Document document2 = Document.parse(request.getParameter(Consts.BSON_2));
                    mongoDB.replaceOne(collectionName, document, document2);
                } catch (Exception e) {
                    return errorMsg(e.toString());
                }
            }
            res = successMsg();
        }
        return res;
    }

    @ResponseBody
    @RequestMapping(value = "demo/updateMany", produces = "application/json; charset=utf-8")
    public String updateMany(ConnectInfo info, final Model model,
                             final HttpServletRequest request) {
        String res = "";
        //根据连接信息获取连接
        MongoDB mongoDB = connectMongoDB(info);
        if (null == mongoDB)
            return errorMsg();

        String collectionName = request.getParameter(Consts.COLLECTION_NAME);
        if (null != collectionName) {
            if (null != request.getParameter(Consts.BSON) && null != request.getParameter(Consts.BSON_2)) {
                try {
                    Document document = Document.parse(request.getParameter(Consts.BSON));
                    Document document2 = Document.parse(request.getParameter(Consts.BSON_2));
                    mongoDB.updateMany(collectionName, document, document2);
                } catch (Exception e) {
                    return errorMsg(e.toString());
                }
            }
            res = successMsg();
        }
        return res;
    }

    @ResponseBody
    @RequestMapping(value = "/demo/find", produces = "application/json; charset=utf-8")
    public String find(ConnectInfo info, final Model model,
                       final HttpServletRequest request) {
        String res = "";
        MongoDB mongoDB = connectMongoDB(info);
        if (null == mongoDB)
            return errorMsg("连接数据库失败");

        String collectionName = request.getParameter(Consts.COLLECTION_NAME);
        if (null != collectionName) {
            try {
                Document document = new Document();
                if (null != request.getParameter(Consts.BSON))
                    document = Document.parse(request.getParameter(Consts.BSON));
                res = JsonService.obj2Json(mongoDB.findAll(collectionName, document));
            } catch (IOException e) {
                LogHelper.error("转换成JSON出错：" + e);
            } catch (Exception e) {
                return errorMsg(e.toString());
            }
        }
        return res;
    }

    private String errorMsg() {
        return "{\"error\":-1}";
    }

    private String errorMsg(String str) {
        return "{\"error\":" + str + "}";
    }

    private String successMsg() {
        return "{\"success\":0}";
    }

    private MongoDB connectMongoDB(ConnectInfo info) {
        MongoDB mongoDB = null;
        if (null == info.getUsername() && null == info.getPassword()) {
            mongoDB = new MongoDB(info.getIp(), info.getPort(), info.getDb());
        } else {
            mongoDB = new MongoDB(info.getIp(), info.getPort(), info.getDb(),
                    info.getUsername(), info.getPassword());
        }
        return mongoDB;
    }
}
