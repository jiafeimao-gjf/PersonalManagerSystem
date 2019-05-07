package edu.gy.personalmanagersystem.dao;

import edu.gy.personalmanagersystem.pojo.Role;

public interface RoleMapper {
    int deleteByPrimaryKey(Integer number);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Integer number);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);
}