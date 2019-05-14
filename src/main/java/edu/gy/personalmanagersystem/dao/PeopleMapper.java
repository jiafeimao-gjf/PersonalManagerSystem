package edu.gy.personalmanagersystem.dao;

import edu.gy.personalmanagersystem.pojo.People;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PeopleMapper {
    int deleteByPrimaryKey(String number);

    int insert(People record);

    int insertSelective(People record);

    People selectByPrimaryKey(String number);

    int updateByPrimaryKeySelective(People record);

    int updateByPrimaryKey(People record);

    List<People> getPeopleInfoByLikes(@Param("entity") People entity);
}