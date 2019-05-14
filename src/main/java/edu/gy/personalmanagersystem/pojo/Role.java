package edu.gy.personalmanagersystem.pojo;

public class Role {
    private String number;

    private Integer roleid;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number == null ? null : number.trim();
    }

    public Integer getRoleid() {
        return roleid;
    }

    public void setRoleid(Integer roleid) {
        this.roleid = roleid;
    }

    @Override
    public String toString() {
        return "[number : "+number+", roleid : "+roleid+"]";
    }
}