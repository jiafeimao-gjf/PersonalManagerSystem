package edu.gy.personalmanagersystem.utils;

import edu.gy.personalmanagersystem.pojo.People;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: LoginManagerUtil
 * @Author: Gu Jiafei
 * @Date: 2019-06-01 16:45
 * @Version: 1.0
 **/
public final class LoginManagerUtil {
    private final static Map<String, People> LOGIN_PEOPLE = new HashMap<String,People>();

    public static void addLoginPerson(People people){
        LOGIN_PEOPLE.put(people.getNumber(),people);
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
}
