package com.ruoyi.project.construction.domain;

import com.ruoyi.common.utils.LabelQueryUtils;
import com.ruoyi.framework.web.domain.BaseEntity;

import java.util.List;

public class ConstructionParam extends BaseEntity {
    private List<Integer> tagHeadIds;
    private String sourceName;
    private String townCode;
    private String sourceStatus;
    private Integer isAudited;
    private String labelQuery;

    public List<Integer> getTagHeadIds() {
        return tagHeadIds;
    }

    public void setTagHeadIds(List<Integer> tagHeadIds) {
        this.tagHeadIds = tagHeadIds;
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

    public String getSourceStatus() {
        return sourceStatus;
    }

    public void setSourceStatus(String sourceStatus) {
        this.sourceStatus = sourceStatus;
    }

    public Integer getIsAudited() {
        return isAudited;
    }

    public void setIsAudited(Integer isAudited) {
        this.isAudited = isAudited;
    }

    public String getLabelQuery() {
        return labelQuery;
    }

    public void setLabelIds(List<List<Integer>> labelIds) {
        labelQuery = LabelQueryUtils.getQuery(labelIds, "s");
    }
}
