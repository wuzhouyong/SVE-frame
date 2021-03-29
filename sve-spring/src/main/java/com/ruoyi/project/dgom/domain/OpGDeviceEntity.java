package com.ruoyi.project.dgom.domain;

import com.ruoyi.common.utils.DateUtils;

//气重点设备
public class OpGDeviceEntity extends OpdeviceEntity {

    private Integer datadevid;
    private String zdatadevdesc;
    private String monitorposition;
    private String devicebrand;
    private String devicedesc;
    private String devicenumber;
    private String workingprinciple;
    private String workrange;
    private String emissionstandard;
    private String tubecoefficient;
    private String velocitycoefficient;
    private String aircoefficient;
    private String chimneysectionarea;
    private String verificationtime;
    private String acceptancetime;
    
    public String getZdatadevdesc() {
        return zdatadevdesc;
    }

    public void setZdatadevdesc(String zdatadevdesc) {
        this.zdatadevdesc = zdatadevdesc;
    }

    public Integer getDatadevid() {
        return datadevid;
    }

    public void setDatadevid(Integer datadevid) {
        this.datadevid = datadevid;
    }

    public String getMonitorposition() {
        return monitorposition;
    }

    public void setMonitorposition(String monitorposition) {
        this.monitorposition = monitorposition;
    }

    public String getDevicebrand() {
        return devicebrand;
    }

    public void setDevicebrand(String devicebrand) {
        this.devicebrand = devicebrand;
    }

    public String getDevicedesc() {
        return devicedesc;
    }

    public void setDevicedesc(String devicedesc) {
        this.devicedesc = devicedesc;
    }

    public String getDevicenumber() {
        return devicenumber;
    }

    public void setDevicenumber(String devicenumber) {
        this.devicenumber = devicenumber;
    }

    public String getWorkingprinciple() {
        return workingprinciple;
    }

    public void setWorkingprinciple(String workingprinciple) {
        this.workingprinciple = workingprinciple;
    }

    public String getWorkrange() {
        return workrange;
    }

    public void setWorkrange(String workrange) {
        this.workrange = workrange;
    }

    public String getEmissionstandard() {
        return emissionstandard;
    }

    public void setEmissionstandard(String emissionstandard) {
        this.emissionstandard = emissionstandard;
    }

    public String getTubecoefficient() {
        return tubecoefficient;
    }

    public void setTubecoefficient(String tubecoefficient) {
        this.tubecoefficient = tubecoefficient;
    }

    public String getVelocitycoefficient() {
        return velocitycoefficient;
    }

    public void setVelocitycoefficient(String velocitycoefficient) {
        this.velocitycoefficient = velocitycoefficient;
    }

    public String getAircoefficient() {
        return aircoefficient;
    }

    public void setAircoefficient(String aircoefficient) {
        this.aircoefficient = aircoefficient;
    }

    public String getChimneysectionarea() {
        return chimneysectionarea;
    }

    public void setChimneysectionarea(String chimneysectionarea) {
        this.chimneysectionarea = chimneysectionarea;
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

}



