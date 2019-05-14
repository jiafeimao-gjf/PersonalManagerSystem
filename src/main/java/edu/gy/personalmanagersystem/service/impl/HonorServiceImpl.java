package edu.gy.personalmanagersystem.service.impl;

import edu.gy.personalmanagersystem.dao.HonorMapper;
import edu.gy.personalmanagersystem.pojo.Honor;
import edu.gy.personalmanagersystem.service.HonorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName: HonorServiceImpl
 * @Author: Gu Jiafei
 * @Date: 2019-05-14 19:44
 * @Version: 1.0
 **/
@Service
public class HonorServiceImpl implements HonorService {
    @Autowired
    HonorMapper honorDao;

    public int addHonor(Honor honor) {
        return honorDao.insert(honor);
    }

    public int updateHonorInfo(Honor honor) {
        return honorDao.updateByPrimaryKeySelective(honor);
    }


    public List<Honor> getByItem(Honor honor, String rule) {
        return honorDao.getByItem(honor,rule);
    }

    public int addHonors(List<Honor> honorList) {
        return honorDao.addEntitys(honorList);
    }

    public int deleteHonor(Integer honorid) {
        return honorDao.deleteByPrimaryKey(honorid);
    }
}
