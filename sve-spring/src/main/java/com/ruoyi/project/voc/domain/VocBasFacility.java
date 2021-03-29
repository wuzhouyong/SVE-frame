package com.ruoyi.project.voc.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 【请填写功能名称】对象 bas_facility
 *
 * @author ruoyi
 * @date 2020-08-12
 */
//@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class VocBasFacility extends BaseEntity {
    private static final long serialVersionUID = 1L;

    private Integer facilityId;

    private Integer[] facilityPid;

    private String facilityType;

    private String facilityName;

    private String[] pidName;

    private String memo;

    private String createdBy;

    private String sourceId;

    private String fullName;
    /**
     * 删除标记
     */
    @Excel(name = "删除标记")
    private Integer isDeleted;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date dateCreated;

    private String updatedBy;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date dateUpdated;

    private List<VocCPElementEntity> entity = new ArrayList<>();     //因子

    private List<VocBasTechnology> techno = new ArrayList<>();    //工艺

    private List<VocBasFacilityElecmeter> elecmeter = new ArrayList<>();    //电表

    public Integer getFacilityId() {
        return facilityId;
    }

    public void setFacilityId(Integer facilityId) {
        this.facilityId = facilityId;
    }

    public Integer[] getFacilityPid() {
        return facilityPid;
    }

    public void setFacilityPid(Integer[] facilityPid) {
        this.facilityPid = facilityPid;
    }

    public String getFacilityType() {
        return facilityType;
    }

    public void setFacilityType(String facilityType) {
        this.facilityType = facilityType;
    }

    public String getFacilityName() {
        return facilityName;
    }

    public void setFacilityName(String facilityName) {
        this.facilityName = facilityName;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Date getDateUpdated() {
        return dateUpdated;
    }

    public void setDateUpdated(Date dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

    public List<VocCPElementEntity> getEntity() {
        return entity;
    }

    public void setEntity(List<VocCPElementEntity> entity) {
        this.entity = entity;
    }

    public List<VocBasTechnology> getTechno() {
        return techno;
    }

    public void setTechno(List<VocBasTechnology> techno) {
        this.techno = techno;
    }

    public List<VocBasFacilityElecmeter> getElecmeter() {
        return elecmeter;
    }

    public void setElecmeter(List<VocBasFacilityElecmeter> elecmeter) {
        this.elecmeter = elecmeter;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String[] getPidName() {
        return pidName;
    }

    public void setPidName(String[] pidName) {
        this.pidName = pidName;
    }
}
