package edu.gy.personalmanagersystem.pojo;

public class User {
    private String number;

    private String password;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number == null ? null : number.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    @Override
    public String toString() {
        return "[number : "+number+", password : "+password+"]";
    }
}