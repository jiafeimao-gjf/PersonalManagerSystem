package edu.gy.personalmanagersystem.service.impl;

import edu.gy.personalmanagersystem.dao.UserMapper;
import edu.gy.personalmanagersystem.pojo.User;
import edu.gy.personalmanagersystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName: UserServiceService
 * @Author: Gu Jiafei
 * @Date: 2019-04-29 16:30
 * @Version: 1.0
 **/
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userDao;


    public User getUserByID(Integer userID) {
        User user = userDao.selectByPrimaryKey(userID);
        return user;
    }

    public Integer insertNewUser(User user) {

        Integer res = userDao.insertSelective(user);
        return res;
    }
}
