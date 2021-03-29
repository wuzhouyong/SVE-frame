package com.ruoyi.project.dgom.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * @author solang
 * @date 2020-09-27 17:20
 */
public class ReturnParam extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String sourceId;

    private Integer checkpointid;

    private String datasourname;

    private String checkpointdesc;

    private Integer checkpointtype;

    private String mn;

    private String datadevid;

    private String datadevdesc;

    private String equipmentdesc;

    private String serialnum;

    private Integer devstatus;

    private String serialbaudrate;

    private String elementid;

    private String elementdesc;

    private Double minvalue;

    private Double maxvalue;

    private String unit;

    private Double pause;

    private Double rangemin;

    private Double rangemax;

    private Integer cdevdigits;

    public Integer getCheckpointid() {
        return checkpointid;
    }

    public void setCheckpointid(Integer checkpointid) {
        this.checkpointid = checkpointid;
    }

    public String getDatasourname() {
        return datasourname;
    }

    public void setDatasourname(String datasourname) {
        this.datasourname = datasourname;
    }

    public String getCheckpointdesc() {
        return checkpointdesc;
    }

    public void setCheckpointdesc(String checkpointdesc) {
        this.checkpointdesc = checkpointdesc;
    }

    public String getMn() {
        return mn;
    }

    public void setMn(String mn) {
        this.mn = mn;
    }

    public String getDatadevid() {
        return datadevid;
    }

    public void setDatadevid(String datadevid) {
        this.datadevid = datadevid;
    }

    public String getDatadevdesc() {
        return datadevdesc;
    }

    public void setDatadevdesc(String datadevdesc) {
        this.datadevdesc = datadevdesc;
    }

    public String getEquipmentdesc() {
        return equipmentdesc;
    }

    public void setEquipmentdesc(String equipmentdesc) {
        this.equipmentdesc = equipmentdesc;
    }

    public String getSerialnum() {
        return serialnum;
    }

    public void setSerialnum(String serialnum) {
        this.serialnum = serialnum;
    }

    public Integer getDevstatus() {
        return devstatus;
    }

    public void setDevstatus(Integer devstatus) {
        this.devstatus = devstatus;
    }

    public String getSerialbaudrate() {
        return serialbaudrate;
    }

    public void setSerialbaudrate(String serialbaudrate) {
        this.serialbaudrate = serialbaudrate;
    }

    public String getElementid() {
        return elementid;
    }

    public void setElementid(String elementid) {
        this.elementid = elementid;
    }

    public String getElementdesc() {
        return elementdesc;
    }

    public void setElementdesc(String elementdesc) {
        this.elementdesc = elementdesc;
    }

    public Double getMinvalue() {
        return minvalue;
    }

    public void setMinvalue(Double minvalue) {
        this.minvalue = minvalue;
    }

    public Double getMaxvalue() {
        return maxvalue;
    }

    public void setMaxvalue(Double maxvalue) {
        this.maxvalue = maxvalue;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Double getPause() {
        return pause;
    }

    public void setPause(Double pause) {
        this.pause = pause;
    }

    public Double getRangemin() {
        return rangemin;
    }

    public void setRangemin(Double rangemin) {
        this.rangemin = rangemin;
    }

    public Double getRangemax() {
        return rangemax;
    }

    public void setRangemax(Double rangemax) {
        this.rangemax = rangemax;
    }

    public Integer getCdevdigits() {
        return cdevdigits;
    }

    public void setCdevdigits(Integer cdevdigits) {
        this.cdevdigits = cdevdigits;
    }

    public Integer getCheckpointtype() {
        return checkpointtype;
    }

    public void setCheckpointtype(Integer checkpointtype) {
        this.checkpointtype = checkpointtype;
    }

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }
}
