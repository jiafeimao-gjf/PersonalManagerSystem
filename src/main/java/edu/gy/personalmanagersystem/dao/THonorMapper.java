package edu.gy.personalmanagersystem.dao;

import edu.gy.personalmanagersystem.pojo.THonor;

public interface THonorMapper {
    int deleteByPrimaryKey(Integer number);

    int insert(THonor record);

    int insertSelective(THonor record);

    THonor selectByPrimaryKey(Integer number);

    int updateByPrimaryKeySelective(THonor record);

    int updateByPrimaryKey(THonor record);
}