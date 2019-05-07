package edu.gy.personalmanagersystem.dao;

import edu.gy.personalmanagersystem.pojo.User;

public interface UserMapper {
    int deleteByPrimaryKey(Integer number);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer number);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}