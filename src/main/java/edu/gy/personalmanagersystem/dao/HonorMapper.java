package edu.gy.personalmanagersystem.dao;

import edu.gy.personalmanagersystem.pojo.Honor;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HonorMapper {
    List<Honor> selectAll();

    int deleteByPrimaryKey(Integer honorid);

    int insert(Honor record);

    int insertSelective(Honor record);

    Honor selectByPrimaryKey(Integer honorid);

    int updateByPrimaryKeySelective(Honor record);

    int updateByPrimaryKey(Honor record);

    int addEntitys(@Param("entitys")List<Honor> entitys);

    List<Honor> getByItem(@Param("entity")Honor entity,@Param("rule")String rule);

    List<Honor> getHonorInfoByLikes(@Param("entity")Honor entity);

}