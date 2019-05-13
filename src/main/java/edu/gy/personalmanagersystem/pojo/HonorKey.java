package edu.gy.personalmanagersystem.pojo;

public class HonorKey {
    private String number;

    private Integer honorid;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number == null ? null : number.trim();
    }

    public Integer getHonorid() {
        return honorid;
    }

    public void setHonorid(Integer honorid) {
        this.honorid = honorid;
    }
}