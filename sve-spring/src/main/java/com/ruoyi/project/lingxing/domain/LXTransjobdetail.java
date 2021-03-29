package com.ruoyi.project.lingxing.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 司机联单子对象 job_transjobdetail
 * 
 * @author ruoyi
 * @date 2020-08-26
 */
public class LXTransjobdetail extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    //联表id
    private String jobDetailid;

    private String jobHeadid;

    private String transbillid;

    private String billNo;

    /** 产污企业ID */
    @Excel(name = "产污企业ID")
    private String sourceId;

    private String fullName;

    /** 开始收水时间  */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Excel(name = "开始收水时间 ", width = 30, dateFormat = "yyyy-MM-dd")
    private Date startflowintime;

    /** 完成收水时间 */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Excel(name = "完成收水时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date endflowintime;

    /**  确认(输入)数量（吨） */
    @Excel(name = " 确认(输入)数量", readConverterExp = "吨=")
    private Double flowinamt;

    /** 计算数量（吨） */
    @Excel(name = "计算数量", readConverterExp = "吨=")
    private Double factinamt;

    private Integer isDeleted;

    private String createdBy;

    private String updatedBy;
   @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date dateCreated;

    private Date dateUpdated;

    /**
     * 联单日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Excel(name = "联单日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date jobDate;

    public void setJobDetailid(String jobDetailid) 
    {
        this.jobDetailid = jobDetailid;
    }

    public String getJobDetailid() 
    {
        return jobDetailid;
    }
    public void setJobHeadid(String jobHeadid) 
    {
        this.jobHeadid = jobHeadid;
    }

    public String getJobHeadid() 
    {
        return jobHeadid;
    }
    public void setTransbillid(String transbillid) 
    {
        this.transbillid = transbillid;
    }

    public String getTransbillid() 
    {
        return transbillid;
    }

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public void setStartflowintime(Date startflowintime)
    {
        this.startflowintime = startflowintime;
    }

    public Date getStartflowintime() 
    {
        return startflowintime;
    }
    public void setEndflowintime(Date endflowintime) 
    {
        this.endflowintime = endflowintime;
    }

    public Date getEndflowintime() 
    {
        return endflowintime;
    }
    public void setFlowinamt(Double flowinamt) 
    {
        this.flowinamt = flowinamt;
    }

    public Double getFlowinamt() 
    {
        return flowinamt;
    }
    public void setFactinamt(Double factinamt) 
    {
        this.factinamt = factinamt;
    }

    public Double getFactinamt() 
    {
        return factinamt;
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

    public String getBillNo() {
        return billNo;
    }

    public void setBillNo(String billNo) {
        this.billNo = billNo;
    }

    public Date getJobDate() {
        return jobDate;
    }

    public void setJobDate(Date jobDate) {
        this.jobDate = jobDate;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("jobDetailid", getJobDetailid())
            .append("jobHeadid", getJobHeadid())
            .append("transbillid", getTransbillid())
            .append("sourceId", getSourceId())
            .append("startflowintime", getStartflowintime())
            .append("endflowintime", getEndflowintime())
            .append("flowinamt", getFlowinamt())
            .append("factinamt", getFactinamt())
            .append("isDeleted", getIsDeleted())
            .append("createdBy", getCreatedBy())
            .append("updatedBy", getUpdatedBy())
            .append("dateCreated", getDateCreated())
            .append("dateUpdated", getDateUpdated())
            .toString();
    }
}
