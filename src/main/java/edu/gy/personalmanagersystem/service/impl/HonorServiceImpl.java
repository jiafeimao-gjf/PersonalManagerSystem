package edu.gy.personalmanagersystem.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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

    public Honor getHonorByKey(Integer honorid) {
        return honorDao.selectByPrimaryKey(honorid);
    }

    public int addHonor(Honor honor) {
        return honorDao.insert(honor);
    }

    public int updateHonorInfo(Honor honor) {
        return honorDao.updateByPrimaryKeySelective(honor);
    }


    public PageInfo<Honor> getByItem(Honor honor, String rule,Integer pageNum) {
        PageHelper.startPage(pageNum,5);
        List<Honor> honorList = honorDao.getByItem(honor,rule);
        PageInfo<Honor> pageInfo = new PageInfo<Honor>(honorList,5);
        return pageInfo;
    }

    public int addHonors(List<Honor> honorList) {
        return honorDao.addEntitys(honorList);
    }

    public int deleteHonor(Integer honorid) {
        return honorDao.deleteByPrimaryKey(honorid);
    }

    public PageInfo<Honor> getByLikes(Honor honor,Integer pageNum) {
        PageHelper.startPage(pageNum,5);
        List<Honor> honorList = honorDao.getHonorInfoByLikes(honor);
        PageInfo<Honor> pageInfo = new PageInfo<Honor>(honorList,5);
        return pageInfo;
    }
}
