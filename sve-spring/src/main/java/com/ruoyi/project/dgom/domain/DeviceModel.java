package com.ruoyi.project.dgom.domain;

/**
 * @author solang
 * @date 2020-09-28 19:46
 */
public class DeviceModel {
    private String detailId;
    private String collectDevId;
    private String element;
    private String brand;
    private String type;
    private String alarmMin;
    private String alarmMax;
    private String mn;
    private String checkpointDesc;

    public String getCollectDevId() {
        return collectDevId;
    }

    public void setCollectDevId(String collectDevId) {
        this.collectDevId = collectDevId;
    }

    public String getElement() {
        return element;
    }

    public void setElement(String element) {
        this.element = element;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAlarmMin() {
        return alarmMin;
    }

    public void setAlarmMin(String alarmMin) {
        this.alarmMin = alarmMin;
    }

    public String getAlarmMax() {
        return alarmMax;
    }

    public void setAlarmMax(String alarmMax) {
        this.alarmMax = alarmMax;
    }

    public String getDetailId() {
        return detailId;
    }

    public void setDetailId(String detailId) {
        this.detailId = detailId;
    }

    public String getMn() {
        return mn;
    }

    public void setMn(String mn) {
        this.mn = mn;
    }

    public String getCheckpointDesc() {
        return checkpointDesc;
    }

    public void setCheckpointDesc(String checkpointDesc) {
        this.checkpointDesc = checkpointDesc;
    }
}
