package com.ruoyi.project.zaozhi.domain;

import com.ruoyi.framework.web.domain.BaseEntity;


public class ZZGatherEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private String sourceId;
    private String fullName;
    private String townCode;
    private String townName;
    private String constructionUnit;
    private Integer warnLevel;
    private Integer warnCount;
    private Integer factoryCount;
    private Integer dutyCount;
    private Integer substationCount;
    private Integer isExceed;
    private String unitName;

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
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

    public Integer getWarnLevel() {
        return warnLevel;
    }

    public void setWarnLevel(Integer warnLevel) {
        this.warnLevel = warnLevel;
    }

    public Integer getWarnCount() {
        return warnCount;
    }

    public void setWarnCount(Integer warnCount) {
        this.warnCount = warnCount;
    }

    public Integer getFactoryCount() {
        return factoryCount;
    }

    public void setFactoryCount(Integer factoryCount) {
        this.factoryCount = factoryCount;
    }

    public Integer getDutyCount() {
        return dutyCount;
    }

    public void setDutyCount(Integer dutyCount) {
        this.dutyCount = dutyCount;
    }

    public Integer getSubstationCount() {
        return substationCount;
    }

    public void setSubstationCount(Integer substationCount) {
        this.substationCount = substationCount;
    }

    public Integer getIsExceed() {
        return isExceed;
    }

    public void setIsExceed(Integer isExceed) {
        this.isExceed = isExceed;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }
}
