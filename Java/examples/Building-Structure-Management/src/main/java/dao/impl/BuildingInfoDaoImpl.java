package dao.impl;

import dao.BuildingInfoDao;
import entity.BuildingInfo;
import entity.SystemAdmin;
import util.JDBCUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BuildingInfoDaoImpl implements BuildingInfoDao {

    @Override
    public List<BuildingInfo> list() throws SQLException {

        Connection connection= JDBCUtil.getConnection();
        String sql ="select * from building";
        Statement statement=connection.createStatement();
        ResultSet resultSet=null;
        resultSet=statement.executeQuery(sql);
        List<BuildingInfo> list=new ArrayList<>();
        while (resultSet.next()){
            int id =resultSet.getInt(1);
            String buildingLocation=resultSet.getString(2);
            String buildingName=resultSet.getString(3);
            String buildingStructure=resultSet.getString(4);
            String buildingTerm=resultSet.getString(5);
            String buildingType=resultSet.getString(6);
            list.add(new BuildingInfo(id,buildingName,buildingLocation,buildingType,buildingStructure,buildingTerm));

        }
        JDBCUtil.release(connection,statement,resultSet);


        return list;
    }

    @Override
    public List<BuildingInfo> search(String key, String value) throws SQLException {
        Connection connection= JDBCUtil.getConnection();
        String sql ="select * from building where "+key+" like '%"+value+"%'";
        PreparedStatement statement=connection.prepareStatement(sql);
        ResultSet resultSet=null;
        List<BuildingInfo> list=new ArrayList<>();
        resultSet=statement.executeQuery();
        while (resultSet.next()){
            int id=resultSet.getInt(1);
            String buildingLocation=resultSet.getString(2);
            String buildingName=resultSet.getString(3);
            String buildingStructure=resultSet.getString(4);
            String buildingTerm=resultSet.getString(5);
            String buildingType=resultSet.getString(6);
            list.add(new BuildingInfo(id,buildingName,buildingLocation,buildingType,buildingStructure,buildingTerm));

        }
        JDBCUtil.release(connection,statement,resultSet);


        return list;
    }

    @Override
    public Integer save(BuildingInfo buildingInfo) throws SQLException {


        Connection connection= JDBCUtil.getConnection();
        String sql ="insert into building(location,buildingname,structure,term,type) values(?,?,?,?,?)";
        Integer result=null;
        PreparedStatement statement=connection.prepareStatement(sql);
        statement.setString(1,buildingInfo.getBuildingLocation());
        statement.setString(2,buildingInfo.getBuildingName());
        statement.setString(3,buildingInfo.getBuildingStructure());
        statement.setString(4,buildingInfo.getBuildingTerm());
        statement.setString(5,buildingInfo.getBuildingType());
        result=statement.executeUpdate();
        JDBCUtil.release(connection,statement,null);
        return result;
    }

    @Override
    public Integer update(BuildingInfo buildingInfo) throws SQLException {

        Connection connection= JDBCUtil.getConnection();
        String sql ="update building set location=?,buildingname=?,structure=?,term=?,type=? where id=?";
        Integer result=null;
        PreparedStatement statement=connection.prepareStatement(sql);
        statement.setString(1,buildingInfo.getBuildingLocation());
        statement.setString(2,buildingInfo.getBuildingName());
        statement.setString(3,buildingInfo.getBuildingStructure());
        statement.setString(4,buildingInfo.getBuildingTerm());
        statement.setString(5,buildingInfo.getBuildingType());
        statement.setInt(6,buildingInfo.getId());
        result=statement.executeUpdate();
        JDBCUtil.release(connection,statement,null);
        return result;


    }

    @Override
    public Integer delete(Integer id) throws SQLException {

        Connection connection= JDBCUtil.getConnection();
        String sql="delete from building where id="+id;
        PreparedStatement statement=null;
        Integer result=null;
        statement=connection.prepareStatement(sql);
        result=statement.executeUpdate();
        JDBCUtil.release(connection,statement,null);
        return result;

    }
}
