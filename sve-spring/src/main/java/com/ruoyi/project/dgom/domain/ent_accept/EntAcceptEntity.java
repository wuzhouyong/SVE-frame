package com.ruoyi.project.dgom.domain.ent_accept;

import com.ruoyi.common.utils.SecurityUtils;

import java.util.Date;

public class EntAcceptEntity {
    private String applyId;
    private Integer sourceId;
    private Date acceptDate;
    private String chargeMan;
    private String chargeManTitle;
    private String chargeManTel;
    private String memo;
    private final String createdBy = SecurityUtils.getUsername();
    private final String updatedBy = SecurityUtils.getUsername();

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
}
