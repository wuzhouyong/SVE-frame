package com.ruoyi.project.lingxing.domain;

public class LXCheckPointElementEntity {
    private String checkpointName;
    private String elemName;
    private String cpElemId;

    public String getCpElemId() {
        return cpElemId;
    }

    public void setCpElemId(String cpElemId) {
        this.cpElemId = cpElemId;
    }

    public String getCheckpointName() {
        return checkpointName;
    }

    public void setCheckpointName(String checkpointName) {
        this.checkpointName = checkpointName;
    }

    public String getElemName() {
        return elemName;
    }

    public void setElemName(String elemName) {
        this.elemName = elemName;
    }
}
