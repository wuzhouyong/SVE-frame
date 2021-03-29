package com.ruoyi.project.dgom.domain.ent_accept;

public class EntAcceptDetailResult {
    private String detailId;
    private Integer checkPointId;
    private String checkPointDesc;
    private Integer dataDevId;
    private String dataDevDesc;

    public String getDetailId() {
        return detailId;
    }

    public void setDetailId(String detailId) {
        this.detailId = detailId;
    }

    public Integer getCheckPointId() {
        return checkPointId;
    }

    public void setCheckPointId(Integer checkPointId) {
        this.checkPointId = checkPointId;
    }

    public String getCheckPointDesc() {
        return checkPointDesc;
    }

    public void setCheckPointDesc(String checkPointDesc) {
        this.checkPointDesc = checkPointDesc;
    }

    public Integer getDataDevId() {
        return dataDevId;
    }

    public void setDataDevId(Integer dataDevId) {
        this.dataDevId = dataDevId;
    }

    public String getDataDevDesc() {
        return dataDevDesc;
    }

    public void setDataDevDesc(String dataDevDesc) {
        this.dataDevDesc = dataDevDesc;
    }
}
