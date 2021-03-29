package com.ruoyi.project.dgom.domain.network_apply;

public class MuApprovalModel {
    private String applyId;
    private Integer status;
    private String muApprovalDesc;
    private String muApprovedBy;
    private String updatedBy;

    private String[] applyIds;

    public String[] getApplyIds() {
        return applyIds;
    }

    public void setApplyIds(String[] applyIds) {
        this.applyIds = applyIds;
    }

    public String getApplyId() {
        return applyId;
    }

    public void setApplyId(String applyId) {
        this.applyId = applyId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMuApprovalDesc() {
        return muApprovalDesc;
    }

    public void setMuApprovalDesc(String muApprovalDesc) {
        this.muApprovalDesc = muApprovalDesc;
    }

    public String getMuApprovedBy() {
        return muApprovedBy;
    }

    public void setMuApprovedBy(String muApprovedBy) {
        this.muApprovedBy = muApprovedBy;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }
}
