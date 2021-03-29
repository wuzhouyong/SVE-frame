package com.ruoyi.project.lwjh.domain;

import com.ruoyi.project.common.poi.TreeNode;

public class ElemData extends TreeNode<ElemData> {

    private String PH;

    private String COD;

    private String NH3;

    private String totalP;

    private String totalN;

    private String hasVideo;

    private String smoke;

    private String SO2;

    private String NOX;

    public String getPH() {
        return PH;
    }

    public void setPH(String PH) {
        this.PH = PH;
    }

    public String getCOD() {
        return COD;
    }

    public void setCOD(String COD) {
        this.COD = COD;
    }

    public String getNH3() {
        return NH3;
    }

    public void setNH3(String NH3) {
        this.NH3 = NH3;
    }

    public String getTotalP() {
        return totalP;
    }

    public void setTotalP(String totalP) {
        this.totalP = totalP;
    }

    public String getTotalN() {
        return totalN;
    }

    public void setTotalN(String totalN) {
        this.totalN = totalN;
    }

    public String getHasVideo() {
        return hasVideo;
    }

    public void setHasVideo(String hasVideo) {
        this.hasVideo = hasVideo;
    }

    public String getSmoke() {
        return smoke;
    }

    public void setSmoke(String smoke) {
        this.smoke = smoke;
    }

    public String getSO2() {
        return SO2;
    }

    public void setSO2(String SO2) {
        this.SO2 = SO2;
    }

    public String getNOX() {
        return NOX;
    }

    public void setNOX(String NOX) {
        this.NOX = NOX;
    }
}
