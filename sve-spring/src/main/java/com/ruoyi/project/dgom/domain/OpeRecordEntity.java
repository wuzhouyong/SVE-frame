package com.ruoyi.project.dgom.domain;

import com.ruoyi.framework.web.domain.BaseEntity;

//气重点设备
public class OpeRecordEntity extends BaseEntity {

    private String zoperationid;
    private Integer zoperationtype;
    private String zoperationdate;
    private String zmaintenancestaff;
    private Integer zdatadevid;
    private String zmaintenancecorp;
    private String expireddate;
    private String zdatasourname;
    private String zdatadevdesc;
    private String zequipmentdesc;
    private String zdevno;
    private String zinstallplace;
    private String zcreatedby;
    private String zupdatedby;

    private String bizSourceId;

    public String getBizSourceId() {
        return bizSourceId;
    }

    public void setBizSourceId(String bizSourceId) {
        this.bizSourceId = bizSourceId;
    }

    public String getZoperationid() {
        return zoperationid;
    }

    public void setZoperationid(String zoperationid) {
        this.zoperationid = zoperationid;
    }

    public Integer getZoperationtype() {
        return zoperationtype;
    }

    public void setZoperationtype(Integer zoperationtype) {
        this.zoperationtype = zoperationtype;
    }

    public String getZoperationdate() {
        return zoperationdate;
    }

    public void setZoperationdate(String zoperationdate) {
        this.zoperationdate = zoperationdate;
    }

    public String getZmaintenancestaff() {
        return zmaintenancestaff;
    }

    public void setZmaintenancestaff(String zmaintenancestaff) {
        this.zmaintenancestaff = zmaintenancestaff;
    }

    public Integer getZdatadevid() {
        return zdatadevid;
    }

    public void setZdatadevid(Integer zdatadevid) {
        this.zdatadevid = zdatadevid;
    }

    public String getZmaintenancecorp() {
        return zmaintenancecorp;
    }

    public void setZmaintenancecorp(String zmaintenancecorp) {
        this.zmaintenancecorp = zmaintenancecorp;
    }

    public String getExpireddate() {
        return expireddate;
    }

    public void setExpireddate(String expireddate) {
        this.expireddate = expireddate;
    }

    public String getZdatasourname() {
        return zdatasourname;
    }

    public void setZdatasourname(String zdatasourname) {
        this.zdatasourname = zdatasourname;
    }

    public String getZdatadevdesc() {
        return zdatadevdesc;
    }

    public void setZdatadevdesc(String zdatadevdesc) {
        this.zdatadevdesc = zdatadevdesc;
    }

    public String getZequipmentdesc() {
        return zequipmentdesc;
    }

    public void setZequipmentdesc(String zequipmentdesc) {
        this.zequipmentdesc = zequipmentdesc;
    }

    public String getZdevno() {
        return zdevno;
    }

    public void setZdevno(String zdevno) {
        this.zdevno = zdevno;
    }

    public String getZinstallplace() {
        return zinstallplace;
    }

    public void setZinstallplace(String zinstallplace) {
        this.zinstallplace = zinstallplace;
    }

    public String getZcreatedby() {
        return zcreatedby;
    }

    public void setZcreatedby(String zcreatedby) {
        this.zcreatedby = zcreatedby;
    }

    public String getZupdatedby() {
        return zupdatedby;
    }

    public void setZupdatedby(String zupdatedby) {
        this.zupdatedby = zupdatedby;
    }
}



