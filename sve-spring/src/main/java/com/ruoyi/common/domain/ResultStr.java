package com.ruoyi.common.domain;

public class ResultStr {

    private int code;

    private String data;

    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ResultStr() {
    }

    public ResultStr(int code, String data, String message) {
        this.code = code;
        this.data = data;
        this.message = message;
    }
}
