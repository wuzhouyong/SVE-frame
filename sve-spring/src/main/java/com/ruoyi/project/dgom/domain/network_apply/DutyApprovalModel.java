package com.ruoyi.project.dgom.domain.network_apply;

public class DutyApprovalModel {
    private String applyId;
    private String connectedDesc;
    private String connectedBy;
    private String updatedBy;

    public String getApplyId() {
        return applyId;
    }

    public void setApplyId(String applyId) {
        this.applyId = applyId;
    }

    public String getConnectedDesc() {
        return connectedDesc;
    }

    public void setConnectedDesc(String connectedDesc) {
        this.connectedDesc = connectedDesc;
    }

    public String getConnectedBy() {
        return connectedBy;
    }

    public void setConnectedBy(String connectedBy) {
        this.connectedBy = connectedBy;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }
}
