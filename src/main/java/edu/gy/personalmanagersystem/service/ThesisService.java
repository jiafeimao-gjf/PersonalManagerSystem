package edu.gy.personalmanagersystem.service;

import edu.gy.personalmanagersystem.pojo.Thesis;

import java.util.List;

/**
 * @InterfaceName: ThesisService
 * @Author: Gu Jiafei
 * @Date: 2019-04-22 16:10
 * @Version: 1.0
 **/
public interface ThesisService {

    int addThesis(Thesis thesis);

    int updateThesisInfo(Thesis thesis);

    int deleteThesis(Integer thesisid);

    int addThesisList(List<Thesis> thesisList);

    Thesis getThesisByKey(Integer thesisid);

    List<Thesis> getByItem(Thesis thesis,String rule);

    List<Thesis> getByLikes(Thesis thesis);

}
