package com.ruoyi.project.youyan.domain;

import java.io.Serializable;

public class YYProssIdStatu implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String warnId;
    private int procState;

    public String getWarnId() {
        return warnId;
    }

    public void setWarnId(String warnId) {
        this.warnId = warnId;
    }

    public int getProcState() {
        return procState;
    }

    public void setProcState(int procState) {
        this.procState = procState;
    }
}
