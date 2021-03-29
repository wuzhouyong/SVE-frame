package com.ruoyi.project.enterprise.domain.work_status;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.framework.web.domain.BaseEntity;

import java.util.Date;

public class WorkStatusParam extends BaseEntity {
    private Integer workId;
    private Integer tagHeadId;
    private String sourceId;
    private String sourceName;
    private Integer workStatus;
    @JsonFormat(pattern = "yyyy-MM-dd HH", timezone = "GMT+8")
    private String startTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH", timezone = "GMT+8")
    private String endTime;
    private Integer auditStatus;

    public Integer getWorkId() {
        return workId;
    }

    public void setWorkId(Integer workId) {
        this.workId = workId;
    }

    public Integer getTagHeadId() {
        return tagHeadId;
    }

    public void setTagHeadId(Integer tagHeadId) {
        this.tagHeadId = tagHeadId;
    }

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public String getSourceName() {
        return sourceName;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }

    public Integer getWorkStatus() {
        return workStatus;
    }

    public void setWorkStatus(Integer workStatus) {
        this.workStatus = workStatus;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Integer getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(Integer auditStatus) {
        this.auditStatus = auditStatus;
    }
}
