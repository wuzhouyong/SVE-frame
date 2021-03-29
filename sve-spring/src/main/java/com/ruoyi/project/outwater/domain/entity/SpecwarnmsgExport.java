package com.ruoyi.project.outwater.domain.entity;

import com.ruoyi.project.common.poi.TreeNode;

/**
 * @author solang
 * @date 2020-11-23 9:48
 */
public class SpecwarnmsgExport extends TreeNode<SpecwarnmsgExport> {

    private String fullName;
    private String townName;
    private String constructionUnit;
    private String brand;
    private String startDate;
    private String endDate;
    private Integer warnType;
    private String warnTypeName = "排水企业监管数据异常 ";
    private String warnDesc;

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

    public String getConstructionUnit() {
        return constructionUnit;
    }

    public void setConstructionUnit(String constructionUnit) {
        this.constructionUnit = constructionUnit;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
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

    public Integer getWarnType() {
        return warnType;
    }

    public void setWarnType(Integer warnType) {
        this.warnType = warnType;
    }

    public String getWarnDesc() {
        return warnDesc;
    }

    public void setWarnDesc(String warnDesc) {
        this.warnDesc = warnDesc;
    }
}
