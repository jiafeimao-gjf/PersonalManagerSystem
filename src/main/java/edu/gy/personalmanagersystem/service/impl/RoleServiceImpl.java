package edu.gy.personalmanagersystem.service.impl;

import edu.gy.personalmanagersystem.dao.RoleMapper;
import edu.gy.personalmanagersystem.pojo.Role;
import edu.gy.personalmanagersystem.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName: RoleServiceImpl
 * @Author: Gu Jiafei
 * @Date: 2019-05-09 16:48
 * @Version: 1.0
 **/
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleMapper roleDao;
    public Role getRole(String number) {
        return roleDao.selectByPrimaryKey(number);
    }

    public Integer insertRole(Role role) {
        return roleDao.insert(role);
    }
}
