package com.ruoyi.project.youyan.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.web.domain.BaseEntity;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 【请填写功能名称】对象 bas_outlet
 *
 * @author ruoyi
 * @date 2020-09-08
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class YYOutletEntity extends BaseEntity {
    private static final long serialVersionUID = 1L;

    private String outletId;

    private String sourceId;

    private Integer isDeleted;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date dateUpdated;

    private String updatedBy;

    private String outletName;

    private String memo;

    private String sourceName;

    public void setOutletId(String outletId) {
        this.outletId = outletId;
    }

    public String getOutletId() {
        return outletId;
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

    public void setDateUpdated(Date dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

    public Date getDateUpdated() {
        return dateUpdated;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setOutletName(String outletName) {
        this.outletName = outletName;
    }

    public String getOutletName() {
        return outletName;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getMemo() {
        return memo;
    }

    public String getSourceName() {
        return sourceName;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("outletId", getOutletId())
                .append("sourceId", getSourceId())
                .append("isDeleted", getIsDeleted())
                .append("dateUpdated", getDateUpdated())
                .append("updatedBy", getUpdatedBy())
                .append("outletName", getOutletName())
                .append("sourceName", getSourceName())
                .append("memo", getMemo())
                .toString();
    }
}
