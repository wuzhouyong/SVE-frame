package com.ruoyi.project.construction.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class ConstructionResult {
    private int tagHeadId;
    private String sourceId;
    private String tagHeadName;
    private String sourceName;
    private String townCode;
    private String townName;
    private Integer sourceStatus;
    private String sourceStatusName;
    private Integer constructionStatus;
    private String constructionStatusName;
    private String envContactName;
    private String envContactPhone;
    private String constructionUnit;
    private String shortName;
    private String constructionPerson;
    private String constructionPhone;
    private String acceptBy;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date acceptDate;
    private String acceptResult;

    public int getTagHeadId() {
        return tagHeadId;
    }

    public void setTagHeadId(int tagHeadId) {
        this.tagHeadId = tagHeadId;
    }

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public String getTagHeadName() {
        return tagHeadName;
    }

    public void setTagHeadName(String tagHeadName) {
        this.tagHeadName = tagHeadName;
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

    public Integer getSourceStatus() {
        return sourceStatus;
    }

    public void setSourceStatus(Integer sourceStatus) {
        this.sourceStatus = sourceStatus;
    }

    public String getSourceStatusName() {
        return sourceStatusName;
    }

    public void setSourceStatusName(String sourceStatusName) {
        this.sourceStatusName = sourceStatusName;
    }

    public Integer getConstructionStatus() {
        return constructionStatus;
    }

    public void setConstructionStatus(Integer constructionStatus) {
        this.constructionStatus = constructionStatus;
    }

    public String getConstructionStatusName() {
        return constructionStatusName;
    }

    public void setConstructionStatusName(String constructionStatusName) {
        this.constructionStatusName = constructionStatusName;
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

    public String getConstructionUnit() {
        return constructionUnit;
    }

    public void setConstructionUnit(String constructionUnit) {
        this.constructionUnit = constructionUnit;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
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

    public String getAcceptBy() {
        return acceptBy;
    }

    public void setAcceptBy(String acceptBy) {
        this.acceptBy = acceptBy;
    }

    public Date getAcceptDate() {
        return acceptDate;
    }

    public void setAcceptDate(Date acceptDate) {
        this.acceptDate = acceptDate;
    }

    public String getAcceptResult() {
        return acceptResult;
    }

    public void setAcceptResult(String acceptResult) {
        this.acceptResult = acceptResult;
    }
}
