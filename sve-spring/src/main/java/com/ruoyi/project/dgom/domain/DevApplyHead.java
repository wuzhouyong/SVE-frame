package com.ruoyi.project.dgom.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.ruoyi.framework.web.domain.BaseEntity;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * @author solang
 * @date 2020-12-21 14:45
 */
public class DevApplyHead extends BaseEntity {

    private String applyId;
    private Integer status;
    private Integer applyType;
    private String sourceId;
    private String contactMan;
    @Pattern(regexp = "^[1][3,4,5,7,8,9][0-9]{9}$", message = "手机号码不合法")
    private String contactTel;
    private String changeReason;
    private String applyDate;
    private String approvedBy;
    private String approvedDate;
    private String approvalDesc;
    private String operatedDate;
    private String operatedBy;
    private String operatedDesc;
    private String sourceName;
    private String townName;
    private String townId;
    private String townCode;
    @JsonIgnore
    private Integer isAcc;

    @JsonIgnore
    private String createdBy;
    @JsonIgnore
    private String updatedBy;
    private String acceptDate;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @NotEmpty(message = "设备不能为空")
    @Size(min = 1, message = "设备至少要有一个自定义属性")
    private List<DevApplyDetail> detail;

    public String getApplyId() {
        return applyId;
    }

    public void setApplyId(String applyId) {
        this.applyId = applyId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getApplyType() {
        return applyType;
    }

    public void setApplyType(Integer applyType) {
        this.applyType = applyType;
    }

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public String getContactMan() {
        return contactMan;
    }

    public void setContactMan(String contactMan) {
        this.contactMan = contactMan;
    }

    public String getContactTel() {
        return contactTel;
    }

    public void setContactTel(String contactTel) {
        this.contactTel = contactTel;
    }

    public String getChangeReason() {
        return changeReason;
    }

    public void setChangeReason(String changeReason) {
        this.changeReason = changeReason;
    }

    public String getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(String applyDate) {
        this.applyDate = applyDate;
    }

    public String getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(String approvedBy) {
        this.approvedBy = approvedBy;
    }

    public String getApprovedDate() {
        return approvedDate;
    }

    public void setApprovedDate(String approvedDate) {
        this.approvedDate = approvedDate;
    }

    public String getApprovalDesc() {
        return approvalDesc;
    }

    public void setApprovalDesc(String approvalDesc) {
        this.approvalDesc = approvalDesc;
    }

    public String getOperatedDate() {
        return operatedDate;
    }

    public void setOperatedDate(String operatedDate) {
        this.operatedDate = operatedDate;
    }

    public String getOperatedBy() {
        return operatedBy;
    }

    public void setOperatedBy(String operatedBy) {
        this.operatedBy = operatedBy;
    }

    public String getOperatedDesc() {
        return operatedDesc;
    }

    public void setOperatedDesc(String operatedDesc) {
        this.operatedDesc = operatedDesc;
    }

    public String getSourceName() {
        return sourceName;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }

    public String getTownName() {
        return townName;
    }

    public void setTownName(String townName) {
        this.townName = townName;
    }

    public String getTownId() {
        return townId;
    }

    public void setTownId(String townId) {
        this.townId = townId;
    }

    public String getTownCode() {
        return townCode;
    }

    public void setTownCode(String townCode) {
        this.townCode = townCode;
    }

    public Integer getIsAcc() {
        return isAcc;
    }

    public void setIsAcc(Integer isAcc) {
        this.isAcc = isAcc;
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

    public String getAcceptDate() {
        return acceptDate;
    }

    public void setAcceptDate(String acceptDate) {
        this.acceptDate = acceptDate;
    }

    public List<DevApplyDetail> getDetail() {
        return detail;
    }

    public void setDetail(List<DevApplyDetail> detail) {
        this.detail = detail;
    }
}



