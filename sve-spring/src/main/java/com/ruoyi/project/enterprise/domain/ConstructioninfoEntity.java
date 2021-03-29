package com.ruoyi.project.enterprise.domain;

import com.ruoyi.framework.web.domain.BaseEntity;

import java.io.Serializable;

public class ConstructioninfoEntity extends BaseEntity implements Serializable {

    private String pfSourceId;

    private String tagHeadId;

    private String constructionUnit;

    private String constructionPerson;

    private String constructionPhone;

    private String completeDate;

    private String completeMemo;

    private String acceptanceDate;

    private String acceptanceMemo;

    private String constructionStatus;

    private String createdBy;

    private String updatedBy;

    private String auditDate;

    private String acceptDate;

    private String acceptBy;

    private Integer acceptResult;

    private String shortName;

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getPfSourceId() {
        return pfSourceId;
    }

    public void setPfSourceId(String pfSourceId) {
        this.pfSourceId = pfSourceId;
    }

    public String getTagHeadId() {
        return tagHeadId;
    }

    public void setTagHeadId(String tagHeadId) {
        this.tagHeadId = tagHeadId;
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

    public String getConstructionStatus() {
        return constructionStatus;
    }

    public void setConstructionStatus(String constructionStatus) {
        this.constructionStatus = constructionStatus;
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

    public Integer getAcceptResult() {
        return acceptResult;
    }

    public void setAcceptResult(Integer acceptResult) {
        this.acceptResult = acceptResult;
    }
}
