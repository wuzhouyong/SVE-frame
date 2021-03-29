package com.ruoyi.project.dgom.domain.abnor_task;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class TaskElementResult {
    private String elementDetailId;
   private String taskHeadId;
   private String elementId;
   private String elementDesc;
   @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
   private Date dataDate;
   private Double amount;
   private Double upperLimit;
   private Double lowerLimit;

    public String getElementDetailId() {
        return elementDetailId;
    }

    public void setElementDetailId(String elementDetailId) {
        this.elementDetailId = elementDetailId;
    }

    public String getTaskHeadId() {
        return taskHeadId;
    }

    public void setTaskHeadId(String taskHeadId) {
        this.taskHeadId = taskHeadId;
    }

    public String getElementId() {
        return elementId;
    }

    public void setElementId(String elementId) {
        this.elementId = elementId;
    }

    public String getElementDesc() {
        return elementDesc;
    }

    public void setElementDesc(String elementDesc) {
        this.elementDesc = elementDesc;
    }

    public Date getDataDate() {
        return dataDate;
    }

    public void setDataDate(Date dataDate) {
        this.dataDate = dataDate;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getUpperLimit() {
        return upperLimit;
    }

    public void setUpperLimit(Double upperLimit) {
        this.upperLimit = upperLimit;
    }

    public Double getLowerLimit() {
        return lowerLimit;
    }

    public void setLowerLimit(Double lowerLimit) {
        this.lowerLimit = lowerLimit;
    }
}
