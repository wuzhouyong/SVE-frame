package com.ruoyi.project.facility.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class ElementResult {
    private Integer cpElemId;
    private String collectId;
    private String collectName;
    private String sourceName;
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
    private Boolean isOther;
    private Integer srcElemId;
    private String srcElemName;
    private Integer srcTagHeadId;
    private String srcCollectId;
    private String srcCollectName;

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

    public String getCollectName() {
        return collectName;
    }

    public void setCollectName(String collectName) {
        this.collectName = collectName;
    }

    public String getSourceName() {
        return sourceName;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
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

    public Boolean getIsOther() {
        return isOther;
    }

    public void setIsOther(Boolean isOther) {
        this.isOther = isOther;
    }

    public Integer getSrcElemId() {
        return srcElemId;
    }

    public void setSrcElemId(Integer srcElemId) {
        this.srcElemId = srcElemId;
    }

    public String getSrcElemName() {
        return srcElemName;
    }

    public void setSrcElemName(String srcElemName) {
        this.srcElemName = srcElemName;
    }

    public Integer getSrcTagHeadId() {
        return srcTagHeadId;
    }

    public void setSrcTagHeadId(Integer srcTagHeadId) {
        this.srcTagHeadId = srcTagHeadId;
    }

    public String getSrcCollectId() {
        return srcCollectId;
    }

    public void setSrcCollectId(String srcCollectId) {
        this.srcCollectId = srcCollectId;
    }

    public String getSrcCollectName() {
        return srcCollectName;
    }

    public void setSrcCollectName(String srcCollectName) {
        this.srcCollectName = srcCollectName;
    }
}
