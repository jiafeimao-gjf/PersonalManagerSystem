package edu.gy.personalmanagersystem.utils;

import javax.servlet.http.HttpSession;

/**
 * @ClassName: SessionDataSetUtil
 * @Author: Gu Jiafei
 * @Date: 2019-05-24 15:28
 * @Version: 1.0
 **/
public class SessionDataSetUtil {
    public static void menuDiaplaySet(String chosenMenu, HttpSession session){
        if ("honorinfo".equals(chosenMenu)) {
            session.setAttribute("isinfoshow","hidden");
            session.setAttribute("ispeopleshow","hidden");
            session.setAttribute("ishonorshow","");
            session.setAttribute("isthesisshow","hidden");
        } else if("thesisinfo".equals(chosenMenu)) {
            session.setAttribute("isinfoshow", "hidden");
            session.setAttribute("ishonorshow", "hidden");
            session.setAttribute("ispeopleshow","hidden");
            session.setAttribute("isthesisshow", "");
        }else if("peoplesinfo".equals(chosenMenu)) {
            session.setAttribute("isinfoshow", "hidden");
            session.setAttribute("ishonorshow", "hidden");
            session.setAttribute("ispeopleshow","");
            session.setAttribute("isthesisshow", "hidden");
        } else {
            session.setAttribute("isinfoshow","");
            session.setAttribute("ispeopleshow","hidden");
            session.setAttribute("ishonorshow","hidden");
            session.setAttribute("isthesisshow","hidden");
        }
    }
}
