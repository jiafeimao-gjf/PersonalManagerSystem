package edu.gy.personalmanagersystem.controller;

import edu.gy.personalmanagersystem.VO.ResultVO;
import edu.gy.personalmanagersystem.pojo.User;
import edu.gy.personalmanagersystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.logging.Logger;

/**
 * @ClassName: UserController
 * @Author: Gu Jiafei
 * @Date: 2019-05-17 00:22
 * @Version: 1.0
 **/
@Controller
public class UserController {

    private Logger logger = Logger.getLogger("UserController.class");
    @Autowired
    UserService userService;

    @RequestMapping(value = "/changepwd",method = RequestMethod.POST)
    @ResponseBody
    public ResultVO<String> changePwd(User user){
        logger.info("修改密码");
        int res = userService.updateUser(user);
        if (res == 1) {
            logger.info("修改密码成功");
            ResultVO<String> resultVO = new ResultVO<String>(200,"successs");
            resultVO.setData("修改密码成功！");
            return resultVO;
        } else {
            logger.info("修改密码失败");
            ResultVO<String> resultVO = new ResultVO<String>(-1,"filed");
            resultVO.setData("修改密码失败！");
            return resultVO;
        }
    }
}
