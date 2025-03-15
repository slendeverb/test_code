package dao;

import java.sql.*;

// 用于获得数据库连接
public class DatabaseConnection {
    protected String databaseDriver = "com.mysql.cj.jdbc.Driver";
    protected String databaseName = "blackjack";
    protected String databaseUrl = "jdbc:mysql://localhost:3306/" + databaseName + "?useUnicode=true&characterEncoding=UTF-8&useSSL=false&serverTimezone=UTC";
    protected String databaseUser = "root";
    protected String databasePassword = "040629";

    // 获得数据库连接
    public Connection getConnection() {
        Connection con = null;
        try {
            Class.forName(databaseDriver);
            con = DriverManager.getConnection(databaseUrl, databaseUser, databasePassword);
        } catch (ClassNotFoundException e) {
            System.out.println("Driver not found");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Connection error");
            e.printStackTrace();
        }
        return con;
    }

    // 关闭数据库连接
    public void closeConnection(Connection con, PreparedStatement pstmt, ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
            if (pstmt != null) {
                pstmt.close();
            }
            if (con != null) {
                con.close();
            }
        }catch (SQLException e) {
            System.out.println("Error closing connection");
            e.printStackTrace();
        }
    }
}
