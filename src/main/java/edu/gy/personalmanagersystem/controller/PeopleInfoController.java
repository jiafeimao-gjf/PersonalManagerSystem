package edu.gy.personalmanagersystem.controller;

import com.github.pagehelper.PageInfo;
import edu.gy.personalmanagersystem.VO.ResultVO;
import edu.gy.personalmanagersystem.pojo.People;
import edu.gy.personalmanagersystem.pojo.Role;
import edu.gy.personalmanagersystem.pojo.User;
import edu.gy.personalmanagersystem.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
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

    private Logger logger =  Logger.getLogger("PeopleInfoController.class");

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

    @RequestMapping(value = "/querypeopleinfo",method = RequestMethod.GET)
    @ResponseBody
    public ResultVO<String> queryPeopleByLikes(@RequestParam("name")String name,
                                               @RequestParam("department")String department,
                                               @RequestParam("birthplace")String birthplace,
                                               @RequestParam("nation")String nation,
                                               @RequestParam("position")String position,
                                               @RequestParam("pagenum")Integer pageNum,
                                               HttpSession session){
        People people = new People();

        people.setName(name);
        people.setDepartment(department);
        people.setBirthplace(birthplace);
        people.setNation(nation);
        people.setPosition(position);

        PageInfo<People> peoplePageInfo = peopleService.getByLikes(people,pageNum);
        ResultVO<String> resultVO = new ResultVO<String>(200,"success");
        resultVO.setData("成功获取教职工信息");
        session.setAttribute("peoplePageInfo",peoplePageInfo);
        session.setAttribute("peopleType",2);// 2表示模糊查询
        return resultVO;

    }

    @RequestMapping(value = "getallpeople",method = RequestMethod.GET)
    @ResponseBody
    public ResultVO<String> getAllPeople(@RequestParam("pagenum")Integer pageNum,
                                         HttpSession session) {
        PageInfo<People> peoplePageInfo = peopleService.getAll(pageNum);
        ResultVO<String> resultVO = new ResultVO<String>(200,"success");
        resultVO.setData("成功获取教职工信息");
        session.setAttribute("peoplePageInfo",peoplePageInfo);
        session.setAttribute("peopleType",1);// 1表示查询全部
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
