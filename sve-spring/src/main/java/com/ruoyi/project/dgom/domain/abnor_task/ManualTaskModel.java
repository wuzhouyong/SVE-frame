package com.ruoyi.project.dgom.domain.abnor_task;

public class ManualTaskModel {

  private String taskHeadId;
  private String abnorTitle;
  private String sourceId;
  private String taskStatus;
  private String abnorTaskType;
  private String checkpointId;
  private String taskSentDate;
  private String startTime;
  private String endTime;
  private String alarmLevel;
  private String abnorDesc;
  private String isManualTask;
  private String createdBy;
  private String updatedBy;

    public String getTaskHeadId() {
        return taskHeadId;
    }

    public void setTaskHeadId(String taskHeadId) {
        this.taskHeadId = taskHeadId;
    }

    public String getAbnorTitle() {
        return abnorTitle;
    }

    public void setAbnorTitle(String abnorTitle) {
        this.abnorTitle = abnorTitle;
    }

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public String getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(String taskStatus) {
        this.taskStatus = taskStatus;
    }

    public String getAbnorTaskType() {
        return abnorTaskType;
    }

    public void setAbnorTaskType(String abnorTaskType) {
        this.abnorTaskType = abnorTaskType;
    }

    public String getCheckpointId() {
        return checkpointId;
    }

    public void setCheckpointId(String checkpointId) {
        this.checkpointId = checkpointId;
    }

    public String getTaskSentDate() {
        return taskSentDate;
    }

    public void setTaskSentDate(String taskSentDate) {
        this.taskSentDate = taskSentDate;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getAlarmLevel() {
        return alarmLevel;
    }

    public void setAlarmLevel(String alarmLevel) {
        this.alarmLevel = alarmLevel;
    }

    public String getAbnorDesc() {
        return abnorDesc;
    }

    public void setAbnorDesc(String abnorDesc) {
        this.abnorDesc = abnorDesc;
    }

    public String getIsManualTask() {
        return isManualTask;
    }

    public void setIsManualTask(String isManualTask) {
        this.isManualTask = isManualTask;
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
}
