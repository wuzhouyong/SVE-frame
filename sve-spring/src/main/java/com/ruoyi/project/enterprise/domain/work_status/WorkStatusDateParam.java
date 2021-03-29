package com.ruoyi.project.enterprise.domain.work_status;

import java.util.Date;

public class WorkStatusDateParam {
    private Integer tagHeadId;
    private String sourceId;
    private Date startTime;
    private Date endTime;

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

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}
