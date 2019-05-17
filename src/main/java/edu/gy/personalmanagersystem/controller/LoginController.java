package edu.gy.personalmanagersystem.controller;

import edu.gy.personalmanagersystem.VO.ResultVO;
import edu.gy.personalmanagersystem.pojo.*;
import edu.gy.personalmanagersystem.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @ClassName: LoginController
 * @Author: Gu Jiafei
 * @Date: 2019-04-29 16:28
 * @Version: 1.0
 **/
@Controller
public class LoginController {

    Logger logger = Logger.getLogger("LoginController.class");

    @Autowired
    private UserService userService;

    @Autowired
    private PeopleService peopleService;

    @Autowired
    private HonorService honorService;

    @Autowired
    private ThesisService thesisService;

    @Autowired
    RoleService roleService;

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    public ResultVO<String> login(@RequestParam String number, @RequestParam String pwd, HttpSession session){
        logger.info("登陆验证");
        User user = userService.getUserByID(number);
        if (user == null) {
            logger.log(Level.WARNING,"用户不存在");
            ResultVO<String> resultVO = new ResultVO<String>(-1,"not exist");
            resultVO.setData("用户不存在");
            return resultVO;
        } else {
            logger.info("用户存在");
            if (user.getPassword().equals(pwd)) {
                logger.info("输入密码正确");
                ResultVO<String> resultVO = new ResultVO<String>(200, "OK");
                People peopleInfo = peopleService.getPeople(number);
                Role role = roleService.getRole(number);
                if (role.getRoleid() == 2) {
                    Honor honor = new Honor();
                    honor.setNumber(number);
                    List<Honor> honorList = honorService.getByItem(honor,null);
                    Thesis thesis = new Thesis();thesis.setNumber(number);
                    List<Thesis> thesisList = thesisService.getByItem(thesis,null);
                    session.setAttribute("honorList",honorList);
                    session.setAttribute("ThesisList",thesisList);
                }
                resultVO.setData("验证成功");
                session.setAttribute("peopleinfo",peopleInfo);
                session.setAttribute("roleinfo",role);
                return resultVO;
            } else {
                logger.info("输入密码错误");
                ResultVO<String> resultVO = new ResultVO<String>(-1,"wrong password");
                resultVO.setData("密码错误");
                return resultVO;
            }
        }
    }

    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public String index(){
        return "index";
    }

    @RequestMapping(value = "/personalIndex")
    public String personalIndex(){
        return "personalIndex";
    }
}
