package com.ruoyi.project.lwjh.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.framework.web.domain.BaseEntity;

public class LWJHCheckPointElementEntity extends BaseEntity {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private String cpelemId;

    private String elementId;

    private String checkpointId;

    private String elementName;

    private String lawStandard;

    @JsonFormat(pattern = "yyyy")
    private String accessDate;

    private String createdBy;

    private String updatedBy;

    public String getElementName() {
        return elementName;
    }

    public void setElementName(String elementName) {
        this.elementName = elementName;
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

    public String getCpelemId() {
        return cpelemId;
    }

    public void setCpelemId(String cpelemId) {
        this.cpelemId = cpelemId;
    }

    public String getElementId() {
        return elementId;
    }

    public void setElementId(String elementId) {
        this.elementId = elementId;
    }

    public String getCheckpointId() {
        return checkpointId;
    }

    public void setCheckpointId(String checkpointId) {
        this.checkpointId = checkpointId;
    }

    public String getLawStandard() {
        return lawStandard;
    }

    public void setLawStandard(String lawStandard) {
        this.lawStandard = lawStandard;
    }

    public String getAccessDate() {
        //return StringUtils.getYear(accessDate);
        return accessDate;
    }

    public void setAccessDate(String accessDate) {
        this.accessDate = accessDate;
    }
}
