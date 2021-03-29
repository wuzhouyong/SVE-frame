package com.ruoyi.project.warnmsg.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class WarnProcResult {
    private Integer detailId;
    private String warnId;
    private Integer procStatus;
    private String userType;
    private Boolean isNormal;
    private String warnReason;
    private String procDesc;
    private String procUser;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date procDate;
    private List<Map> attaches;

    public Integer getDetailId() {
        return detailId;
    }

    public void setDetailId(Integer detailId) {
        this.detailId = detailId;
    }

    public String getWarnId() {
        return warnId;
    }

    public void setWarnId(String warnId) {
        this.warnId = warnId;
    }

    public Integer getProcStatus() {
        return procStatus;
    }

    public void setProcStatus(Integer procStatus) {
        this.procStatus = procStatus;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public Boolean getIsNormal() {
        return isNormal;
    }

    public void setIsNormal(Boolean isNormal) {
        this.isNormal = isNormal;
    }

    public String getWarnReason() {
        return warnReason;
    }

    public void setWarnReason(String warnReason) {
        this.warnReason = warnReason;
    }

    public String getProcDesc() {
        return procDesc;
    }

    public void setProcDesc(String procDesc) {
        this.procDesc = procDesc;
    }

    public String getProcUser() {
        return procUser;
    }

    public void setProcUser(String procUser) {
        this.procUser = procUser;
    }

    public Date getProcDate() {
        return procDate;
    }

    public void setProcDate(Date procDate) {
        this.procDate = procDate;
    }

    public List<Map> getAttaches() {
        return attaches;
    }

    public void setAttaches(List<Map> attaches) {
        this.attaches = attaches;
    }
}
