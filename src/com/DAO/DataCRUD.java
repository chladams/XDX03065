package com.DAO;

import com.Crawl.Result;

import java.sql.*;

import static java.lang.System.out;

/**
 * Created by lewis on 2016/11/30.
 * 本类提供数据的增改查
 *
 */
public class DataCRUD {
    private static Connection connection = null;                           //数据库的连接（会话）
    private static ResultSet resultSet = null;                             //表示数据库结果集的数据表
    private static PreparedStatement preparedStatement = null;             //表示预编译的 SQL 语句的对象。


    public static void Create(String title,String context){                               //增加
        String sql = "insert into text(title,context) values(?,?);";
        connection = new DBUtil().openConnection();                                 //建立连接
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,title);                     //填充参数
            preparedStatement.setString(2,context);
            preparedStatement.execute();                                            // 执行
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ReleaseConnection();
        }
    }

    public static ResultSet Query() {                                                  //读取查询
        String sql = " SELECT * FROM text;";                                          //按照分数大小降序排列
        connection = new DBUtil().openConnection();
        try {
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            return resultSet;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }


    public static void ReleaseConnection() {                                                 //释放数据链接
        try {
            if(connection!=null) {
                connection.close();
                connection = null;
            }
            if(preparedStatement!=null){
                preparedStatement.close();
                preparedStatement = null;
            }
            if(resultSet!=null){
                resultSet.close();
                resultSet=null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void persistent(Result result){
        Create(result.getTitle(),result.getContext());
    }

    public static void main(String []args){
//      1.test Create
//       new DataCRUD().Create(444,"h");

//      2.test Update
//        Update(10000,"a");

//       3. test Query
       ResultSet rs = new DataCRUD().Query();
        try {
            while(rs.next()){
                String s = rs.getString(1);
                String n = rs.getString(2);
                out.printf("%s %s\n",s,n);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ReleaseConnection();
        }
    }
}
