package com.ruoyi.project.lingxing.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 司机联单对象 job_transjobhead
 *
 * @author ruoyi
 * @date 2020-08-26
 */
public class LXTransjobhead extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 司机联单ID
     */
    private String jobHeadid;

    /**
     * 处置企业ID
     */
    @Excel(name = "处置企业ID")
    private String procSourceid;

    private String fullName;

    /**
     * $column.columnComment
     */
    @Excel(name = "状态")
    private Integer jobStatus;

    private String status;

    /**
     * 车辆
     */
    @Excel(name = "车辆")
    private Integer carId;

    private String carTag;

    /**
     * 司机
     */
    @Excel(name = "司机")
    private String driverId;

    private String driverName;

    /**
     * 联单日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Excel(name = "联单日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date jobDate;

    /**
     * 司机出发/离开污水厂时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Excel(name = "司机出发/离开污水厂时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date leaveTime;

    /**
     * 司机返回污水厂时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Excel(name = "司机返回污水厂时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date returnTime;

    /**
     * 放水起始时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Excel(name = "放水起始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date startflowouttime;

    /**
     * 防水结束时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Excel(name = "防水结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date endflowouttime;

    /**
     * 企业、司机确认得数量
     */
    @Excel(name = "企业、司机确认得数量")
    private Double flowoutamt;

    /**
     * 根据车辆水箱液位计算得数量
     */
    @Excel(name = "根据车辆水箱液位计算得数量")
    private Double factoutamt;

    private String memo;

    private Integer isDeleted;

    private String createdBy;

    private String updatedBy;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date dateCreated;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date dateUpdated;

    public void setJobHeadid(String jobHeadid) {
        this.jobHeadid = jobHeadid;
    }

    public String getJobHeadid() {
        return jobHeadid;
    }

    public String getProcSourceid() {
        return procSourceid;
    }

    public void setProcSourceid(String procSourceid) {
        this.procSourceid = procSourceid;
    }

    public void setJobStatus(Integer jobStatus) {
        this.jobStatus = jobStatus;
    }

    public Integer getJobStatus() {
        return jobStatus;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
    }

    public Integer getCarId() {
        return carId;
    }

    public void setDriverId(String driverId) {
        this.driverId = driverId;
    }

    public String getDriverId() {
        return driverId;
    }

    public void setJobDate(Date jobDate) {
        this.jobDate = jobDate;
    }

    public Date getJobDate() {
        return jobDate;
    }

    public void setLeaveTime(Date leaveTime) {
        this.leaveTime = leaveTime;
    }

    public Date getLeaveTime() {
        return leaveTime;
    }

    public void setReturnTime(Date returnTime) {
        this.returnTime = returnTime;
    }

    public Date getReturnTime() {
        return returnTime;
    }

    public void setStartflowouttime(Date startflowouttime) {
        this.startflowouttime = startflowouttime;
    }

    public Date getStartflowouttime() {
        return startflowouttime;
    }

    public void setEndflowouttime(Date endflowouttime) {
        this.endflowouttime = endflowouttime;
    }

    public Date getEndflowouttime() {
        return endflowouttime;
    }

    public void setFlowoutamt(Double flowoutamt) {
        this.flowoutamt = flowoutamt;
    }

    public Double getFlowoutamt() {
        return flowoutamt;
    }

    public void setFactoutamt(Double factoutamt) {
        this.factoutamt = factoutamt;
    }

    public Double getFactoutamt() {
        return factoutamt;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getMemo() {
        return memo;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Integer getIsDeleted() {
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

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCarTag() {
        return carTag;
    }

    public void setCarTag(String carTag) {
        this.carTag = carTag;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("jobHeadid", getJobHeadid())
                .append("procSourceid", getProcSourceid())
                .append("jobStatus", getJobStatus())
                .append("carId", getCarId())
                .append("driverId", getDriverId())
                .append("jobDate", getJobDate())
                .append("leaveTime", getLeaveTime())
                .append("returnTime", getReturnTime())
                .append("startflowouttime", getStartflowouttime())
                .append("endflowouttime", getEndflowouttime())
                .append("flowoutamt", getFlowoutamt())
                .append("factoutamt", getFactoutamt())
                .append("memo", getMemo())
                .append("isDeleted", getIsDeleted())
                .append("createdBy", getCreatedBy())
                .append("updatedBy", getUpdatedBy())
                .append("dateCreated", getDateCreated())
                .append("dateUpdated", getDateUpdated())
                .toString();
    }
}
