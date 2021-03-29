package com.ruoyi.project.lingxing.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 拉水车司机对象 bas_driverinfo
 *
 * @author ruoyi
 * @date 2020-09-01
 */
public class LXDriverinfo extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 司机ID，默认使用身份证
     */
    @Excel(name = "司机ID，默认使用身份证")
    private String driverId;

    /**
     * 司机所属处置企业
     */
    @Excel(name = "司机所属处置企业")
    private String sourceId;

    private String fullName;

    /**
     * 姓名
     */
    @Excel(name = "姓名")
    private String driverName;

    /**
     * APP登陆账号
     */
    @Excel(name = "APP登陆账号")
    private String loginAccount;

    /**
     * APP登陆密码
     */
    @Excel(name = "APP登陆密码")
    private String loginPassword;

    /**
     * 备注
     */
    @Excel(name = "备注")
    private String memo;

    /**
     * 删除标记
     */
    @Excel(name = "删除标记")
    private Integer isDeleted;

    /**
     * 创建人
     */
    @Excel(name = "创建人")
    private String createdBy;

    /**
     * 更新人
     */
    @Excel(name = "更新人")
    private String updatedBy;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date dateCreated;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "更新时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date dateUpdated;

    public void setDriverId(String driverId) {
        this.driverId = driverId;
    }

    public String getDriverId() {
        return driverId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public String getSourceId() {
        return sourceId;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setLoginAccount(String loginAccount) {
        this.loginAccount = loginAccount;
    }

    public String getLoginAccount() {
        return loginAccount;
    }

    public void setLoginPassword(String loginPassword) {
        this.loginPassword = loginPassword;
    }

    public String getLoginPassword() {
        return loginPassword;
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

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("driverId", getDriverId())
                .append("sourceId", getSourceId())
                .append("driverName", getDriverName())
                .append("fullName", getFullName())
                .append("loginAccount", getLoginAccount())
                .append("loginPassword", getLoginPassword())
                .append("memo", getMemo())
                .append("isDeleted", getIsDeleted())
                .append("createdBy", getCreatedBy())
                .append("updatedBy", getUpdatedBy())
                .append("dateCreated", getDateCreated())
                .append("dateUpdated", getDateUpdated())
                .toString();
    }
}
