package com.ruoyi.project.outwater.domain.entity;

import java.io.Serializable;

public class CheckPointElementEntity implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private String cpElementId;

    private String elemName;

    private String elemTypeId;

    private String elemCode;

    private String updatedBy;

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public String getCpElementId() {
        return cpElementId;
    }

    public void setCpElementId(String cpElementId) {
        this.cpElementId = cpElementId;
    }

    public String getElemName() {
        return elemName;
    }

    public void setElemName(String elemName) {
        this.elemName = elemName;
    }

    public String getElemTypeId() {
        return elemTypeId;
    }

    public void setElemTypeId(String elemTypeId) {
        this.elemTypeId = elemTypeId;
    }

    public String getElemCode() {
        return elemCode;
    }

    public void setElemCode(String elemCode) {
        this.elemCode = elemCode;
    }
}
