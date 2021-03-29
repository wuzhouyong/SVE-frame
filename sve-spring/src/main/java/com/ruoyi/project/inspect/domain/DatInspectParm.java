package com.ruoyi.project.inspect.domain;


import com.ruoyi.framework.web.domain.BaseEntity;

import java.util.Date;

public class DatInspectParm extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 企业名称
     */
    private String sourceName;
    private String townCode;
    private Date startDate;
    private Date endDate;

    /**
     * 预警级别
     */
    private String warnLevel;

    /**
     * 重点/非重点企业
     */
    private String keyPoint;

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

    public String getWarnLevel() {
        return warnLevel;
    }

    public void setWarnLevel(String warnLevel) {
        this.warnLevel = warnLevel;
    }

    public String getKeyPoint() {
        return keyPoint;
    }

    public void setKeyPoint(String keyPoint) {
        this.keyPoint = keyPoint;
    }

    @Override
    public String toString() {
        return "InspectParm{" +
                "sourceName='" + sourceName + '\'' +
                ", warnLevel='" + warnLevel + '\'' +
                ", keyPoint='" + keyPoint + '\'' +
                '}';
    }
}
