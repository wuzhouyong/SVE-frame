package com.ruoyi.project.enterprise.domain;

import java.io.Serializable;

public class MenuParamEntity implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public MenuParamEntity(String sourceId, String[] array) {
        this.sourceId = sourceId;
        this.array = array;
    }

    public MenuParamEntity() {
    }

    private String sourceId;

    private String[] array;

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public String[] getArray() {
        return array;
    }

    public void setArray(String[] array) {
        this.array = array;
    }
}
