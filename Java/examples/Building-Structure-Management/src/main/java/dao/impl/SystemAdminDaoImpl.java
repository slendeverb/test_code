package dao.impl;

import dao.SystemAdminDao;
import entity.SystemAdmin;
import util.JDBCUtil;

import java.sql.*;

public class SystemAdminDaoImpl implements SystemAdminDao {

    @Override
    public SystemAdmin findByUsername(String username) throws SQLException {

        Connection connection= JDBCUtil.getConnection();
        String sql ="select * from admin where email = '"+username+"'";
        Statement statement=connection.createStatement();
        ResultSet resultSet=null;
        resultSet=statement.executeQuery(sql);

        if (resultSet.next()){
            username=resultSet.getString(1);
            String password=resultSet.getString(2);

            return new SystemAdmin(username,password);

        }

        JDBCUtil.release(connection,statement,resultSet);

        return null;
    }


    @Override
    public SystemAdmin insertNewUser(String username, String password) throws SQLException {

        Connection connection = JDBCUtil.getConnection();
        Integer result = null;
        String sql="insert into admin (email,password) values (?,?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, username);
        statement.setString(2, password);
        result = statement.executeUpdate();

        JDBCUtil.release(connection, statement, null);

        return new SystemAdmin(username,password);

    }

}
//insert into SYSDBA.\"building\"(\"location\",\"buildingname\",\"structure\",\"term\",\"type\") values(?,?,?,?,?)
