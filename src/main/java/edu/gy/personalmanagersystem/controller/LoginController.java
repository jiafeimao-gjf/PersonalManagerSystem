package edu.gy.personalmanagersystem.controller;

import edu.gy.personalmanagersystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @ClassName: LoginController
 * @Author: Gu Jiafei
 * @Date: 2019-04-29 16:28
 * @Version: 1.0
 **/
@Controller
@RequestMapping("/user")
public class LoginController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String Login(){
        return "login";
    }
}
