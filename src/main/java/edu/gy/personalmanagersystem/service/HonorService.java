package edu.gy.personalmanagersystem.service;

import edu.gy.personalmanagersystem.pojo.Honor;

import java.util.List;

/**
 * @InterfaceName: HonorService
 * @Author: Gu Jiafei
 * @Date: 2019-04-22 16:11
 * @Version: 1.0
 **/
public interface HonorService {

    Honor getHonorByKey(Integer honorid);

    int addHonor(Honor honor);

    int updateHonorInfo(Honor honor);


    List<Honor> getByItem(Honor honor,String rule);

    int addHonors(List<Honor> honorList);

    int deleteHonor(Integer number);

    List<Honor> getByLikes(Honor honor);
}
