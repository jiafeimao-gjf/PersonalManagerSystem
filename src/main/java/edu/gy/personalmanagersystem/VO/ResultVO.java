package edu.gy.personalmanagersystem.VO;

/**
 * @ClassName: ResultVO
 * @Author: Gu Jiafei
 * @Date: 2019-05-07 16:36
 * @Version: 1.0
 **/
public class ResultVO<T> {
    int code;
    String info;
    T data;

    public ResultVO(int code,String info){
        this.code = code;
        this.info = info;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getInfo() {
        return info;
    }

    public void setData(T data) {
        this.data = data;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public T getData() {
        return data;
    }
}
