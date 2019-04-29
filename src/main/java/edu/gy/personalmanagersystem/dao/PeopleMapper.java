package edu.gy.personalmanagersystem.dao;

import edu.gy.personalmanagersystem.pojo.People;

public interface PeopleMapper {
    int deleteByPrimaryKey(Integer number);

    int insert(People record);

    int insertSelective(People record);

    People selectByPrimaryKey(Integer number);

    int updateByPrimaryKeySelective(People record);

    int updateByPrimaryKey(People record);
}