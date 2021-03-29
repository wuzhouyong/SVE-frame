package com.ruoyi.project.enterprise.domain;

import com.ruoyi.common.utils.LabelQueryUtils;
import com.ruoyi.framework.web.domain.BaseEntity;

import java.util.List;

public class MonitorTaskParam extends BaseEntity {

    private String[] listTypes;

    private String listType;

    private String isAudit;

    private String fullName;

    private String townCode;

    private String[] townCodes;

    private String tagHeadId;

    private Long[] tagHeadIds;

    private String labelQuery;

    public String getTagHeadId() {
        return tagHeadId;
    }

    public void setTagHeadId(String tagHeadId) {
        this.tagHeadId = tagHeadId;
    }

    public String[] getListTypes() {
        return listTypes;
    }

    public void setListTypes(String[] listTypes) {
        this.listTypes = listTypes;
    }

    public String getListType() {
        return listType;
    }

    public void setListType(String listType) {
        this.listType = listType;
    }

    public String[] getTownCodes() {
        return townCodes;
    }

    public void setTownCodes(String[] townCodes) {
        this.townCodes = townCodes;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getTownCode() {
        return townCode;
    }

    public void setTownCode(String townCode) {
        this.townCode = townCode;
    }

    public String getIsAudit() {
        return isAudit;
    }

    public void setIsAudit(String isAudit) {
        this.isAudit = isAudit;
    }

    public Long[] getTagHeadIds() {
        return tagHeadIds;
    }

    public void setTagHeadIds(Long[] tagHeadIds) {
        this.tagHeadIds = tagHeadIds;
    }

    public void setLabelIds(List<List<Integer>> labelIds) {
        labelQuery = LabelQueryUtils.getQuery(labelIds, "a");
    }

    public String getLabelQuery() {
        return labelQuery;
    }

    public MonitorTaskParam() {
    }

    public MonitorTaskParam(String[] listTypes, String listType, String isAudit, String fullName, String townCode, String[] townCodes, String tagHeadId, Long[] tagHeadIds) {
        this.listTypes = listTypes;
        this.listType = listType;
        this.isAudit = isAudit;
        this.fullName = fullName;
        this.townCode = townCode;
        this.townCodes = townCodes;
        this.tagHeadId = tagHeadId;
        this.tagHeadIds = tagHeadIds;
    }
}
