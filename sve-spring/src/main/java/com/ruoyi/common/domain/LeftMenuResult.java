package com.ruoyi.common.domain;

import com.ruoyi.project.enterprise.domain.LeftMneuData;

public class LeftMenuResult {
    private int code;

    private LeftMneuData data;

    private String msg;

    public LeftMenuResult(int code, LeftMneuData data, String msg) {
        this.code = code;
        this.data = data;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public LeftMneuData getData() {
        return data;
    }

    public void setData(LeftMneuData data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
