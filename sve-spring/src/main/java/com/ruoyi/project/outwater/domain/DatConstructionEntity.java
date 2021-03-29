package com.ruoyi.project.outwater.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.utils.DateStringUtils;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

public class DatConstructionEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private String sourceId;

    private Integer constructionStatus;

    private String constructionUnit;

    private String constructionPerson;

    private String constructionPhone;
    
    private String auditDate;
    
    private String completeDate;

    private String completeMemo;

    private String acceptanceDate;

    private String acceptanceMemo;
    
    private String acceptDate;

    private String acceptBy;

    private Integer acceptResult;

    private String tagHeadId;

    private int isDeleted;

    private String createdBy;

    private String updatedBy;

    private String shortName;
    private String socialCreditCode;
    private boolean flag;

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public String getSocialCreditCode() {
        return socialCreditCode;
    }

    public void setSocialCreditCode(String socialCreditCode) {
        this.socialCreditCode = socialCreditCode;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date dateCreated;

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date dateUpdated;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
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

    public String getAuditDate() {
        return DateStringUtils.formatTimeStr(auditDate);
    }

    public void setAuditDate(String auditDate) {
        this.auditDate = auditDate;
    }

    public String getCompleteDate() {
        return completeDate;
    }

    public void setCompleteDate(String completeDate) {
        this.completeDate = completeDate;
    }

    public String getCompleteMemo() {
        return completeMemo;
    }

    public void setCompleteMemo(String completeMemo) {
        this.completeMemo = completeMemo;
    }

    public String getAcceptanceDate() {
        return acceptanceDate;
    }

    public void setAcceptanceDate(String acceptanceDate) {
        this.acceptanceDate = acceptanceDate;
    }

    public String getAcceptanceMemo() {
        return acceptanceMemo;
    }

    public void setAcceptanceMemo(String acceptanceMemo) {
        this.acceptanceMemo = acceptanceMemo;
    }

    public String getAcceptDate() {
        return DateStringUtils.formatTimeStr(acceptDate);
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

    public Integer getAcceptResult() {
        return acceptResult;
    }

    public void setAcceptResult(Integer acceptResult) {
        this.acceptResult = acceptResult;
    }

    public String getTagHeadId() {
        return tagHeadId;
    }

    public void setTagHeadId(String tagHeadId) {
        this.tagHeadId = tagHeadId;
    }

    public int getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(int isDeleted) {
        this.isDeleted = isDeleted;
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

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getDateUpdated() {
        return dateUpdated;
    }

    public void setDateUpdated(Date dateUpdated) {
        this.dateUpdated = dateUpdated;
    }
}
