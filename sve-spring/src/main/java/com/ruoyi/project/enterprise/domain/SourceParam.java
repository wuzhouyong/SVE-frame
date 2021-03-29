package com.ruoyi.project.enterprise.domain;

import com.ruoyi.framework.web.domain.BaseEntity;

public class SourceParam extends BaseEntity {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private String[] array;

    private String status;

    private String[] statusArray;

    private String townCode;

    private String fullName;

    private String warnLevel;

    public String getWarnLevel() {
        return warnLevel;
    }

    public void setWarnLevel(String warnLevel) {
        this.warnLevel = warnLevel;
    }

    public String[] getStatusArray() {
        return statusArray;
    }

    public void setStatusArray(String[] statusArray) {
        this.statusArray = statusArray;
    }

    public String[] getArray() {
        return array;
    }

    public void setArray(String[] array) {
        this.array = array;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTownCode() {
        return townCode;
    }

    public void setTownCode(String townCode) {
        this.townCode = townCode;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
