package com.ruoyi.project.enterprise.domain;

import com.ruoyi.framework.web.domain.BaseEntity;

public class TaskSourceEntity extends BaseEntity {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String sourceId;
    private String tagHeadId;
    private String tagHeadName;
    private String townName;
    private String fullName;
    private String sourceStatus;
    private String constructionUnit;
    private String constructionPerson;
    private String constructionPhone;
    private String constructionStatus;
    private String loginFlag;
    private String verifyStatus;
    private String envContactName;
    private String envContactPhone;
    private String auditDate;
    private String acceptDate;
    private String acceptBy;
    private String acceptResult;
    private String status;
    private String shortName;
    private String taskAdjustId;
    private Integer waitReview;
    private String nickName;

    public String getTaskAdjustId() {
        return taskAdjustId;
    }

    public void setTaskAdjustId(String taskAdjustId) {
        this.taskAdjustId = taskAdjustId;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public String getTagHeadId() {
        return tagHeadId;
    }

    public void setTagHeadId(String tagHeadId) {
        this.tagHeadId = tagHeadId;
    }

    public String getTagHeadName() {
        return tagHeadName;
    }

    public void setTagHeadName(String tagHeadName) {
        this.tagHeadName = tagHeadName;
    }

    public String getTownName() {
        return townName;
    }

    public void setTownName(String townName) {
        this.townName = townName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getSourceStatus() {
        return sourceStatus;
    }

    public void setSourceStatus(String sourceStatus) {
        this.sourceStatus = sourceStatus;
    }

    public String getConstructionUnit() {
        return constructionUnit;
    }

    public void setConstructionUnit(String constructionUnit) {
        this.constructionUnit = constructionUnit;
    }

    public String getConstructionPerson() {
        return constructionPerson;
    }

    public void setConstructionPerson(String constructionPerson) {
        this.constructionPerson = constructionPerson;
    }

    public String getConstructionPhone() {
        return constructionPhone;
    }

    public void setConstructionPhone(String constructionPhone) {
        this.constructionPhone = constructionPhone;
    }

    public String getConstructionStatus() {
        return constructionStatus;
    }

    public void setConstructionStatus(String constructionStatus) {
        this.constructionStatus = constructionStatus;
    }

    public String getLoginFlag() {
        return loginFlag;
    }

    public void setLoginFlag(String loginFlag) {
        this.loginFlag = loginFlag;
    }

    public String getVerifyStatus() {
        return verifyStatus;
    }

    public void setVerifyStatus(String verifyStatus) {
        this.verifyStatus = verifyStatus;
    }

    public String getEnvContactName() {
        return envContactName;
    }

    public void setEnvContactName(String envContactName) {
        this.envContactName = envContactName;
    }

    public String getEnvContactPhone() {
        return envContactPhone;
    }

    public void setEnvContactPhone(String envContactPhone) {
        this.envContactPhone = envContactPhone;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAuditDate() {
        return auditDate;
    }

    public void setAuditDate(String auditDate) {
        this.auditDate = auditDate;
    }

    public String getAcceptDate() {
        return acceptDate;
    }

    public void setAcceptDate(String acceptDate) {
        this.acceptDate = acceptDate;
    }

    public String getAcceptBy() {
        return acceptBy;
    }

    public void setAcceptBy(String acceptBy) {
        this.acceptBy = acceptBy;
    }

    public String getAcceptResult() {
        return acceptResult;
    }

    public void setAcceptResult(String acceptResult) {
        this.acceptResult = acceptResult;
    }

    public Integer getWaitReview() {
        return waitReview;
    }

    public void setWaitReview(Integer waitReview) {
        this.waitReview = waitReview;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
}