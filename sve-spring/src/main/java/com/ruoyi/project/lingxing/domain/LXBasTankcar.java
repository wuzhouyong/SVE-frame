package com.ruoyi.project.lingxing.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 【请填写功能名称】对象 bas_tankcar
 * 
 * @author ruoyi
 * @date 2020-08-25
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class LXBasTankcar extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    private Integer carId;  //车辆ID

    private String sourceId;

    private String fullName;

    private String carTag;  //车牌

    private String carType;  //车辆类型

    private String transcert;

    private String carSize;

    private Double maxLoadamt;

    private Integer isSetup;

    private Double fullVolume;

    private String memo;

    private String mn;

    private String dtuIP;

    /**
     * 删除标记
     */
    @Excel(name = "删除标记")
    private Integer isDeleted;

    private String createdBy;

    private String updatedBy;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date dateCreated;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date dateUpdated;

    public void setCarId(Integer carId) 
    {
        this.carId = carId;
    }

    public Integer getCarId() 
    {
        return carId;
    }
    public void setSourceId(String sourceId) 
    {
        this.sourceId = sourceId;
    }

    public String getSourceId() 
    {
        return sourceId;
    }
    public void setCarTag(String carTag) 
    {
        this.carTag = carTag;
    }

    public String getCarTag() 
    {
        return carTag;
    }
    public void setCarType(String carType) 
    {
        this.carType = carType;
    }

    public String getCarType() 
    {
        return carType;
    }
    public void setTranscert(String transcert) 
    {
        this.transcert = transcert;
    }

    public String getTranscert() 
    {
        return transcert;
    }
    public void setCarSize(String carSize) 
    {
        this.carSize = carSize;
    }

    public String getCarSize() 
    {
        return carSize;
    }
    public void setMaxLoadamt(Double maxLoadamt) 
    {
        this.maxLoadamt = maxLoadamt;
    }

    public Double getMaxLoadamt() 
    {
        return maxLoadamt;
    }
    public void setIsSetup(Integer isSetup) 
    {
        this.isSetup = isSetup;
    }

    public Integer getIsSetup() 
    {
        return isSetup;
    }
    public void setFullVolume(Double fullVolume) 
    {
        this.fullVolume = fullVolume;
    }

    public Double getFullVolume() 
    {
        return fullVolume;
    }
    public void setMemo(String memo) 
    {
        this.memo = memo;
    }

    public String getMemo() 
    {
        return memo;
    }

    public String getMn() {
        return mn;
    }

    public void setMn(String mn) {
        this.mn = mn;
    }

    public void setIsDeleted(Integer isDeleted)
    {
        this.isDeleted = isDeleted;
    }

    public Integer getIsDeleted() 
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

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getDtuIP() {
        return dtuIP;
    }

    public void setDtuIP(String dtuIP) {
        this.dtuIP = dtuIP;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("carId", getCarId())
            .append("sourceId", getSourceId())
            .append("carTag", getCarTag())
            .append("carType", getCarType())
            .append("transcert", getTranscert())
            .append("carSize", getCarSize())
            .append("maxLoadamt", getMaxLoadamt())
            .append("isSetup", getIsSetup())
            .append("fullVolume", getFullVolume())
            .append("memo", getMemo())
            .append("mn", getMn())
            .append("isDeleted", getIsDeleted())
            .append("createdBy", getCreatedBy())
            .append("updatedBy", getUpdatedBy())
            .append("dateCreated", getDateCreated())
            .append("dateUpdated", getDateUpdated())
            .append("fullName", getFullName())
            .append("dtuIP", getDtuIP())
            .toString();
    }
}
