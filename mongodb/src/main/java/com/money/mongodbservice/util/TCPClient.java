package com.money.mongodbservice.util;

import java.io.*;
import java.net.Socket;
import java.net.SocketTimeoutException;

/**
 * [Description]
 * <p/>
 * date: 2016/7/4
 *
 * @author maofagui
 * @version 1.0
 */
public class TCPClient {
    public static void main(String[] args) {
        //TODO
    }
    public static String sendRequest(String ip, int port, String cmdStr) throws Exception {
        //客户端请求与本机在20006端口建立TCP连接
        Socket client = new Socket(ip, port);
        client.setSoTimeout(10000);
        //获取键盘输入
        //BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader input = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(cmdStr.getBytes())));
        //获取Socket的输出流，用来发送数据到服务端
        PrintStream out = new PrintStream(client.getOutputStream());
        //获取Socket的输入流，用来接收从服务端发送过来的数据
        BufferedReader buf =  new BufferedReader(new InputStreamReader(client.getInputStream()));
        boolean flag = true;
        String res="";
        while(flag){
            System.out.print("输入信息：");
            String str = input.readLine();
            System.out.println(str);
            //发送数据到服务端
            out.println(str);
            if("bye".equals(str)){
                flag = false;
            }else{
                try{
                    //从服务器端接收数据有个时间限制（系统自设，也可以自己设置），超过了这个时间，便会抛出该异常
                    String echo = buf.readLine();
                    res += echo;
                    System.out.println(echo);
                }catch(SocketTimeoutException e){
                    System.out.println("Time out, No response");
                }
            }
            break;
        }
        input.close();
        if(client != null){
            //如果构造函数建立起了连接，则关闭套接字，如果没有建立起连接，自然不用关闭
            client.close(); //只关闭socket，其关联的输入输出流也会被关闭
        }
        return res;
    }
}
