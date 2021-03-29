package com.ruoyi.project.zaozhi.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class ZZCheckPointElementsEntity {
    private String elementId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date dateCreated;

    private String createdBy;;
    private int isDefault;
    private int isDeleted;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date dateUpdated;

    private String updatedBy;
    private String collectId;

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    private String eleTypeId;
    private String elementCode;
    private String elementDesc;
    private String installLocation;
    private int judgementVal;
    private int isOnline;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date checkDate;

    private int cpElemId;

    public String getElementId() {
        return elementId;
    }

    public void setElementId(String elementId) {
        this.elementId = elementId;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }


    public int getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(int isDefault) {
        this.isDefault = isDefault;
    }

    public int getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(int isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Date getDateUpdated() {
        return dateUpdated;
    }

    public void setDateUpdated(Date dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public String getCollectId() {
        return collectId;
    }

    public void setCollectId(String collectId) {
        this.collectId = collectId;
    }

    public String getEleTypeId() {
        return eleTypeId;
    }

    public void setEleTypeId(String eleTypeId) {
        this.eleTypeId = eleTypeId;
    }

    public String getElementCode() {
        return elementCode;
    }

    public void setElementCode(String elementCode) {
        this.elementCode = elementCode;
    }

    public String getElementDesc() {
        return elementDesc;
    }

    public void setElementDesc(String elementDesc) {
        this.elementDesc = elementDesc;
    }

    public String getInstallLocation() {
        return installLocation;
    }

    public void setInstallLocation(String installLocation) {
        this.installLocation = installLocation;
    }

    public int getJudgementVal() {
        return judgementVal;
    }

    public void setJudgementVal(int judgementVal) {
        this.judgementVal = judgementVal;
    }

    public int getIsOnline() {
        return isOnline;
    }

    public void setIsOnline(int isOnline) {
        this.isOnline = isOnline;
    }

    public Date getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(Date checkDate) {
        this.checkDate = checkDate;
    }

    public int getCpElemId() {
        return cpElemId;
    }

    public void setCpElemId(int cpElemId) {
        this.cpElemId = cpElemId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getEleTypeDesc() {
        return eleTypeDesc;
    }

    public void setEleTypeDesc(String eleTypeDesc) {
        this.eleTypeDesc = eleTypeDesc;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public int getValType() {
        return valType;
    }

    public void setValType(int valType) {
        this.valType = valType;
    }

    private int amount;
    private String eleTypeDesc;
    private String startTime;
    private String endTime;
    private int valType;
}
