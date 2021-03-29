package com.ruoyi.project.dgom.domain.network_apply;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ruoyi.project.dgom.domain.CollectModel;

import java.util.List;

public class RegisterModel {
    private String applyId;
    private Integer sourceId;
    private String sourceName;
    private Integer industryId;
    private String industryName;
    private String envChargeMan;
    private String socialCreditCode;
    private String contactTel;
    private String networkOperator;
    private String townId;
    private String townName;
    private String address;
    private String longitude;
    private String latitude;
    private String applyPerson;
    private String applyDate;
    private String contactCellPhone;
    private String approvalDesc;
    private String approvedBy;
    private String approvedDate;
    private String muApprovalDesc;
    private String muApprovedBy;
    private String muApprovedDate;

    private Integer status;
    private List<CollectModel> collects;

    @JsonIgnore
    private String updatedBy;

    @JsonIgnore
    private String createdBy;

    public String getIndustryName() {
        return industryName;
    }

    public void setIndustryName(String industryName) {
        this.industryName = industryName;
    }

    public String getTownName() {
        return townName;
    }

    public void setTownName(String townName) {
        this.townName = townName;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getApplyId() {
        return applyId;
    }

    public void setApplyId(String applyId) {
        this.applyId = applyId;
    }

    public Integer getSourceId() {
        return sourceId;
    }

    public void setSourceId(Integer sourceId) {
        this.sourceId = sourceId;
    }

    public String getSourceName() {
        return sourceName;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }

    public Integer getIndustryId() {
        return industryId;
    }

    public void setIndustryId(Integer industryId) {
        this.industryId = industryId;
    }

    public String getEnvChargeMan() {
        return envChargeMan;
    }

    public void setEnvChargeMan(String envChargeMan) {
        this.envChargeMan = envChargeMan;
    }

    public String getSocialCreditCode() {
        return socialCreditCode;
    }

    public void setSocialCreditCode(String socialCreditCode) {
        this.socialCreditCode = socialCreditCode;
    }

    public String getContactTel() {
        return contactTel;
    }

    public void setContactTel(String contactTel) {
        this.contactTel = contactTel;
    }

    public String getNetworkOperator() {
        return networkOperator;
    }

    public void setNetworkOperator(String networkOperator) {
        this.networkOperator = networkOperator;
    }

    public String getTownId() {
        return townId;
    }

    public void setTownId(String townId) {
        this.townId = townId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getApplyPerson() {
        return applyPerson;
    }

    public void setApplyPerson(String applyPerson) {
        this.applyPerson = applyPerson;
    }

    public String getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(String applyDate) {
        this.applyDate = applyDate;
    }

    public String getContactCellPhone() {
        return contactCellPhone;
    }

    public void setContactCellPhone(String contactCellPhone) {
        this.contactCellPhone = contactCellPhone;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<CollectModel> getCollects() {
        return collects;
    }

    public void setCollects(List<CollectModel> collects) {
        this.collects = collects;
    }

    public String getApprovalDesc() {
        return approvalDesc;
    }

    public void setApprovalDesc(String approvalDesc) {
        this.approvalDesc = approvalDesc;
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

    public String getMuApprovalDesc() {
        return muApprovalDesc;
    }

    public void setMuApprovalDesc(String muApprovalDesc) {
        this.muApprovalDesc = muApprovalDesc;
    }

    public String getMuApprovedBy() {
        return muApprovedBy;
    }

    public void setMuApprovedBy(String muApprovedBy) {
        this.muApprovedBy = muApprovedBy;
    }

    public String getMuApprovedDate() {
        return muApprovedDate;
    }

    public void setMuApprovedDate(String muApprovedDate) {
        this.muApprovedDate = muApprovedDate;
    }
}
