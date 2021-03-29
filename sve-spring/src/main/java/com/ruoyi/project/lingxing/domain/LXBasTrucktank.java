package com.ruoyi.project.lingxing.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 【请填写功能名称】对象 bas_trucktank
 *
 * @author ruoyi
 * @date 2020-08-25
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class LXBasTrucktank extends BaseEntity {
    private static final long serialVersionUID = 1L;

    private Integer tankId;

    private Integer carId;

    private String tankName;

    private String mn;

    private Double warnRate;

    private Double fullVolume;

    private Double sectionArea;

    private String memo;

    /**
     * 删除标记
     */
    @Excel(name = "删除标记")
    private Integer isDeleted;

    private Integer tankSn;

    private String createdBy;

    private String updatedBy;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date dateCreated;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date dateUpdated;

    public void setTankId(Integer tankId) {
        this.tankId = tankId;
    }

    public Integer getTankId() {
        return tankId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
    }

    public Integer getCarId() {
        return carId;
    }

    public void setMn(String mn) {
        this.mn = mn;
    }

    public String getMn() {
        return mn;
    }

    public void setWarnRate(Double warnRate) {
        this.warnRate = warnRate;
    }

    public Double getWarnRate() {
        return warnRate;
    }

    public void setFullVolume(Double fullVolume) {
        this.fullVolume = fullVolume;
    }

    public Double getFullVolume() {
        return fullVolume;
    }

    public void setSectionArea(Double sectionArea) {
        this.sectionArea = sectionArea;
    }

    public Double getSectionArea() {
        return sectionArea;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getMemo() {
        return memo;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateUpdated(Date dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

    public Date getDateUpdated() {
        return dateUpdated;
    }

    public String getTankName() {
        return tankName;
    }

    public void setTankName(String tankName) {
        this.tankName = tankName;
    }

    public Integer getTankSn() {
        return tankSn;
    }

    public void setTankSn(Integer tankSn) {
        this.tankSn = tankSn;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("tankId", getTankId())
                .append("carId", getCarId())
                .append("mn", getMn())
                .append("warnRate", getWarnRate())
                .append("fullVolume", getFullVolume())
                .append("sectionArea", getSectionArea())
                .append("memo", getMemo())
                .append("isDeleted", getIsDeleted())
                .append("createdBy", getCreatedBy())
                .append("updatedBy", getUpdatedBy())
                .append("dateCreated", getDateCreated())
                .append("dateUpdated", getDateUpdated())
                .append("tankName", getTankName())
                .append("tankSn", getTankSn())
                .toString();
    }
}
