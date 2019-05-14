package edu.gy.personalmanagersystem.pojo;

public class Honor {
    private Integer honorid;

    private String number;

    private String company;

    private String awardname;

    private String awardlevel;

    private String awardcpy;

    private String remarks;

    private String grade;

    public Integer getHonorid() {
        return honorid;
    }

    public void setHonorid(Integer honorid) {
        this.honorid = honorid;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number == null ? null : number.trim();
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company == null ? null : company.trim();
    }

    public String getAwardname() {
        return awardname;
    }

    public void setAwardname(String awardname) {
        this.awardname = awardname == null ? null : awardname.trim();
    }

    public String getAwardlevel() {
        return awardlevel;
    }

    public void setAwardlevel(String awardlevel) {
        this.awardlevel = awardlevel == null ? null : awardlevel.trim();
    }

    public String getAwardcpy() {
        return awardcpy;
    }

    public void setAwardcpy(String awardcpy) {
        this.awardcpy = awardcpy == null ? null : awardcpy.trim();
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade == null ? null : grade.trim();
    }

    @Override
    public String toString() {
        return "[honorid : "+honorid+", number : "+number+", company : "+company+", awardname : "+awardname+
                ", awardlevel : "+awardlevel+", awardcpy : "+awardcpy+", reamrks : "+remarks+", grade :" +grade+"]";
    }
}