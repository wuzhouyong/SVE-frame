package com.ruoyi.project.dgom.domain.abnor_task;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class AbnorTaskParam {
    private String townCode;
    private Integer townId;
    private String sourceId;
    private String sourceName;
    private String elementId;
    private Integer taskStatus;
    private Boolean isManualTask;
    private Integer abnorTaskType;
    private Boolean isReported;
    private Boolean isReturnNormal;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date startDate;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date endDate;

    public String getTownCode() {
        return townCode;
    }

    public void setTownCode(String townCode) {
        this.townCode = townCode;
    }

    public Integer getTownId() {
        return townId;
    }

    public void setTownId(Integer townId) {
        this.townId = townId;
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

    public String getElementId() {
        return elementId;
    }

    public void setElementId(String elementId) {
        this.elementId = elementId;
    }

    public Integer getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(Integer taskStatus) {
        this.taskStatus = taskStatus;
    }

    public Boolean getIsManualTask() {
        return isManualTask;
    }

    public void setIsManualTask(Boolean isManualTask) {
        this.isManualTask = isManualTask;
    }

    public Integer getAbnorTaskType() {
        return abnorTaskType;
    }

    public void setAbnorTaskType(Integer abnorTaskType) {
        this.abnorTaskType = abnorTaskType;
    }

    public Boolean getIsReported() {
        return isReported;
    }

    public void setIsReported(Boolean isReported) {
        this.isReported = isReported;
    }

    public Boolean getIsReturnNormal() {
        return isReturnNormal;
    }

    public void setIsReturnNormal(Boolean isReturnNormal) {
        this.isReturnNormal = isReturnNormal;
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
}
