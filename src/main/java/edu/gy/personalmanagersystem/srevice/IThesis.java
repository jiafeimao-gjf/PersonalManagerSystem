package edu.gy.personalmanagersystem.srevice;

import edu.gy.personalmanagersystem.pojo.TThesis;

import java.util.List;

/**
 * @InterfaceName: IThesis
 * @Author: Gu Jiafei
 * @Date: 2019-04-22 16:10
 * @Version: 1.0
 **/
public interface IThesis {

    int addThesis(TThesis thesis);

    int updateThesisInfo(TThesis thesis);

    TThesis getThesis(Integer number);

    int deleteTheis(Integer number);


}
