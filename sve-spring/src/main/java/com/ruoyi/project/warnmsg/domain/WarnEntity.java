package com.ruoyi.project.warnmsg.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class WarnEntity {
    private String warnId;
    private Integer tagHeadId;
    private String tagHeadName;
    private String sourceId;
    private String sourceName;
    private String townCode;
    private String townName;
    private String acquisitionBrand;
    private String constructionUnit;
    private String warnLevel;
    private String warnLevelName;
    private Integer upgradeLevel;
    private String warnType;
    private String warnTypeName;
    private Integer procStatus;
    private String procStatusName;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date startDate;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date endDate;
    private String warnDesc;

    public String getWarnId() {
        return warnId;
    }

    public void setWarnId(String warnId) {
        this.warnId = warnId;
    }

    public Integer getTagHeadId() {
        return tagHeadId;
    }

    public void setTagHeadId(Integer tagHeadId) {
        this.tagHeadId = tagHeadId;
    }

    public String getTagHeadName() {
        return tagHeadName;
    }

    public void setTagHeadName(String tagHeadName) {
        this.tagHeadName = tagHeadName;
    }

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public String getSourceName() {
        return sourceName;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
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

    public String getAcquisitionBrand() {
        return acquisitionBrand;
    }

    public void setAcquisitionBrand(String acquisitionBrand) {
        this.acquisitionBrand = acquisitionBrand;
    }

    public String getConstructionUnit() {
        return constructionUnit;
    }

    public void setConstructionUnit(String constructionUnit) {
        this.constructionUnit = constructionUnit;
    }

    public String getWarnLevel() {
        return warnLevel;
    }

    public void setWarnLevel(String warnLevel) {
        this.warnLevel = warnLevel;
    }

    public String getWarnLevelName() {
        return warnLevelName;
    }

    public void setWarnLevelName(String warnLevelName) {
        this.warnLevelName = warnLevelName;
    }

    public Integer getUpgradeLevel() {
        return upgradeLevel;
    }

    public void setUpgradeLevel(Integer upgradeLevel) {
        this.upgradeLevel = upgradeLevel;
    }

    public String getWarnType() {
        return warnType;
    }

    public void setWarnType(String warnType) {
        this.warnType = warnType;
    }

    public String getWarnTypeName() {
        return warnTypeName;
    }

    public void setWarnTypeName(String warnTypeName) {
        this.warnTypeName = warnTypeName;
    }

    public void setProcStatus(Integer procStatus) {
        this.procStatus = procStatus;
    }

    public String getProcStatusName() {
        return procStatusName;
    }

    public void setProcStatusName(String procStatusName) {
        this.procStatusName = procStatusName;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getWarnDesc() {
        return warnDesc;
    }

    public void setWarnDesc(String warnDesc) {
        this.warnDesc = warnDesc;
    }

    public Integer getProcStatus() {
        return procStatus;
    }
}
