package com.ruoyi.project.lwjh.domain;

import com.ruoyi.framework.web.domain.BaseEntity;

public class RmCPElementEntity extends BaseEntity{

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private String[] array;

    private String updatedBy;

    private String checkpointId;

    public String[] getArray() {
        return array;
    }

    public void setArray(String[] array) {
        this.array = array;
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
}
