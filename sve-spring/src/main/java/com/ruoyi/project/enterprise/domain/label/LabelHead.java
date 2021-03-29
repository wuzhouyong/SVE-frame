package com.ruoyi.project.enterprise.domain.label;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ruoyi.common.utils.SecurityUtils;

import java.util.List;

public class LabelHead {
    private int labelHeadId;
    private String labelHeadName;
    private String userName;
    private Integer parentId;
    private boolean isDefault;
    @JsonIgnore
    private String createdBy = SecurityUtils.getLoginUser().getUsername();
    @JsonIgnore
    private String updatedBy = SecurityUtils.getLoginUser().getUsername();
    private List<LabelHead> children;

    public int getLabelHeadId() {
        return labelHeadId;
    }

    public void setLabelHeadId(int labelHeadId) {
        this.labelHeadId = labelHeadId;
    }

    public String getLabelHeadName() {
        return labelHeadName;
    }

    public void setLabelHeadName(String labelHeadName) {
        this.labelHeadName = labelHeadName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public boolean getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(boolean isDefault) {
        this.isDefault = isDefault;
    }

    public List<LabelHead> getChildren() {
        return children;
    }

    public void setChildren(List<LabelHead> children) {
        this.children = children;
    }
}
