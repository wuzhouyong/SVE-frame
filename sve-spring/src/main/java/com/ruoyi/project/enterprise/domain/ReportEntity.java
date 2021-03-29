package com.ruoyi.project.enterprise.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.ruoyi.framework.web.domain.BaseEntity;


/**
 * @author solang
 * @date 2020-11-17 11:16
 */
public class ReportEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;
    private Integer reportId;
    private String sourceId;
    private Integer auditStatus;
    private String auditName;
    private String fullName;
    private String showName;
    private String townCode;
    private String townName;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String createdBy;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String updatedBy;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String attachmentId;

    public Integer getReportId() {
        return reportId;
    }

    public void setReportId(Integer reportId) {
        this.reportId = reportId;
    }

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public Integer getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(Integer auditStatus) {
        this.auditStatus = auditStatus;
    }

    public String getAuditName() {
        if (auditStatus != null) {
            switch (auditStatus) {
                case 0:
                    return  "待审核";
                case 2:
                    return  "同意";
                case 1:
                    return  "退回";
            }
        }
        return "";
    }

    public void setAuditName(String auditName) {
        this.auditName = auditName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getShowName() {
        return showName;
    }

    public void setShowName(String showName) {
        this.showName = showName;
    }

    public String getTownCode() {
        return townCode;
    }

    public void setTownCode(String townCode) {
        this.townCode = townCode;
    }

    public String getTownName() {
        return townName;
    }

    public void setTownName(String townName) {
        this.townName = townName;
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

    public String getAttachmentId() {
        return attachmentId;
    }

    public void setAttachmentId(String attachmentId) {
        this.attachmentId = attachmentId;
    }
}
