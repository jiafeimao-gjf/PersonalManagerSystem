package edu.gy.personalmanagersystem.pojo;

public class Thesis {
    private Integer thesisid;

    private String number;

    private String name;

    private String company;

    private String title;

    private String classify;

    private String magazine;

    public Integer getThesisid() {
        return thesisid;
    }

    public void setThesisid(Integer thesisid) {
        this.thesisid = thesisid;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number == null ? null : number.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company == null ? null : company.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getClassify() {
        return classify;
    }

    public void setClassify(String classify) {
        this.classify = classify == null ? null : classify.trim();
    }

    public String getMagazine() {
        return magazine;
    }

    public void setMagazine(String magazine) {
        this.magazine = magazine == null ? null : magazine.trim();
    }

    @Override
    public String toString() {
        return "[thesisid : "+thesisid+", number : "+number+", name : "+name+
                ", company : "+company+", title : "+title+", classify : "+classify+
                ", magazine : " + magazine + "]";
    }
}