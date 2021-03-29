package com.ruoyi.project.enterprise.domain;

import com.ruoyi.common.utils.LabelQueryUtils;
import com.ruoyi.framework.web.domain.BaseEntity;

import java.util.List;

public class EnterpriseEntity extends BaseEntity {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String[] tagHeadIds;
    private String townCode;
    private String sourceStatus;
    private String constructionStatus;
    private String loginFlag;
    private String verifyStatus;
    private String fullName;
    private String listType;
    private String acceptBy;
    private String acceptResult;
    private String isAudit;
    private Integer isAudited;
    private String isApply;
    private String isFinish;
    private String[] listTypes;
    private String labelQuery;

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

    public String getIsAudit() {
        return isAudit;
    }

    public void setIsAudit(String isAudit) {
        this.isAudit = isAudit;
    }

    public String[] getTagHeadIds() {
        return tagHeadIds;
    }

    public void setTagHeadIds(String[] tagHeadIds) {
        this.tagHeadIds = tagHeadIds;
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

    public String getConstructionStatus() {
        return constructionStatus;
    }

    public void setConstructionStatus(String constructionStatus) {
        this.constructionStatus = constructionStatus;
    }

    public String getLoginFlag() {
        return loginFlag;
    }

    public void setLoginFlag(String loginFlag) {
        this.loginFlag = loginFlag;
    }

    public String getVerifyStatus() {
        return verifyStatus;
    }

    public void setVerifyStatus(String verifyStatus) {
        this.verifyStatus = verifyStatus;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getIsApply() {
        return isApply;
    }

    public void setIsApply(String isApply) {
        this.isApply = isApply;
    }

    public Integer getIsAudited() {
        return isAudited;
    }

    public void setIsAudited(Integer isAudited) {
        this.isAudited = isAudited;
    }

    public String getAcceptBy() {
        return acceptBy;
    }

    public void setAcceptBy(String acceptBy) {
        this.acceptBy = acceptBy;
    }

    public String getAcceptResult() {
        return acceptResult;
    }

    public void setAcceptResult(String acceptResult) {
        this.acceptResult = acceptResult;
    }

    public String getIsFinish() {
        return isFinish;
    }

    public void setIsFinish(String isFinish) {
        this.isFinish = isFinish;
    }

    public void setLabelIds(List<List<Integer>> labelIds) {
        labelQuery = LabelQueryUtils.getQuery(labelIds, "s");
    }

    public String getLabelQuery() {
        return labelQuery;
    }
}
