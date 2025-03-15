package util;

import java.sql.*;

public class JDBCUtil {

    private static String url="jdbc:mysql://localhost:3306/bsmm";

    private static String pwd="040629";
    private static String username="root";


    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    
    public static Connection getConnection() throws SQLException {
    
        Connection connection= DriverManager.getConnection(url,username,pwd);
    
        return connection;
    }
    

    public static void release(Connection connection, Statement statement, ResultSet resultSet) throws SQLException {
        if (connection != null) {
            connection.close();
        }

        if (statement != null) {
            statement.close();
        }

        if (resultSet != null) {
            resultSet.close();
        }




    }


}
