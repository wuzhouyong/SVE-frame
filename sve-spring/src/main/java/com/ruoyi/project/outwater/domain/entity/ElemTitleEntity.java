package com.ruoyi.project.outwater.domain.entity;

import java.io.Serializable;

public class ElemTitleEntity implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private String elemCode;

    private String elemName;

    private String elemTypeId;

    private String unit;

    private String elemTypeDesc;

    public String getElemTypeDesc() {
        return elemTypeDesc;
    }

    public void setElemTypeDesc(String elemTypeDesc) {
        this.elemTypeDesc = elemTypeDesc;
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

    public String getElemName() {
        return elemName;
    }

    public void setElemName(String elemName) {
        this.elemName = elemName;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
