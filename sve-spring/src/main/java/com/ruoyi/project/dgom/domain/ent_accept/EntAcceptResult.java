package com.ruoyi.project.dgom.domain.ent_accept;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.List;

public class EntAcceptResult {
    private String applyId;
    private String sourceName;
    private String townName;
    private Integer status;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date acceptDate;
    private String chargeMan;
    private String chargeManTitle;
    private String chargeManTel;
    private String memo;
    private String approvedBy;
    private String approvalDesc;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date approvedDate;
    private List<EntAcceptDetailResult> details;

    public String getApplyId() {
        return applyId;
    }

    public void setApplyId(String applyId) {
        this.applyId = applyId;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getAcceptDate() {
        return acceptDate;
    }

    public void setAcceptDate(Date acceptDate) {
        this.acceptDate = acceptDate;
    }

    public String getChargeMan() {
        return chargeMan;
    }

    public void setChargeMan(String chargeMan) {
        this.chargeMan = chargeMan;
    }

    public String getChargeManTitle() {
        return chargeManTitle;
    }

    public void setChargeManTitle(String chargeManTitle) {
        this.chargeManTitle = chargeManTitle;
    }

    public String getChargeManTel() {
        return chargeManTel;
    }

    public void setChargeManTel(String chargeManTel) {
        this.chargeManTel = chargeManTel;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(String approvedBy) {
        this.approvedBy = approvedBy;
    }

    public String getApprovalDesc() {
        return approvalDesc;
    }

    public void setApprovalDesc(String approvalDesc) {
        this.approvalDesc = approvalDesc;
    }

    public Date getApprovedDate() {
        return approvedDate;
    }

    public void setApprovedDate(Date approvedDate) {
        this.approvedDate = approvedDate;
    }

    public List<EntAcceptDetailResult> getDetails() {
        return details;
    }

    public void setDetails(List<EntAcceptDetailResult> details) {
        this.details = details;
    }
}
