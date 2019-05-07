package edu.gy.personalmanagersystem.service;

import edu.gy.personalmanagersystem.pojo.User;

/**
 * @InterfaceName: UserService
 * @Author: Gu Jiafei
 * @Date: 2019-04-29 16:29
 * @Version: 1.0
 **/
public interface UserService {
    User getUserByID(Integer userID);

    Integer insertNewUser(User user);


}
