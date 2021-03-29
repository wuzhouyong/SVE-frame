package com.ruoyi.project.outwater.domain;

import com.ruoyi.framework.web.domain.BaseEntity;

import java.io.Serializable;

/**
 * @author solang
 * @date 2020-11-23 9:48
 */
public class SpecwarnmsgEntity extends BaseEntity {

    private String detailId;
    private String headId;
    private String cpElemId;
    private Integer dataType;
    private String dataDate;
    private Double amount;
    private String sourceId;
    private String fullName;
    private String warnLevel;
    private Integer warnType;
    private String startDate;
    private String endDate;
    private String sendDate;
    private Integer warnState;
    private Integer procState;
    private String warnDesc;
    private String townCode;
    private String townName;
    private String constructionUnit;
    private String brand;

    public String getDetailId() {
        return detailId;
    }

    public void setDetailId(String detailId) {
        this.detailId = detailId;
    }

    public String getHeadId() {
        return headId;
    }

    public void setHeadId(String headId) {
        this.headId = headId;
    }

    public String getCpElemId() {
        return cpElemId;
    }

    public void setCpElemId(String cpElemId) {
        this.cpElemId = cpElemId;
    }

    public Integer getDataType() {
        return dataType;
    }

    public void setDataType(Integer dataType) {
        this.dataType = dataType;
    }

    public String getDataDate() {
        return dataDate;
    }

    public void setDataDate(String dataDate) {
        this.dataDate = dataDate;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public String getWarnLevel() {
        return warnLevel;
    }

    public void setWarnLevel(String warnLevel) {
        this.warnLevel = warnLevel;
    }

    public Integer getWarnType() {
        return warnType;
    }

    public void setWarnType(Integer warnType) {
        this.warnType = warnType;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getSendDate() {
        return sendDate;
    }

    public void setSendDate(String sendDate) {
        this.sendDate = sendDate;
    }

    public Integer getWarnState() {
        return warnState;
    }

    public void setWarnState(Integer warnState) {
        this.warnState = warnState;
    }

    public Integer getProcState() {
        return procState;
    }

    public void setProcState(Integer procState) {
        this.procState = procState;
    }

    public String getWarnDesc() {
        return warnDesc;
    }

    public void setWarnDesc(String warnDesc) {
        this.warnDesc = warnDesc;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getTownCode() {
        return townCode;
    }

    public void setTownCode(String townCode) {
        this.townCode = townCode;
    }

    public String getTownName() {
        return townName;
    }

    public void setTownName(String townName) {
        this.townName = townName;
    }

    public String getConstructionUnit() {
        return constructionUnit;
    }

    public void setConstructionUnit(String constructionUnit) {
        this.constructionUnit = constructionUnit;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
}
