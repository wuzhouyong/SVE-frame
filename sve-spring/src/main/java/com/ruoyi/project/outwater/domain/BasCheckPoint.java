package com.ruoyi.project.outwater.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.aspectj.lang.annotation.Excel.Type;
import com.ruoyi.framework.web.domain.BaseEntity;

public class BasCheckPoint extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Excel(name = "监测点ID")
    private String checkPointId;

    @Excel(name = "数采ID")
    private String collectId;

    @Excel(name = "污染源编号")
    private String sourceId;

    @Excel(name = "监测点名称")
    private String checkPointName;

    @Excel(name = "监测点类型", readConverterExp = "1=污水检测井，2=雨水检测井")
    private String checkPointType;

    @Excel(name = "是否在线", readConverterExp = "0=否,1=是")
    private int isOnline;

    @Excel(name = "检测时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss", type = Type.EXPORT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date checkDate;

    private int isDeleted;

    private String createdBy;

    private String updatedBy;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date dateCreated;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date dateUpdated;

    private String fullName;

    private String collectName;

    private String mn;

    private String ipAddress;

    private String acquisitionBrand;

    private String isEnvcert;

    private String latItude;

    private String longItude;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getCollectName() {
        return collectName;
    }

    public void setCollectName(String collectName) {
        this.collectName = collectName;
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

    public String getCheckPointId() {
        return checkPointId;
    }

    public void setCheckPointId(String checkPointId) {
        this.checkPointId = checkPointId;
    }

    public String getCollectId() {
        return collectId;
    }

    public void setCollectId(String collectId) {
        this.collectId = collectId;
    }

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public String getCheckPointName() {
        return checkPointName;
    }

    public void setCheckPointName(String checkPointName) {
        this.checkPointName = checkPointName;
    }

    public String getCheckPointType() {
        return checkPointType;
    }

    public void setCheckPointType(String checkPointType) {
        this.checkPointType = checkPointType;
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

    public String getMn() {
        return mn;
    }

    public void setMn(String mn) {
        this.mn = mn;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getAcquisitionBrand() {
        return acquisitionBrand;
    }

    public void setAcquisitionBrand(String acquisitionBrand) {
        this.acquisitionBrand = acquisitionBrand;
    }

    public String getIsEnvcert() {
        return isEnvcert;
    }

    public void setIsEnvcert(String isEnvcert) {
        this.isEnvcert = isEnvcert;
    }

    public String getLatItude() {
        return latItude;
    }

    public void setLatItude(String latItude) {
        this.latItude = latItude;
    }

    public String getLongItude() {
        return longItude;
    }

    public void setLongItude(String longItude) {
        this.longItude = longItude;
    }
}
