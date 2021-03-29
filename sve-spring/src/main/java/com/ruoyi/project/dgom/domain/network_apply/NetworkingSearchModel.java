package com.ruoyi.project.dgom.domain.network_apply;

import com.ruoyi.framework.web.domain.BaseEntity;

import java.util.Date;

public class NetworkingSearchModel extends BaseEntity {
    private Integer sourceId;
    private String sourceName;
    private String townCode;
    private Integer townId;
    private Integer industryId;
    private String status;
    private String[] statu;
    private Date startDate;
    private Date endDate;
    private String approvedBy;
    private String muApprovedBy;

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

    public String getTownCode() {
        return townCode;
    }

    public void setTownCode(String townCode) {
        this.townCode = townCode;
    }

    public Integer getTownId() {
        return townId;
    }

    public void setTownId(Integer townId) {
        this.townId = townId;
    }

    public Integer getIndustryId() {
        return industryId;
    }

    public void setIndustryId(Integer industryId) {
        this.industryId = industryId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String[] getStatu() {
        return statu;
    }

    public void setStatu(String[] statu) {
        this.statu = statu;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(String approvedBy) {
        this.approvedBy = approvedBy;
    }

    public String getMuApprovedBy() {
        return muApprovedBy;
    }

    public void setMuApprovedBy(String muApprovedBy) {
        this.muApprovedBy = muApprovedBy;
    }
}
