package com.ruoyi.project.voc.domain;

public class VocDeleteEntity {

    private String[] array;

    private String userName;

    public String[] getArray() {
        return array;
    }

    public void setArray(String[] array) {
        this.array = array;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public VocDeleteEntity(String[] array, String userName) {
        this.array = array;
        this.userName = userName;
    }
}
