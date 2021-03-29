package com.ruoyi.project.dgom.domain.abnor_task;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class ProcDetailModel {

    private String procDetailId;
    private String taskHeadId;
    private Integer userType;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date procDate;
    private String procDesc;
    private String procUnit;
    private String procUserName;

    public String getProcDetailId() {
        return procDetailId;
    }

    public void setProcDetailId(String procDetailId) {
        this.procDetailId = procDetailId;
    }

    public String getTaskHeadId() {
        return taskHeadId;
    }

    public void setTaskHeadId(String taskHeadId) {
        this.taskHeadId = taskHeadId;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public Date getProcDate() {
        return procDate;
    }

    public void setProcDate(Date procDate) {
        this.procDate = procDate;
    }

    public String getProcDesc() {
        return procDesc;
    }

    public void setProcDesc(String procDesc) {
        this.procDesc = procDesc;
    }

    public String getProcUnit() {
        return procUnit;
    }

    public void setProcUnit(String procUnit) {
        this.procUnit = procUnit;
    }

    public String getProcUserName() {
        return procUserName;
    }

    public void setProcUserName(String procUserName) {
        this.procUserName = procUserName;
    }
}
