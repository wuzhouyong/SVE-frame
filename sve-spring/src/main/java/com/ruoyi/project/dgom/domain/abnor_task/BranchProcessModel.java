package com.ruoyi.project.dgom.domain.abnor_task;

import com.ruoyi.common.utils.SecurityUtils;

public class BranchProcessModel {
    private String taskHeadId;
    private String adminUnitName;
    private String adminUnitProcDesc;
    private String updatedBy = SecurityUtils.getUsername();

    public String getTaskHeadId() {
        return taskHeadId;
    }

    public void setTaskHeadId(String taskHeadId) {
        this.taskHeadId = taskHeadId;
    }

    public String getAdminUnitName() {
        return adminUnitName;
    }

    public void setAdminUnitName(String adminUnitName) {
        this.adminUnitName = adminUnitName;
    }

    public String getAdminUnitProcDesc() {
        return adminUnitProcDesc;
    }

    public void setAdminUnitProcDesc(String adminUnitProcDesc) {
        this.adminUnitProcDesc = adminUnitProcDesc;
    }
}
