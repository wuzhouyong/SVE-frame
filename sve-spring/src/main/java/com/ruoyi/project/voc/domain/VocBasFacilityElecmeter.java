package com.ruoyi.project.voc.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 【请填写功能名称】对象 bas_settletechnology
 *
 * @author ruoyi
 * @date 2020-08-12
 */

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class VocBasFacilityElecmeter extends BaseEntity {
    private static final long serialVersionUID = 1L;

    private String elecmeterId;

    private Integer facilityId;

    private String elecmeterName;

    private String memo;

    private String updatedBy;

    private String createdBy;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date dateUpdated;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date dateCreated;

    /**
     * 删除标记
     */
    @Excel(name = "删除标记")
    private Integer isDeleted;

    //因子
    private List<VocCPElementEntity> entity = new ArrayList<>();

    public String getElecmeterId() {
        return elecmeterId;
    }

    public void setElecmeterId(String elecmeterId) {
        this.elecmeterId = elecmeterId;
    }

    public Integer getFacilityId() {
        return facilityId;
    }

    public void setFacilityId(Integer facilityId) {
        this.facilityId = facilityId;
    }

    public String getElecmeterName() {
        return elecmeterName;
    }

    public void setElecmeterName(String elecmeterName) {
        this.elecmeterName = elecmeterName;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getDateUpdated() {
        return dateUpdated;
    }

    public void setDateUpdated(Date dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    public List<VocCPElementEntity> getEntity() {
        return entity;
    }

    public void setEntity(List<VocCPElementEntity> entity) {
        this.entity = entity;
    }
}
