package com.ruoyi.project.lingxing.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;
import com.ruoyi.project.voc.domain.VocCPElementEntity;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 【请填写功能名称】对象 bas_facility
 *
 * @author ruoyi
 * @date 2020-08-12
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class LXStorageinfo extends BaseEntity {
    private static final long serialVersionUID = 1L;

    private String storageId;

    private String collectId;

    private String[] storageIds;

    private String storageName;

    private String sourceId;

    private String fullName;

    private String storageType;

    private String warnRate;

    private String fullVolume;

    private String maxHeight;

    private String sectionArea;

    private String density;

    private String memo;

    private String createdBy;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date dateCreated;

    private String updatedBy;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date dateUpdated;

    @Excel(name = "删除标记")
    private Integer isDeleted;

    //因子
    private List<LXCPElementEntity> entity = new ArrayList<>();

    public String getStorageId() {
        return storageId;
    }

    public void setStorageId(String storageId) {
        this.storageId = storageId;
    }

    public String[] getStorageIds() {
        return storageIds;
    }

    public void setStorageIds(String[] storageIds) {
        this.storageIds = storageIds;
    }

    public String getStorageName() {
        return storageName;
    }

    public void setStorageName(String storageName) {
        this.storageName = storageName;
    }

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getStorageType() {
        return storageType;
    }

    public void setStorageType(String storageType) {
        this.storageType = storageType;
    }

    public String getWarnRate() {
        return warnRate;
    }

    public void setWarnRate(String warnRate) {
        this.warnRate = warnRate;
    }

    public String getFullVolume() {
        return fullVolume;
    }

    public void setFullVolume(String fullVolume) {
        this.fullVolume = fullVolume;
    }

    public String getMaxHeight() {
        return maxHeight;
    }

    public void setMaxHeight(String maxHeight) {
        this.maxHeight = maxHeight;
    }

    public String getSectionArea() {
        return sectionArea;
    }

    public void setSectionArea(String sectionArea) {
        this.sectionArea = sectionArea;
    }

    public String getDensity() {
        return density;
    }

    public void setDensity(String density) {
        this.density = density;
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

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    public List<LXCPElementEntity> getEntity() {
        return entity;
    }

    public void setEntity(List<LXCPElementEntity> entity) {
        this.entity = entity;
    }

    public String getCollectId() {
        return collectId;
    }

    public void setCollectId(String collectId) {
        this.collectId = collectId;
    }
}
