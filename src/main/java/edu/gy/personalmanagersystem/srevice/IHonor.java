package edu.gy.personalmanagersystem.srevice;

import edu.gy.personalmanagersystem.pojo.THonor;

/**
 * @InterfaceName: IHonor
 * @Author: Gu Jiafei
 * @Date: 2019-04-22 16:11
 * @Version: 1.0
 **/
public interface IHonor {

    int addHonor(THonor honor);

    int updateHonorInfo(THonor honor);

    THonor getHonor(Integer number);

    int deleteHonor(Integer number);
}
