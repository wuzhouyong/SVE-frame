package com.ruoyi.project.dgom.domain.abnor_task;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.project.common.poi.TreeNode;

import java.util.Date;

public class AbnorTaskResult extends TreeNode<AbnorTaskResult> {

    private String taskHeadId;
    private String taskTitle;
    private Integer taskStatus;
    private String taskStatusName;
    private Integer sourceId;
    private String sourceName;
    private String townName;
    private Integer abnorTaskType;
    private String abnorTaskTypeName;
    private Integer alarmLevel;
    private String alarmLevelName;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date taskSentDate;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date startDate;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date endDate;
    private Integer checkpointId;
    private String checkpointDesc;
    private String abnorDesc;
    private Integer abnorReason;
    private String abnorReasonName;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date returnApproveDate;
    private Boolean isReported;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date dateReported;
    private Boolean isReturnNormal;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date returnNormalDate;
    private String entProcessMemo;
    private String adminUnitName;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date adminUnitProcDate;
    private String adminUnitProcDesc;
    private Integer elementId;
    private String elementDesc;
    private String deptName;

    public String getTaskHeadId() {
        return taskHeadId;
    }

    public void setTaskHeadId(String taskHeadId) {
        this.taskHeadId = taskHeadId;
    }

    public String getTaskTitle() {
        return taskTitle;
    }

    public void setTaskTitle(String taskTitle) {
        this.taskTitle = taskTitle;
    }

    public Integer getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(Integer taskStatus) {
        this.taskStatus = taskStatus;
    }

    public String getTaskStatusName() {
        return taskStatusName;
    }

    public void setTaskStatusName(String taskStatusName) {
        this.taskStatusName = taskStatusName;
    }

    public Integer getSourceId() {
        return sourceId;
    }

    public void setSourceId(Integer sourceId) {
        this.sourceId = sourceId;
    }

    public String getSourceName() {
        return sourceName;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }

    public String getTownName() {
        return townName;
    }

    public void setTownName(String townName) {
        this.townName = townName;
    }

    public Integer getAbnorTaskType() {
        return abnorTaskType;
    }

    public void setAbnorTaskType(Integer abnorTaskType) {
        this.abnorTaskType = abnorTaskType;
    }

    public String getAbnorTaskTypeName() {
        return abnorTaskTypeName;
    }

    public void setAbnorTaskTypeName(String abnorTaskTypeName) {
        this.abnorTaskTypeName = abnorTaskTypeName;
    }

    public Integer getAlarmLevel() {
        return alarmLevel;
    }

    public void setAlarmLevel(Integer alarmLevel) {
        this.alarmLevel = alarmLevel;
    }

    public String getAlarmLevelName() {
        return alarmLevelName;
    }

    public void setAlarmLevelName(String alarmLevelName) {
        this.alarmLevelName = alarmLevelName;
    }

    public Date getTaskSentDate() {
        return taskSentDate;
    }

    public void setTaskSentDate(Date taskSentDate) {
        this.taskSentDate = taskSentDate;
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

    public Integer getCheckpointId() {
        return checkpointId;
    }

    public void setCheckpointId(Integer checkpointId) {
        this.checkpointId = checkpointId;
    }

    public String getCheckpointDesc() {
        return checkpointDesc;
    }

    public void setCheckpointDesc(String checkpointDesc) {
        this.checkpointDesc = checkpointDesc;
    }

    public String getAbnorDesc() {
        return abnorDesc;
    }

    public void setAbnorDesc(String abnorDesc) {
        this.abnorDesc = abnorDesc;
    }

    public Integer getAbnorReason() {
        return abnorReason;
    }

    public void setAbnorReason(Integer abnorReason) {
        this.abnorReason = abnorReason;
    }

    public String getAbnorReasonName() {
        return abnorReasonName;
    }

    public void setAbnorReasonName(String abnorReasonName) {
        this.abnorReasonName = abnorReasonName;
    }

    public Date getReturnApproveDate() {
        return returnApproveDate;
    }

    public void setReturnApproveDate(Date returnApproveDate) {
        this.returnApproveDate = returnApproveDate;
    }

    public Boolean getIsReported() {
        return isReported;
    }

    public void setIsReported(Boolean isReported) {
        this.isReported = isReported;
    }

    public Date getDateReported() {
        return dateReported;
    }

    public void setDateReported(Date dateReported) {
        this.dateReported = dateReported;
    }

    public Boolean getIsReturnNormal() {
        return isReturnNormal;
    }

    public void setIsReturnNormal(Boolean isReturnNormal) {
        this.isReturnNormal = isReturnNormal;
    }

    public Date getReturnNormalDate() {
        return returnNormalDate;
    }

    public void setReturnNormalDate(Date returnNormalDate) {
        this.returnNormalDate = returnNormalDate;
    }

    public String getEntProcessMemo() {
        return entProcessMemo;
    }

    public void setEntProcessMemo(String entProcessMemo) {
        this.entProcessMemo = entProcessMemo;
    }

    public String getAdminUnitName() {
        return adminUnitName;
    }

    public void setAdminUnitName(String adminUnitName) {
        this.adminUnitName = adminUnitName;
    }

    public Date getAdminUnitProcDate() {
        return adminUnitProcDate;
    }

    public void setAdminUnitProcDate(Date adminUnitProcDate) {
        this.adminUnitProcDate = adminUnitProcDate;
    }

    public String getAdminUnitProcDesc() {
        return adminUnitProcDesc;
    }

    public void setAdminUnitProcDesc(String adminUnitProcDesc) {
        this.adminUnitProcDesc = adminUnitProcDesc;
    }

    public Integer getElementId() {
        return elementId;
    }

    public void setElementId(Integer elementId) {
        this.elementId = elementId;
    }

    public String getElementDesc() {
        return elementDesc;
    }

    public void setElementDesc(String elementDesc) {
        this.elementDesc = elementDesc;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }
}
