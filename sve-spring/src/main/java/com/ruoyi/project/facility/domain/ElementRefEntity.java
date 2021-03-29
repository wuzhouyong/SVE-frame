package com.ruoyi.project.facility.domain;

import com.ruoyi.common.utils.SecurityUtils;

public class ElementRefEntity {
    private String srcElemId;
    private int srcTagHeadId;
    private String srcCollectId;
    private String dstElemId;
    private int dstTagHeadId;
    private String createdBy = SecurityUtils.getUsername();
    private String updatedBy = SecurityUtils.getUsername();

    public String getSrcElemId() {
        return srcElemId;
    }

    public void setSrcElemId(String srcElemId) {
        this.srcElemId = srcElemId;
    }

    public int getSrcTagHeadId() {
        return srcTagHeadId;
    }

    public void setSrcTagHeadId(int srcTagHeadId) {
        this.srcTagHeadId = srcTagHeadId;
    }

    public String getSrcCollectId() {
        return srcCollectId;
    }

    public void setSrcCollectId(String srcCollectId) {
        this.srcCollectId = srcCollectId;
    }

    public String getDstElemId() {
        return dstElemId;
    }

    public void setDstElemId(String dstElemId) {
        this.dstElemId = dstElemId;
    }

    public int getDstTagHeadId() {
        return dstTagHeadId;
    }

    public void setDstTagHeadId(int dstTagHeadId) {
        this.dstTagHeadId = dstTagHeadId;
    }
}
