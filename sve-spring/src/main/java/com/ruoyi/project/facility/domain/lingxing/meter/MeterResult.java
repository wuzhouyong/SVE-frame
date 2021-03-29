package com.ruoyi.project.facility.domain.lingxing.meter;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.project.facility.domain.ElementEntity;
import com.ruoyi.project.facility.domain.ElementResult;

import java.util.Date;
import java.util.List;

public class MeterResult {
    private String meterId;
    private String sourceName;
    private String meterName;
    private Integer meterType;
    private String memo;
    private Double amount;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date dataDate;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date dateCreated;
    private List<ElementResult> elements;

    public String getMeterId() {
        return meterId;
    }

    public void setMeterId(String meterId) {
        this.meterId = meterId;
    }

    public String getSourceName() {
        return sourceName;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
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

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Date getDataDate() {
        return dataDate;
    }

    public void setDataDate(Date dataDate) {
        this.dataDate = dataDate;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public List<ElementResult> getElements() {
        return elements;
    }

    public void setElements(List<ElementResult> elements) {
        this.elements = elements;
    }
}
