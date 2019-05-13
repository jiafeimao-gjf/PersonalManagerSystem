package edu.gy.personalmanagersystem.controller;

import edu.gy.personalmanagersystem.VO.ResultVO;
import edu.gy.personalmanagersystem.pojo.People;
import edu.gy.personalmanagersystem.pojo.Role;
import edu.gy.personalmanagersystem.pojo.User;
import edu.gy.personalmanagersystem.service.PeopleService;
import edu.gy.personalmanagersystem.service.RoleService;
import edu.gy.personalmanagersystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName: LoginController
 * @Author: Gu Jiafei
 * @Date: 2019-04-29 16:28
 * @Version: 1.0
 **/
@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private PeopleService peopleService;

    @Autowired
    RoleService roleService;

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    public ResultVO<People> login(@RequestParam String number, @RequestParam String pwd, HttpServletRequest request){
        System.out.println("Login Verify");
        User user = userService.getUserByID(number);
        if (user == null) {
            ResultVO<People> resultVO = new ResultVO<People>(-1,"not exist");
            resultVO.setData(null);
            return resultVO;
        } else {
            if (user.getPassword().equals(pwd)) {
                ResultVO<People> resultVO = new ResultVO<People>(200, "OK");
                People peopleInfo = peopleService.getPeople(number);
                Role role = roleService.getRole(number);
                resultVO.setData(peopleInfo);
                request.getSession().setAttribute("peopleinfo",peopleInfo);
                request.getSession().setAttribute("roleinfo",role);
                return resultVO;
            } else {
                ResultVO<People> resultVO = new ResultVO<People>(-1,"wrong pwd");
                resultVO.setData(null);
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
