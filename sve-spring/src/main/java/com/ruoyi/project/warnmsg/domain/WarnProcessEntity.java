package com.ruoyi.project.warnmsg.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.utils.SecurityUtils;

import java.util.Date;

public class WarnProcessEntity {
    private String procId;
    private String warnId;
    private Integer procResult;
    private Integer procState;
    private String procUserName;
    private String exceptionDesc;
    private String procMemo;
    private final String createdBy = SecurityUtils.getUsername();
    private final String updatedBy = SecurityUtils.getUsername();

    public String getProcId() {
        return procId;
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

    public Integer getProcResult() {
        return procResult;
    }

    public void setProcResult(Integer procResult) {
        this.procResult = procResult;
    }

    public Integer getProcState() {
        return procState;
    }

    public void setProcState(Integer procState) {
        this.procState = procState;
    }

    public String getProcUserName() {
        return procUserName;
    }

    public void setProcUserName(String procUserName) {
        this.procUserName = procUserName;
    }

    public String getExceptionDesc() {
        return exceptionDesc;
    }

    public void setExceptionDesc(String exceptionDesc) {
        this.exceptionDesc = exceptionDesc;
    }

    public String getProcMemo() {
        return procMemo;
    }

    public void setProcMemo(String procMemo) {
        this.procMemo = procMemo;
    }
}
