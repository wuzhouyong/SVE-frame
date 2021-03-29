package com.ruoyi.project.dgom.domain;

import com.ruoyi.common.utils.DateUtils;

/**
 * @author solang
 * @date 2020-11-10 10:31
 */
public class ExportSuperviseEntity {
    private int count;
    private String cityName = "东莞市";
    //街镇
    private String townName;
    //工厂名称
    private String factoryName;
    //监控点名称
    private String checkpointName;
    //因子名称
    private String elementName;
    //超标天数
    private Integer overDays;
    //监测时间
    private String monitorTime;
    //排放浓度
    private Double amount;
    //排放标准
    private Double standardval;
    //超标倍数
    private Double overTimes;
    private Integer taskStatus;
    private String taskStatusName;
    //任务起始时间
    private String startDate;
    //任务结束时间
    private String endDate;
    //超标是否属实
    private Integer istrue;
    private String istrueName;
    //超标原因
    private String reason;
    //处理情况
    private String situation;
    //整改情况
    private String recityinfo;

    private String taskType;

    public String getTaskType() {
        return taskType;
    }

    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getCityName() {
        return "东莞市";
    }

    public String getTownName() {
        return townName;
    }

    public void setTownName(String townName) {
        this.townName = townName;
    }

    public String getFactoryName() {
        return factoryName;
    }

    public void setFactoryName(String factoryName) {
        this.factoryName = factoryName;
    }

    public String getCheckpointName() {
        return checkpointName;
    }

    public void setCheckpointName(String checkpointName) {
        this.checkpointName = checkpointName;
    }

    public String getElementName() {
        return elementName;
    }

    public void setElementName(String elementName) {
        this.elementName = elementName;
    }

    public Integer getOverDays() {
        return overDays;
    }

    public void setOverDays(Integer overDays) {
        this.overDays = overDays;
    }

    public String getMonitorTime() {
        return monitorTime;
    }

    public void setMonitorTime(String monitorTime) {
        this.monitorTime = monitorTime;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getStandardval() {
        return standardval;
    }

    public void setStandardval(Double standardval) {
        this.standardval = standardval;
    }

    public Double getOverTimes() {
        return overTimes;
    }

    public void setOverTimes(Double overTimes) {
        this.overTimes = overTimes;
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

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Integer getIstrue() {
        return istrue;
    }

    public void setIstrue(Integer istrue) {
        this.istrue = istrue;
    }

    public String getIstrueName() {
        return istrueName;
    }

    public void setIstrueName(String istrueName) {
        this.istrueName = istrueName;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getSituation() {
        return situation;
    }

    public void setSituation(String situation) {
        this.situation = situation;
    }

    public String getRecityinfo() {
        return recityinfo;
    }

    public void setRecityinfo(String recityinfo) {
        this.recityinfo = recityinfo;
    }

    public ExportSuperviseEntity(SuperviseEntity entity) {
        this.townName = entity.getTownName();
        this.taskType = entity.getTaskType();
        this.factoryName = entity.getFactoryName();
        this.checkpointName = entity.getCheckpointName();
        this.elementName = entity.getElementName();
        this.overDays = entity.getOverDays();
        this.amount = entity.getAmount();
        this.standardval = entity.getStandardval();
        this.overTimes = entity.getOverTimes();
        this.istrue = entity.getIstrue();
        this.reason = entity.getReason();
        this.situation = entity.getSituation();
        this.recityinfo = entity.getRecityinfo();
        this.taskStatus = entity.getTaskStatus();
        this.monitorTime = entity.getMonitorTime() == null ? null : DateUtils.dateTime(entity.getMonitorTime());
        this.startDate = entity.getStartDate() == null ? null : DateUtils.dateTime(entity.getStartDate());
        this.endDate = entity.getEndDate() == null ? null : DateUtils.dateTime(entity.getEndDate());

    }

}
