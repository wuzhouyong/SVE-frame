package com.ruoyi.project.enterprise.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.ruoyi.common.utils.DateStringUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TaskAdjustEntity implements Serializable {

    public String taskAdjustId;
    private String tagHeadId;
    private String pfSourceId;
    private String adjustItem;
    private String adjustReason;
    private String otherReason;
    private String memo;
    private String applyDate;
    public String aduitStatus;
    public String aduitReason;
    private String aduitDate;
    private String updatedBy;
    private String constructionStatus;
    private String suspendDate;
    private String verifyStatus;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<TaskAdjustDetail> details = new ArrayList<>();

    public String getSuspendDate() {
        return DateStringUtils.formatDateStr(suspendDate);
    }

    public void setSuspendDate(String suspendDate) {
        this.suspendDate = suspendDate;
    }

    public String getConstructionStatus() {
        return constructionStatus;
    }

    public void setConstructionStatus(String constructionStatus) {
        this.constructionStatus = constructionStatus;
    }

    public String getTaskAdjustId() {
        return taskAdjustId;
    }

    public void setTaskAdjustId(String taskAdjustId) {
        this.taskAdjustId = taskAdjustId;
    }

    public String getTagHeadId() {
        return tagHeadId;
    }

    public void setTagHeadId(String tagHeadId) {
        this.tagHeadId = tagHeadId;
    }

    public String getPfSourceId() {
        return pfSourceId;
    }

    public void setPfSourceId(String pfSourceId) {
        this.pfSourceId = pfSourceId;
    }

    public String getAdjustItem() {
        return adjustItem;
    }

    public void setAdjustItem(String adjustItem) {
        this.adjustItem = adjustItem;
    }

    public String getAdjustReason() {
        return adjustReason;
    }

    public void setAdjustReason(String adjustReason) {
        this.adjustReason = adjustReason;
    }

    public String getOtherReason() {
        return otherReason;
    }

    public void setOtherReason(String otherReason) {
        this.otherReason = otherReason;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getApplyDate() {
        return DateStringUtils.formatTimeStr(applyDate);
    }

    public void setApplyDate(String applyDate) {
        this.applyDate = applyDate;
    }

    public String getAduitStatus() {
        return aduitStatus;
    }

    public void setAduitStatus(String aduitStatus) {
        this.aduitStatus = aduitStatus;
    }

    public String getAduitReason() {
        return aduitReason;
    }

    public void setAduitReason(String aduitReason) {
        this.aduitReason = aduitReason;
    }

    public String getAduitDate() {
        return DateStringUtils.formatTimeStr(aduitDate);
    }

    public void setAduitDate(String aduitDate) {
        this.aduitDate = aduitDate;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public String getVerifyStatus() {
        return verifyStatus;
    }

    public void setVerifyStatus(String verifyStatus) {
        this.verifyStatus = verifyStatus;
    }

    public List<TaskAdjustDetail> getDetails() {
        return details;
    }

    public void setDetails(List<TaskAdjustDetail> details) {
        this.details = details;
    }
}
