package com.ruoyi.project.dgom.domain.check_report;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class CheckReportResult {
    private Integer reportId;
    private String SourceName;
    private String townName;
    private String issue;
    private String dutyCom;
    private String deptCode;
    private String checkBy;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dateChecked;
    private String request;
    private String procResult;
    private String memo;
    private Integer status;
    private String reportMonth;

    public Integer getReportId() {
        return reportId;
    }

    public void setReportId(Integer reportId) {
        this.reportId = reportId;
    }

    public String getSourceName() {
        return SourceName;
    }

    public void setSourceName(String sourceName) {
        SourceName = sourceName;
    }

    public String getTownName() {
        return townName;
    }

    public void setTownName(String townName) {
        this.townName = townName;
    }

    public String getIssue() {
        return issue;
    }

    public void setIssue(String issue) {
        this.issue = issue;
    }

    public String getDutyCom() {
        return dutyCom;
    }

    public void setDutyCom(String dutyCom) {
        this.dutyCom = dutyCom;
    }

    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getReportMonth() {
        return reportMonth;
    }

    public void setReportMonth(String reportMonth) {
        this.reportMonth = reportMonth;
    }
}
