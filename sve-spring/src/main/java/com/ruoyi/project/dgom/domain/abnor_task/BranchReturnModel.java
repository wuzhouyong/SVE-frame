package com.ruoyi.project.dgom.domain.abnor_task;

import com.ruoyi.common.utils.SecurityUtils;

public class BranchReturnModel {
    private String taskHeadId;
    private String returnUnit;
    private String returnReason;
    private String updatedBy = SecurityUtils.getUsername();

    public String getTaskHeadId() {
        return taskHeadId;
    }

    public void setTaskHeadId(String taskHeadId) {
        this.taskHeadId = taskHeadId;
    }

    public String getReturnUnit() {
        return returnUnit;
    }

    public void setReturnUnit(String returnUnit) {
        this.returnUnit = returnUnit;
    }

    public String getReturnReason() {
        return returnReason;
    }

    public void setReturnReason(String returnReason) {
        this.returnReason = returnReason;
    }
}
