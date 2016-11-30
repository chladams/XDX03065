package com.DAO;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by lewis on 2016/11/30.
 *
 * 本类主要提供数据库的连接
 */
public class DBUtil {
    private static String driver;                       //驱动jar包路径
    private static String url;                          //数据库端口及url路径
    private static String dbName;                       //数据库名称
    private static String dbPassWd;                     //数据库密码

    static {                                            //静态代码块，初始化数据变量
        Properties properties = new Properties();       //实例化一个Properties类，保存和读取配置文件的键值对
        Reader in =null;                                //通过Reader接口实例化一个文件字符流
        try {
            in =new FileReader("config.properties");
            properties.load(in);                        //按简单的面向行的格式从输入字符流中读取属性列表（键和元素对）。
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {                             //关闭文件字符流，if判断防止NullPointerException
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
                                                            //Properties 继承于 Hashtable,类似键值对取值
        driver = properties.getProperty("driver");
        url=properties.getProperty("url");
        dbName=properties.getProperty("dbName");
        dbPassWd=properties.getProperty("dbPassWd");
    }

    public Connection openConnection(){                     //获得一个数据库的链接
        Connection connection = null;                       //定义接口connection，作为引用结果返回
        try {
            Class.forName(driver);                          //注册的 JDBC 驱动程序
            connection = DriverManager.getConnection(url,dbName,dbPassWd);//建立制定url的数据库链接
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
