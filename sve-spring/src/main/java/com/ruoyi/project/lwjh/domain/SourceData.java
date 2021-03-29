package com.ruoyi.project.lwjh.domain;

import com.ruoyi.project.common.poi.TreeNode;

public class SourceData extends TreeNode<CheckPointData> {

    private String number;

    private String city;

    private String fullName;

    private String emissionPermitCode;

    private String polluteType;

    private String envContactName;

    private String envContactPhone;

    private String industryName;

    private String sourceId;

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmissionPermitCode() {
        return emissionPermitCode;
    }

    public void setEmissionPermitCode(String emissionPermitCode) {
        this.emissionPermitCode = emissionPermitCode;
    }

    public String getPolluteType() {
        return polluteType;
    }

    public void setPolluteType(String polluteType) {
        this.polluteType = polluteType;
    }

    public String getEnvContactName() {
        return envContactName;
    }

    public void setEnvContactName(String envContactName) {
        this.envContactName = envContactName;
    }

    public String getEnvContactPhone() {
        return envContactPhone;
    }

    public void setEnvContactPhone(String envContactPhone) {
        this.envContactPhone = envContactPhone;
    }

    public String getIndustryName() {
        return industryName;
    }

    public void setIndustryName(String industryName) {
        this.industryName = industryName;
    }

}
