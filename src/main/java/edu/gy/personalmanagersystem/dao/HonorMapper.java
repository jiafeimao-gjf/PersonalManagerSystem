package edu.gy.personalmanagersystem.dao;

import edu.gy.personalmanagersystem.pojo.Honor;
import edu.gy.personalmanagersystem.pojo.HonorKey;

public interface HonorMapper {
    int deleteByPrimaryKey(HonorKey key);

    int insert(Honor record);

    int insertSelective(Honor record);

    Honor selectByPrimaryKey(HonorKey key);

    int updateByPrimaryKeySelective(Honor record);

    int updateByPrimaryKey(Honor record);
}