package edu.gy.personalmanagersystem.service;

import com.github.pagehelper.PageInfo;
import edu.gy.personalmanagersystem.pojo.People;

import java.util.List;

/**
 * @InterfaceName: PeopleService
 * @Author: Gu Jiafei
 * @Date: 2019-04-22 16:10
 * @Version: 1.0
 **/
public interface PeopleService {
    /** 插入新员工
     * @Author Gu Jiafei
     * @Date 16:18 2019-04-22
     * @Param [people]
     * @return int
     **/
    int addPeople(People people);
    /** 更据ID获取员工信息
     * @Author Gu Jiafei
     * @Date 16:18 2019-04-22
     * @Param [number]
     * @return java.util.List<edu.gy.personalmanagersystem.pojo.TPeople>
     **/
    People getPeople(String number);
    /** 删除员工
     * @Author Gu Jiafei
     * @Date 16:18 2019-04-22
     * @Param [number]
     * @return int
     **/
    int deletePeople(String number);
    /** 跟新员工信息
     * @Author Gu Jiafei
     * @Date 16:18 2019-04-22
     * @Param [people]
     * @return int
     **/
    int updatePeopleInfo(People people);

    PageInfo<People> getByLikes(People people,Integer pageNum);

    PageInfo<People> getAll(Integer pageNum);

}
