package edu.gy.personalmanagersystem.dao;

import edu.gy.personalmanagersystem.pojo.People;

public interface PeopleMapper {
    int deleteByPrimaryKey(String number);

    int insert(People record);

    int insertSelective(People record);

    People selectByPrimaryKey(String number);

    int updateByPrimaryKeySelective(People record);

    int updateByPrimaryKey(People record);
}