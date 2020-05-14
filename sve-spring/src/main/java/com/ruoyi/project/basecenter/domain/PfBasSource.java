package com.ruoyi.project.basecenter.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.framework.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;

/**
 * 【请填写功能名称】对象 pf_bas_source
 * 
 * @author ruoyi
 * @date 2020-05-09
 */
public class PfBasSource extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String sourceId;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String sourceNo;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String fullName;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String socialCreditCode;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String organizationCode;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String emissionPermitCode;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String townCode;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String townName;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String villageCode;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String villageName;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String industryCode;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String industryName;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String address;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long longitude;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long latitude;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String sourceStatus;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String sourceScale;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String legalPersonName;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String legalPersonPhone;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String envContactName;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String envContactPhone;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String inflowWaterName;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String processFlow;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String envMonitorLevel;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String hasWasteWater;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String hasWasteGas;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String hasGeneralSolid;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String hasHazardousWaste;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String hasRareEarth;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String memo;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String longitudeD;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String longitudeF;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String longitudeM;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String latitudeD;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String latitudeF;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String latitudeM;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long isDeleted;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String createdBy;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String updatedBy;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Date dateCreated;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Date dateUpdated;

    public void setSourceId(String sourceId) 
    {
        this.sourceId = sourceId;
    }

    public String getSourceId() 
    {
        return sourceId;
    }
    public void setSourceNo(String sourceNo) 
    {
        this.sourceNo = sourceNo;
    }

    public String getSourceNo() 
    {
        return sourceNo;
    }
    public void setFullName(String fullName) 
    {
        this.fullName = fullName;
    }

    public String getFullName() 
    {
        return fullName;
    }
    public void setSocialCreditCode(String socialCreditCode) 
    {
        this.socialCreditCode = socialCreditCode;
    }

    public String getSocialCreditCode() 
    {
        return socialCreditCode;
    }
    public void setOrganizationCode(String organizationCode) 
    {
        this.organizationCode = organizationCode;
    }

    public String getOrganizationCode() 
    {
        return organizationCode;
    }
    public void setEmissionPermitCode(String emissionPermitCode) 
    {
        this.emissionPermitCode = emissionPermitCode;
    }

    public String getEmissionPermitCode() 
    {
        return emissionPermitCode;
    }
    public void setTownCode(String townCode) 
    {
        this.townCode = townCode;
    }

    public String getTownCode() 
    {
        return townCode;
    }
    public void setTownName(String townName) 
    {
        this.townName = townName;
    }

    public String getTownName() 
    {
        return townName;
    }
    public void setVillageCode(String villageCode) 
    {
        this.villageCode = villageCode;
    }

    public String getVillageCode() 
    {
        return villageCode;
    }
    public void setVillageName(String villageName) 
    {
        this.villageName = villageName;
    }

    public String getVillageName() 
    {
        return villageName;
    }
    public void setIndustryCode(String industryCode) 
    {
        this.industryCode = industryCode;
    }

    public String getIndustryCode() 
    {
        return industryCode;
    }
    public void setIndustryName(String industryName) 
    {
        this.industryName = industryName;
    }

    public String getIndustryName() 
    {
        return industryName;
    }
    public void setAddress(String address) 
    {
        this.address = address;
    }

    public String getAddress() 
    {
        return address;
    }
    public void setLongitude(Long longitude) 
    {
        this.longitude = longitude;
    }

    public Long getLongitude() 
    {
        return longitude;
    }
    public void setLatitude(Long latitude) 
    {
        this.latitude = latitude;
    }

    public Long getLatitude() 
    {
        return latitude;
    }
    public void setSourceStatus(String sourceStatus) 
    {
        this.sourceStatus = sourceStatus;
    }

    public String getSourceStatus() 
    {
        return sourceStatus;
    }
    public void setSourceScale(String sourceScale) 
    {
        this.sourceScale = sourceScale;
    }

    public String getSourceScale() 
    {
        return sourceScale;
    }
    public void setLegalPersonName(String legalPersonName) 
    {
        this.legalPersonName = legalPersonName;
    }

    public String getLegalPersonName() 
    {
        return legalPersonName;
    }
    public void setLegalPersonPhone(String legalPersonPhone) 
    {
        this.legalPersonPhone = legalPersonPhone;
    }

    public String getLegalPersonPhone() 
    {
        return legalPersonPhone;
    }
    public void setEnvContactName(String envContactName) 
    {
        this.envContactName = envContactName;
    }

    public String getEnvContactName() 
    {
        return envContactName;
    }
    public void setEnvContactPhone(String envContactPhone) 
    {
        this.envContactPhone = envContactPhone;
    }

    public String getEnvContactPhone() 
    {
        return envContactPhone;
    }
    public void setInflowWaterName(String inflowWaterName) 
    {
        this.inflowWaterName = inflowWaterName;
    }

    public String getInflowWaterName() 
    {
        return inflowWaterName;
    }
    public void setProcessFlow(String processFlow) 
    {
        this.processFlow = processFlow;
    }

    public String getProcessFlow() 
    {
        return processFlow;
    }
    public void setEnvMonitorLevel(String envMonitorLevel) 
    {
        this.envMonitorLevel = envMonitorLevel;
    }

    public String getEnvMonitorLevel() 
    {
        return envMonitorLevel;
    }
    public void setHasWasteWater(String hasWasteWater) 
    {
        this.hasWasteWater = hasWasteWater;
    }

    public String getHasWasteWater() 
    {
        return hasWasteWater;
    }
    public void setHasWasteGas(String hasWasteGas) 
    {
        this.hasWasteGas = hasWasteGas;
    }

    public String getHasWasteGas() 
    {
        return hasWasteGas;
    }
    public void setHasGeneralSolid(String hasGeneralSolid) 
    {
        this.hasGeneralSolid = hasGeneralSolid;
    }

    public String getHasGeneralSolid() 
    {
        return hasGeneralSolid;
    }
    public void setHasHazardousWaste(String hasHazardousWaste) 
    {
        this.hasHazardousWaste = hasHazardousWaste;
    }

    public String getHasHazardousWaste() 
    {
        return hasHazardousWaste;
    }
    public void setHasRareEarth(String hasRareEarth) 
    {
        this.hasRareEarth = hasRareEarth;
    }

    public String getHasRareEarth() 
    {
        return hasRareEarth;
    }
    public void setMemo(String memo) 
    {
        this.memo = memo;
    }

    public String getMemo() 
    {
        return memo;
    }
    public void setLongitudeD(String longitudeD) 
    {
        this.longitudeD = longitudeD;
    }

    public String getLongitudeD() 
    {
        return longitudeD;
    }
    public void setLongitudeF(String longitudeF) 
    {
        this.longitudeF = longitudeF;
    }

    public String getLongitudeF() 
    {
        return longitudeF;
    }
    public void setLongitudeM(String longitudeM) 
    {
        this.longitudeM = longitudeM;
    }

    public String getLongitudeM() 
    {
        return longitudeM;
    }
    public void setLatitudeD(String latitudeD) 
    {
        this.latitudeD = latitudeD;
    }

    public String getLatitudeD() 
    {
        return latitudeD;
    }
    public void setLatitudeF(String latitudeF) 
    {
        this.latitudeF = latitudeF;
    }

    public String getLatitudeF() 
    {
        return latitudeF;
    }
    public void setLatitudeM(String latitudeM) 
    {
        this.latitudeM = latitudeM;
    }

    public String getLatitudeM() 
    {
        return latitudeM;
    }
    public void setIsDeleted(Long isDeleted) 
    {
        this.isDeleted = isDeleted;
    }

    public Long getIsDeleted() 
    {
        return isDeleted;
    }
    public void setCreatedBy(String createdBy) 
    {
        this.createdBy = createdBy;
    }

    public String getCreatedBy() 
    {
        return createdBy;
    }
    public void setUpdatedBy(String updatedBy) 
    {
        this.updatedBy = updatedBy;
    }

    public String getUpdatedBy() 
    {
        return updatedBy;
    }
    public void setDateCreated(Date dateCreated) 
    {
        this.dateCreated = dateCreated;
    }

    public Date getDateCreated() 
    {
        return dateCreated;
    }
    public void setDateUpdated(Date dateUpdated) 
    {
        this.dateUpdated = dateUpdated;
    }

    public Date getDateUpdated() 
    {
        return dateUpdated;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
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
            .toString();
    }
}
