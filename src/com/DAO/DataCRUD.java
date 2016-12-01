package com.DAO;

import com.DAO.DBUtil;

import java.sql.*;

import static java.lang.System.out;


/**
 * Created by lewis on 2016/11/30.
 * 本类提供数据的增删改查
 *
 */
public class DataCRUD {
    private static Connection connection = null;                           //数据库的连接（会话）
    private static ResultSet resultSet = null;                             //表示数据库结果集的数据表
    private static PreparedStatement preparedStatement = null;             //表示预编译的 SQL 语句的对象。


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

    public ResultSet Query() {                                                  //读取查询
        String sql = "SELECT * FROM rank ORDER BY secore DESC;";             //按照分数大小降序排列
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

    public static void Update(int secore , String playerName){                              //更新
        String sql = "";
    }

    public static void Delete(String playerName){                                            //删除
        String sql = "";
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

    public static void main(String []args){

//      1.test Create
//       new DataCRUD().Create(444,"h");

//       2. test Query
       ResultSet rs = new DataCRUD().Query();
        try {
            while(rs.next()){
                int s = rs.getInt(1);
                String n = rs.getString(2);
                out.printf("%d %s\n",s,n);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ReleaseConnection();
        }



    }
}
