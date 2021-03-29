package com.ruoyi.project.construction.domain;

import com.ruoyi.common.utils.SecurityUtils;

public class AcceptRecordEntity {
    private Integer returnId;
    private String sourceId;
    private String tagHeadId;
    private Integer constructionStatus;
    private Integer returnResult;
    private String returnReason;
    private String contactName;
    private String contactPhone;
    private String memo;
    private final String createdBy = SecurityUtils.getUsername();

    public Integer getReturnId() {
        return returnId;
    }

    public void setReturnId(Integer returnId) {
        this.returnId = returnId;
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

    public Integer getConstructionStatus() {
        return constructionStatus;
    }

    public void setConstructionStatus(Integer constructionStatus) {
        this.constructionStatus = constructionStatus;
    }

    public Integer getReturnResult() {
        return returnResult;
    }

    public void setReturnResult(Integer returnResult) {
        this.returnResult = returnResult;
    }

    public String getReturnReason() {
        return returnReason;
    }

    public void setReturnReason(String returnReason) {
        this.returnReason = returnReason;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }
}
