package com.money.mongodb.domain;

import java.io.Serializable;

/**
 * 连接信息
 * date: 2016/7/5
 *
 * @author maofagui
 * @version 1.0
 */
public class ConnectInfo implements Serializable{
    private String ip;
    private int port;
    private String db;
    private String username;
    private String password;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getDb() {
        return db;
    }

    public void setDb(String db) {
        this.db = db;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "ConnectInfo [" +
                "ip='" + ip + '\'' +
                ", port=" + port +
                ", db='" + db + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ']';
    }
}
