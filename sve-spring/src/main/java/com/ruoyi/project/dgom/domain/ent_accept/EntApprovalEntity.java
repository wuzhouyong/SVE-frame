package com.ruoyi.project.dgom.domain.ent_accept;

import com.ruoyi.common.utils.SecurityUtils;

public class EntApprovalEntity {
    private String applyId;
    private Integer status;
    private String approvedBy;
    private String approvalDesc;
    private final String updatedBy = SecurityUtils.getUsername();

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

    public String getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(String approvedBy) {
        this.approvedBy = approvedBy;
    }

    public String getApprovalDesc() {
        return approvalDesc;
    }

    public void setApprovalDesc(String approvalDesc) {
        this.approvalDesc = approvalDesc;
    }
}
