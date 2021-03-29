package com.ruoyi.project.enterprise.domain.dashboard;

import com.ruoyi.framework.web.domain.BaseEntity;

import java.util.Date;

public class DashboardParam extends BaseEntity {
    private String townCode;
    private Integer[] tagHeadIds;
    private Date startDate;
    private Date endDate;

    public String getTownCode() {
        return townCode;
    }

    public void setTownCode(String townCode) {
        this.townCode = townCode;
    }

    public Integer[] getTagHeadIds() {
        return tagHeadIds;
    }

    public void setTagHeadIds(Integer[] tagHeadIds) {
        this.tagHeadIds = tagHeadIds;
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
}
