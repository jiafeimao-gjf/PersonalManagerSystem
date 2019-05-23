package edu.gy.personalmanagersystem.controller;

import com.github.pagehelper.PageInfo;
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

import javax.servlet.http.HttpSession;
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
    private Logger logger = Logger.getLogger("HonorController.class");

    @Autowired
    private HonorService honorService;

    @RequestMapping(value = "/addnewhonor",method = RequestMethod.POST)
    @ResponseBody
    public ResultVO<String> addNewHonor(Honor honor){
        int res = honorService.addHonor(honor);
        if (res == 1){
            logger.info("成功插入新的荣誉信息");
            ResultVO<String> resultVO = new ResultVO<String>(200,"success");
            resultVO.setData("成功插入新的荣誉信息");
            return resultVO;
        } else {
            logger.warning("插入新的荣誉信息失败");
            ResultVO<String> resultVO = new ResultVO<String>(-1,"filed");
            resultVO.setData("插入新的荣誉信息失败");
            return resultVO;
        }
    }

    @RequestMapping(value = "/addnewhonors",method = RequestMethod.POST)
    @ResponseBody
    public ResultVO<String> addNewHonors(List<Honor> honorList){
        int res = honorService.addHonors(honorList);
        if (res == honorList.size()){
            logger.info("成功插入新的荣誉信息");
            ResultVO<String> resultVO = new ResultVO<String>(200,"success");
            resultVO.setData("成功插入 "+res+" 条新的荣誉信息");
            return resultVO;
        } else {
            logger.warning("批量插入新的荣誉信息失败，插入了"+res+"条有效信息");
            ResultVO<String> resultVO = new ResultVO<String>(-1,"filed");
            resultVO.setData("批量插入新的荣誉信息失败，插入了"+res+"条有效信息");
            return resultVO;
        }
    }

    @RequestMapping(value = "/gethonorsbynumber",method = RequestMethod.GET)
    @ResponseBody
    public ResultVO<String> getHonorsByNumber(@RequestParam("number")String number,
                                              @RequestParam(value = "pagenum",required = false) Integer pageNum,HttpSession session){
        logger.info("getHonorsByNumber");
        Honor honor = new Honor();
        honor.setNumber(number);
        PageInfo<Honor> honorPageInfo;
        if (pageNum != null){
            honorPageInfo = honorService.getByItem(honor,null,pageNum);
        } else {
            honorPageInfo = honorService.getByItem(honor,null,1);
        }
        session.setAttribute("honorType",2);// 1表示具体某个人的信息
        return setResultVO(honorPageInfo,session);
    }

    @RequestMapping(value = "/gethonorsbylikes",method = RequestMethod.GET)
    @ResponseBody
    public ResultVO<String> getOnceHonorsByLikes(@RequestParam("awardname")String awardname,
                                             @RequestParam("department")String department,
                                             @RequestParam("awardlevel")String awardlevel,
                                                 @RequestParam(value = "pagenum",required = false)Integer pageNum,
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
            PageInfo<Honor> honorPageInfo;
            if (pageNum != null) {
                honorPageInfo = honorService.getByLikes(honor,pageNum);
            } else {
                honorPageInfo = honorService.getByLikes(honor,1);
            }
            session.setAttribute("honorType",1);// 1表示具体某个人的信息
            return setResultVO(honorPageInfo,session);
        }
    }
    @RequestMapping(value = "/lookhonorinfo",method = RequestMethod.GET)
    @ResponseBody
    public ResultVO<String> lookThesisInfo(@RequestParam("honorid") Integer honorid, HttpSession session){
        logger.info("lookHonorInfo");
        Honor honor = honorService.getHonorByKey(honorid);
        if (honor != null) {
            logger.info("成功查询到数据");
            ResultVO<String> resultVO =  new ResultVO<String>(200,"success");
            session.setAttribute("honorinfo",honor);
            resultVO.setData("成功查询到数据");
            return resultVO;
        } else {
            logger.warning("查询数据失败");
            ResultVO<String> resultVO =  new ResultVO<String>(-1,"filed");
            resultVO.setData("查询数据失败");
            return resultVO;
        }
    }

    @RequestMapping(value = "/honordetail")
    public String thesisDetail(){
        logger.info("honordetail");
        return "honordetail";
    }

    @RequestMapping(value = "updatehonorinfo",method = RequestMethod.POST)
    @ResponseBody
    public ResultVO<String> updateThesisInfo(Honor honor){
        logger.info("updateThesisInfo");
        int res = honorService.updateHonorInfo(honor);
        if (res == 1) {
            logger.info("成功更新荣誉信息");
            ResultVO<String> resultVO = new ResultVO<String>(200,"success");
            resultVO.setData("成功更新荣誉信息");
            return resultVO;
        } else {
            logger.warning("更新荣誉信息失败");
            ResultVO<String> resultVO = new ResultVO<String>(-1,"success");
            resultVO.setData("更新荣誉信息失败");
            return resultVO;
        }
    }

    @RequestMapping(value = "getallhonor",method = RequestMethod.GET)
    @ResponseBody
    public ResultVO<String> getAllHonor(@RequestParam(value = "pagenum",required = false)Integer pageNum,
                                        HttpSession session){
        PageInfo<Honor> honorPageInfo;
        if (pageNum != null) {
            honorPageInfo = honorService.getAll(pageNum);
        } else {
            honorPageInfo = honorService.getAll(1);
        }
        return setResultVO(honorPageInfo,session);
    }

    private ResultVO<String> setResultVO(PageInfo<Honor> honorPageInfo,HttpSession session){
        if (honorPageInfo.getList() == null) {
            logger.info("没有荣誉信息");
            session.removeAttribute("honorPageInfo");
            session.setAttribute("honorPageInfo",null);
            ResultVO<String> resultVO = new ResultVO<String>(-1,"none");
            resultVO.setData("没有荣誉信息");
            return resultVO;
        } else {
            logger.info("成功获取荣誉信息");
            session.removeAttribute("honorPageInfo");
            session.setAttribute("honorPageInfo",honorPageInfo);
            ResultVO<String> resultVO = new ResultVO<String>(200,"success");
            resultVO.setData("成功获取荣誉信息");
            return resultVO;
        }
    }


}
