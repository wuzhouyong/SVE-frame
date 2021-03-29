package com.ruoyi.project.construction.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ruoyi.common.utils.SecurityUtils;

import java.util.Date;

public class ConstructionEntity {
    private int tagHeadId;
    private String sourceId;
    private String socialCreditCode;
    private Integer constructionStatus;
    private String constructionUnit;
    private String shortName;
    private String constructionPerson;
    private String constructionPhone;
    private Integer verifyStatus;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date completeDate;
    private String completeMemo;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date acceptanceDate;
    private String acceptanceMemo;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date acceptDate;
    private String acceptBy;
    private String acceptResult;
    private Boolean flag;
    @JsonIgnore
    private final String createdBy = SecurityUtils.getUsername();
    @JsonIgnore
    private final String updatedBy = SecurityUtils.getUsername();

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

    public String getSocialCreditCode() {
        return socialCreditCode;
    }

    public void setSocialCreditCode(String socialCreditCode) {
        this.socialCreditCode = socialCreditCode;
    }

    public Integer getConstructionStatus() {
        return constructionStatus;
    }

    public void setConstructionStatus(Integer constructionStatus) {
        this.constructionStatus = constructionStatus;
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

    public Integer getVerifyStatus() {
        return verifyStatus;
    }

    public void setVerifyStatus(Integer verifyStatus) {
        this.verifyStatus = verifyStatus;
    }

    public Date getCompleteDate() {
        return completeDate;
    }

    public void setCompleteDate(Date completeDate) {
        this.completeDate = completeDate;
    }

    public String getCompleteMemo() {
        return completeMemo;
    }

    public void setCompleteMemo(String completeMemo) {
        this.completeMemo = completeMemo;
    }

    public Date getAcceptanceDate() {
        return acceptanceDate;
    }

    public void setAcceptanceDate(Date acceptanceDate) {
        this.acceptanceDate = acceptanceDate;
    }

    public String getAcceptanceMemo() {
        return acceptanceMemo;
    }

    public void setAcceptanceMemo(String acceptanceMemo) {
        this.acceptanceMemo = acceptanceMemo;
    }

    public Date getAcceptDate() {
        return acceptDate;
    }

    public void setAcceptDate(Date acceptDate) {
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

    public Boolean getFlag() {
        return flag;
    }

    public void setFlag(Boolean flag) {
        this.flag = flag;
    }
}
