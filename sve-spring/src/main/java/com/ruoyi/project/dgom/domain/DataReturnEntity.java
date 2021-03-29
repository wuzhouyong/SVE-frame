package com.ruoyi.project.dgom.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @author solang
 * @date 2020-09-10 17:03
 */
//@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class DataReturnEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date datadate;

    private String sourceName;

    private String townName;

    private String categoryname;

    private String checkpointdesc;

    private String elementdesc;

    private String unit;

    private Integer lowerlimit;

    private Double upperlimit;

    private Double amount;

    public Date getDatadate() {
        return datadate;
    }

    public void setDatadate(Date datadate) {
        this.datadate = datadate;
    }

    public String getSourceName() {
        return sourceName;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }

    public String getTownName() {
        return townName;
    }

    public void setTownName(String townName) {
        this.townName = townName;
    }

    public String getCategoryname() {
        return categoryname;
    }

    public void setCategoryname(String categoryname) {
        this.categoryname = categoryname;
    }

    public String getCheckpointdesc() {
        return checkpointdesc;
    }

    public void setCheckpointdesc(String checkpointdesc) {
        this.checkpointdesc = checkpointdesc;
    }

    public String getElementdesc() {
        return elementdesc;
    }

    public void setElementdesc(String elementdesc) {
        this.elementdesc = elementdesc;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Integer getLowerlimit() {
        return lowerlimit;
    }

    public void setLowerlimit(Integer lowerlimit) {
        this.lowerlimit = lowerlimit;
    }

    public Double getUpperlimit() {
        return upperlimit;
    }

    public void setUpperlimit(Double upperlimit) {
        this.upperlimit = upperlimit;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

}
