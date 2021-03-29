package com.ruoyi.project.dgom.domain.check_report;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ruoyi.common.utils.SecurityUtils;

import java.util.Date;

public class CheckReportModel {
    private Integer reportId;
    private String checkBy;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dateChecked;
    private Integer status;
    private String issue;
    private String request;
    private String procResult;
    private String memo;
    @JsonIgnore
    private final String updatedBy = SecurityUtils.getUsername();

    public Integer getReportId() {
        return reportId;
    }

    public void setReportId(Integer reportId) {
        this.reportId = reportId;
    }

    public String getCheckBy() {
        return checkBy;
    }

    public void setCheckBy(String checkBy) {
        this.checkBy = checkBy;
    }

    public Date getDateChecked() {
        return dateChecked;
    }

    public void setDateChecked(Date dateChecked) {
        this.dateChecked = dateChecked;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getIssue() {
        return issue;
    }

    public void setIssue(String issue) {
        this.issue = issue;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public String getProcResult() {
        return procResult;
    }

    public void setProcResult(String procResult) {
        this.procResult = procResult;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }
}
