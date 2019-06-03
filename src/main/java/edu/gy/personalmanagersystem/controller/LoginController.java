package edu.gy.personalmanagersystem.controller;

import com.github.pagehelper.PageInfo;
import edu.gy.personalmanagersystem.VO.ResultVO;
import edu.gy.personalmanagersystem.pojo.*;
import edu.gy.personalmanagersystem.service.*;
import edu.gy.personalmanagersystem.utils.DataTypesUtil;
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
        if (SessionManagerUtil.isDeviceExist(session.getId())) {
            ResultVO<String> resultVO = new ResultVO<String>(-1,"一个设备只能登录一个用户。若要切换用户清先推出登录");
            resultVO.setData("该用户已经登录，请不要重复登录");
            return resultVO;
        }
        if (LoginManagerUtil.isPeopleLogin(number)) {
            ResultVO<String> resultVO = new ResultVO<String>(-1,"该用户已经登录");
            resultVO.setData("该用户已经登录，请不要重复登录");
            return resultVO;
        }
        User user = userService.getUserByID(number);
        if (user == null) {
            logger.log(Level.WARNING, "用户不存在");
            ResultVO<String> resultVO = new ResultVO<String>(-1, "not exist");
            resultVO.setData("用户不存在");
            return resultVO;
        } else {
            logger.info("用户存在");
            if (user.getPassword().equals(pwd)) {
                logger.info("输入密码正确");
                LoginManagerUtil.addLoginPerson(number);
                ResultVO<String> resultVO = new ResultVO<String>(200, "OK");
                People peopleInfo = peopleService.getPeople(number);
                Role role = roleService.getRole(number);
                if (role.getRoleid() == 2) {
                    Honor honor = new Honor();
                    honor.setNumber(number);
                    PageInfo<Honor> honorPageInfo = honorService.getByItem(honor, null, 1);
                    Thesis thesis = new Thesis();
                    thesis.setNumber(number);
                    PageInfo<Thesis> thesisPageInfo = thesisService.getByItem(thesis, null, 1);
                    SessionManagerUtil.setHonorList(honorPageInfo,session);
                    SessionManagerUtil.setThesisList(thesisPageInfo,session);
                    SessionManagerUtil.setThesisDataType(DataTypesUtil.THESIS_DATA_FOR_GET,session);
                    SessionManagerUtil.setHonorDataType(DataTypesUtil.HONOR_DATA_FOR_GET,session);
                    SessionManagerUtil.setLogin(peopleInfo,session);
                    resultVO.setData("user");
                } else if (role.getRoleid() == 1) {
                    PageInfo<Honor> honorPageInfo = honorService.getAll(1);
                    PageInfo<Thesis> thesisPageInfo = thesisService.getAll(1);
                    PageInfo<People> peoplePageInfo = peopleService.getAll(1);
                    SessionManagerUtil.setHonorList(honorPageInfo,session);
                    SessionManagerUtil.setThesisList(thesisPageInfo,session);
                    SessionManagerUtil.setThesisDataType(DataTypesUtil.THESIS_DATA_FOR_GET,session);
                    SessionManagerUtil.setHonorDataType(DataTypesUtil.HONOR_DATA_FOR_GET,session);
                    SessionManagerUtil.setStuffList(peoplePageInfo,session);
                    SessionManagerUtil.setStuffDataType(DataTypesUtil.STUFF_DATA_FOR_ADMIN,session);
                    SessionManagerUtil.setLogin(peopleInfo,session);
                    resultVO.setData("admin");
                }
                SessionManagerUtil.setRole(role,session);
                return resultVO;
            } else {
                logger.info("输入密码错误");
                ResultVO<String> resultVO = new ResultVO<String>(-1, "wrong password");
                resultVO.setData("密码错误");
                return resultVO;
            }
        }
    }

    @RequestMapping(value = "/")
    public String index(@RequestParam(value = "logout",required = false)String number,
                        HttpSession session){
        if (LoginManagerUtil.isPeopleLogin(number)){
            LoginManagerUtil.removeLoginPerson(number);
            SessionManagerUtil.removeALl(session);
            logger.info("用户已退出登录");
        }
        return "index";
    }


}
