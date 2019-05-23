package edu.gy.personalmanagersystem.service;

import com.github.pagehelper.PageInfo;
import edu.gy.personalmanagersystem.pojo.Thesis;

import java.util.List;

/**
 * @InterfaceName: ThesisService
 * @Author: Gu Jiafei
 * @Date: 2019-04-22 16:10
 * @Version: 1.0
 **/
public interface ThesisService {

    PageInfo<Thesis> getAll(Integer pageNum);

    int addThesis(Thesis thesis);

    int updateThesisInfo(Thesis thesis);

    int deleteThesis(Integer thesisid);

    int addThesisList(List<Thesis> thesisList);

    Thesis getThesisByKey(Integer thesisid);

    PageInfo<Thesis> getByItem(Thesis thesis, String rule,Integer pageNum);

    PageInfo<Thesis> getByLikes(Thesis thesis,Integer pageInfo);

}
