package edu.gy.personalmanagersystem.dao;

import edu.gy.personalmanagersystem.pojo.Thesis;

public interface ThesisMapper {
    int deleteByPrimaryKey(Integer number);

    int insert(Thesis record);

    int insertSelective(Thesis record);

    Thesis selectByPrimaryKey(Integer number);

    int updateByPrimaryKeySelective(Thesis record);

    int updateByPrimaryKey(Thesis record);
}