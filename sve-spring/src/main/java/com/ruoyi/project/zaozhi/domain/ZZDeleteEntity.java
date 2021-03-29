package com.ruoyi.project.zaozhi.domain;

public class ZZDeleteEntity {
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

    public ZZDeleteEntity(String[] array, String userName) {
        this.array = array;
        this.userName = userName;
    }
}
