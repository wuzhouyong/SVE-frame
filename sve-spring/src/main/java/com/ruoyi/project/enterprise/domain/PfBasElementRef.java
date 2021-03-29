package com.ruoyi.project.enterprise.domain;

public class PfBasElementRef {
    private String srcElemId;
    private int srcTagHeadId;
    private String srcMN;
    private int dstElemId;
    private int dstTagHeadId;
    private boolean isDeleted;
    private String createdBy;
    private String updatedBy;

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

    public String getSrcMN() {
        return srcMN;
    }

    public void setSrcMN(String srcMN) {
        this.srcMN = srcMN;
    }

    public int getDstElemId() {
        return dstElemId;
    }

    public void setDstElemId(int dstElemId) {
        this.dstElemId = dstElemId;
    }

    public int getDstTagHeadId() {
        return dstTagHeadId;
    }

    public void setDstTagHeadId(int dstTagHeadId) {
        this.dstTagHeadId = dstTagHeadId;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
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
