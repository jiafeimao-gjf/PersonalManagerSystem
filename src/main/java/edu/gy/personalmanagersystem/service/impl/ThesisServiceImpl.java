package edu.gy.personalmanagersystem.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.gy.personalmanagersystem.dao.ThesisMapper;
import edu.gy.personalmanagersystem.pojo.Thesis;
import edu.gy.personalmanagersystem.service.ThesisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName: ThesisServiceImpl
 * @Author: Gu Jiafei
 * @Date: 2019-05-14 19:48
 * @Version: 1.0
 **/
@Service
public class ThesisServiceImpl implements ThesisService {
    @Autowired
    ThesisMapper thesisDao;

    public Thesis getThesisByKey(Integer thesisid) {
        return thesisDao.selectByPrimaryKey(thesisid);
    }

    public int addThesis(Thesis thesis) {
        return thesisDao.insert(thesis);
    }

    public int updateThesisInfo(Thesis thesis) {
        return thesisDao.updateByPrimaryKeySelective(thesis);
    }

    public int deleteThesis(Integer thesisid) {
        return thesisDao.deleteByPrimaryKey(thesisid);
    }

    public int addThesisList(List<Thesis> thesisList) {
        return thesisDao.addEntitys(thesisList);
    }

    public PageInfo<Thesis> getByItem(Thesis thesis, String rule,Integer pageNum) {
        PageHelper.startPage(pageNum,5);
        List<Thesis> thesisList = thesisDao.getByItem(thesis,rule);
        PageInfo<Thesis> thesisPageInfo = new PageInfo<Thesis>(thesisList,5);
        return thesisPageInfo;
    }

    public PageInfo<Thesis> getByLikes(Thesis thesis,Integer pageNum) {
        PageHelper.startPage(pageNum,5);
        List<Thesis> thesisList = thesisDao.getThesisInfoByLikes(thesis);
        PageInfo<Thesis> thesisPageInfo = new PageInfo<Thesis>(thesisList,5);
        return thesisPageInfo;
    }
}
