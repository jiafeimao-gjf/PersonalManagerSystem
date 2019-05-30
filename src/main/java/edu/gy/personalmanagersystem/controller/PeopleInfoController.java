package edu.gy.personalmanagersystem.controller;

import com.github.pagehelper.PageInfo;
import edu.gy.personalmanagersystem.VO.ResultVO;
import edu.gy.personalmanagersystem.pojo.People;
import edu.gy.personalmanagersystem.pojo.Role;
import edu.gy.personalmanagersystem.pojo.User;
import edu.gy.personalmanagersystem.service.*;
import edu.gy.personalmanagersystem.utils.PasswordCreatorUtil;
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
    private RoleService roleService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/addnewpeople",method = RequestMethod.POST)
    @ResponseBody
    public ResultVO<String> addNewPeople(People people){
        if (peopleService.getPeople(people.getNumber())==null) {
            people.setChecked(1);
            int res = peopleService.addPeople(people);
            if (res == 1) {
                logger.info("成功插入新的教职工信息");
                ResultVO<String> resultVO = new ResultVO<String>(200, "success");
                String pwdInfo;
                // 添加角色信息
                Role role = new Role();
                role.setNumber(people.getNumber());
                role.setRoleid(2);
                int roleRes = roleService.insertRole(role);
                if (roleRes == 1) {
                    logger.info("成功插入新的角色信息");
                } else {
                    logger.log(Level.WARNING, "角色信息插入失败");
                }
                // 添加登录信息
                User user = new User();
                user.setNumber(people.getNumber());
                int pwdLength;
                if (people.getIdentityno().length() >= 18) {
                    pwdLength = 18;
                    user.setPassword(people.getIdentityno().substring(pwdLength-6, pwdLength));// 身份证后六位作为登陆密码
                    pwdInfo = "身份号后六位位";
                } else {
                    pwdInfo = PasswordCreatorUtil.getDefaultPwd();
                    user.setPassword(pwdInfo);// 随机生成密码
                }
                int userRes = userService.insertNewUser(user);
                if (userRes == 1) {
                    logger.info("成功插入新的登陆信息");
                } else {
                    logger.log(Level.WARNING, "  教职工信息插入失败");
                }
                resultVO.setData("新增教职工信息成功，密码为："+pwdInfo);
                return resultVO;
            } else {
                logger.log(Level.WARNING, "教职工信息插入失败");
                ResultVO<String> resultVO = new ResultVO<String>(-1, "failed");
                resultVO.setData("教职工信息插入失败");
                return resultVO;
            }
        } else {
            ResultVO<String> resultVO = new ResultVO<String>(-1, "failed");
            resultVO.setData("教职工编号已存在");
            return resultVO;
        }
    }

    @RequestMapping(value = "/querypeoplebylikes",method = RequestMethod.GET)
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
        logger.info("成功获取教职工信息");
        ResultVO<String> resultVO = new ResultVO<String>(200,"success");
        resultVO.setData("成功获取教职工信息");
        session.setAttribute("peoplePageInfo",peoplePageInfo);
        session.setAttribute("peopleType",1);// 1表示模糊查询
        return resultVO;

    }

    @RequestMapping(value = "getallpeople",method = RequestMethod.GET)
    @ResponseBody
    public ResultVO<String> getAllPeople(@RequestParam("pagenum")Integer pageNum,
                                         HttpSession session) {
        PageInfo<People> peoplePageInfo = peopleService.getAll(pageNum);
        logger.info("成功获取教职工信息");
        ResultVO<String> resultVO = new ResultVO<String>(200,"success");
        resultVO.setData("成功获取教职工信息");
        session.setAttribute("peoplePageInfo",peoplePageInfo);
        session.setAttribute("peopleType",2);// 2表示查询全部
        return resultVO;
    }

    @RequestMapping(value = "/deletepeople",method = RequestMethod.POST)
    @ResponseBody
    public ResultVO<String> deletePeopleInfo(@RequestParam("number")String number,
                                             HttpSession session){

        int res = peopleService.deletePeople(number);
        if (res == 1){
            logger.info("成功删除该职工相关的信息");
            ResultVO<String> resultVO = new ResultVO<String>(200,"success");
            PageInfo<People> peoplePageInfo = peopleService.getAll(1);
            session.setAttribute("peoplePageInfo",peoplePageInfo);
            resultVO.setData("成功删除该职工相关的信息");
            return resultVO;
        } else {
            logger.warning("删除该职工相关的信息失败");
            ResultVO<String> resultVO = new ResultVO<String>(-1,"failed");
            resultVO.setData("删除该职工相关的信息失败");
            return resultVO;
        }
    }

    @RequestMapping(value = "/updatepeopleinfo",method = RequestMethod.POST)
    @ResponseBody
    public ResultVO<String> updatePeopleInfo(People people,HttpSession session){
        logger.info("updatePeopleInfo");
        int res = peopleService.updatePeopleInfo(people);
        if (res == 1) {
            logger.info("成功修改教职工信息");
            People stuff = peopleService.getPeople(people.getNumber());
            session.setAttribute("stuffinfo",stuff);
            ResultVO<String> resultVO = new ResultVO<String>(200,"success");
            resultVO.setData("成功修改教职工信息");
            return resultVO;
        } else {
            logger.info("修改教职工信息失败");
            ResultVO<String> resultVO = new ResultVO<String>(-1,"failed");
            resultVO.setData("修改教职工信息失败");
            return resultVO;
        }
    }

    @RequestMapping(value = "/checkstuff",method = RequestMethod.POST)
    @ResponseBody
    public ResultVO<String> checkStuff(@RequestParam("number")String number,
                                       @RequestParam("checked")Integer checked
                                       ,HttpSession session){
        logger.info("checkStuff");
        People people = new People();
        people.setNumber(number);
        people.setChecked(checked);
        int res = peopleService.updatePeopleInfo(people);
        if (res == 1) {
            logger.info("教职工信息通过审核");
            People stuff = peopleService.getPeople(people.getNumber());
            session.setAttribute("stuffinfo",stuff);
            ResultVO<String> resultVO = new ResultVO<String>(200,"success");
            if(roleService.getRole(number) == null) {
                Role role = new Role();
                role.setRoleid(2);
                role.setNumber(number);
                roleService.insertRole(role);
            }
            if (userService.getUserByID(number) == null) {
                User user = new User();
                user.setNumber(number);
                user.setPassword(PasswordCreatorUtil.getDefaultPwd());
                userService.insertNewUser(user);
            }
            resultVO.setData("教职工信息通过审核");
            return resultVO;
        } else {
            logger.info("教职工信息未通过审核");
            ResultVO<String> resultVO = new ResultVO<String>(-1,"failed");
            resultVO.setData("教职工信息未通过审核");
            return resultVO;
        }
    }

    @RequestMapping(value = "lookpeopleinfo",method = RequestMethod.GET)
    @ResponseBody
    public ResultVO<String> lookPeopleInfo(@RequestParam("number")String number,
                                       HttpSession session){
        People people = peopleService.getPeople(number);

        if (people != null){
            logger.info("成功获取职工信息");
            session.setAttribute("stuffinfo",people);
            ResultVO<String> resultVO = new ResultVO<String>(200,"success");
            resultVO.setData("成功获取职工信息");
            return resultVO;
        } else {
            logger.warning("获取职工信息失败");
            session.setAttribute("stuffinfo",null);
            ResultVO<String> resultVO = new ResultVO<String>(-1,"filed");
            resultVO.setData("获取职工信息失败");
            return  resultVO;
        }
    }

    @RequestMapping(value = "/peopledetail")
    public String peopleDetail(HttpSession session){
        logger.info("peopleDetail");
        if (session.getAttribute("peopleinfo") != null) {
            return "peopledetail";
        } else {
            return "index";
        }
    }

    @RequestMapping(value = "/addnewpeople")
    public String addNewPeople(HttpSession session){
        logger.info("addNewPeople");
        if (session.getAttribute("peopleinfo") != null) {
            return "addnewpeople";
        } else {
            return "index";
        }

    }
}
