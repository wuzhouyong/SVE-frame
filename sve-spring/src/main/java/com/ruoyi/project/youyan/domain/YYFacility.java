package com.ruoyi.project.youyan.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 油烟治理设施信息对象 bas_facility
 *
 * @author ruoyi
 * @date 2020-09-22
 */
public class YYFacility {
    /**
     * 主键
     */
    private Integer facilityId;

    private Integer facilityPid;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String facilityPids;

    /**
     * 设施类型
     */
    @Excel(name = "设施类型")
    private String facilityType;

    /**
     * 设施名字
     */
    @Excel(name = "设施名字")
    private String facilityName;

    private String memo;

    @JsonIgnore
    private String createdBy;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date dateCreated;

    @JsonIgnore
    private String updatedBy;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date dateUpdated;

    /**
     * 关联企业
     */
    @Excel(name = "关联企业")
    private String sourceId;

    private String fullName;

    @JsonIgnore
    private Integer isDeleted;

    /**
     * 判断设施起停的因子ID
     */
    @Excel(name = "判断设施起停的因子ID")
    private Integer cpElemId;

    /**
     * 判断启停的阈值
     */
    @Excel(name = "判断启停的阈值")
    private Double threshold;

    private Double amount;

    private Integer depthLevel;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<YYFacility> children = new ArrayList<YYFacility>();

    List<YYCPElementEntity> entity = new ArrayList<>(); //因子

    public Integer getFacilityPid() {
        return facilityPid;
    }

    public void setFacilityPid(Integer facilityPid) {
        this.facilityPid = facilityPid;
    }

    public void setFacilityId(Integer facilityId) {
        this.facilityId = facilityId;
    }

    public Integer getFacilityId() {
        return facilityId;
    }

    public void setFacilityType(String facilityType) {
        this.facilityType = facilityType;
    }

    public String getFacilityType() {
        return facilityType;
    }

    public void setFacilityName(String facilityName) {
        this.facilityName = facilityName;
    }

    public String getFacilityName() {
        return facilityName;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getMemo() {
        return memo;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setDateUpdated(Date dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

    public Date getDateUpdated() {
        return dateUpdated;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public String getSourceId() {
        return sourceId;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setCpElemId(Integer cpElemId) {
        this.cpElemId = cpElemId;
    }

    public Integer getCpElemId() {
        return cpElemId;
    }

    public Double getThreshold() {
        return threshold;
    }

    public void setThreshold(Double threshold) {
        this.threshold = threshold;
    }

    public List<YYCPElementEntity> getEntity() {
        return entity;
    }

    public void setEntity(List<YYCPElementEntity> entity) {
        this.entity = entity;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Integer getDepthLevel() {
        return depthLevel;
    }

    public void setDepthLevel(Integer depthLevel) {
        this.depthLevel = depthLevel;
    }

    public List<YYFacility> getChildren() {
        return children;
    }

    public void setChildren(List<YYFacility> children) {
        this.children = children;
    }

    public String getFacilityPids() {
        return facilityPids;
    }

    public void setFacilityPids(String facilityPids) {
        this.facilityPids = facilityPids;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("facilityId", getFacilityId())
                .append("facilityType", getFacilityType())
                .append("facilityName", getFacilityName())
                .append("memo", getMemo())
                .append("createdBy", getCreatedBy())
                .append("dateCreated", getDateCreated())
                .append("updatedBy", getUpdatedBy())
                .append("dateUpdated", getDateUpdated())
                .append("sourceId", getSourceId())
                .append("isDeleted", getIsDeleted())
                .append("cpElemId", getCpElemId())
                .append("threshold", getThreshold())
                .append("entity", getEntity())
                .toString();
    }
}
