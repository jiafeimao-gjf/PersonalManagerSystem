package edu.gy.personalmanagersystem.controller;

import edu.gy.personalmanagersystem.VO.ResultVO;
import edu.gy.personalmanagersystem.pojo.People;
import edu.gy.personalmanagersystem.pojo.User;
import edu.gy.personalmanagersystem.service.PeopleService;
import edu.gy.personalmanagersystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public ResultVO<People> login(@RequestParam Integer number, @RequestParam String pwd){
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
                resultVO.setData(peopleInfo);
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
}
