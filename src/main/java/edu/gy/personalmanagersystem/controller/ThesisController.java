package edu.gy.personalmanagersystem.controller;

import com.github.pagehelper.PageInfo;
import edu.gy.personalmanagersystem.VO.ResultVO;
import edu.gy.personalmanagersystem.pojo.People;
import edu.gy.personalmanagersystem.pojo.Thesis;
import edu.gy.personalmanagersystem.service.ThesisService;
import edu.gy.personalmanagersystem.utils.DataTypesUtil;
import edu.gy.personalmanagersystem.utils.SessionManagerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
            ResultVO<String> resultVO = new ResultVO<String>(-1,"failed");
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
            ResultVO<String> resultVO = new ResultVO<String>(-1,"failed");
            resultVO.setData("批量插入新的论文信息失败，插入了"+res+"条有效信息");
            return resultVO;
        }
    }

    @RequestMapping(value = "/getthesisbynumber",method = RequestMethod.GET)
    @ResponseBody
    public ResultVO<String> getThesisByNumber(@RequestParam("number")String number,
                                              @RequestParam(value = "pagenum",required = false)Integer pageNum,
                                              HttpSession session){
        logger.info("getThesisByNumber");
        Thesis thesis = new Thesis();
        thesis.setNumber(number);
        PageInfo<Thesis> thesisPageInfo;
        if (pageNum == null){
            thesisPageInfo = thesisService.getByItem(thesis,null,1);
        } else{
            thesisPageInfo = thesisService.getByItem(thesis,null,pageNum);
        }
        SessionManagerUtil.setThesisDataType(DataTypesUtil.THESIS_DATA_FOR_GET,session);
        return setResultVO(session,thesisPageInfo);
    }

    @RequestMapping(value = "/lookthesisinfo",method = RequestMethod.GET)
    @ResponseBody
    public ResultVO<String> lookThesisInfo(@RequestParam("thesisid") Integer thesisid, HttpSession session){
        logger.info("lookThesisInfo");
        Thesis thesis = thesisService.getThesisByKey(thesisid);
        if (thesis != null) {
            logger.info("成功查询到数据");
            ResultVO<String> resultVO =  new ResultVO<String>(200,"success");
            SessionManagerUtil.setStuffThesis(thesis,session);
            resultVO.setData("成功查询到数据");
            return resultVO;
        } else {
            logger.warning("查询数据失败");
            ResultVO<String> resultVO =  new ResultVO<String>(-1,"failed");
            resultVO.setData("查询数据失败");
            return resultVO;
        }
    }

    @RequestMapping(value = "/getthesisbylikes",method = RequestMethod.GET)
    @ResponseBody
    public ResultVO<String> getThesisByLikes(@RequestParam(value = "who",required = false)String who,
                                             @RequestParam(value = "name",required = false)String name,
                                             @RequestParam(value = "department",required = false)String deparment,
                                             @RequestParam("title")String title,
                                             @RequestParam("classify")String classify,
                                             @RequestParam("magazine")String magazine,
                                             @RequestParam(value = "pagenum",required = false)Integer pageNum,
                                             HttpSession session){
        People people = (People) session.getAttribute("login_people");
        logger.info("getThesisByLikes");
        if (SessionManagerUtil.isDeviceExist(session.getId())) {
            logger.log(Level.WARNING,"用户没有登录");
            ResultVO<String> resultVO = new ResultVO<String>(-1,"not login");
            resultVO.setData("用户没有登录");
            return resultVO;
        } else {
            Thesis thesis = new Thesis();
            if ("admin".equals(who)){
                thesis.setName(name);
                thesis.setCompany(deparment);
            }else {
                thesis.setName(people.getName());
            }
            thesis.setTitle(title);
            thesis.setClassify(classify);
            thesis.setMagazine(magazine);
            PageInfo<Thesis> thesisPageInfo;
            if (pageNum == null){
                thesisPageInfo = thesisService.getByLikes(thesis,1);
            } else{
                thesisPageInfo = thesisService.getByLikes(thesis,pageNum);
            }
            SessionManagerUtil.setThesisDataType(DataTypesUtil.THESIS_DATA_FOR_SEARCH,session);
            return setResultVO(session,thesisPageInfo);
        }
    }

    @RequestMapping(value = "/thesisdetail")
    public String thesisDetail(HttpSession session){
        logger.info("thesisdetail");
        if (SessionManagerUtil.isDeviceExist(session.getId())) {
            return "thesisdetail";
        } else {
            return "index";
        }
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

    @RequestMapping(value = "/getallthesis",method = RequestMethod.GET)
    @ResponseBody
    public ResultVO<String> getAllThesis(@RequestParam(value = "pagenum",required = false)Integer pageNum,
                                         HttpSession session){
        PageInfo<Thesis> thesisPageInfo ;
        if (pageNum!=null) {
            thesisPageInfo = thesisService.getAll(pageNum);
        } else {
            thesisPageInfo= thesisService.getAll(1);
        }
        SessionManagerUtil.setThesisList(thesisPageInfo,session);
        SessionManagerUtil.setThesisDataType(DataTypesUtil.THESIS_DATA_FOR_GET,session);
        ResultVO<String> resultVO = new ResultVO<String>(200,"success");
        resultVO.setData("获取全部论文信息，第 "+pageNum+" 页");
        return resultVO;
    }

    @RequestMapping(value = "/checkthesis",method = RequestMethod.POST)
    @ResponseBody
    public ResultVO<String> checkThesis(@RequestParam("thesisid")Integer thesisId,
                                       HttpSession session){
        logger.info("checkThesis");
        Thesis thesis = new Thesis();
        thesis.setThesisid(thesisId);
        thesis.setChecked(1);
        int res = thesisService.updateThesisInfo(thesis);
        if (res == 1) {
            ResultVO<String> resultVO = new ResultVO<String>(200,"success");
            Thesis thesisInfo = thesisService.getThesisByKey(thesisId);
            SessionManagerUtil.setStuffThesis(thesisInfo,session);
            resultVO.setData("论文审核成功");
            return resultVO;
        } else {
            ResultVO<String> resultVO = new ResultVO<String>(-1,"failed");
            resultVO.setData("论文审核失败");
            return resultVO;
        }
    }

    @RequestMapping(value = "/deletethesis",method = RequestMethod.POST)
    @ResponseBody
    public ResultVO<String> deleteThesis(@RequestParam("thesisid")Integer thesisId){
        logger.info("deleteThesis");
        int res = thesisService.deleteThesis(thesisId);
        if (res == 1) {
            ResultVO<String> resultVO = new ResultVO<String>(200,"success");
            resultVO.setData("论文删除成功");
            return resultVO;
        } else {
            ResultVO<String> resultVO = new ResultVO<String>(-1,"failed");
            resultVO.setData("论文删除失败");
            return resultVO;
        }
    }

    @RequestMapping(value = "/addnewthesis")
    public String addNewThesis(HttpSession session){
        logger.info("addNewThesis");
        if (SessionManagerUtil.isDeviceExist(session.getId())) {
            return "addnewthesis";
        } else {
            return "index";
        }
    }

    private ResultVO<String> setResultVO(HttpSession session,PageInfo<Thesis> thesisPageInfo){
        if (thesisPageInfo.getList() == null){
            logger.log(Level.WARNING,"没有论文信息");
            SessionManagerUtil.setThesisList(null,session);
            ResultVO<String> resultVO = new ResultVO<String>(-1,"none");
            resultVO.setData("没有论文信息");
            return resultVO;
        } else {
            logger.info("已获取论文信息");
            SessionManagerUtil.setThesisList(thesisPageInfo,session);
            ResultVO<String> resultVO = new ResultVO<String>(200,"success");
            resultVO.setData("已获取论文信息");
            return resultVO;
        }
    }
}
