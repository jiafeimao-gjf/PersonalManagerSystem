package edu.gy.personalmanagersystem.dao;

import edu.gy.personalmanagersystem.pojo.TPeople;

public interface TPeopleMapper {
    int deleteByPrimaryKey(Integer number);

    int insert(TPeople record);

    int insertSelective(TPeople record);

    TPeople selectByPrimaryKey(Integer number);

    int updateByPrimaryKeySelective(TPeople record);

    int updateByPrimaryKey(TPeople record);
}