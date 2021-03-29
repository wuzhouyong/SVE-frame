package com.ruoyi.project.facility.domain.lingxing.meter;

import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.project.facility.domain.ElementEntity;

import java.util.List;

public class MeterEntity {
    private String meterId;
    private String sourceId;
    private String meterName;
    private Integer meterType;
    private String memo;
    private Boolean isOther;
    private List<ElementEntity> elements;
    private String createdBy = SecurityUtils.getUsername();
    private String updatedBy = SecurityUtils.getUsername();

    public String getMeterId() {
        return meterId;
    }

    public void setMeterId(String meterId) {
        this.meterId = meterId;
    }

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public String getMeterName() {
        return meterName;
    }

    public void setMeterName(String meterName) {
        this.meterName = meterName;
    }

    public Integer getMeterType() {
        return meterType;
    }

    public void setMeterType(Integer meterType) {
        this.meterType = meterType;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public Boolean getIsOther() {
        return isOther;
    }

    public void setIsOther(Boolean isOther) {
        this.isOther = isOther;
    }

    public List<ElementEntity> getElements() {
        return elements;
    }

    public void setElements(List<ElementEntity> elements) {
        this.elements = elements;
    }
}
