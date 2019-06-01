package edu.gy.personalmanagersystem.utils;

import com.github.pagehelper.PageInfo;
import edu.gy.personalmanagersystem.pojo.Honor;
import edu.gy.personalmanagersystem.pojo.People;
import edu.gy.personalmanagersystem.pojo.Role;
import edu.gy.personalmanagersystem.pojo.Thesis;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.PublicKey;

/**
 * @ClassName: SessionManagerUtil
 * @Author: Gu Jiafei
 * @Date: 2019-05-24 15:28
 * @Version: 1.0
 **/
public class SessionManagerUtil {
    public static void menuDisplaySet(String chosenMenu,String type,HttpSession session){
        String infoPage = type + "_isInfoShow";
        String stuffPage = type + "_isStuffShow";
        String thesisPage = type + "_isThesisShow";
        String honorPage = type + "_isHonorShow";
        if ("honorinfo".equals(chosenMenu)) {
            session.setAttribute(infoPage,"hidden");
            session.setAttribute(stuffPage,"hidden");
            session.setAttribute(honorPage,"");
            session.setAttribute(thesisPage,"hidden");
        } else if("thesisinfo".equals(chosenMenu)) {
            session.setAttribute(infoPage,"hidden");
            session.setAttribute(stuffPage,"hidden");
            session.setAttribute(honorPage,"hidden");
            session.setAttribute(thesisPage,"");
        }else if("peoplesinfo".equals(chosenMenu)) {
            session.setAttribute(infoPage,"hidden");
            session.setAttribute(stuffPage,"");
            session.setAttribute(honorPage,"hidden");
            session.setAttribute(thesisPage,"hidden");
        } else {
            session.setAttribute(infoPage,"");
            session.setAttribute(stuffPage,"hidden");
            session.setAttribute(honorPage,"hidden");
            session.setAttribute(thesisPage,"hidden");
        }
    }

    public static void setLogin(People people,String type,HttpSession session) {
        session.setAttribute(type+"_login",people);
    }

    public static void setPeopleList(PageInfo<People> pageInfo,HttpSession session){
        session.setAttribute("PeoplePageInfo",pageInfo);
    }

    public static void setThesisList(PageInfo<Thesis> thesisPageInfo,String type,HttpSession session) {
        session.setAttribute(type+"_thesisPageInfo",thesisPageInfo);
    }

    public static void setHonorList(PageInfo<Honor> honorPageInfo, String type, HttpSession session) {
        session.setAttribute(type+"_honorPageInfo",honorPageInfo);
    }

    public static void setHonorDataType(int dataType,String type,HttpSession session){
        session.setAttribute(type+"_honorDataType",dataType);
    }

    public static void setThesisDataType(int dataType,String type,HttpSession session){
        session.setAttribute(type+"_thesisDataType",dataType);
    }

    public static void setRole(Role role,HttpSession session) {
        session.setAttribute("roleInfo",role);
    }

    public static void setStuffHonor(Honor honor,HttpSession session) {
        session.setAttribute("honorInfo",honor);
    }

    public static void setStuff(People people,HttpSession session) {
        session.setAttribute("StuffInfo",people);
    }

    public static void setStuffThesis(Thesis thesis,HttpSession session) {
        session.setAttribute("thesisInfo",thesis);
    }

    public static boolean isSomeLogin(HttpSession session) {
        return session.getAttribute("admin_login") != null
                && session.getAttribute("user_login")!=null;
    }

    public static void removeALl(HttpSession session){

    }
}
