package com.ruoyi.project.enterprise.domain;

import com.ruoyi.framework.web.domain.BaseEntity;

public class WadeTaskSummary extends BaseEntity{

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private String townName;

    private int qyzs;

    private int zdpwdw;

    private int zdpsh;

    private int lxfscsdw;

    private int lxfscldw;

    private int sdly;

    private int zzhy;

    private int jsbmcl;

    private int zcdmyth;

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

    public int getZdpsh() {
        return zdpsh;
    }

    public void setZdpsh(int zdpsh) {
        this.zdpsh = zdpsh;
    }

    public int getLxfscsdw() {
        return lxfscsdw;
    }

    public void setLxfscsdw(int lxfscsdw) {
        this.lxfscsdw = lxfscsdw;
    }

    public int getLxfscldw() {
        return lxfscldw;
    }

    public void setLxfscldw(int lxfscldw) {
        this.lxfscldw = lxfscldw;
    }

    public int getSdly() {
        return sdly;
    }

    public void setSdly(int sdly) {
        this.sdly = sdly;
    }

    public int getZzhy() {
        return zzhy;
    }

    public void setZzhy(int zzhy) {
        this.zzhy = zzhy;
    }

    public int getJsbmcl() {
        return jsbmcl;
    }

    public void setJsbmcl(int jsbmcl) {
        this.jsbmcl = jsbmcl;
    }

    public int getZcdmyth() {
        return zcdmyth;
    }

    public void setZcdmyth(int zcdmyth) {
        this.zcdmyth = zcdmyth;
    }
}
