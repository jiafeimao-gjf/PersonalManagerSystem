package edu.gy.personalmanagersystem.dao;

import edu.gy.personalmanagersystem.pojo.User;

public interface UserMapper {
    int deleteByPrimaryKey(String number);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String number);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}