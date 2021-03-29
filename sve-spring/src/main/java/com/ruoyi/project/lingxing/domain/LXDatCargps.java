package com.ruoyi.project.lingxing.domain;

import java.io.Serializable;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 【请填写功能名称】对象 dat_cargps
 *
 * @author ruoyi
 * @date 2020-08-25
 */

//@JsonInclude(JsonInclude.Include.NON_NULL)
public class LXDatCargps implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer carId;

    private Double longitude;

    private Double latitude;

    private Double flowinamt;

    private Double factinamt;

    private String transbillid;

    private String fullName;

    private String carTag;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date gisTime;

    /** 开始时间 */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private String beginTime;

    /** 结束时间 */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private String endTime;

    private Double speed;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date dateCreated;

    public void setCarId(Integer carId) {
        this.carId = carId;
    }

    public Integer getCarId() {
        return carId;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setGisTime(Date gisTime) {
        this.gisTime = gisTime;
    }

    public Date getGisTime() {
        return gisTime;
    }

    public void setSpeed(Double speed) {
        this.speed = speed;
    }

    public Double getSpeed() {
        return speed;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getCarTag() {
        return carTag;
    }

    public void setCarTag(String carTag) {
        this.carTag = carTag;
    }

    public Double getFlowinamt() {
        return flowinamt;
    }

    public void setFlowinamt(Double flowinamt) {
        this.flowinamt = flowinamt;
    }

    public Double getFactinamt() {
        return factinamt;
    }

    public void setFactinamt(Double factinamt) {
        this.factinamt = factinamt;
    }

    public String getTransbillid() {
        return transbillid;
    }

    public void setTransbillid(String transbillid) {
        this.transbillid = transbillid;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("carId", getCarId())
                .append("longitude", getLongitude())
                .append("latitude", getLatitude())
                .append("gisTime", getGisTime())
                .append("speed", getSpeed())
                .append("flowinamt", getFlowinamt())
                .append("factinamt", getFactinamt())
                .append("transbillid", getTransbillid())
                .append("fullName", getFullName())
                .append("dateCreated", getDateCreated())
                .append("beginTime", getBeginTime())
                .append("endTime", getEndTime())
                .toString();
    }

}
