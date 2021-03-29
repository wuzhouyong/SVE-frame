package com.ruoyi.project.dgom.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;
import com.ruoyi.project.common.poi.AutoIncrement;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author solang
 * @date 2020-11-10 10:31
 */
@Data
public class SuperviseEntity extends BaseEntity {
    @AutoIncrement
    private Integer no;

    private String cityName;
    //督办任务ID
    private Integer suptaskId;

    @Excel(name = "镇街", type = Excel.Type.IMPORT)
    private String townName;
    //工厂名称
    @Excel(name = "企业名称", type = Excel.Type.IMPORT)
    private String factoryName;
    //监控点名称
    @Excel(name = "监控点名称", type = Excel.Type.IMPORT)
    private String checkpointName;
    //因子名称
    @Excel(name = "监测因子", type = Excel.Type.IMPORT)
    private String elementName;

    //监测时间
    @Excel(name = "监测时间", type = Excel.Type.IMPORT)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date monitorTime;

    @Excel(name = "督办类型", type = Excel.Type.IMPORT)
    private String taskType;
    //排放浓度
    @Excel(name = "排放浓度", type = Excel.Type.IMPORT)
    private Double amount;

    //排放标准
    @Excel(name = "排放标准", type = Excel.Type.IMPORT)
    private Double standardval;

    //超标倍数
    @Excel(name = "超标倍数", type = Excel.Type.IMPORT)
    private Double overTimes;

    //超标天数
    @Excel(name = "超标天数", type = Excel.Type.IMPORT)
    private Integer overDays;

    //任务状态 1:草稿 2:待处理 3:超时 4:待核实 5:值守退回 6:已完成
    private Integer taskStatus;
    private String taskStatusName;

    //任务起始时间
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date startDate;

    //任务结束时间
    @DateTimeFormat(pattern = "yyyy-MM-dd ")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date endDate;
    //镇街ID
    private Integer untownId;
    private Integer omtownId;
    //工厂ID
    private String datasourId;
    //监控点ID
    private String checkpointId;
    //因子ID
    private String elementId;
    //处理人
    private String handleby;
    //联系电话
    private String contactPhone;

    //处理时间
    @DateTimeFormat(pattern = "yyyy-MM-dd ")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date handleTime;

    //超标是否属实 ，当excel中汉字位是，导入之后变为1
    private Integer istrue;

    private String isTrueName;

    //超标原因
    private String reason;

    //处理情况
    private String situation;

    //整改情况
    private String recityinfo;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date dateCreated;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date dateUpdated;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String createdby;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String updatedby;

    public Integer getNo() {
        return no;
    }

    public String getCityName() {
        return "东莞市";
    }

    public Integer getSuptaskId() {
        return suptaskId;
    }

    public void setSuptaskId(Integer suptaskId) {
        this.suptaskId = suptaskId;
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

    public Date getMonitorTime() {
        return monitorTime;
    }

    public void setMonitorTime(Date monitorTime) {
        this.monitorTime = monitorTime;
    }

    public String getTaskType() {
        return taskType;
    }

    public void setTaskType(String taskType) {
        this.taskType = taskType;
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

    public Integer getOverDays() {
        return overDays;
    }

    public void setOverDays(Integer overDays) {
        this.overDays = overDays;
    }

    public Integer getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(Integer taskStatus) {
        this.taskStatus = taskStatus;
    }

    public String getTaskStatusName() {
        if (taskStatus != null) {
            switch (taskStatus) {
                case 1:
                    taskStatusName = "草稿";
                    break;
                case 2:
                    taskStatusName = "分局待处理";
                    break;
                case 3:
                    taskStatusName = "超时未处理";
                    break;
                case 4:
                    taskStatusName = "市局待核实";
                    break;
                case 5:
                    taskStatusName = "市局退回";
                    break;
                case 6:
                    taskStatusName = "已完成";
                    break;
            }
        }
        return taskStatusName;
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

    public Integer getUntownId() {
        return untownId;
    }

    public void setUntownId(Integer untownId) {
        this.untownId = untownId;
    }

    public Integer getOmtownId() {
        return omtownId;
    }

    public void setOmtownId(Integer omtownId) {
        this.omtownId = omtownId;
    }

    public String getDatasourId() {
        return datasourId;
    }

    public void setDatasourId(String datasourId) {
        this.datasourId = datasourId;
    }

    public String getCheckpointId() {
        return checkpointId;
    }

    public void setCheckpointId(String checkpointId) {
        this.checkpointId = checkpointId;
    }

    public String getElementId() {
        return elementId;
    }

    public void setElementId(String elementId) {
        this.elementId = elementId;
    }

    public String getHandleby() {
        return handleby;
    }

    public void setHandleby(String handleby) {
        this.handleby = handleby;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public Date getHandleTime() {
        return handleTime;
    }

    public void setHandleTime(Date handleTime) {
        this.handleTime = handleTime;
    }

    public Integer getIstrue() {
        return istrue;
    }

    public void setIstrue(Integer istrue) {
        this.istrue = istrue;
    }

    public String getIsTrueName() {
        isTrueName = "";
        if (istrue != null) {
            isTrueName = getIstrue() == 1 ? "是" : "否";
        }
        return isTrueName;
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

    public String getCreatedby() {
        return createdby;
    }

    public void setCreatedby(String createdby) {
        this.createdby = createdby;
    }

    public String getUpdatedby() {
        return updatedby;
    }

    public void setUpdatedby(String updatedby) {
        this.updatedby = updatedby;
    }
}
