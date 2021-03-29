package com.ruoyi.project.enterprise.domain;

import com.ruoyi.framework.web.domain.BaseEntity;

public class GasTaskSummary extends BaseEntity {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private String townName;

    private int qyzs;

    private int zdpwdw;

    private int voc;

    private int qxhy;

    private int cyhy;

    private int jyz;

    public String getTownName() {
        return townName;
    }

    public void setTownName(String townName) {
        this.townName = townName;
    }

    public int getQyzs() {
        return qyzs;
    }

    public void setQyzs(int qyzs) {
        this.qyzs = qyzs;
    }

    public int getZdpwdw() {
        return zdpwdw;
    }

    public void setZdpwdw(int zdpwdw) {
        this.zdpwdw = zdpwdw;
    }

    public int getVoc() {
        return voc;
    }

    public void setVoc(int voc) {
        this.voc = voc;
    }

    public int getQxhy() {
        return qxhy;
    }

    public void setQxhy(int qxhy) {
        this.qxhy = qxhy;
    }

    public int getCyhy() {
        return cyhy;
    }

    public void setCyhy(int cyhy) {
        this.cyhy = cyhy;
    }

    public int getJyz() {
        return jyz;
    }

    public void setJyz(int jyz) {
        this.jyz = jyz;
    }
}
