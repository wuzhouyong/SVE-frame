package com.ruoyi.project.voc.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.web.domain.BaseEntity;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 【请填写功能名称】对象 bas_settletechnology
 *
 * @author ruoyi
 * @date 2020-08-12
 */

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class VocBasTechnology extends BaseEntity {
    private static final long serialVersionUID = 1L;

    private String techId;

    private Integer facilityId;

    private String techType;

    private Integer techNo;

    private String memo;

    private String updatedBy;

    private String amount;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date dateUpdated;

    /**
     * 删除标记
     */
    @Excel(name = "删除标记")
    private Integer isDeleted;

    //因子
    private List<VocCPElementEntity> entity = new ArrayList<>();

    public String getTechId() {
        return techId;
    }

    public void setTechId(String techId) {
        this.techId = techId;
    }

    public Integer getFacilityId() {
        return facilityId;
    }

    public void setFacilityId(Integer facilityId) {
        this.facilityId = facilityId;
    }

    public String getTechType() {
        return techType;
    }

    public void setTechType(String techType) {
        this.techType = techType;
    }

    public Integer getTechNo() {
        return techNo;
    }

    public void setTechNo(Integer techNo) {
        this.techNo = techNo;
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

    public List<VocCPElementEntity> getEntity() {
        return entity;
    }

    public void setEntity(List<VocCPElementEntity> entity) {
        this.entity = entity;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
