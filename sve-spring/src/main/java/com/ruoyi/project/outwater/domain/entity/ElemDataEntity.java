package com.ruoyi.project.outwater.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class ElemDataEntity {

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date dataDate;

    private Integer workStatus;

    private String workStatusName;

    private String checkPointName;

    private String pH;

    private String conductivity;

    private String WQI;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dataDateD;

    public Date getDataDateD() {
        return dataDateD;
    }

    public void setDataDateD(Date dataDateD) {
        this.dataDateD = dataDateD;
    }

    public Integer getWorkStatus() {
        return workStatus;
    }

    public void setWorkStatus(Integer workStatus) {
        this.workStatus = workStatus;
    }

    public String getWorkStatusName() {
        return workStatusName;
    }

    public void setWorkStatusName(String workStatusName) {
        this.workStatusName = workStatusName;
    }

    public String getWQI() {
        return WQI;
    }

    public void setWQI(String WQI) {
        this.WQI = WQI;
    }

    public String getpH() {
        return pH;
    }

    public void setpH(String pH) {
        this.pH = pH;
    }

    public String getConductivity() {
        return conductivity;
    }

    public void setConductivity(String conductivity) {
        this.conductivity = conductivity;
    }

    public Date getDataDate() {
        return dataDate;
    }

    public void setDataDate(Date dataDate) {
        this.dataDate = dataDate;
    }

    public String getCheckPointName() {
        return checkPointName;
    }

    public void setCheckPointName(String checkPointName) {
        this.checkPointName = checkPointName;
    }

}
