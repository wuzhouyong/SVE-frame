package com.ruoyi.project.lingxing.domain;

import com.ruoyi.framework.web.domain.BaseEntity;

public class LXSourceParam extends BaseEntity {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String sourceId;

    private String sourceName;

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
}
