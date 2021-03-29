package com.ruoyi.project.warnmsg.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ruoyi.common.utils.SecurityUtils;

import java.util.Date;

public class WarnProcEntity extends WarnProcResult implements Cloneable {
    private String[] warnIds;
    private Integer warnLevel;

    public String[] getWarnIds() {
        return warnIds;
    }

    public void setWarnIds(String[] warnIds) {
        this.warnIds = warnIds;
    }

    public Integer getWarnLevel() {
        return warnLevel;
    }

    public void setWarnLevel(Integer warnLevel) {
        this.warnLevel = warnLevel;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
