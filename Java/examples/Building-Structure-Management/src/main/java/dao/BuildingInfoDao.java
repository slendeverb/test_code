package dao;

import entity.BuildingInfo;

import java.sql.SQLException;
import java.util.List;

public interface BuildingInfoDao {
    public List<BuildingInfo> list() throws SQLException;
    public List<BuildingInfo> search(String key,String value) throws SQLException;

    public Integer save(BuildingInfo buildingInfo) throws SQLException;
    public Integer update(BuildingInfo buildingInfo) throws SQLException;

    public Integer delete(Integer id) throws SQLException;



}
