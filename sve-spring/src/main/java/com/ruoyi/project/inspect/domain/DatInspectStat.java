package com.ruoyi.project.inspect.domain;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class DatInspectStat {

    private static final long serialVersionUID = 1L;

    private Integer statId;

    /**
     * 企业名称
     */
    @Excel(name = "企业名称", type = Excel.Type.IMPORT)
    private String sourceName;

    /**
     * 镇街编号
     */
    private String townCode;

    /**
     * 镇街名称
     */
    @Excel(name = "镇属", type = Excel.Type.IMPORT)
    private String townName;

    /**
     * 巡检日期
     */
    @Excel(name = "巡查日期", type = Excel.Type.IMPORT)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date inspectDate;

    /**
     * 异常原因
     */
    @Excel(name = "存在问题", type = Excel.Type.IMPORT)
    private String abnormalCause;

    /**
     * 预警级别
     */
    @Excel(name = "预警级别\n白/黄/红", type = Excel.Type.IMPORT)
    private String warnLevel;

    /**
     * 重点/非重点企业
     */
    @Excel(name = "重点/非重点排污单位", type = Excel.Type.IMPORT)
    private String keyPoint;

    /**
     * 整改情况
     */
    private String rectification;

    /**
     * 预计整改完成时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date expectFinishDate;

    private String createdBy;

    public String getKeyPoint() {
        return keyPoint;
    }

    public void setKeyPoint(String keyPoint) {
        this.keyPoint = keyPoint;
    }

    public Integer getStatId() {
        return statId;
    }

    public void setStatId(Integer statId) {
        this.statId = statId;
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

    public String getTownName() {
        return townName;
    }

    public void setTownName(String townName) {
        this.townName = townName;
    }

    public Date getInspectDate() {
        return inspectDate;
    }

    public void setInspectDate(Date inspectDate) {
        this.inspectDate = inspectDate;
    }

    public String getAbnormalCause() {
        return abnormalCause;
    }

    public void setAbnormalCause(String abnormalCause) {
        this.abnormalCause = abnormalCause;
    }

    public String getWarnLevel() {
        return warnLevel;
    }

    public void setWarnLevel(String warnLevel) {
        this.warnLevel = warnLevel;
    }

    public String getRectification() {
        return rectification;
    }

    public void setRectification(String rectification) {
        this.rectification = rectification;
    }

    public Date getExpectFinishDate() {
        return expectFinishDate;
    }

    public void setExpectFinishDate(Date expectFinishDate) {
        this.expectFinishDate = expectFinishDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    @Override
    public String toString() {
        return "DatInspectStat{" +
                "statId=" + statId +
                ", sourceName='" + sourceName + '\'' +
                ", townCode='" + townCode + '\'' +
                ", townName='" + townName + '\'' +
                ", inspectDate=" + inspectDate +
                ", abnormalCause='" + abnormalCause + '\'' +
                ", warnLevel='" + warnLevel + '\'' +
                ", keyPoint='" + keyPoint + '\'' +
                ", rectification='" + rectification + '\'' +
                ", expectFinishDate=" + expectFinishDate +
                ", createdBy='" + createdBy + '\'' +
                '}';
    }
}
