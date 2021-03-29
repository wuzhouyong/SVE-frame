package com.ruoyi.project.facility.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.utils.SecurityUtils;

import java.util.Date;

public class ElementEntity extends ElementRefEntity {
    private Integer cpElemId;
    private String collectId;
    private String eleTypeId;
    private String elementCode;
    private String elementDesc;
    private String installLocation;
    private Double judgementVal;
    private Boolean isOnline;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date checkDate;
    private String facilityId;
    private String techId;
    private String outletId;
    private final String createdBy = SecurityUtils.getUsername();
    private final String updatedBy = SecurityUtils.getUsername();

    public Integer getCpElemId() {
        return cpElemId;
    }

    public void setCpElemId(Integer cpElemId) {
        this.cpElemId = cpElemId;
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

    public String getElementCode() {
        return elementCode;
    }

    public void setElementCode(String elementCode) {
        this.elementCode = elementCode;
    }

    public String getElementDesc() {
        return elementDesc;
    }

    public void setElementDesc(String elementDesc) {
        this.elementDesc = elementDesc;
    }

    public String getInstallLocation() {
        return installLocation;
    }

    public void setInstallLocation(String installLocation) {
        this.installLocation = installLocation;
    }

    public Double getJudgementVal() {
        return judgementVal;
    }

    public void setJudgementVal(Double judgementVal) {
        this.judgementVal = judgementVal;
    }

    public Boolean getIsOnline() {
        return isOnline;
    }

    public void setIsOnline(Boolean isOnline) {
        this.isOnline = isOnline;
    }

    public Date getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(Date checkDate) {
        this.checkDate = checkDate;
    }

    public String getFacilityId() {
        return facilityId;
    }

    public void setFacilityId(String facilityId) {
        this.facilityId = facilityId;
    }

    public String getTechId() {
        return techId;
    }

    public void setTechId(String techId) {
        this.techId = techId;
    }

    public String getOutletId() {
        return outletId;
    }

    public void setOutletId(String outletId) {
        this.outletId = outletId;
    }
}
