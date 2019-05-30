package edu.gy.personalmanagersystem.utils;

import sun.jvm.hotspot.tools.FinalizerInfo;

import javax.jws.Oneway;
import java.util.Date;
import java.util.Random;

import static java.lang.Thread.sleep;

/**
 * @ClassName: PasswordCreatorUtil
 * @Author: Gu Jiafei
 * @Date: 2019-05-30 17:00
 * @Version: 1.0
 **/
public class PasswordCreatorUtil {

    final static String string = "abcedfghijklmnopqrstuvwxyz1234567890/@#$%&*ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    final static char[] chars = string.toCharArray();
    final static Random random = new Random();
    public static String creatorPwd(int len){
        StringBuilder pwd = new StringBuilder();
        int length = string.length();
        random.setSeed(new Date().getTime()%100000000L);
        for (int i = 0;i < len;i++){
            pwd.append(chars[Math.abs(random.nextInt())%length]);
        }
        return pwd.toString();
    }

    public static String getDefaultPwd(){
        return "123456";
    }

    public static void main(String args[]) {
        for (int i = 0;i < 10;i++){
            try {
                sleep(10);
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }
            System.out.println(creatorPwd(i+Math.abs(random.nextInt())%10));
        }
    }
}
