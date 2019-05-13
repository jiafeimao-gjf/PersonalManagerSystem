package edu.gy.personalmanagersystem.dao;

import edu.gy.personalmanagersystem.pojo.Role;

public interface RoleMapper {
    int deleteByPrimaryKey(String number);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(String number);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);
}