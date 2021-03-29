package com.ruoyi.project.outwater.domain;

import java.io.Serializable;

/**
 * @author solang
 * @date 2020-11-23 9:48
 */
public class ExceptformulaEntity implements Serializable {

    private String formulaId;
    private String warnType;
    private String warnDesc;
    private String warnLevel;
    private String elemTypeId;
    private Integer termsGroup;
    private String termsDesc;
    private String maxValue;
    private String minValue;
    private Integer termsType;
    private String termsTemp;

    public String getFormulaId() {
        return formulaId;
    }

    public void setFormulaId(String formulaId) {
        this.formulaId = formulaId;
    }

    public String getWarnType() {
        return warnType;
    }

    public void setWarnType(String warnType) {
        this.warnType = warnType;
    }

    public String getWarnDesc() {
        return warnDesc;
    }

    public void setWarnDesc(String warnDesc) {
        this.warnDesc = warnDesc;
    }

    public String getWarnLevel() {
        return warnLevel;
    }

    public void setWarnLevel(String warnLevel) {
        this.warnLevel = warnLevel;
    }

    public String getElemTypeId() {
        return elemTypeId;
    }

    public void setElemTypeId(String elemTypeId) {
        this.elemTypeId = elemTypeId;
    }

    public Integer getTermsGroup() {
        return termsGroup;
    }

    public void setTermsGroup(Integer termsGroup) {
        this.termsGroup = termsGroup;
    }

    public String getTermsDesc() {
        return termsDesc;
    }

    public void setTermsDesc(String termsDesc) {
        this.termsDesc = termsDesc;
    }

    public String getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(String maxValue) {
        this.maxValue = maxValue;
    }

    public String getMinValue() {
        return minValue;
    }

    public void setMinValue(String minValue) {
        this.minValue = minValue;
    }

    public Integer getTermsType() {
        return termsType;
    }

    public void setTermsType(Integer termsType) {
        this.termsType = termsType;
    }

    public String getTermsTemp() {
        return termsTemp;
    }

    public void setTermsTemp(String termsTemp) {
        this.termsTemp = termsTemp;
    }
}
