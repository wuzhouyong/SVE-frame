package com.ruoyi.project.enterprise.domain.label;

public class LabelSourceEntity {
    private String sourceId;
    private String fullName;
    private String townName;
    private String labelList;
    private String labelIdList;

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

    public String getTownName() {
        return townName;
    }

    public void setTownName(String townName) {
        this.townName = townName;
    }

    public String getLabelList() {
        return labelList;
    }

    public void setLabelList(String labelList) {
        this.labelList = labelList;
    }

    public String getLabelIdList() {
        return labelIdList;
    }

    public void setLabelIdList(String labelIdList) {
        this.labelIdList = labelIdList;
    }
}
