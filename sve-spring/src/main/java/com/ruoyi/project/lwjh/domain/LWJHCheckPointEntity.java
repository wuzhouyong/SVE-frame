package com.ruoyi.project.lwjh.domain;

import java.util.List;

import com.ruoyi.framework.web.domain.BaseEntity;

public class LWJHCheckPointEntity extends BaseEntity {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private String checkpointId;

    private String sourceId;

    private String checkpointDesc;

    private String checkpointType;

    private String processFlow;

    private String emissionCode;

    private String meno;

    private String createdBy;

    private String updatedBy;

    private List<LWJHCheckPointElementEntity> elements;

    public List<LWJHCheckPointElementEntity> getElements() {
        return elements;
    }

    public void setElements(List<LWJHCheckPointElementEntity> elements) {
        this.elements = elements;
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

    public String getCheckpointId() {
        return checkpointId;
    }

    public void setCheckpointId(String checkpointId) {
        this.checkpointId = checkpointId;
    }

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public String getCheckpointDesc() {
        return checkpointDesc;
    }

    public void setCheckpointDesc(String checkpointDesc) {
        this.checkpointDesc = checkpointDesc;
    }

    public String getCheckpointType() {
        return checkpointType;
    }

    public void setCheckpointType(String checkpointType) {
        this.checkpointType = checkpointType;
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

    public String getMeno() {
        return meno;
    }

    public void setMeno(String meno) {
        this.meno = meno;
    }
}
