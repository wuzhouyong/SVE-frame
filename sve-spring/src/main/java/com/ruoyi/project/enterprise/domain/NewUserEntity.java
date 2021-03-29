package com.ruoyi.project.enterprise.domain;

public class NewUserEntity {

    private String userName;
    private String password;

    public NewUserEntity(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public NewUserEntity(String userName, String password) {

        this.userName = userName;
        this.password = password;
    }
}
