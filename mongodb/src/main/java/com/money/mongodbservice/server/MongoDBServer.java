package com.money.mongodbservice.server;

import com.money.common.util.LogHelper;
import com.money.mongodbservice.common.MongoDB;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 监控程序
 * date: 2016/7/4
 * @author maofagui
 * @version 1.0
 */
public class MongoDBServer extends ServerSocket {
    MongoDB mongoDB = new MongoDB("127.0.0.1", 27017, "db1");

    public MongoDBServer(int port) throws IOException {
        super(port);
        try {
            while (true) {
                //监听一端口，等待客户接入
                Socket socket = accept();
                //将会话交给线程处理
                new ServerThread(socket);

            }
        } catch (IOException e)	{
            LogHelper.error("监控程序出错："+e);
        }
        finally {
            //关闭监听端口
            close();
        }
    }


    class ServerThread extends Thread {
        private Socket socket;
        private BufferedReader in;
        private PrintWriter out;

        // 准备转接
        public ServerThread(Socket s) throws IOException {
            this.socket = s;
            // 构造该会话中的输入输出流
            in = new BufferedReader(new InputStreamReader(
                    socket.getInputStream(), "UTF-8"));
            out = new PrintWriter(socket.getOutputStream(), true);
            start();
        }

        // 执行转接
        public void run() {
            try {
                while (true) {
                    // 通过输入流接收客户端信息
                    String line = in.readLine();
                    while(null == line){
                        sleep(100);
                        continue;
                    }

                    System.out.println("Received message:" + line);
                    String msg = "'" + line + "'has been accepted by server.";

                    // 是否终止会话
                    if ("bye".equals(line)) {
                        break;
                    }
                    if (line.startsWith("get ")){
                        String collectionName=line.split(" ")[1];
//                        msg = mongoDB.getDocuments(collectionName).toString();
                    }

                    // 通过输出流向客户端发送信息
                    out.println(msg);
                    out.flush();
                }

                out.close();
                in.close();
                socket.close();

            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    // main method
    public static void main(String[] args) throws IOException {
        try {
            int port = 2000;
            new MongoDBServer(port);
            System.out.println("已启动端口监听:" + port);
        } catch (IOException e) {
            LogHelper.error("MongoDBService 运行错误：" + e);
        }
    }

}
