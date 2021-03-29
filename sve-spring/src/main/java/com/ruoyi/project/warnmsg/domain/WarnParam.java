package com.ruoyi.project.warnmsg.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.utils.LabelQueryUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.framework.web.domain.BaseEntity;

import java.util.Date;
import java.util.List;

public class WarnParam extends BaseEntity {
    private String tagHeadId;
    private String sourceId;
    private String sourceName;
    private String townCode;
    private String acquisitionBrand;
    private String constructionUnit;
    private Integer constructionStatus;
    private Integer warnType;
    private Integer warnLevel;
    private Integer procStatus;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date startDate;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date endDate;
    private String userType;
    private String labelQuery;
    private final String currentUserId = SecurityUtils.getLoginUser().getUser().getUserId().toString();

    public String getTagHeadId() {
        return tagHeadId;
    }

    public void setTagHeadId(String tagHeadId) {
        this.tagHeadId = tagHeadId;
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

    public String getTownCode() {
        return townCode;
    }

    public void setTownCode(String townCode) {
        this.townCode = townCode;
    }

    public String getAcquisitionBrand() {
        return acquisitionBrand;
    }

    public void setAcquisitionBrand(String acquisitionBrand) {
        this.acquisitionBrand = acquisitionBrand;
    }

    public String getConstructionUnit() {
        return constructionUnit;
    }

    public void setConstructionUnit(String constructionUnit) {
        this.constructionUnit = constructionUnit;
    }

    public Integer getConstructionStatus() {
        return constructionStatus;
    }

    public void setConstructionStatus(Integer constructionStatus) {
        this.constructionStatus = constructionStatus;
    }

    public Integer getWarnType() {
        return warnType;
    }

    public void setWarnType(Integer warnType) {
        this.warnType = warnType;
    }

    public Integer getWarnLevel() {
        return warnLevel;
    }

    public void setWarnLevel(Integer warnLevel) {
        this.warnLevel = warnLevel;
    }

    public Integer getProcStatus() {
        return procStatus;
    }

    public void setProcStatus(Integer procStatus) {
        this.procStatus = procStatus;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public void setLabelIds(List<List<Integer>> labelIds) {
        labelQuery = LabelQueryUtils.getQuery(labelIds, "s");
    }

    public String getLabelQuery() {
        return labelQuery;
    }
}
