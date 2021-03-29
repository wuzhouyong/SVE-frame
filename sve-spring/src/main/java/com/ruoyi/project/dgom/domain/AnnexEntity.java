package com.ruoyi.project.dgom.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author solang
 * @date 2020-09-30 15:27
 */
public class AnnexEntity {
    @JsonIgnore
    private String updatedby;
    private String type;
    private String masterType;
    private String applyId;
    private String fileName;
    private String savePath;
    @JsonIgnore
    private String createdby;
    private String attachfileid;

    private String[] masterTypes;

    public String[] getMasterTypes() {
        return masterTypes;
    }

    public void setMasterTypes(String[] masterTypes) {
        this.masterTypes = masterTypes;
    }

    public String getUpdatedby() {
        return updatedby;
    }

    public void setUpdatedby(String updatedby) {
        this.updatedby = updatedby;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMasterType() {
        return masterType;
    }

    public void setMasterType(String masterType) {
        this.masterType = masterType;
    }

    public String getApplyId() {
        return applyId;
    }

    public void setApplyId(String applyId) {
        this.applyId = applyId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getSavePath() {
        return savePath;
    }

    public void setSavePath(String savePath) {
        this.savePath = savePath;
    }

    public String getCreatedby() {
        return createdby;
    }

    public void setCreatedby(String createdby) {
        this.createdby = createdby;
    }

    public String getAttachfileid() {
        return attachfileid;
    }

    public void setAttachfileid(String attachfileid) {
        this.attachfileid = attachfileid;
    }
}
