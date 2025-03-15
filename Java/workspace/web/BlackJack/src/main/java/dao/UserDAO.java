package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

// 用于访问user表
public class UserDAO {
    protected DatabaseConnection databaseConnection=new DatabaseConnection();
    protected Connection con=null;
    protected PreparedStatement pst=null;
    protected ResultSet rs=null;
    protected String sql=null;
    protected String tableName = "user";

    // 查找用户
    public boolean findUser(String username) {
        con=databaseConnection.getConnection();
        sql= "select * from " + tableName + " where username=?";
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, username);
            rs = pst.executeQuery();
            if (rs.next()) {
                return true;
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }finally {
            databaseConnection.closeConnection(con, pst, rs);
        }
        return false;
    }

    // 查询密码
    public String getPassword(String username) {
        con=databaseConnection.getConnection();
        sql="select password from " + tableName + " where username=?";
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, username);
            rs = pst.executeQuery();
            if (rs.next()) {
                return rs.getString("password");
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }finally {
            databaseConnection.closeConnection(con, pst, rs);
        }
        return null;
    }

    // 添加用户
    public void addUser(String username, String password) {
        con=databaseConnection.getConnection();
        sql="insert into " + tableName + " values(?,?)";
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, username);
            pst.setString(2, password);
            pst.execute();
        }catch (SQLException e) {
            e.printStackTrace();
        }finally {
            databaseConnection.closeConnection(con, pst, rs);
        }
    }

    // 删除用户
    public void removeUser(String username) {
        con=databaseConnection.getConnection();
        sql="delete from " + tableName + " where username=?";
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, username);
            pst.execute();
        }catch (SQLException e) {
            e.printStackTrace();
        }finally {
            databaseConnection.closeConnection(con, pst, rs);
        }
    }
}
