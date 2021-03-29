package com.ruoyi.project.voc.domain;


import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

/**
 * @author solang
 * @date 2020-08-21 13:33
 */
public class Technology implements Serializable {
    private static final long serialVersionUID = 1L;

    private String facilityId;

    private String facilityName;

    private Double amount;

    private boolean status;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String elementCode;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String elementDesc;

    public String getFacilityId() {
        return facilityId;
    }

    public void setFacilityId(String facilityId) {
        this.facilityId = facilityId;
    }

    public String getFacilityName() {
        return facilityName;
    }

    public void setFacilityName(String facilityName) {
        this.facilityName = facilityName;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getElementCode() {
        return elementCode;
    }

    public void setElementCode(String elementCode) {
        this.elementCode = elementCode;
    }

    public String getElementDesc() {
        return elementDesc;
    }

    public void setElementDesc(String elementDesc) {
        this.elementDesc = elementDesc;
    }
}
