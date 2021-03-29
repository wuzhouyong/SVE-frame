package com.ruoyi.common.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class TransferRateEntity{

    private String uuid = UUID.randomUUID().toString();

    private String sourceId;

    private String checkpointName;

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date dataDate;

    private BigDecimal real;

    private BigDecimal minute;

    private BigDecimal hour;

    private BigDecimal day;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String shortName;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String brandName;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<TransferRateEntity> children;

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public String getCheckpointName() {
        return checkpointName;
    }

    public void setCheckpointName(String checkpointName) {
        this.checkpointName = checkpointName;
    }

    public Date getDataDate() {
        return dataDate;
    }

    public void setDataDate(Date dataDate) {
        this.dataDate = dataDate;
    }

    public BigDecimal getReal() {
        return real;
    }

    public void setReal(BigDecimal real) {
        this.real = real;
    }

    public BigDecimal getMinute() {
        return minute;
    }

    public void setMinute(BigDecimal minute) {
        this.minute = minute;
    }

    public BigDecimal getHour() {
        return hour;
    }

    public void setHour(BigDecimal hour) {
        this.hour = hour;
    }

    public BigDecimal getDay() {
        return day;
    }

    public void setDay(BigDecimal day) {
        this.day = day;
    }

    public List<TransferRateEntity> getChildren() {
        return children;
    }

    public void setChildren(List<TransferRateEntity> children) {
        this.children = children;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }
}