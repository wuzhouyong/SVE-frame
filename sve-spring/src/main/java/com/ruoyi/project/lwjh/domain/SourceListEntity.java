package com.ruoyi.project.lwjh.domain;

import java.util.List;

import com.ruoyi.framework.web.domain.BaseEntity;

public class SourceListEntity extends BaseEntity {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private String sourceId;

    private String fullName;

    private String bizSourceId;

    private String townCode;

    private String townName;

    private String industryName;

    private String socialCreditCode;

    private String envContactName;

    private String envContactPhone;

    private String address;

    private String emissionPermitCode;

    private int polluteType;

    private String memo;

    private String hasVideo;

    private String userName;

    private List<String> polluteTypes;

    private String factoryStatus;

    public String getFactoryStatus() {
        return factoryStatus;
    }

    public void setFactoryStatus(String factoryStatus) {
        this.factoryStatus = factoryStatus;
    }

    public List<String> getPolluteTypes() {
        return polluteTypes;
    }

    public void setPolluteTypes(List<String> polluteTypes) {
        this.polluteTypes = polluteTypes;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getPolluteType() {
        return polluteType;
    }

    public void setPolluteType(int polluteType) {
        this.polluteType = polluteType;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getHasVideo() {
        return hasVideo;
    }

    public void setHasVideo(String hasVideo) {
        this.hasVideo = hasVideo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmissionPermitCode() {
        return emissionPermitCode;
    }

    public void setEmissionPermitCode(String emissionPermitCode) {
        this.emissionPermitCode = emissionPermitCode;
    }

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getBizSourceId() {
        return bizSourceId;
    }

    public void setBizSourceId(String bizSourceId) {
        this.bizSourceId = bizSourceId;
    }

    public String getTownCode() {
        return townCode;
    }

    public void setTownCode(String townCode) {
        this.townCode = townCode;
    }

    public String getTownName() {
        return townName;
    }

    public void setTownName(String townName) {
        this.townName = townName;
    }

    public String getIndustryName() {
        return industryName;
    }

    public void setIndustryName1(String industryName) {
        this.industryName = industryName;
    }

    public String getSocialCreditCode() {
        return socialCreditCode;
    }

    public void setSocialCreditCode(String socialCreditCode) {
        this.socialCreditCode = socialCreditCode;
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
