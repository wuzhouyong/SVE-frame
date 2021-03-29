package com.ruoyi.project.construction.domain;

import com.ruoyi.common.utils.SecurityUtils;

public class AcceptParam {
    private int tagHeadId;
    private String sourceId;
    private Integer constructionStatus;
    private Integer acceptResult;
    private final String acceptBy = SecurityUtils.getUsername();
    private final String updatedBy = SecurityUtils.getUsername();

    public int getTagHeadId() {
        return tagHeadId;
    }

    public void setTagHeadId(int tagHeadId) {
        this.tagHeadId = tagHeadId;
    }

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public Integer getConstructionStatus() {
        return constructionStatus;
    }

    public void setConstructionStatus(Integer constructionStatus) {
        this.constructionStatus = constructionStatus;
    }

    public Integer getAcceptResult() {
        return acceptResult;
    }

    public void setAcceptResult(Integer acceptResult) {
        this.acceptResult = acceptResult;
    }
}
