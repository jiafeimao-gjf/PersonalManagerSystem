package edu.gy.personalmanagersystem.pojo;

public class ThesisKey {
    private String number;

    private Integer thesisid;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number == null ? null : number.trim();
    }

    public Integer getThesisid() {
        return thesisid;
    }

    public void setThesisid(Integer thesisid) {
        this.thesisid = thesisid;
    }
}