package edu.gy.personalmanagersystem.dao;

import edu.gy.personalmanagersystem.pojo.TThesis;

public interface TThesisMapper {
    int deleteByPrimaryKey(Integer number);

    int insert(TThesis record);

    int insertSelective(TThesis record);

    TThesis selectByPrimaryKey(Integer number);

    int updateByPrimaryKeySelective(TThesis record);

    int updateByPrimaryKey(TThesis record);
}