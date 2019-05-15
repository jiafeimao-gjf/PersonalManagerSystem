package edu.gy.personalmanagersystem.service.impl;

import edu.gy.personalmanagersystem.dao.PeopleMapper;
import edu.gy.personalmanagersystem.pojo.People;
import edu.gy.personalmanagersystem.service.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName: PeopleServiceImpl
 * @Author: Gu Jiafei
 * @Date: 2019-05-07 16:45
 * @Version: 1.0
 **/
@Service
public class PeopleServiceImpl implements PeopleService {

    @Autowired
    PeopleMapper peopleDao;

    public People getPeople(String number) {
        return peopleDao.selectByPrimaryKey(number);
    }

    public int addPeople(People people) {
        return peopleDao.insert(people);
    }

    public int deletePeople(String number) {
        return peopleDao.deleteByPrimaryKey(number);
    }

    public int updatePeopleInfo(People people) {
        return peopleDao.updateByPrimaryKeySelective(people);
    }

    public List<People> getByLikes(People people) {
        return peopleDao.getPeopleInfoByLikes(people);
    }
}
