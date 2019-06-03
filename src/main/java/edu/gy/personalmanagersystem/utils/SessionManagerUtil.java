package edu.gy.personalmanagersystem.utils;

import com.github.pagehelper.PageInfo;
import edu.gy.personalmanagersystem.pojo.Honor;
import edu.gy.personalmanagersystem.pojo.People;
import edu.gy.personalmanagersystem.pojo.Role;
import edu.gy.personalmanagersystem.pojo.Thesis;
import org.springframework.scheduling.annotation.Scheduled;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.PublicKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName: SessionManagerUtil
 * @Author: Gu Jiafei
 * @Date: 2019-05-24 15:28
 * @Version: 1.0
 **/
public class SessionManagerUtil {
    public static void menuDisplaySet(String chosenMenu,HttpSession session){
        String infoPage = "isInfoShow";
        String stuffPage = "isStuffShow";
        String thesisPage = "isThesisShow";
        String honorPage = "isHonorShow";
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

    public static void setLogin(People people,HttpSession session) {
        session.setAttribute("login_people",people);
        addSessionId(session.getId());
    }

    public static void setStuffList(PageInfo<People> peoplePageInfo,HttpSession session){
        session.setAttribute("peoplePageInfo",peoplePageInfo);
    }

    public static void setThesisList(PageInfo<Thesis> thesisPageInfo,HttpSession session) {
        session.setAttribute("thesisPageInfo",thesisPageInfo);
    }

    public static void setHonorList(PageInfo<Honor> honorPageInfo,  HttpSession session) {
        session.setAttribute("honorPageInfo",honorPageInfo);
    }

    public static void setHonorDataType(int dataType,HttpSession session){
        session.setAttribute("honorDataType",dataType);
    }

    public static void setThesisDataType(int dataType,HttpSession session){
        session.setAttribute("thesisDataType",dataType);
    }

    public static void setStuffDataType(int dataType,HttpSession session) {
        session.setAttribute("stuffDataType",dataType);
    }

    public static void setRole(Role role,HttpSession session) {
        session.setAttribute("roleInfo",role);
    }

    public static void setStuffHonor(Honor honor,HttpSession session) {
        session.setAttribute("honorInfo",honor);
    }

    public static void setStuff(People people,HttpSession session) {
        session.setAttribute("stuffInfo",people);
    }

    public static void setStuffThesis(Thesis thesis,HttpSession session) {
        session.setAttribute("thesisInfo",thesis);
    }

    public static void removeALl(HttpSession session){
        if (session.getAttribute("login_people")!=null) {
            session.removeAttribute("login_people");
            session.removeAttribute("thesisPageInfo");
            session.removeAttribute("honorPageInfo");
            session.removeAttribute("peoplePageInfo");
            session.removeAttribute("roleInfo");
            session.removeAttribute("honorInfo");
            session.removeAttribute("thesisInfo");
            session.removeAttribute("stuffInfo");
            session.removeAttribute("honorDataType");
            session.removeAttribute("thesisDataType");
            session.removeAttribute("isInfoShow");
            session.removeAttribute("isStuffShow");
            session.removeAttribute("isHonorShow");
            session.removeAttribute("isThesisShow");
            removeSessionId(session.getId());
        }

    }

    private final static Map<String, Date> SESSION_ID_MAP = new ConcurrentHashMap<String, Date>();

    private static boolean isSessionExist(String sessionId){
        return SESSION_ID_MAP.containsKey(sessionId);
    }

    private static void addSessionId(String sessionId){
        if (!isSessionExist(sessionId)) {
            SESSION_ID_MAP.put(sessionId,new Date());
        }
    }

    private static void removeSessionId(String sessionId){
        if (SESSION_ID_MAP.containsKey(sessionId)) {
            SESSION_ID_MAP.remove(sessionId);
        }
    }

    public static boolean isDeviceExist(String sessionId) {
        return isSessionExist(sessionId);
    }

    /** 定时调用，去除超时的sessionID
     * @Author Gu Jiafei
     * @Date 17:23 2019-06-03
     * @Param
     * @return
     **/
    @Scheduled(fixedDelay = 1800000)
    public static void removeTimeoutSessionId(){
        Date now = new Date();
        for (Map.Entry<String,Date> entry :SESSION_ID_MAP.entrySet()) {
            if (entry.getValue().getTime() - now.getTime() > 1800000) {
                removeSessionId(entry.getKey());
            }
        }
    }
}
