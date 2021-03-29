package com.ruoyi.project.dgom.domain.attach;

import com.ruoyi.common.utils.SecurityUtils;

public class InsertAttachModel {
    private String attachFileId;
    private String refId;
    private String refType;
    private String fileName;
    private String attachType;
    private String savePath;
    private String createdBy = SecurityUtils.getUsername();
    private String updatedBy = SecurityUtils.getUsername();

    public String getAttachFileId() {
        return attachFileId;
    }

    public void setAttachFileId(String attachFileId) {
        this.attachFileId = attachFileId;
    }

    public String getRefId() {
        return refId;
    }

    public void setRefId(String refId) {
        this.refId = refId;
    }

    public String getRefType() {
        return refType;
    }

    public void setRefType(String refType) {
        this.refType = refType;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getAttachType() {
        return attachType;
    }

    public void setAttachType(String attachType) {
        this.attachType = attachType;
    }

    public String getSavePath() {
        return savePath;
    }

    public void setSavePath(String savePath) {
        this.savePath = savePath;
    }
}
