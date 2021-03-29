package com.ruoyi.project.lwjh.domain;

import java.util.List;

import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

public class LWJHSourceEntity extends BaseEntity {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Excel(name = "污染源ID")
    private String sourceId;

    /**
     * 污染源名称
     */
    @Excel(name = "污染源名称")
    private String fullName;

    /**
     * 地址
     */
    @Excel(name = "地址")
    private String address;

    /**
     * 企业状态
     */
    @Excel(name = "企业状态")
    private int factoryStatus;

    /**
     * 联网接入状态
     */
    @Excel(name = "联网接入状态")
    private int accessStatus;

    /**
     * 建设单位
     */
    @Excel(name = "建设单位")
    private String constructUnit;

    /**
     * 建设单位联系人
     */
    @Excel(name = "建设单位联系人")
    private String constructPerson;

    /**
     * 建设单位联系电话
     */
    @Excel(name = "建设单位联系电话")
    private String constructPhone;

    /**
     * 污染源类型
     */
    @Excel(name = "污染源类型")
    private int polluteType;

    private List<String> polluteTypes;

    /**
     * 镇Code
     */
    @Excel(name = "镇代码")
    private String townCode;

    /**
     * 镇名称
     */
    @Excel(name = "镇名称")
    private String townName;

    /**
     * 社会统一信用代码
     */
    @Excel(name = "社会统一信用代码")
    private String socialCreditCode;

    /**
     * 排污许可证代码
     */
    @Excel(name = "排污许可证代码")
    private String emissionPermitCode;

    /**
     * $column.columnComment
     */
    @Excel(name = "行业代码")
    private String industryCode;

    /**
     * $column.columnComment
     */
    @Excel(name = "行业名称")
    private String industryName;

    /**
     * 监控级别
     */
    @Excel(name = "监控级别")
    private String envMonitorLevel;

    /**
     * 环保联系人
     */
    @Excel(name = "环保联系人")
    private String envContactName;

    /**
     * 环保联系人电话
     */
    @Excel(name = "环保联系人电话")
    private String envContactPhone;

    /**
     * 经度(度)
     */
    @Excel(name = "经度(度)")
    private String longitudeD;

    /**
     * 经度(分)
     */
    @Excel(name = "经度(分)")
    private String longitudeF;

    /**
     * 经度(秒)
     */
    @Excel(name = "经度(秒)")
    private String longitudeM;

    /**
     * 纬度(度)
     */
    @Excel(name = "纬度(度)")
    private String latitudeD;

    /**
     * 纬度(分)
     */
    @Excel(name = "纬度(分)")
    private String latitudeF;

    /**
     * 纬度(秒)
     */
    @Excel(name = "纬度(秒)")
    private String latitudeM;

    /**
     * 站房和排放口视频监控
     */
    @Excel(name = "站房和排放口视频监控")
    private String hasVideo;

    /**
     * 备注
     */
    @Excel(name = "备注")
    private String memo;

    private int isDeleted;

    private String createdBy;

    private String updatedBy;

    private String dateUreated;

    private String dateUpdated;

    private String accessDate;

    public String getAccessDate() {
        return accessDate;
    }

    public void setAccessDate(String accessDate) {
        this.accessDate = accessDate;
    }

    private List<LWJHCheckPointEntity> checkPoints;

    public List<LWJHCheckPointEntity> getCheckPoints() {
        return checkPoints;
    }

    public void setCheckPoints(List<LWJHCheckPointEntity> checkPoints) {
        this.checkPoints = checkPoints;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getFactoryStatus() {
        return factoryStatus;
    }

    public void setFactoryStatus(int factoryStatus) {
        this.factoryStatus = factoryStatus;
    }

    public int getAccessStatus() {
        return accessStatus;
    }

    public void setAccessStatus(int accessStatus) {
        this.accessStatus = accessStatus;
    }

    public String getConstructUnit() {
        return constructUnit;
    }

    public void setConstructUnit(String constructUnit) {
        this.constructUnit = constructUnit;
    }

    public String getConstructPerson() {
        return constructPerson;
    }

    public void setConstructPerson(String constructPerson) {
        this.constructPerson = constructPerson;
    }

    public String getConstructPhone() {
        return constructPhone;
    }

    public void setConstructPhone(String constructPhone) {
        this.constructPhone = constructPhone;
    }

    public int getPolluteType() {
        return polluteType;
    }

    public void setPolluteType(int polluteType) {
        this.polluteType = polluteType;
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

    public String getSocialCreditCode() {
        return socialCreditCode;
    }

    public void setSocialCreditCode(String socialCreditCode) {
        this.socialCreditCode = socialCreditCode;
    }

    public String getEmissionPermitCode() {
        return emissionPermitCode;
    }

    public void setEmissionPermitCode(String emissionPermitCode) {
        this.emissionPermitCode = emissionPermitCode;
    }

    public String getIndustryCode() {
        return industryCode;
    }

    public void setIndustryCode(String industryCode) {
        this.industryCode = industryCode;
    }

    public String getIndustryName() {
        return industryName;
    }

    public void setIndustryName(String industryName) {
        this.industryName = industryName;
    }

    public String getEnvMonitorLevel() {
        return envMonitorLevel;
    }

    public void setEnvMonitorLevel(String envMonitorLevel) {
        this.envMonitorLevel = envMonitorLevel;
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

    public String getLongitudeD() {
        return longitudeD;
    }

    public void setLongitudeD(String longitudeD) {
        this.longitudeD = longitudeD;
    }

    public String getLongitudeF() {
        return longitudeF;
    }

    public void setLongitudeF(String longitudeF) {
        this.longitudeF = longitudeF;
    }

    public String getLongitudeM() {
        return longitudeM;
    }

    public void setLongitudeM(String longitudeM) {
        this.longitudeM = longitudeM;
    }

    public String getLatitudeD() {
        return latitudeD;
    }

    public void setLatitudeD(String latitudeD) {
        this.latitudeD = latitudeD;
    }

    public String getLatitudeF() {
        return latitudeF;
    }

    public void setLatitudeF(String latitudeF) {
        this.latitudeF = latitudeF;
    }

    public String getLatitudeM() {
        return latitudeM;
    }

    public void setLatitudeM(String latitudeM) {
        this.latitudeM = latitudeM;
    }

    public String getHasVideo() {
        return hasVideo;
    }

    public void setHasVideo(String hasVideo) {
        this.hasVideo = hasVideo;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public int getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(int isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public String getDateUreated() {
        return dateUreated;
    }

    public void setDateUreated(String dateUreated) {
        this.dateUreated = dateUreated;
    }

    public String getDateUpdated() {
        return dateUpdated;
    }

    public void setDateUpdated(String dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

    public List<String> getPolluteTypes() {
        return polluteTypes;
    }

    public void setPolluteTypes(List<String> polluteTypes) {
        this.polluteTypes = polluteTypes;
    }
}
