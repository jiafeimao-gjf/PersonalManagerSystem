package edu.gy.personalmanagersystem.dao;

import edu.gy.personalmanagersystem.pojo.Thesis;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ThesisMapper {
    List<Thesis> selectAll();

    int deleteByPrimaryKey(Integer thesisid);

    int insert(Thesis record);

    int insertSelective(Thesis record);

    Thesis selectByPrimaryKey(Integer thesisid);

    int updateByPrimaryKeySelective(Thesis record);

    int updateByPrimaryKey(Thesis record);

    int addEntitys(@Param("entitys")List<Thesis> entitys);

    List<Thesis> getByItem(@Param("entity")Thesis entity,@Param("rule")String rule);

    List<Thesis> getThesisInfoByLikes(@Param("entity")Thesis entity);

}