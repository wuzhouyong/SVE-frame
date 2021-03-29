package com.ruoyi.project.dgom.domain;

import java.math.BigDecimal;

/**
 * @author solang
 * @date 2020-12-25 17:14
 */
public class DutyExport  {

    private String isReported;
    private String townName;
    private String isReturnNormal;
    private String processMemo;
    private String returnNormalDate;
    private String dateReported;
    private String abnorDesc;
    private String sourceName;
    private String startDate;
    private String twoDayCnt;
    private String fourDayCnt;

    private String rateLess90;
    private String rate;
    private String zdatasourname;
    private BigDecimal overCount;

    public String getTwoDayCnt() {
        return twoDayCnt;
    }

    public void setTwoDayCnt(String twoDayCnt) {
        this.twoDayCnt = twoDayCnt;
    }

    public String getFourDayCnt() {
        return fourDayCnt;
    }

    public void setFourDayCnt(String fourDayCnt) {
        this.fourDayCnt = fourDayCnt;
    }

    public BigDecimal getOverCount() {
        return overCount;
    }
    public void setOverCount(BigDecimal overCount) {
        this.overCount = overCount;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getRateLess90() {
        return rateLess90;
    }

    public void setRateLess90(String rateLess90) {
        this.rateLess90 = rateLess90;
    }

    public String getZdatasourname() {
        return zdatasourname;
    }

    public void setZdatasourname(String zdatasourname) {
        this.zdatasourname = zdatasourname;
    }

    public String getIsReported() {
        return isReported;
    }

    public void setIsReported(String isReported) {
        this.isReported = isReported;
    }

    public String getTownName() {
        return townName;
    }

    public void setTownName(String townName) {
        this.townName = townName;
    }

    public String getIsReturnNormal() {
        return isReturnNormal;
    }

    public void setIsReturnNormal(String isReturnNormal) {
        this.isReturnNormal = isReturnNormal;
    }

    public String getProcessMemo() {
        return processMemo;
    }

    public void setProcessMemo(String processMemo) {
        this.processMemo = processMemo;
    }

    public String getReturnNormalDate() {
        return returnNormalDate;
    }

    public void setReturnNormalDate(String returnNormalDate) {
        this.returnNormalDate = returnNormalDate;
    }

    public String getDateReported() {
        return dateReported;
    }

    public void setDateReported(String dateReported) {
        this.dateReported = dateReported;
    }

    public String getAbnorDesc() {
        return abnorDesc;
    }

    public void setAbnorDesc(String abnorDesc) {
        this.abnorDesc = abnorDesc;
    }

    public String getSourceName() {
        return sourceName;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
}
