package com.ruoyi.project.voc.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

public class VocCPElementEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private String elementId;

    private String createdBy;

    private String updatedBy;

    private String collectId;

    private String eleTypeId;

    private String eleCode;

    private String eleDesc;

    private String installLocation;

    private String cpElemId;

    private String outletId;

    private String outletName;

    private Integer facilityId;

    private String techId;

    public String getOutletId() {
        return outletId;
    }

    public void setOutletId(String outletId) {
        this.outletId = outletId;
    }

    public String getOutletName() {
        return outletName;
    }

    public void setOutletName(String outletName) {
        this.outletName = outletName;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date checkDate;

    private String isOnline;

    private String judgementval;

    private String cpName;

    private String sourceName;

    private String eleTypeDesc;

    private String sourceId;

    /**
     * 删除标记
     */
    @Excel(name = "删除标记")
    private Integer isDeleted;

    public String getElementId() {
        return elementId;
    }

    public void setElementId(String elementId) {
        this.elementId = elementId;
    }

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public String getCpName() {
        return cpName;
    }

    public void setCpName(String cpName) {
        this.cpName = cpName;
    }

    public String getSourceName() {
        return sourceName;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }

    public String getEleTypeDesc() {
        return eleTypeDesc;
    }

    public void setEleTypeDesc(String eleTypeDesc) {
        this.eleTypeDesc = eleTypeDesc;
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

    public String getCollectId() {
        return collectId;
    }

    public void setCollectId(String collectId) {
        this.collectId = collectId;
    }

    public String getEleTypeId() {
        return eleTypeId;
    }

    public void setEleTypeId(String eleTypeId) {
        this.eleTypeId = eleTypeId;
    }

    public String getEleCode() {
        return eleCode;
    }

    public void setEleCode(String eleCode) {
        this.eleCode = eleCode;
    }

    public String getEleDesc() {
        return eleDesc;
    }

    public void setEleDesc(String eleDesc) {
        this.eleDesc = eleDesc;
    }

    public String getInstallLocation() {
        return installLocation;
    }

    public void setInstallLocation(String installLocation) {
        this.installLocation = installLocation;
    }

    public String getCpElemId() {
        return cpElemId;
    }

    public void setCpElemId(String cpElemId) {
        this.cpElemId = cpElemId;
    }

    public Date getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(Date checkDate) {
        this.checkDate = checkDate;
    }

    public String getIsOnline() {
        return isOnline;
    }

    public void setIsOnline(String isOnline) {
        this.isOnline = isOnline;
    }

    public String getJudgementval() {
        return judgementval;
    }

    public void setJudgementval(String judgementval) {
        this.judgementval = judgementval;
    }

    public Integer getFacilityId() {
        return facilityId;
    }

    public void setFacilityId(Integer facilityId) {
        this.facilityId = facilityId;
    }

    public String getTechId() {
        return techId;
    }

    public void setTechId(String techId) {
        this.techId = techId;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }
}
