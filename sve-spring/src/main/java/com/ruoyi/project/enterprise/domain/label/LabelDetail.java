package com.ruoyi.project.enterprise.domain.label;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ruoyi.common.utils.SecurityUtils;

public class LabelDetail {
    private int labelDetailId;
    private int labelHeadId;
    private String sourceId;
    @JsonIgnore
    private String createdBy = SecurityUtils.getLoginUser().getUsername();
    @JsonIgnore
    private String updatedBy = SecurityUtils.getLoginUser().getUsername();

    public int getLabelDetailId() {
        return labelDetailId;
    }

    public void setLabelDetailId(int labelDetailId) {
        this.labelDetailId = labelDetailId;
    }

    public int getLabelHeadId() {
        return labelHeadId;
    }

    public void setLabelHeadId(int labelHeadId) {
        this.labelHeadId = labelHeadId;
    }

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
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
}
