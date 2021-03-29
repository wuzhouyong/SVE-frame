package com.ruoyi.project.enterprise.domain;

import java.io.Serializable;

public class PfBasTagHead implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private String tagHeadId;

    private String tagHeadName;

    private String tagDesc;

    private String parentId;

    private String sortId;

    private String isSysTag;

    private String memo;

    public String getTagHeadId() {
        return tagHeadId;
    }

    public void setTagHeadId(String tagHeadId) {
        this.tagHeadId = tagHeadId;
    }

    public String getTagHeadName() {
        return tagHeadName;
    }

    public void setTagHeadName(String tagHeadName) {
        this.tagHeadName = tagHeadName;
    }

    public String getTagDesc() {
        return tagDesc;
    }

    public void setTagDesc(String tagDesc) {
        this.tagDesc = tagDesc;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getSortId() {
        return sortId;
    }

    public void setSortId(String sortId) {
        this.sortId = sortId;
    }

    public String getIsSysTag() {
        return isSysTag;
    }

    public void setIsSysTag(String isSysTag) {
        this.isSysTag = isSysTag;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }
}
