package com.ruoyi.project.dgom.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.framework.web.domain.BaseEntity;
import org.springframework.format.annotation.DateTimeFormat;

//@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class DataSearchPar extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    private String taskheadid;

    private String sourceName;

    private String sourceId;

    private Integer taskstatus;

    private Integer ismanualtask;

    private String townName;

    private Integer townCode;

    private Integer abnortasktype;

    private String elementdesc;

    private Integer elementid;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private String tasksentDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private String startDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private String endDate;

    private String checkpointdesc;

    private Integer checkpointid;

    private String datadevid;

    private Integer alarmlevel;

    private Integer isreported;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private String reportedDate;

    private Integer isnormal;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private String normalDate;

    public String getTaskheadid() {
        return taskheadid;
    }

    public void setTaskheadid(String taskheadid) {
        this.taskheadid = taskheadid;
    }

    public String getSourceName() {
        return sourceName;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public Integer getTaskstatus() {
        return taskstatus;
    }

    public void setTaskstatus(Integer taskstatus) {
        this.taskstatus = taskstatus;
    }

    public Integer getIsmanualtask() {
        return ismanualtask;
    }

    public void setIsmanualtask(Integer ismanualtask) {
        this.ismanualtask = ismanualtask;
    }

    public String getTownName() {
        return townName;
    }

    public void setTownName(String townName) {
        this.townName = townName;
    }

    public Integer getTownCode() {
        return townCode;
    }

    public void setTownCode(Integer townCode) {
        this.townCode = townCode;
    }

    public Integer getAbnortasktype() {
        return abnortasktype;
    }

    public void setAbnortasktype(Integer abnortasktype) {
        this.abnortasktype = abnortasktype;
    }

    public String getElementdesc() {
        return elementdesc;
    }

    public void setElementdesc(String elementdesc) {
        this.elementdesc = elementdesc;
    }

    public Integer getElementid() {
        return elementid;
    }

    public void setElementid(Integer elementid) {
        this.elementid = elementid;
    }

    public String getTasksentDate() {
        return tasksentDate;
    }

    public void setTasksentDate(String tasksentDate) {
        this.tasksentDate = tasksentDate;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getCheckpointdesc() {
        return checkpointdesc;
    }

    public void setCheckpointdesc(String checkpointdesc) {
        this.checkpointdesc = checkpointdesc;
    }

    public Integer getCheckpointid() {
        return checkpointid;
    }

    public void setCheckpointid(Integer checkpointid) {
        this.checkpointid = checkpointid;
    }

    public Integer getAlarmlevel() {
        return alarmlevel;
    }

    public void setAlarmlevel(Integer alarmlevel) {
        this.alarmlevel = alarmlevel;
    }

    public Integer getIsreported() {
        return isreported;
    }

    public void setIsreported(Integer isreported) {
        this.isreported = isreported;
    }

    public String getReportedDate() {
        return reportedDate;
    }

    public void setReportedDate(String reportedDate) {
        this.reportedDate = reportedDate;
    }

    public Integer getIsnormal() {
        return isnormal;
    }

    public void setIsnormal(Integer isnormal) {
        this.isnormal = isnormal;
    }

    public String getNormalDate() {
        return normalDate;
    }

    public void setNormalDate(String normalDate) {
        this.normalDate = normalDate;
    }

    public String getDatadevid() {
        return datadevid;
    }

    public void setDatadevid(String datadevid) {
        this.datadevid = datadevid;
    }
}
