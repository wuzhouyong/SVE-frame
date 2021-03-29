package com.ruoyi.project.enterprise.domain.dashboard;

import com.ruoyi.framework.web.domain.BaseEntity;

public class SourceSearchParam extends BaseEntity {
    private String townCode;
    private String sourceId;
    private String sourceName;
    private Integer warnLevel;
    private String[] tagHeadIds;
    private Integer[] statuses;

    public String getTownCode() {
        return townCode;
    }

    public void setTownCode(String townCode) {
        this.townCode = townCode;
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

    public Integer getWarnLevel() {
        return warnLevel;
    }

    public void setWarnLevel(Integer warnLevel) {
        this.warnLevel = warnLevel;
    }

    public String[] getTagHeadIds() {
        return tagHeadIds;
    }

    public void setTagHeadIds(String[] tagHeadIds) {
        this.tagHeadIds = tagHeadIds;
    }

    public Integer[] getStatuses() {
        return statuses;
    }

    public void setStatuses(Integer[] statuses) {
        this.statuses = statuses;
    }
}
