package com.ruoyi.project.guocheng.domain;

import java.io.Serializable;

public class GCProcModel implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String procId;
    private String warnId;
    private int procState;

    private int warnLevel;

    public int getWarnLevel() {
        return warnLevel;
    }

    public void setWarnLevel(int warnLevel) {
        this.warnLevel = warnLevel;
    }

    public String getProcId() {
        return procId;
    }

    public String getExceptionDesc() {
        return exceptionDesc;
    }

    public void setExceptionDesc(String exceptionDesc) {
        this.exceptionDesc = exceptionDesc;
    }

    public void setProcId(String procId) {
        this.procId = procId;
    }

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


    public String getProcUserName() {
        return procUserName;
    }

    public void setProcUserName(String procUserName) {
        this.procUserName = procUserName;
    }


    private String procDate;
    private String procUserName;
    private String exceptionDesc;

    public String getProcDate() {
        return procDate;
    }

    public void setProcDate(String procDate) {
        this.procDate = procDate;
    }

    private String ProcMemo;
    private String sourceId;


    public String getProcMemo() {
        return ProcMemo;
    }

    public void setProcMemo(String procMemo) {
        ProcMemo = procMemo;
    }

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public String getWid() {
        return wid;
    }

    public void setWid(String wid) {
        this.wid = wid;
    }

    private String wid;
}
