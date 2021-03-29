package com.ruoyi.project.dgom.domain;

import com.ruoyi.common.utils.DateUtils;

//水重点设备
public class OpWDeviceEntity {

    private Integer datadevid;
    private String zdatadevdesc;
    private Integer checkpointid;
    private String devicebrand;
    private String devicenumber;
    private String measuringmethod;
    private String heatingtemp;
    private String heatingtime;
    private String workrange;
    private String refcoefficient;
    private String verificationtime;
    private String acceptancetime;
    private String emissionstandard;
    private String createdby;
    private String updatedby;

    public Integer getDatadevid() {
        return datadevid;
    }

    public void setDatadevid(Integer datadevid) {
        this.datadevid = datadevid;
    }

    public String getZdatadevdesc() {
        return zdatadevdesc;
    }

    public void setZdatadevdesc(String zdatadevdesc) {
        this.zdatadevdesc = zdatadevdesc;
    }

    public Integer getCheckpointid() {
        return checkpointid;
    }

    public void setCheckpointid(Integer checkpointid) {
        this.checkpointid = checkpointid;
    }

    public String getDevicebrand() {
        return devicebrand;
    }

    public void setDevicebrand(String devicebrand) {
        this.devicebrand = devicebrand;
    }

    public String getDevicenumber() {
        return devicenumber;
    }

    public void setDevicenumber(String devicenumber) {
        this.devicenumber = devicenumber;
    }

    public String getMeasuringmethod() {
        return measuringmethod;
    }

    public void setMeasuringmethod(String measuringmethod) {
        this.measuringmethod = measuringmethod;
    }

    public String getHeatingtemp() {
        return heatingtemp;
    }

    public void setHeatingtemp(String heatingtemp) {
        this.heatingtemp = heatingtemp;
    }

    public String getHeatingtime() {
        return DateUtils.formatDateStr(heatingtime);
    }

    public void setHeatingtime(String heatingtime) {
        this.heatingtime = heatingtime;
    }

    public String getWorkrange() {
        return workrange;
    }

    public void setWorkrange(String workrange) {
        this.workrange = workrange;
    }

    public String getRefcoefficient() {
        return refcoefficient;
    }

    public void setRefcoefficient(String refcoefficient) {
        this.refcoefficient = refcoefficient;
    }

    public String getVerificationtime() {
        return DateUtils.formatDateStr(verificationtime);
    }

    public void setVerificationtime(String verificationtime) {
        this.verificationtime = verificationtime;
    }

    public String getAcceptancetime() {
        return DateUtils.formatDateStr(acceptancetime);
    }

    public void setAcceptancetime(String acceptancetime) {
        this.acceptancetime = acceptancetime;
    }

    public String getEmissionstandard() {
        return emissionstandard;
    }

    public void setEmissionstandard(String emissionstandard) {
        this.emissionstandard = emissionstandard;
    }

    public String getCreatedby() {
        return createdby;
    }

    public void setCreatedby(String createdby) {
        this.createdby = createdby;
    }

    public String getUpdatedby() {
        return updatedby;
    }

    public void setUpdatedby(String updatedby) {
        this.updatedby = updatedby;
    }
}
