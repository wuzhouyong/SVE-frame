package com.ruoyi.project.lingxing.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author solang
 * @date 2020-08-27 16:14
 */
public class LXDataSearch {

    private String dataDate;

    private Double couAmt;

    private String meterName;

    @JsonIgnore
    private String storageName;

    private String elementDesc;

    public String getDataDate() {
        return dataDate;
    }

    public void setDataDate(String dataDate) {
        this.dataDate = dataDate;
    }

    public Double getCouAmt() {
        return couAmt;
    }

    public void setCouAmt(Double couAmt)
    {
        this.couAmt = couAmt;
    }

    public String getMeterName() {
        return meterName;
    }

    public void setMeterName(String meterName) {
        this.meterName = meterName;
    }

    public String getStorageName() {
        return storageName;
    }

    public void setStorageName(String storageName) {

        this.storageName = storageName;
    }

    public String getElementDesc() {
        return elementDesc;
    }

    public void setElementDesc(String elementDesc) {
        this.elementDesc = elementDesc;
    }
}
