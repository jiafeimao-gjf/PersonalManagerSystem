package edu.gy.personalmanagersystem.controller;

import edu.gy.personalmanagersystem.VO.ResultVO;
import edu.gy.personalmanagersystem.pojo.User;
import edu.gy.personalmanagersystem.service.UserService;
import edu.gy.personalmanagersystem.utils.LoginManagerUtil;
import edu.gy.personalmanagersystem.utils.SessionManagerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.logging.Level;
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
    public ResultVO<String> changePwd(User user,HttpSession session){
        logger.info("修改密码");
        LoginManagerUtil.updateLoginTime(SessionManagerUtil.getLogin(session).getNumber());
        int res = userService.updateUser(user);
        if (res == 1) {
            logger.info("修改密码成功");
            ResultVO<String> resultVO = new ResultVO<String>(200,"success");
            resultVO.setData("修改密码成功！");
            return resultVO;
        } else {
            logger.log(Level.WARNING,"修改密码失败");
            ResultVO<String> resultVO = new ResultVO<String>(-1,"failed");
            resultVO.setData("修改密码失败！");
            return resultVO;
        }
    }

    @RequestMapping(value = "/adminIndex")
    public String adminIndex(@RequestParam(value = "chosenmenu",required = false)String chosenMenu,
                             HttpSession session){
        logger.info("adminIndex");
        if (SessionManagerUtil.isDeviceExist(session.getId())) {
            SessionManagerUtil.menuDisplaySet(chosenMenu,session);
            LoginManagerUtil.updateLoginTime(SessionManagerUtil.getLogin(session).getNumber());
            return "adminIndex";
        } else {
            return "index";
        }
    }

    @RequestMapping(value = "/personalIndex")
    public String personalIndex(@RequestParam(value = "chosenmenu",required = false)String chosenMenu
            ,HttpSession session){
        logger.info("personalIndex");
        if (SessionManagerUtil.isDeviceExist(session.getId())) {
            SessionManagerUtil.menuDisplaySet(chosenMenu,session);
            LoginManagerUtil.updateLoginTime(SessionManagerUtil.getLogin(session).getNumber());
            return "personalIndex";
        } else {
            return "index";
        }
    }
}
