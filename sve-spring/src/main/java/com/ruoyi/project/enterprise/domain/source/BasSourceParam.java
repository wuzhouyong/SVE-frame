package com.ruoyi.project.enterprise.domain.source;

import com.ruoyi.framework.web.domain.BaseEntity;

import java.util.List;

public class BasSourceParam extends BaseEntity {
    private List<String> tagHeadIds;
    private String sourceName;
    private String townCode;
    private String sourceStatus;
    private String industryCode;

    public List<String> getTagHeadIds() {
        return tagHeadIds;
    }

    public void setTagHeadIds(List<String> tagHeadIds) {
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

    public String getIndustryCode() {
        return industryCode;
    }

    public void setIndustryCode(String industryCode) {
        this.industryCode = industryCode;
    }
}
