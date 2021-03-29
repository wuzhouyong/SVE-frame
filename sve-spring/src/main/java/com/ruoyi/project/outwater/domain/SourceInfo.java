package com.ruoyi.project.outwater.domain;

import com.ruoyi.framework.aspectj.lang.annotation.Excel;

public class SourceInfo {
    @Excel(name = "编号")
    private String sourceId;

    @Excel(name = "污染源名称")
    private String fullName;

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
