package edu.gy.personalmanagersystem.dao;

import edu.gy.personalmanagersystem.pojo.Thesis;
import edu.gy.personalmanagersystem.pojo.ThesisKey;

public interface ThesisMapper {
    int deleteByPrimaryKey(ThesisKey key);

    int insert(Thesis record);

    int insertSelective(Thesis record);

    Thesis selectByPrimaryKey(ThesisKey key);

    int updateByPrimaryKeySelective(Thesis record);

    int updateByPrimaryKey(Thesis record);
}