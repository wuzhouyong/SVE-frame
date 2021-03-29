package com.ruoyi.project.dgom.domain;

import java.util.List;

/**
 * @author solang
 * @date 2020-09-28 19:45
 */
public class CollectModel {

    private String detailId;
    private String applyId;
    private String checkpointDesc;
    private String collectDevDesc;
    private String collectDevSpec;
    private String mn;
    private Integer colno;
    private List<DeviceModel> devices;

    public String getDetailId() {
        return detailId;
    }

    public void setDetailId(String detailId) {
        this.detailId = detailId;
    }

    public String getApplyId() {
        return applyId;
    }

    public void setApplyId(String applyId) {
        this.applyId = applyId;
    }

    public String getCheckpointDesc() {
        return checkpointDesc;
    }

    public void setCheckpointDesc(String checkpointDesc) {
        this.checkpointDesc = checkpointDesc;
    }

    public String getCollectDevDesc() {
        return collectDevDesc;
    }

    public void setCollectDevDesc(String collectDevDesc) {
        this.collectDevDesc = collectDevDesc;
    }

    public String getCollectDevSpec() {
        return collectDevSpec;
    }

    public void setCollectDevSpec(String collectDevSpec) {
        this.collectDevSpec = collectDevSpec;
    }

    public String getMn() {
        return mn;
    }

    public void setMn(String mn) {
        this.mn = mn;
    }

    public List<DeviceModel> getDevices() {
        return devices;
    }

    public void setDevices(List<DeviceModel> devices) {
        this.devices = devices;
    }

    public Integer getColno() {
        return colno;
    }

    public void setColno(Integer colno) {
        this.colno = colno;
    }
}
