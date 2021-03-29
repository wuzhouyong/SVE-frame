package com.ruoyi.project.dgom.domain.abnor_task;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.utils.SecurityUtils;

import java.util.Date;

public class EnterpriseProcessModel {
    private String taskHeadId;
    private Integer taskStatus;
    private Integer abnorReason;
    private Boolean isReported;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dateReported;
    private Boolean isReturnNormal;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date returnNormalDate;
    private String entProcessMemo;
    private String procUserName;
    private String updatedBy = SecurityUtils.getUsername();

    public String getTaskHeadId() {
        return taskHeadId;
    }

    public void setTaskHeadId(String taskHeadId) {
        this.taskHeadId = taskHeadId;
    }

    public Integer getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(Integer taskStatus) {
        this.taskStatus = taskStatus;
    }

    public Integer getAbnorReason() {
        return abnorReason;
    }

    public void setAbnorReason(Integer abnorReason) {
        this.abnorReason = abnorReason;
    }

    public Boolean getReported() {
        return isReported;
    }

    public void setReported(Boolean reported) {
        isReported = reported;
    }

    public Date getDateReported() {
        return dateReported;
    }

    public void setDateReported(Date dateReported) {
        this.dateReported = dateReported;
    }

    public Boolean getIsReturnNormal() {
        return isReturnNormal;
    }

    public void setIsReturnNormal(Boolean isReturnNormal) {
        this.isReturnNormal = isReturnNormal;
    }

    public Date getReturnNormalDate() {
        return returnNormalDate;
    }

    public void setReturnNormalDate(Date returnNormalDate) {
        this.returnNormalDate = returnNormalDate;
    }

    public String getEntProcessMemo() {
        return entProcessMemo;
    }

    public void setEntProcessMemo(String entProcessMemo) {
        this.entProcessMemo = entProcessMemo;
    }

    public String getProcUserName() {
        return procUserName;
    }

    public void setProcUserName(String procUserName) {
        this.procUserName = procUserName;
    }
}
