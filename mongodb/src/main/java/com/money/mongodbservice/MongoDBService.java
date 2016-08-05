package com.money.mongodbservice;

import com.money.common.util.LogHelper;
import com.money.mongodbservice.server.MongoDBServer;

import java.io.IOException;

/**
 * date: 2016/7/2
 *
 * @author maofagui
 * @version 1.0
 */
public class MongoDBService {

    public static void main(String[] args) {
        try {
            int port = 2000;
            new MongoDBServer(port);
            System.out.println("已启动端口监听:" + port);
        } catch (IOException e) {
            LogHelper.error("MongoDBService 运行错误：" + e);
        }
    }
}
