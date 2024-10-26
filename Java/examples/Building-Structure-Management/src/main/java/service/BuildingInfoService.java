package service;

import entity.BuildingInfo;

import java.sql.SQLException;
import java.util.List;

public interface BuildingInfoService {
    public List<BuildingInfo> list() throws SQLException;
    public List<BuildingInfo> search(String key,String value) throws SQLException;

    public void save(BuildingInfo buildingInfo) throws SQLException;
    public void update(BuildingInfo buildingInfo) throws SQLException;
    public void delete(Integer id) throws SQLException;
}
