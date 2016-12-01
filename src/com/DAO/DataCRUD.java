package com.DAO;

import com.DAO.DBUtil;

import java.sql.*;


/**
 * Created by lewis on 2016/11/30.
 * 本类提供数据的增删改查
 *
 */
public class DataCRUD {
    private Connection connection = null;                           //数据库的连接（会话）
    private Statement statement = null;                             //用于执行静态 SQL 语句并返回它所生成结果的对象
    private ResultSet resultSet = null;                             //表示数据库结果集的数据表
    private PreparedStatement preparedStatement = null;             //表示预编译的 SQL 语句的对象。


    public void Create(int secore ,String playerName){                               //增加
        String sql = "insert into rank(secore,playername) values(?,?);";
        connection = new DBUtil().openConnection();                                 //建立连接
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,secore);                     //填充参数
            preparedStatement.setString(2,playerName);
            preparedStatement.execute();                                            // 执行
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ReleaseConnection();
        }
    }

    public void Query(String playerName) {                                           //读取查询
        String sql = "select * from rank where playername = ?";
    }

    public void Update(int secore , String playerName){                              //更新
        String sql = "";
    }

    public void Delete(String playerName){                                            //删除
        String sql = "";
    }

    public void ReleaseConnection() {                                                 //释放数据链接
        try {
            if(connection!=null) {
                connection.close();
                connection = null;
            }
            if(statement!=null){
                statement.close();
                statement = null;
            }
            if(resultSet!=null){
                resultSet.close();
                resultSet = null;
            }
            if(preparedStatement!=null){
                preparedStatement.close();
                preparedStatement = null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String []args){
         new DataCRUD().Create(0,"admin");
    }
}
