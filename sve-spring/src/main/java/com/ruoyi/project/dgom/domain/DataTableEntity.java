package com.ruoyi.project.dgom.domain;

import java.io.Serializable;

/**
 * @author solang
 * @date 2020-09-14 10:03
 */
public class DataTableEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer elementid;

    private String elementdesc;

    private String unit;

    private Integer decimaldigit;

    private Double minvalue;

    private Double maxvalue;

    public Integer getElementid() {
        return elementid;
    }

    public void setElementid(Integer elementid) {
        this.elementid = elementid;
    }

    public String getElementdesc() {
        return elementdesc;
    }

    public void setElementdesc(String elementdesc) {
        this.elementdesc = elementdesc;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Integer getDecimaldigit() {
        return decimaldigit;
    }

    public void setDecimaldigit(Integer decimaldigit) {
        this.decimaldigit = decimaldigit;
    }

    public Double getMinvalue() {
        return minvalue;
    }

    public void setMinvalue(Double minvalue) {
        this.minvalue = minvalue;
    }

    public Double getMaxvalue() {
        return maxvalue;
    }

    public void setMaxvalue(Double maxvalue) {
        this.maxvalue = maxvalue;
    }
}