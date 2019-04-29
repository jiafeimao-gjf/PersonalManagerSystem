package edu.gy.personalmanagersystem.dao;

import edu.gy.personalmanagersystem.pojo.Honor;

public interface HonorMapper {
    int deleteByPrimaryKey(Integer number);

    int insert(Honor record);

    int insertSelective(Honor record);

    Honor selectByPrimaryKey(Integer number);

    int updateByPrimaryKeySelective(Honor record);

    int updateByPrimaryKey(Honor record);
}