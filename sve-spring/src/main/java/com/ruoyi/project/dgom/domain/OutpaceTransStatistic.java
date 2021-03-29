package com.ruoyi.project.dgom.domain;

import java.math.BigDecimal;

public class OutpaceTransStatistic {
    private BigDecimal checkpointCount;
    private BigDecimal finishCount;
    private BigDecimal finishRate;
    private BigDecimal lessthanCount;
    private BigDecimal lostCount;
    private BigDecimal negativeCount;
    private BigDecimal overCount;
    private BigDecimal sourceCount;
    private BigDecimal staticCount;
    private String townName;
    private BigDecimal transportRate;
    private BigDecimal unhandleCount;
    private BigDecimal zeroCount;

    public BigDecimal getCheckpointCount() {
        return checkpointCount;
    }

    public void setCheckpointCount(BigDecimal checkpointCount) {
        this.checkpointCount = checkpointCount;
    }

    public BigDecimal getFinishCount() {
        return finishCount;
    }

    public void setFinishCount(BigDecimal finishCount) {
        this.finishCount = finishCount;
    }

    public BigDecimal getFinishRate() {
        return finishRate;
    }

    public void setFinishRate(BigDecimal finishRate) {
        this.finishRate = finishRate;
    }

    public BigDecimal getLessthanCount() {
        return lessthanCount;
    }

    public void setLessthanCount(BigDecimal lessthanCount) {
        this.lessthanCount = lessthanCount;
    }

    public BigDecimal getLostCount() {
        return lostCount;
    }

    public void setLostCount(BigDecimal lostCount) {
        this.lostCount = lostCount;
    }

    public BigDecimal getNegativeCount() {
        return negativeCount;
    }

    public void setNegativeCount(BigDecimal negativeCount) {
        this.negativeCount = negativeCount;
    }

    public BigDecimal getOverCount() {
        return overCount;
    }

    public void setOverCount(BigDecimal overCount) {
        this.overCount = overCount;
    }

    public BigDecimal getSourceCount() {
        return sourceCount;
    }

    public void setSourceCount(BigDecimal sourceCount) {
        this.sourceCount = sourceCount;
    }

    public BigDecimal getStaticCount() {
        return staticCount;
    }

    public void setStaticCount(BigDecimal staticCount) {
        this.staticCount = staticCount;
    }

    public String getTownName() {
        return townName;
    }

    public void setTownName(String townName) {
        this.townName = townName;
    }

    public BigDecimal getTransportRate() {
        return transportRate;
    }

    public void setTransportRate(BigDecimal transportRate) {
        this.transportRate = transportRate;
    }

    public BigDecimal getUnhandleCount() {
        return unhandleCount;
    }

    public void setUnhandleCount(BigDecimal unhandleCount) {
        this.unhandleCount = unhandleCount;
    }

    public BigDecimal getZeroCount() {
        return zeroCount;
    }

    public void setZeroCount(BigDecimal zeroCount) {
        this.zeroCount = zeroCount;
    }
}
