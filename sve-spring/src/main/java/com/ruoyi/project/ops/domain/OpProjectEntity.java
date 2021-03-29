package com.ruoyi.project.ops.domain;

import java.util.List;

/**
 * 【请填写功能名称】对象 pf_bas_devops
 *
 * @author ruoyi
 * @date 2020-08-25
 */

public class OpProjectEntity {

    private Integer opProjectId;
    private String pfSourceId;
    private Integer opSourceId;
    private Integer tagHeadId;
    private String tagHeadName;
    private String fullName;
    private String sourceName;
    private String projectTitle;
    private String collectId;
    private String collectName;
    private String startDate;
    private String endDate;
    private String memo;
    private String createdBy;
    private String updatedBy;
    private List<Integer> elements;

    public String getSourceName() {
        return sourceName;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }

    public Integer getOpProjectId() {
        return opProjectId;
    }

    public void setOpProjectId(Integer opProjectId) {
        this.opProjectId = opProjectId;
    }

    public String getPfSourceId() {
        return pfSourceId;
    }

    public void setPfSourceId(String pfSourceId) {
        this.pfSourceId = pfSourceId;
    }

    public Integer getOpSourceId() {
        return opSourceId;
    }

    public void setOpSourceId(Integer opSourceId) {
        this.opSourceId = opSourceId;
    }

    public Integer getTagHeadId() {
        return tagHeadId;
    }

    public void setTagHeadId(Integer tagHeadId) {
        this.tagHeadId = tagHeadId;
    }

    public String getTagHeadName() {
        return tagHeadName;
    }

    public void setTagHeadName(String tagHeadName) {
        this.tagHeadName = tagHeadName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getProjectTitle() {
        return projectTitle;
    }

    public void setProjectTitle(String projectTitle) {
        this.projectTitle = projectTitle;
    }

    public String getCollectId() {
        return collectId;
    }

    public void setCollectId(String collectId) {
        this.collectId = collectId;
    }

    public String getCollectName() {
        return collectName;
    }

    public void setCollectName(String collectName) {
        this.collectName = collectName;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public List<Integer> getElements() {
        return elements;
    }

    public void setElements(List<Integer> elements) {
        this.elements = elements;
    }
}
