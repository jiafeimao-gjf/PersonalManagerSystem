package edu.gy.personalmanagersystem.service;

import edu.gy.personalmanagersystem.pojo.Role;

/**
 * @InterfaceName: RoleService
 * @Author: Gu Jiafei
 * @Date: 2019-05-05 15:49
 * @Version: 1.0
 **/
public interface RoleService {

    Role getRole(String number);

    Integer insertRole(Role role);


}
