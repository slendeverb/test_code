package service.impl;

import dao.SystemAdminDao;
import dao.impl.SystemAdminDaoImpl;
import dto.SystemAdminDto;
import entity.SystemAdmin;
import service.SystemAdminService;

import java.sql.SQLException;

/*
* 通过email查询登陆用户是否存在。
* 结果为空，用户不存在
* 查询结果不为空，再判断password是否正确
*
*   ps：前端已经实现邮箱校验和非空校验。
* */
public class SystemAdminServiceImpl implements SystemAdminService {

    private SystemAdminDao systemAdminDao =new SystemAdminDaoImpl();

    @Override
    public SystemAdminDto login(String email, String password) throws SQLException {

        SystemAdmin systemAdmin=this.systemAdminDao.findByUsername(email);

        SystemAdminDto systemAdminDto=new SystemAdminDto();

        if (systemAdmin==null){

            systemAdminDto.setCode(-1);

        }else{
            if(!systemAdmin.getPassword().equals(password)){
                systemAdminDto.setCode(-2);
            }else{
                systemAdminDto.setCode(1);
                systemAdminDto.setSystemAdmin(systemAdmin);
            }

        }

        return systemAdminDto;
    }


    @Override
    public SystemAdminDto register(String email, String password) throws SQLException {

        SystemAdmin systemAdmin=this.systemAdminDao.findByUsername(email);

        SystemAdminDto systemAdminDto=new SystemAdminDto();


        if(systemAdmin!=null)
        {
        if(systemAdmin.getEmail().equals(email))
            systemAdminDto.setCode(-3); //邮箱查重
        }
        else {

            systemAdminDto.setCode(1);
            this.systemAdminDao.insertNewUser(email,password);


        }


        return systemAdminDto;
    }

}
