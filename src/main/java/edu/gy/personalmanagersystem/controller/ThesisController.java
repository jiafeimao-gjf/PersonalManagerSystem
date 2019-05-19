package edu.gy.personalmanagersystem.controller;

import edu.gy.personalmanagersystem.VO.ResultVO;
import edu.gy.personalmanagersystem.pojo.People;
import edu.gy.personalmanagersystem.pojo.Thesis;
import edu.gy.personalmanagersystem.service.ThesisService;
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
 * @ClassName: ThesisController
 * @Author: Gu Jiafei
 * @Date: 2019-05-17 12:14
 * @Version: 1.0
 **/
@Controller
public class ThesisController {

    Logger logger = Logger.getLogger("ThesisController.class");

    @Autowired
    private ThesisService thesisService;

    @RequestMapping(value = "/getthesisbynumber",method = RequestMethod.GET)
    @ResponseBody
    public ResultVO<String> getThesisByNumber(@RequestParam("number")String number, HttpSession session){
        logger.info("getThesisByNumber");
        Thesis thesis = new Thesis();
        thesis.setNumber(number);
        List<Thesis> thesisList = thesisService.getByItem(thesis,null);
        return setResultVO(session,thesisList);
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
