package service;

import dto.SystemAdminDto;

import entity.SystemAdmin;

import java.sql.SQLException;


public interface SystemAdminService {

    public SystemAdminDto login(String email,String password) throws SQLException;


    public SystemAdminDto register(String email,String password) throws SQLException;


}
