package com.ruoyi.project.enterprise.domain;

public class PfBasSourceList extends PfBasSourceCount{

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String fullName;
    private String townCode;
    private String industryName;
    private String sourceStatus;
    private String envContactName;
    private String envContactPhone;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Override
    public String getTownCode() {
        return townCode;
    }

    @Override
    public void setTownCode(String townCode) {
        this.townCode = townCode;
    }

    public String getIndustryName() {
        return industryName;
    }

    public void setIndustryName(String industryName) {
        this.industryName = industryName;
    }

    public String getSourceStatus() {
        return sourceStatus;
    }

    public void setSourceStatus(String sourceStatus) {
        this.sourceStatus = sourceStatus;
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



}
