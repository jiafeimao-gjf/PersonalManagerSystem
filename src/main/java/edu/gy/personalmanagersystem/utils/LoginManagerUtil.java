package edu.gy.personalmanagersystem.utils;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName: LoginManagerUtil
 * @Author: Gu Jiafei
 * @Date: 2019-06-01 16:45
 * @Version: 1.0
 **/
public final class LoginManagerUtil {
    private static Map<String, Date> LOGIN_PEOPLE = new ConcurrentHashMap<String, Date>();

    public static void addLoginPerson(String number){
        LOGIN_PEOPLE.put(number,new Date());
    }

    public static Boolean isPeopleLogin(String number){
        return LOGIN_PEOPLE.containsKey(number);
    }

    public static boolean removeLoginPerson(String number) {
        if (LOGIN_PEOPLE.containsKey(number)) {
            LOGIN_PEOPLE.remove(number);
            return true;
        } else {
            return false;
        }
    }

    public static void updateLoginTime(String number) {
        if (LOGIN_PEOPLE.containsKey(number)) {
            LOGIN_PEOPLE.put(number,new Date());
        }
    }

    public static void removeTimeoutUser(Date now){
        for (Map.Entry<String,Date> entry:LOGIN_PEOPLE.entrySet()) {
            if (now.getTime() - entry.getValue().getTime() > 1800000) {
                removeLoginPerson(entry.getKey());
            }
        }
    }
}
