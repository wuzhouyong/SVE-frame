package com.ruoyi.project.lwjh.domain;

import com.ruoyi.project.common.poi.TreeNode;

public class CheckPointData extends TreeNode<ElementData> {

    private String processFlow;

    private String emissionCode;

    private String checkpointDesc;

    private String checkpointId;

    public String getCheckpointId() {
        return checkpointId;
    }

    public void setCheckpointId(String checkpointId) {
        this.checkpointId = checkpointId;
    }

    public String getProcessFlow() {
        return processFlow;
    }

    public void setProcessFlow(String processFlow) {
        this.processFlow = processFlow;
    }

    public String getEmissionCode() {
        return emissionCode;
    }

    public void setEmissionCode(String emissionCode) {
        this.emissionCode = emissionCode;
    }

    public String getCheckpointDesc() {
        return checkpointDesc;
    }

    public void setCheckpointDesc(String checkpointDesc) {
        this.checkpointDesc = checkpointDesc;
    }

}
