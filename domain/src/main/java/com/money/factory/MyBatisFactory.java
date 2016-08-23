package com.money.factory;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;

/**
 * mybatis 工厂类
 * date: 2016/8/19
 *
 * @author maofagui
 * @version 1.0
 */
public class MyBatisFactory {
    private static final String CONFIG_FILE = "ibatis-config.xml";

    private static final ThreadLocal<SqlSession> threadLocal = new ThreadLocal<SqlSession>();
    // 读取配置文件的类
    private static InputStream inputStream;
    // 用来建立连接的，该类就是连接池，使用单例设计模式
    private static SqlSessionFactory sqlsessionFactory;

    static {
        try {
            // 加载配置文件到内存中
            inputStream = Resources.getResourceAsStream(CONFIG_FILE);
            // 建立连接池以及里面的连接
            sqlsessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        } catch (Exception e) {
            System.out.println("Failed to create SessionFactory"+e);
        }
    }

    private static void initFactory() {
        try {
            // 加载配置文件到内存中
            inputStream = Resources.getResourceAsStream(CONFIG_FILE);
            // 建立连接池以及里面的连接
                sqlsessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        } catch (Exception e) {
            System.out.println("Failed to create SessionFactory" + e);
        }
    }

    private MyBatisFactory() {
    }

    public static SqlSession getSession() {
        // 先从ThreadLocal中取得连接。
        SqlSession session = (SqlSession) threadLocal.get();

        // 如果手头没有连接，则取得一个新的连接
        if (session == null) {
            if (sqlsessionFactory == null) {
                System.out.println("初始化 sqlsessionFactory.");
                initFactory();
            }
            session = sqlsessionFactory.openSession();
            // 把取得出的连接记录到ThreadLocal中，以便下次使用。
            threadLocal.set(session);
        }
        return session;
    }

    /**
     * 连接关闭的方法
     */
    public static void closeSession() {
        SqlSession session = (SqlSession) threadLocal.get();
        // 将ThreadLocal清空，表示当前线程已经没有连接。
        threadLocal.set(null);
        // 连接放回到连接池
        if (session != null) {
            session.close();
        }
    }

}
