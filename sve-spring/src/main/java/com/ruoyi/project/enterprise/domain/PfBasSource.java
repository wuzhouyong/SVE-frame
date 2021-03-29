package com.ruoyi.project.enterprise.domain;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.utils.LabelQueryUtils;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 企业信息对象 pf_bas_source
 *
 * @author ruoyi
 * @date 2020-05-19
 */
public class PfBasSource extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 编号   -- GUID
     */
    private String sourceId;

    private String tagHeadId;

    private String dictLabel;

    private String warnLevel;

    public String getWarnLevel() {
        return warnLevel;
    }

    public void setWarnLevel(String warnLevel) {
        this.warnLevel = warnLevel;
    }

    public String getDictLabel() {
        return dictLabel;
    }

    public void setDictLabel(String dictLabel) {
        this.dictLabel = dictLabel;
    }

    /**
     * 污染源代码 (参考固定污染源编码)
     */
    @Excel(name = "污染源代码 (参考固定污染源编码)")
    private String sourceNo;

    /**
     * 污染源名称
     */
    @Excel(name = "污染源名称")
    private String fullName;

    /**
     * 社会统一信用代码
     */
    @Excel(name = "社会统一信用代码")
    private String socialCreditCode;

    /**
     * 组织机构代码
     */
    @Excel(name = "组织机构代码")
    private String organizationCode;

    /**
     * 排污许可证代码
     */
    @Excel(name = "排污许可证代码")
    private String emissionPermitCode;

    /**
     * 镇街代码
     */
    @Excel(name = "镇街代码")
    private String townCode;

    /**
     * 镇街名称
     */
    @Excel(name = "镇街名称")
    private String townName;

    /**
     * 社区代码
     */
    @Excel(name = "社区代码")
    private String villageCode;

    /**
     * 社区名称
     */
    @Excel(name = "社区名称")
    private String villageName;

    /**
     * $column.columnComment
     */
    @Excel(name = "行业名称")
    private String industryCode;

    /**
     * $column.columnComment
     */
    @Excel(name = "行业名称")
    private String industryName;

    /**
     * 地址
     */
    @Excel(name = "地址")
    private String address;

    /**
     * 经度(百度坐标)
     */
    @Excel(name = "经度(百度坐标)")
    private Double longitude;

    /**
     * 纬度(百度坐标)
     */
    @Excel(name = "纬度(百度坐标)")
    private Double latitude;

    /**
     * 污染源状态： -- （数据字典）  在产， 关停， 关闭,   其他
     */
    @Excel(name = "污染源状态： -- ", readConverterExp = "数=据字典")
    private String sourceStatus;

    /**
     * 企业规模  -- （数据字典）微型，小型，中型，大型
     */
    @Excel(name = "企业规模  -- ", readConverterExp = "数=据字典")
    private String sourceScale;

    /**
     * 法人姓名
     */
    @Excel(name = "法人姓名")
    private String legalPersonName;

    /**
     * 法人联系电话
     */
    @Excel(name = "法人联系电话")
    private String legalPersonPhone;

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
     * 收纳水体名称 / 河流
     */
    @Excel(name = "收纳水体名称 / 河流")
    private String inflowWaterName;

    /**
     * 工艺流程
     */
    @Excel(name = "工艺流程")
    private String processFlow;

    /**
     * 监控级别  -- （数据字典）重点污染源、非重点污染源
     */
    @Excel(name = "监控级别  -- ", readConverterExp = "数=据字典")
    private String envMonitorLevel;

    /**
     * 产生工业废水
     */
    @Excel(name = "产生工业废水")
    private String hasWasteWater;

    /**
     * 产生工业废气
     */
    @Excel(name = "产生工业废气")
    private String hasWasteGas;

    /**
     * 产生一般工业固体废物
     */
    @Excel(name = "产生一般工业固体废物")
    private String hasGeneralSolid;

    /**
     * 工业危险废物
     */
    @Excel(name = "工业危险废物")
    private String hasHazardousWaste;

    /**
     * 涉及稀土等15类矿产
     */
    @Excel(name = "涉及稀土等15类矿产")
    private String hasRareEarth;

    /**
     * 备注
     */
    @Excel(name = "备注")
    private String memo;

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
     * $column.columnComment
     */
    @Excel(name = "纬度(秒)")
    private Long isDeleted;

    /**
     * $column.columnComment
     */
    @Excel(name = "纬度(秒)")
    private String createdBy;

    /**
     * $column.columnComment
     */
    @Excel(name = "纬度(秒)")
    private String updatedBy;

    /**
     * $column.columnComment
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "纬度(秒)", width = 30, dateFormat = "yyyy-MM-dd")
    private Date dateCreated;

    /**
     * $column.columnComment
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "纬度(秒)", width = 30, dateFormat = "yyyy-MM-dd")
    private Date dateUpdated;

    /**
     * $column.columnComment
     */
    @Excel(name = "纬度(秒)")
    private String zsourcenamenew;

    /**
     * $column.columnComment
     */
    @Excel(name = "纬度(秒)")
    private Long zflag;

    /**
     * $column.columnComment
     */
    @Excel(name = "纬度(秒)")
    private String zpsourceid;

    private int status;

    private String userName;

    private String constructionStatus;
    private String labelQuery;

    private List<TagHeadEntity> tagHeadList;

    private int isHasVOCs;

    private SourceVocsParamEntity sourceVocsParamEntity;

    public SourceVocsParamEntity getSourceVocsParamEntity() {
        return sourceVocsParamEntity;
    }

    public void setSourceVocsParamEntity(SourceVocsParamEntity sourceVocsParamEntity) {
        this.sourceVocsParamEntity = sourceVocsParamEntity;
    }

    public int getIsHasVOCs() {
        return isHasVOCs;
    }

    public void setIsHasVOCs(int isHasVOCs) {
        this.isHasVOCs = isHasVOCs;
    }

    public List<TagHeadEntity> getTagHeadList() {
        return tagHeadList;
    }

    public void setTagHeadList(List<TagHeadEntity> tagHeadList) {
        this.tagHeadList = tagHeadList;
    }

    public String getConstructionStatus() {
        return constructionStatus;
    }

    public void setConstructionStatus(String constructionStatus) {
        this.constructionStatus = constructionStatus;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    private List<Map> tags;

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceNo(String sourceNo) {
        this.sourceNo = sourceNo;
    }

    public String getSourceNo() {
        return sourceNo;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setSocialCreditCode(String socialCreditCode) {
        this.socialCreditCode = socialCreditCode;
    }

    public String getSocialCreditCode() {
        return socialCreditCode;
    }

    public void setOrganizationCode(String organizationCode) {
        this.organizationCode = organizationCode;
    }

    public String getOrganizationCode() {
        return organizationCode;
    }

    public void setEmissionPermitCode(String emissionPermitCode) {
        this.emissionPermitCode = emissionPermitCode;
    }

    public String getEmissionPermitCode() {
        return emissionPermitCode;
    }

    public void setTownCode(String townCode) {
        this.townCode = townCode;
    }

    public String getTownCode() {
        return townCode;
    }

    public void setTownName(String townName) {
        this.townName = townName;
    }

    public String getTownName() {
        return townName;
    }

    public void setVillageCode(String villageCode) {
        this.villageCode = villageCode;
    }

    public String getVillageCode() {
        return villageCode;
    }

    public void setVillageName(String villageName) {
        this.villageName = villageName;
    }

    public String getVillageName() {
        return villageName;
    }

    public void setIndustryCode(String industryCode) {
        this.industryCode = industryCode;
    }

    public String getIndustryCode() {
        return industryCode;
    }

    public void setIndustryName(String industryName) {
        this.industryName = industryName;
    }

    public String getIndustryName() {
        return industryName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
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

    public void setSourceStatus(String sourceStatus) {
        this.sourceStatus = sourceStatus;
    }

    public String getSourceStatus() {
        return sourceStatus;
    }

    public void setSourceScale(String sourceScale) {
        this.sourceScale = sourceScale;
    }

    public String getSourceScale() {
        return sourceScale;
    }

    public void setLegalPersonName(String legalPersonName) {
        this.legalPersonName = legalPersonName;
    }

    public String getLegalPersonName() {
        return legalPersonName;
    }

    public void setLegalPersonPhone(String legalPersonPhone) {
        this.legalPersonPhone = legalPersonPhone;
    }

    public String getLegalPersonPhone() {
        return legalPersonPhone;
    }

    public void setEnvContactName(String envContactName) {
        this.envContactName = envContactName;
    }

    public String getEnvContactName() {
        return envContactName;
    }

    public void setEnvContactPhone(String envContactPhone) {
        this.envContactPhone = envContactPhone;
    }

    public String getEnvContactPhone() {
        return envContactPhone;
    }

    public void setInflowWaterName(String inflowWaterName) {
        this.inflowWaterName = inflowWaterName;
    }

    public String getInflowWaterName() {
        return inflowWaterName;
    }

    public void setProcessFlow(String processFlow) {
        this.processFlow = processFlow;
    }

    public String getProcessFlow() {
        return processFlow;
    }

    public void setEnvMonitorLevel(String envMonitorLevel) {
        this.envMonitorLevel = envMonitorLevel;
    }

    public String getEnvMonitorLevel() {
        return envMonitorLevel;
    }

    public void setHasWasteWater(String hasWasteWater) {
        this.hasWasteWater = hasWasteWater;
    }

    public String getHasWasteWater() {
        return hasWasteWater;
    }

    public void setHasWasteGas(String hasWasteGas) {
        this.hasWasteGas = hasWasteGas;
    }

    public String getHasWasteGas() {
        return hasWasteGas;
    }

    public void setHasGeneralSolid(String hasGeneralSolid) {
        this.hasGeneralSolid = hasGeneralSolid;
    }

    public String getHasGeneralSolid() {
        return hasGeneralSolid;
    }

    public void setHasHazardousWaste(String hasHazardousWaste) {
        this.hasHazardousWaste = hasHazardousWaste;
    }

    public String getHasHazardousWaste() {
        return hasHazardousWaste;
    }

    public void setHasRareEarth(String hasRareEarth) {
        this.hasRareEarth = hasRareEarth;
    }

    public String getHasRareEarth() {
        return hasRareEarth;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getMemo() {
        return memo;
    }

    public void setLongitudeD(String longitudeD) {
        this.longitudeD = longitudeD;
    }

    public String getLongitudeD() {
        return longitudeD;
    }

    public void setLongitudeF(String longitudeF) {
        this.longitudeF = longitudeF;
    }

    public String getLongitudeF() {
        return longitudeF;
    }

    public void setLongitudeM(String longitudeM) {
        this.longitudeM = longitudeM;
    }

    public String getLongitudeM() {
        return longitudeM;
    }

    public void setLatitudeD(String latitudeD) {
        this.latitudeD = latitudeD;
    }

    public String getLatitudeD() {
        return latitudeD;
    }

    public void setLatitudeF(String latitudeF) {
        this.latitudeF = latitudeF;
    }

    public String getLatitudeF() {
        return latitudeF;
    }

    public void setLatitudeM(String latitudeM) {
        this.latitudeM = latitudeM;
    }

    public String getLatitudeM() {
        return latitudeM;
    }

    public void setIsDeleted(Long isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Long getIsDeleted() {
        return isDeleted;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateUpdated(Date dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

    public Date getDateUpdated() {
        return dateUpdated;
    }

    public void setZsourcenamenew(String zsourcenamenew) {
        this.zsourcenamenew = zsourcenamenew;
    }

    public String getZsourcenamenew() {
        return zsourcenamenew;
    }

    public void setZflag(Long zflag) {
        this.zflag = zflag;
    }

    public Long getZflag() {
        return zflag;
    }

    public void setZpsourceid(String zpsourceid) {
        this.zpsourceid = zpsourceid;
    }

    public String getZpsourceid() {
        return zpsourceid;
    }

    public String getTagHeadId() {
        return tagHeadId;
    }

    public void setTagHeadId(String tagHeadId) {
        this.tagHeadId = tagHeadId;
    }

    public void setLabelIds(List<List<Integer>> labelIds) {
        labelQuery = LabelQueryUtils.getQuery(labelIds, "s");
    }

    public String getLabelQuery() {
        return labelQuery;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("sourceId", getSourceId())
                .append("sourceNo", getSourceNo())
                .append("fullName", getFullName())
                .append("socialCreditCode", getSocialCreditCode())
                .append("organizationCode", getOrganizationCode())
                .append("emissionPermitCode", getEmissionPermitCode())
                .append("townCode", getTownCode())
                .append("townName", getTownName())
                .append("villageCode", getVillageCode())
                .append("villageName", getVillageName())
                .append("industryCode", getIndustryCode())
                .append("industryName", getIndustryName())
                .append("address", getAddress())
                .append("longitude", getLongitude())
                .append("latitude", getLatitude())
                .append("sourceStatus", getSourceStatus())
                .append("sourceScale", getSourceScale())
                .append("legalPersonName", getLegalPersonName())
                .append("legalPersonPhone", getLegalPersonPhone())
                .append("envContactName", getEnvContactName())
                .append("envContactPhone", getEnvContactPhone())
                .append("inflowWaterName", getInflowWaterName())
                .append("processFlow", getProcessFlow())
                .append("envMonitorLevel", getEnvMonitorLevel())
                .append("hasWasteWater", getHasWasteWater())
                .append("hasWasteGas", getHasWasteGas())
                .append("hasGeneralSolid", getHasGeneralSolid())
                .append("hasHazardousWaste", getHasHazardousWaste())
                .append("hasRareEarth", getHasRareEarth())
                .append("memo", getMemo())
                .append("longitudeD", getLongitudeD())
                .append("longitudeF", getLongitudeF())
                .append("longitudeM", getLongitudeM())
                .append("latitudeD", getLatitudeD())
                .append("latitudeF", getLatitudeF())
                .append("latitudeM", getLatitudeM())
                .append("isDeleted", getIsDeleted())
                .append("createdBy", getCreatedBy())
                .append("updatedBy", getUpdatedBy())
                .append("dateCreated", getDateCreated())
                .append("dateUpdated", getDateUpdated())
                .append("zsourcenamenew", getZsourcenamenew())
                .append("zflag", getZflag())
                .append("zpsourceid", getZpsourceid())
                .append("tags", getTags())
                .toString();
    }

    public List<Map> getTags() {
        return tags;
    }

    public void setTags(List<Map> tags) {
        this.tags = tags;
    }
}