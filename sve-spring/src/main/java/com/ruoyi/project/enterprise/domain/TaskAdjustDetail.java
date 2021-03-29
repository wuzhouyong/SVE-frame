package com.ruoyi.project.enterprise.domain;


import java.io.Serializable;

public class TaskAdjustDetail implements Serializable {

    private String detailId;
    private String taskAdjustId;
    private String aduitStatus;
    private String aduitReason;
    private String aduitDate;
    private String userName;
    private String roleName;
    private String createdBy;
    private String dateCreated;

    public String getDetailId() {
        return detailId;
    }

    public void setDetailId(String detailId) {
        this.detailId = detailId;
    }

    public String getTaskAdjustId() {
        return taskAdjustId;
    }

    public void setTaskAdjustId(String taskAdjustId) {
        this.taskAdjustId = taskAdjustId;
    }

    public String getAduitStatus() {
        return aduitStatus;
    }

    public void setAduitStatus(String aduitStatus) {
        this.aduitStatus = aduitStatus;
    }

    public String getAduitReason() {
        return aduitReason;
    }

    public void setAduitReason(String aduitReason) {
        this.aduitReason = aduitReason;
    }

    public String getAduitDate() {
        return aduitDate;
    }

    public void setAduitDate(String aduitDate) {
        this.aduitDate = aduitDate;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }
}
