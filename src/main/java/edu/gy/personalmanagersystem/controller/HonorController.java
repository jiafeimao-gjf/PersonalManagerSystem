package edu.gy.personalmanagersystem.controller;

import edu.gy.personalmanagersystem.VO.ResultVO;
import edu.gy.personalmanagersystem.pojo.Honor;
import edu.gy.personalmanagersystem.pojo.People;
import edu.gy.personalmanagersystem.service.HonorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.POST;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @ClassName: HonorController
 * @Author: Gu Jiafei
 * @Date: 2019-05-17 11:04
 * @Version: 1.0
 **/
@Controller
public class HonorController {
    Logger logger = Logger.getLogger("HonorController.class");

    @Autowired
    private HonorService honorService;

    @RequestMapping(value = "/gethonorsbynumber",method = RequestMethod.GET)
    @ResponseBody
    public ResultVO<String> getHonorsByNumber(@RequestParam("number") String number,HttpSession session){
        logger.info("getHonorsByNumber");
        Honor honor = new Honor();
        honor.setNumber(number);
        List<Honor> honorList = honorService.getByItem(honor,null);
        return setResultVO(honorList,session);
    }

    @RequestMapping(value = "/gethonorsbylikes",method = RequestMethod.GET)
    @ResponseBody
    public ResultVO<String> getOnceHonorsByLikes(@RequestParam("awardname")String awardname,
                                             @RequestParam("department")String department,
                                             @RequestParam("awardlevel")String awardlevel,
                                                 HttpSession session){
        logger.info("getOnceHonorsByLikes");
        People people = (People) session.getAttribute("peopleinfo");
        if (people == null) {
            logger.log(Level.WARNING,"用户没有登录");
            ResultVO<String> resultVO = new ResultVO<String>(-1,"not login");
            resultVO.setData("用户没有登录");
            return resultVO;
        } else {
            Honor honor = new Honor();
            honor.setNumber(people.getNumber());
            honor.setAwardname(awardname);
            honor.setCompany(department);
            honor.setAwardlevel(awardlevel);
            List<Honor> honorList = honorService.getByLikes(honor);
            return setResultVO(honorList,session);
        }
    }

    private ResultVO<String> setResultVO(List<Honor> honorList,HttpSession session){
        if (honorList == null) {
            logger.info("没有荣誉信息");
            session.removeAttribute("honorList");
            session.setAttribute("honorList",null);
            ResultVO<String> resultVO = new ResultVO<String>(-1,"none");
            resultVO.setData("没有荣誉信息");
            return resultVO;
        } else {
            logger.info("成功获取荣誉信息");
            session.removeAttribute("honorList");
            session.setAttribute("honorList",honorList);
            ResultVO<String> resultVO = new ResultVO<String>(200,"success");
            resultVO.setData("成功获取荣誉信息");
            return resultVO;
        }
    }


}
