package edu.gy.personalmanagersystem.controller;

import edu.gy.personalmanagersystem.VO.ResultVO;
import edu.gy.personalmanagersystem.pojo.People;
import edu.gy.personalmanagersystem.pojo.Role;
import edu.gy.personalmanagersystem.pojo.User;
import edu.gy.personalmanagersystem.service.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @ClassName: PeopleInfoController
 * @Author: Gu Jiafei
 * @Date: 2019-05-15 10:56
 * @Version: 1.0
 **/
@Controller
public class PeopleInfoController {

    Logger logger =  Logger.getLogger("PeopleInfoController.class");

    @Autowired
    private PeopleService peopleService;
    @Autowired
    private HonorService honorService;
    @Autowired
    private ThesisService thesisService;
    @Autowired
    private RoleService roleService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/addnewpeople",method = RequestMethod.POST)
    @ResponseBody
    public ResultVO<String> addNewPeople(People people){
        int res = peopleService.addPeople(people);
        if (res == 1) {
            logger.info("成功插入新的教职工信息");
            ResultVO<String> resultVO = new ResultVO<String>(200,"success");
            resultVO.setData("新增教职工信息成功");
            Role role = new Role();
            role.setNumber(people.getNumber());
            role.setRoleid(2);
            int roleres = roleService.insertRole(role);
            if (roleres == 1) {
                logger.info("成功插入新的角色信息");
            } else {
                logger.log(Level.WARNING,"角色信息插入失败");
            }
            User user = new User();
            user.setNumber(people.getNumber());
            user.setPassword(people.getIdentityno().substring(12,18));// 身份证后六位作为登陆密码
            int userRes = userService.insertNewUser(user);
            if (userRes == 1) {
                logger.info("成功插入新的登陆信息");
            } else {
                logger.log(Level.WARNING,"  用户信息插入失败");
            }
            return resultVO;
        } else {
            logger.log(Level.WARNING,"教职工信息插入失败");
            ResultVO<String> resultVO = new ResultVO<String>(-1,"failed");
            return resultVO;
        }
    }

    @RequestMapping(value = "/queryPeopleInfo",method = RequestMethod.GET)
    @ResponseBody
    public ResultVO<List<People>> queryPeopleByLikes(@RequestParam("name")String name, @RequestParam("department")String department,
                                   @RequestParam("birthplace")String birthplace){
        People people = new People();
        if("".equals(name)){

        } else {
            people.setName(name);
            people.setDepartment(department);
            people.setBirthplace(birthplace);
        }

        List<People> peopleList = peopleService.getByLikes(people);

        ResultVO<List<People>> resultVO = new ResultVO<List<People>>(200,"success");

        return resultVO;

    }

    @RequestMapping(value = "/deletePeople",method = RequestMethod.POST)
    @ResponseBody
    public ResultVO<String> deletePeopleInfo(@RequestParam("number")String number){

        int res = peopleService.deletePeople(number);

        if (res == 1){
            ResultVO<String> resultVO = new ResultVO<String>(200,"success");
            resultVO.setData("成功删除该职工相关的信息");
            return resultVO;
        } else {
            ResultVO<String> resultVO = new ResultVO<String>(-1,"failed");
            return resultVO;
        }
    }

    @RequestMapping(value = "/updatePeopleInfo",method = RequestMethod.POST)
    @ResponseBody
    public ResultVO<String> updatePeopleInfo(People people){
        int res = peopleService.updatePeopleInfo(people);
        if (res == 1) {
            ResultVO<String> resultVO = new ResultVO<String>(200,"success");
            return resultVO;
        } else {
            ResultVO<String> resultVO = new ResultVO<String>(-1,"failed");
            return resultVO;
        }
    }


}
