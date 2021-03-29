package com.ruoyi.project.youyan.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.framework.web.domain.BaseEntity;

public class YYCheckPointEntity extends BaseEntity {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private String sourceName;

    private String collectId;

    private String sourceId;

    private String cpName;

    private String mn;

    private String ipAddress;

    private String isOnline;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date checkDate;

    private String acquisitionBrand;

    private String isEnvcert;

    private String createdBy;

    private String updatedBy;

    private String bizSourceId;

    private String collectPass;

    public String getCollectPass() {
        return collectPass;
    }

    public void setCollectPass(String collectPass) {
        this.collectPass = collectPass;
    }

    public String getBizSourceId() {
        return bizSourceId;
    }

    public void setBizSourceId(String bizSourceId) {
        this.bizSourceId = bizSourceId;
    }

    public String getCpName() {
        return cpName;
    }

    public void setCpName(String cpName) {
        this.cpName = cpName;
    }

    public String getSourceName() {
        return sourceName;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }

    public String getCollectId() {
        return collectId;
    }

    public void setCollectId(String collectId) {
        this.collectId = collectId;
    }

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public String getMn() {
        return mn;
    }

    public void setMn(String mn) {
        this.mn = mn;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getIsOnline() {
        return isOnline;
    }

    public void setIsOnline(String isOnline) {
        this.isOnline = isOnline;
    }

    public Date getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(Date checkDate) {
        this.checkDate = checkDate;
    }

    public String getAcquisitionBrand() {
        return acquisitionBrand;
    }

    public void setAcquisitionBrand(String acquisitionBrand) {
        this.acquisitionBrand = acquisitionBrand;
    }

    public String getIsEnvcert() {
        return isEnvcert;
    }

    public void setIsEnvcert(String isEnvcert) {
        this.isEnvcert = isEnvcert;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }
}
