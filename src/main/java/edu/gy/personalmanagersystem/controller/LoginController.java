package edu.gy.personalmanagersystem.controller;

import com.github.pagehelper.PageInfo;
import edu.gy.personalmanagersystem.VO.ResultVO;
import edu.gy.personalmanagersystem.pojo.*;
import edu.gy.personalmanagersystem.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
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

    private Logger logger = Logger.getLogger("LoginController.class");

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
                    PageInfo<Honor> honorPageInfo = honorService.getByItem(honor,null,1);
                    Thesis thesis = new Thesis();thesis.setNumber(number);
                    PageInfo<Thesis> thesisPageInfo = thesisService.getByItem(thesis,null,1);
                    session.setAttribute("honorPageInfo",honorPageInfo);
                    session.setAttribute("thesisPageInfo",thesisPageInfo);
                    session.setAttribute("thesisType",2);// 2表示具体某个人的信息
                    session.setAttribute("honorType",2);// 2表示具体某个人的信息
                    resultVO.setData("user");
                } else if (role.getRoleid() == 1) {
                    PageInfo<Honor> honorPageInfo = honorService.getAll(1);
                    PageInfo<Thesis> thesisPageInfo = thesisService.getAll(1);
                    PageInfo<People> peoplePageInfo = peopleService.getAll(1);
                    session.setAttribute("honorPageInfo",honorPageInfo);
                    session.setAttribute("thesisPageInfo",thesisPageInfo);
                    session.setAttribute("peoplePageInfo",peoplePageInfo);
                    session.setAttribute("thesisType",3);// 2表示具体某个人的信息
                    session.setAttribute("honorType",3);// 2表示具体某个人的信息
                    session.setAttribute("peopleType",1);// 1 全部人员
                    resultVO.setData("admin");
                }
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

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String index(HttpSession session){
        if (session.getAttribute("peopleinfo") != null){
            session.removeAttribute("peopleinfo");
            session.removeAttribute("honorPageInfo");
            session.removeAttribute("thesisPageInfo");
            logger.info("用户已退出登录");
        }
        return "index";
    }

    @RequestMapping(value = "/personalIndex")
    public String personalIndex(@RequestParam(value = "chosenmune",required = false)String chosenmune
                                ,HttpSession session){
        if ("honorinfo".equals(chosenmune)) {
            session.setAttribute("isinfoshow","hidden");
            session.setAttribute("ispeopleshow","hidden");
            session.setAttribute("ishonorshow","");
            session.setAttribute("isthesisshow","hidden");
        } else if("thesisinfo".equals(chosenmune)) {
            session.setAttribute("isinfoshow", "hidden");
            session.setAttribute("ishonorshow", "hidden");
            session.setAttribute("ispeopleshow","hidden");
            session.setAttribute("isthesisshow", "");
        }else if("peoplesinfo".equals(chosenmune)) {
            session.setAttribute("isinfoshow", "hidden");
            session.setAttribute("ishonorshow", "hidden");
            session.setAttribute("ispeopleshow","");
            session.setAttribute("isthesisshow", "hidden");
        } else {
            session.setAttribute("isinfoshow","");
            session.setAttribute("ispeopleshow","hidden");
            session.setAttribute("ishonorshow","hidden");
            session.setAttribute("isthesisshow","hidden");
        }
        return "personalIndex";
    }
}
