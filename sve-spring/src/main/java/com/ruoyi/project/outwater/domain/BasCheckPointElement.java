package com.ruoyi.project.outwater.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;

import java.io.Serializable;
import java.util.Date;

public class BasCheckPointElement implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private String cpElementId;

    private String sourceId;

    @Excel(name = "监测点编号")
    private String collectId;

    @Excel(name = "因子名称")
    private String elemName;

    @Excel(name = "因子类型编号")
    private String elemTypeId;

    @Excel(name = "因子代码")
    private String elemCode;

    @Excel(name = "量程下限")
    private double rangeMin;

    @Excel(name = "量程上限")
    private double rangeMax;

    @Excel(name = "报警下限")
    private double warnMin;

    @Excel(name = "报警上限")
    private double warnMax;

    @Excel(name = "是否在线", readConverterExp = "0=否,1=是")
    private int isOnline;

    @Excel(name = "检测时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss", type = Excel.Type.EXPORT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date checkDate;

    private int isDeleted;

    private String createdBy;

    private String updatedBy;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date dateCreated;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date dateUpdated;

    @Excel(name = "监测点名称")
    private String checkPointName;

    @Excel(name = "数采名称")
    private String collectName;

    @Excel(name = "污染源名称")
    private String fullName;

    @Excel(name = "因子类型描述")
    private String elemTypeDesc;

    public String getElemTypeDesc() {
        return elemTypeDesc;
    }

    public void setElemTypeDesc(String elemTypeDesc) {
        this.elemTypeDesc = elemTypeDesc;
    }

    public String getCollectName() {
        return collectName;
    }

    public void setCollectName(String collectName) {
        this.collectName = collectName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getCpElementId() {
        return cpElementId;
    }

    public void setCpElementId(String cpElementId) {
        this.cpElementId = cpElementId;
    }

    public String getCollectId() {
        return collectId;
    }

    public void setCollectId(String collectId) {
        this.collectId = collectId;
    }

    public String getElemName() {
        return elemName;
    }

    public void setElemName(String elemName) {
        this.elemName = elemName;
    }

    public String getElemTypeId() {
        return elemTypeId;
    }

    public void setElemTypeId(String elemTypeId) {
        this.elemTypeId = elemTypeId;
    }

    public String getElemCode() {
        return elemCode;
    }

    public void setElemCode(String elemCode) {
        this.elemCode = elemCode;
    }

    public double getRangeMin() {
        return rangeMin;
    }

    public void setRangeMin(double rangeMin) {
        this.rangeMin = rangeMin;
    }

    public double getRangeMax() {
        return rangeMax;
    }

    public void setRangeMax(double rangeMax) {
        this.rangeMax = rangeMax;
    }

    public double getWarnMin() {
        return warnMin;
    }

    public void setWarnMin(double warnMin) {
        this.warnMin = warnMin;
    }

    public double getWarnMax() {
        return warnMax;
    }

    public void setWarnMax(double warnMax) {
        this.warnMax = warnMax;
    }

    public int getIsOnline() {
        return isOnline;
    }

    public void setIsOnline(int isOnline) {
        this.isOnline = isOnline;
    }

    public Date getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(Date checkDate) {
        this.checkDate = checkDate;
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

    public String getCheckPointName() {
        return checkPointName;
    }

    public void setCheckPointName(String checkPointName) {
        this.checkPointName = checkPointName;
    }

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }
}
