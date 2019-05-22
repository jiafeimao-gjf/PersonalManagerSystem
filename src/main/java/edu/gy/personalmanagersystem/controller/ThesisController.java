package edu.gy.personalmanagersystem.controller;

import com.sun.tools.internal.xjc.model.Model;
import edu.gy.personalmanagersystem.VO.ResultVO;
import edu.gy.personalmanagersystem.pojo.People;
import edu.gy.personalmanagersystem.pojo.Thesis;
import edu.gy.personalmanagersystem.service.ThesisService;
import org.omg.PortableInterceptor.INACTIVE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.xml.ws.Response;
import java.awt.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @ClassName: ThesisController
 * @Author: Gu Jiafei
 * @Date: 2019-05-17 12:14
 * @Version: 1.0
 **/
@Controller
public class ThesisController {

    private Logger logger = Logger.getLogger("ThesisController.class");

    @Autowired
    private ThesisService thesisService;

    @RequestMapping(value = "/addnewthesis",method = RequestMethod.POST)
    @ResponseBody
    public ResultVO<String> addNewThesis(Thesis thesis){
        int res = thesisService.addThesis(thesis);
        if (res == 1){
            logger.info("成功插入新的论文信息");
            ResultVO<String> resultVO = new ResultVO<String>(200,"success");
            resultVO.setData("成功插入新的论文信息");
            return resultVO;
        } else {
            logger.warning("插入新的论文信息失败");
            ResultVO<String> resultVO = new ResultVO<String>(-1,"filed");
            resultVO.setData("插入新的论文信息失败");
            return resultVO;
        }
    }

    @RequestMapping(value = "/addnewthesislist",method = RequestMethod.POST)
    @ResponseBody
    public ResultVO<String> addNewThesisList(List<Thesis> thesisList){
        int res = thesisService.addThesisList(thesisList);
        if (res == thesisList.size()){
            logger.info("成功插入新的论文信息");
            ResultVO<String> resultVO = new ResultVO<String>(200,"success");
            resultVO.setData("成功插入 "+res+" 条新的论文信息");
            return resultVO;
        } else {
            logger.warning("批量插入新的论文信息失败，插入了"+res+"条有效信息");
            ResultVO<String> resultVO = new ResultVO<String>(-1,"filed");
            resultVO.setData("批量插入新的论文信息失败，插入了"+res+"条有效信息");
            return resultVO;
        }
    }

    @RequestMapping(value = "/getthesisbynumber",method = RequestMethod.GET)
    @ResponseBody
    public ResultVO<String> getThesisByNumber(@RequestParam("number")String number, HttpSession session){
        logger.info("getThesisByNumber");
        Thesis thesis = new Thesis();
        thesis.setNumber(number);
        List<Thesis> thesisList = thesisService.getByItem(thesis,null);
        return setResultVO(session,thesisList);
    }

    @RequestMapping(value = "/lookthesisinfo",method = RequestMethod.GET)
    @ResponseBody
    public ResultVO<String> lookThesisInfo(@RequestParam("thesisid") Integer thesisid, HttpSession session){
        logger.info("lookThesisInfo");
        Thesis thesis = thesisService.getThesisByKey(thesisid);
        if (thesis != null) {
            logger.info("成功查询到数据");
            ResultVO<String> resultVO =  new ResultVO<String>(200,"success");
            session.setAttribute("thesisinfo",thesis);
            resultVO.setData("成功查询到数据");
            return resultVO;
        } else {
            logger.warning("查询数据失败");
            ResultVO<String> resultVO =  new ResultVO<String>(-1,"filed");
            resultVO.setData("查询数据失败");
            return resultVO;
        }
    }

    @RequestMapping(value = "/getthesisbylikes",method = RequestMethod.GET)
    @ResponseBody
    public ResultVO<String> getThesisByLikes(@RequestParam("name")String name,
                                             @RequestParam("title")String title,
                                             @RequestParam("department")String department,
                                             @RequestParam("classify")String classify,
                                             @RequestParam("magazine")String magazine,
                                             HttpSession session){
        People people = (People) session.getAttribute("peopleinfo");
        if (people == null) {
            logger.log(Level.WARNING,"用户没有登录");
            ResultVO<String> resultVO = new ResultVO<String>(-1,"not login");
            resultVO.setData("用户没有登录");
            return resultVO;
        } else {
            logger.info("getThesisByLikes");
            Thesis thesis = new Thesis();
            thesis.setNumber(people.getNumber());
            thesis.setName(name);
            thesis.setTitle(title);
            thesis.setClassify(classify);
            thesis.setMagazine(magazine);
            thesis.setCompany(department);
            List<Thesis> thesisList = thesisService.getByLikes(thesis);
            return setResultVO(session,thesisList);
        }
    }

    @RequestMapping(value = "/thesisdetail")
    public String thesisDetail(){
        logger.info("thesisdetail");
        return "thesisdetail";
    }

    @RequestMapping(value = "updatethesisinfo",method = RequestMethod.POST)
    @ResponseBody
    public ResultVO<String> updateThesisInfo(Thesis thesis){
        logger.info("updateThesisInfo");
        int res = thesisService.updateThesisInfo(thesis);
        if (res == 1) {
            logger.info("成功跟新论文信息");
            ResultVO<String> resultVO = new ResultVO<String>(200,"success");
            resultVO.setData("成功跟新论文信息");
            return resultVO;
        } else {
            logger.warning("跟新论文信息失败");
            ResultVO<String> resultVO = new ResultVO<String>(-1,"success");
            resultVO.setData("跟新论文信息失败");
            return resultVO;
        }
    }

    private ResultVO<String> setResultVO(HttpSession session,List<Thesis> thesisList){
        if (thesisList == null){
            logger.log(Level.WARNING,"没有论文信息");
            ResultVO<String> resultVO = new ResultVO<String>(-1,"none");
            resultVO.setData("没有论文信息");
            return resultVO;
        } else {
            logger.info("已获取论文信息");
            session.setAttribute("thesisList",thesisList);
            ResultVO<String> resultVO = new ResultVO<String>(200,"success");
            resultVO.setData("已获取论文信息");
            return resultVO;
        }
    }
}
