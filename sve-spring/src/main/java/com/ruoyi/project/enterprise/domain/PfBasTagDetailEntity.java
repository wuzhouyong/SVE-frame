package com.ruoyi.project.enterprise.domain;

import java.io.Serializable;

public class PfBasTagDetailEntity implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private String tagDetailId;

    private String tagHeadId;

    private String pfSourceId;

    private Boolean isCheck;

    private String bizSourceId;

    private String isAudit;

    private Integer listType;

    private String createdBy;

    private String updatedBy;

    public String getIsAudit() {
        return isAudit;
    }

    public void setIsAudit(String isAudit) {
        this.isAudit = isAudit;
    }

    public Integer getListType() {
        return listType;
    }

    public void setListType(Integer listType) {
        this.listType = listType;
    }

    public Boolean getIsCheck() {
        return isCheck;
    }

    public void setIsCheck(Boolean isCheck) {
        this.isCheck = isCheck;
    }

    public String getTagDetailId() {
        return tagDetailId;
    }

    public void setTagDetailId(String tagDetailId) {
        this.tagDetailId = tagDetailId;
    }

    public String getTagHeadId() {
        return tagHeadId;
    }

    public void setTagHeadId(String tagHeadId) {
        this.tagHeadId = tagHeadId;
    }

    public String getPfSourceId() {
        return pfSourceId;
    }

    public void setPfSourceId(String pfSourceId) {
        this.pfSourceId = pfSourceId;
    }

    public String getBizSourceId() {
        return bizSourceId;
    }

    public void setBizSourceId(String bizSourceId) {
        this.bizSourceId = bizSourceId;
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
