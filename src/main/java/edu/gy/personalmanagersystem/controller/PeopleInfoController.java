package edu.gy.personalmanagersystem.controller;

import com.github.pagehelper.PageInfo;
import edu.gy.personalmanagersystem.VO.ResultVO;
import edu.gy.personalmanagersystem.pojo.People;
import edu.gy.personalmanagersystem.pojo.Role;
import edu.gy.personalmanagersystem.pojo.User;
import edu.gy.personalmanagersystem.service.*;
import edu.gy.personalmanagersystem.utils.*;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;
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

    private Logger logger =  Logger.getLogger("PeopleInfoController.class");

    @Autowired
    private PeopleService peopleService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/addnewpeople",method = RequestMethod.POST)
    @ResponseBody
    public ResultVO<String> addNewPeople(People people,HttpSession session){
        logger.info("addNewPeople");
        LoginManagerUtil.updateLoginTime(SessionManagerUtil.getLogin(session).getNumber());
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
        logger.info("queryPeopleByLikes");
        LoginManagerUtil.updateLoginTime(SessionManagerUtil.getLogin(session).getNumber());
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
        SessionManagerUtil.setStuffList(peoplePageInfo,session);
        SessionManagerUtil.setStuffDataType(DataTypesUtil.STUFF_DATA_FOR_SEARCH,session);// 1表示模糊查询
        return resultVO;

    }

    @RequestMapping(value = "getallpeople",method = RequestMethod.GET)
    @ResponseBody
    public ResultVO<String> getAllPeople(@RequestParam("pagenum")Integer pageNum,
                                         HttpSession session) {
        logger.info("getAllPeople");
        LoginManagerUtil.updateLoginTime(SessionManagerUtil.getLogin(session).getNumber());
        PageInfo<People> peoplePageInfo = peopleService.getAll(pageNum);
        logger.info("成功获取教职工信息");
        ResultVO<String> resultVO = new ResultVO<String>(200,"success");
        resultVO.setData("成功获取教职工信息");
        SessionManagerUtil.setStuffList(peoplePageInfo,session);
        SessionManagerUtil.setStuffDataType(DataTypesUtil.STUFF_DATA_FOR_ADMIN,session);// 2表示查询全部
        return resultVO;
    }

    @RequestMapping(value = "/deletepeople",method = RequestMethod.POST)
    @ResponseBody
    public ResultVO<String> deletePeopleInfo(@RequestParam("number")String number,
                                             HttpSession session){
        logger.info("deletePeopleInfo");
        LoginManagerUtil.updateLoginTime(SessionManagerUtil.getLogin(session).getNumber());
        int res = peopleService.deletePeople(number);
        if (res == 1){
            logger.info("成功删除该职工相关的信息");
            ResultVO<String> resultVO = new ResultVO<String>(200,"success");
            PageInfo<People> peoplePageInfo = peopleService.getAll(1);
            SessionManagerUtil.setStuffList(peoplePageInfo,session);
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
        LoginManagerUtil.updateLoginTime(SessionManagerUtil.getLogin(session).getNumber());
        int res = peopleService.updatePeopleInfo(people);
        if (res == 1) {
            logger.info("成功修改教职工信息");
            People stuff = peopleService.getPeople(people.getNumber());
            SessionManagerUtil.setStuff(stuff,session);
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
        LoginManagerUtil.updateLoginTime(SessionManagerUtil.getLogin(session).getNumber());
        People people = new People();
        people.setNumber(number);
        people.setChecked(checked);
        int res = peopleService.updatePeopleInfo(people);
        if (res == 1) {
            logger.info("教职工信息通过审核");
            People stuff = peopleService.getPeople(people.getNumber());
            SessionManagerUtil.setStuff(stuff,session);
            ResultVO<String> resultVO = new ResultVO<String>(200,"success");
            if(roleService.getRole(number) == null) { // 创建角色
                Role role = new Role();
                role.setRoleid(2);
                role.setNumber(number);
                roleService.insertRole(role);
            }
            if (userService.getUserByID(number) == null) { // 创建登录账号
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
        logger.info("lookPeopleInfo");
        LoginManagerUtil.updateLoginTime(SessionManagerUtil.getLogin(session).getNumber());
        People people = peopleService.getPeople(number);
        if (people != null){
            logger.info("成功获取职工信息");
            SessionManagerUtil.setStuff(people,session);
            ResultVO<String> resultVO = new ResultVO<String>(200,"success");
            resultVO.setData("成功获取职工信息");
            return resultVO;
        } else {
            logger.warning("获取职工信息失败");
            ResultVO<String> resultVO = new ResultVO<String>(-1,"filed");
            resultVO.setData("该教职工不存在");
            return  resultVO;
        }
    }

    @RequestMapping(value = "/peopledetail")
    public String peopleDetail(HttpSession session){
        logger.info("peopleDetail");
        if (SessionManagerUtil.isDeviceExist(session.getId())) {
            LoginManagerUtil.updateLoginTime(SessionManagerUtil.getLogin(session).getNumber());
            return "peopledetail";
        } else {
            return "index";
        }
    }

    @RequestMapping(value = "/addnewpeople")
    public String addNewPeople(HttpSession session){
        logger.info("addNewPeople");
        if (SessionManagerUtil.isDeviceExist(session.getId())) {
            LoginManagerUtil.updateLoginTime(SessionManagerUtil.getLogin(session).getNumber());
            return "addnewpeople";
        } else {
            return "index";
        }

    }

    @RequestMapping(value = "/addpeoplebyfile",method = RequestMethod.POST)
    @ResponseBody
    public ResultVO<String> addPeopleByFile(@RequestParam("stuffs") MultipartFile file, HttpSession session){
        logger.info("addPeopleByFile");
        //检查文件类型
        if (FileCheck.checkExcelFile(file.getOriginalFilename())) {
            ResultVO<String> resultVO = new ResultVO<String>(-1,"请上传.xls或者.xlsx格式的文件");
            resultVO.setData("文件格式不正确");
            return resultVO;
        }
        // 读取文件信息
        List<People> peopleList;
        try {
            peopleList = readExcelFile(file);
        } catch (IOException ioe) {
            logger.warning("文件读取错误");
            ioe.printStackTrace();
        }

        // 上传文件数据

        // 返回值处理
        ResultVO<String> resultVO = new ResultVO<String>(200,"已经存储所有的数据！");
        resultVO.setData("success");
        return resultVO;
    }

    private List<People> readExcelFile(MultipartFile file) throws IOException {
        try {
            Workbook workBook = WorkbookFactory.create(file.getInputStream());
            Sheet sheet = workBook.getSheetAt(0);

            for (int i = 0;i < sheet.getLastRowNum();i++){
                Row row = sheet.getRow(i);
                System.out.println("第"+i+"行：");
                for (int j = 0;j < row.getLastCellNum();j++) {
                    Cell cell = row.getCell(j);
                    switch (cell.getCellType()) {
                        case BOOLEAN:
                            System.out.println("Boolean:"+cell.getBooleanCellValue());
                            break;
                        case STRING:
                            System.out.println("String:"+cell.getStringCellValue());
                            break;
                        case NUMERIC:
                            System.out.println("numeric:"+cell.getNumericCellValue());
                            break;
                        case FORMULA:
                            System.out.println("Formula:"+cell.getCellFormula());
                            break;
                    }
                }
                System.out.println("=====================================");
            }

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        return null;
    }

}
