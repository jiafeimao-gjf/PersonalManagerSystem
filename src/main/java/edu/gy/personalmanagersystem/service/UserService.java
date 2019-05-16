package edu.gy.personalmanagersystem.service;

import com.sun.tools.doclets.formats.html.ClassUseWriter;
import edu.gy.personalmanagersystem.pojo.User;

/**
 * @InterfaceName: UserService
 * @Author: Gu Jiafei
 * @Date: 2019-04-29 16:29
 * @Version: 1.0
 **/
public interface UserService {
    User getUserByID(String number);

    Integer insertNewUser(User user);

    int updateUser(User user);

}
