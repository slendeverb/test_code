package service.impl;

import dao.BuildingInfoDao;
import dao.impl.BuildingInfoDaoImpl;
import entity.BuildingInfo;
import service.BuildingInfoService;

import java.sql.SQLException;
import java.util.List;

public class BuildingInfoServiceImpl implements BuildingInfoService {

    private BuildingInfoDao buildingInfoDao=new BuildingInfoDaoImpl();
    @Override
    public List<BuildingInfo> list() throws SQLException {
        return this.buildingInfoDao.list();
    }

    @Override
    public List<BuildingInfo> search(String key,String value) throws SQLException {
        if (value.isEmpty()) return this.buildingInfoDao.list();
        return this.buildingInfoDao.search(key,value);
    }

    @Override
    public void save(BuildingInfo buildingInfo) throws SQLException {
        Integer save=this.buildingInfoDao.save(buildingInfo);
        if(save!=1) throw new RuntimeException("建筑信息添加失败!");
    }

    @Override
    public void update(BuildingInfo buildingInfo) throws SQLException {
        Integer update=this.buildingInfoDao.update(buildingInfo);
        if(update!=1) throw new RuntimeException("建筑信息更新失败!");
    }

    @Override
    public void delete(Integer id) throws SQLException {
        Integer delete=this.buildingInfoDao.delete(id);
        if(delete!=1) throw new RuntimeException("建筑信息删除失败!");
    }
}
