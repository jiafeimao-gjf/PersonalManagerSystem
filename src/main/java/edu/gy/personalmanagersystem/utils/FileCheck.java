package edu.gy.personalmanagersystem.utils;

/**
 * @ClassName: FileCheck
 * @Author: Gu Jiafei
 * @Date: 2019-06-05 12:28
 * @Version: 1.0
 **/
public final class FileCheck {
    public static boolean checkExcelFile(String path){
        String paths[] = path.split("/");
        String filename = paths[paths.length -1];
        return !filename.contains(".xls") && !filename.contains(".xlsx");
    }

    public static void main(String args[]) {
        // 文件后缀名测试
        System.out.println(checkExcelFile("/home/test/aaa.doc"));
        System.out.println(checkExcelFile("/home/test/aaa"));
        System.out.println(checkExcelFile("aaa.doc"));
        System.out.println(checkExcelFile("/home/aaa.xls"));
        System.out.println(checkExcelFile("/home/aaa.xlsx"));
    }
}
