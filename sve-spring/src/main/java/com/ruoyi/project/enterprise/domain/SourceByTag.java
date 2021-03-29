package com.ruoyi.project.enterprise.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ruoyi.common.utils.LabelQueryUtils;

import java.util.List;

public class SourceByTag {
    private String tagHeadId;
    private String sourceName;
    private String townCode;
    private String townName;
    private String sourceId;
    private Integer constructionStatus;
    @JsonIgnore
    private String labelQuery;

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public void setLabelQuery(String labelQuery) {
        this.labelQuery = labelQuery;
    }

    public String getTagHeadId() {
        return tagHeadId;
    }

    public void setTagHeadId(String tagHeadId) {
        this.tagHeadId = tagHeadId;
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

    public String getTownName() {
        return townName;
    }

    public void setTownName(String townName) {
        this.townName = townName;
    }

    public Integer getConstructionStatus() {
        return constructionStatus;
    }

    public void setConstructionStatus(Integer constructionStatus) {
        this.constructionStatus = constructionStatus;
    }

    public void setLabelIds(List<List<Integer>> labelIds) {
        labelQuery = LabelQueryUtils.getQuery(labelIds, "s");
    }

    public String getLabelQuery() {
        return labelQuery;
    }
}
