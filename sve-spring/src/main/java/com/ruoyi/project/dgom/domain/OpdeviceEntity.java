package com.ruoyi.project.dgom.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.ruoyi.common.utils.DateUtils;

import java.util.ArrayList;
import java.util.List;

public class OpdeviceEntity {

    private Integer checkpointid;
    private Integer checkpointtype;
    private String checkpointdesc;
    private String sourcename;
    private String sourceenvhead;
    private String sourceenvheadinfo;
    private String opcompanyname;
    private String opcompanytype;
    private String opcompanyhead;
    private String opcompanyheadinfo;
    private String checkpointname;
    private String oppersonname;
    private String oppersontel;
    private String opstartdate;
    private String openddate;
    private String oplicensenum;
    private String oplicissueunit;
    private String oplicissuedate;
    private String opnolicreason;
    private String createdby;
    private String updatedby;

    private String bizSourceId;

    public String getBizSourceId() {
        return bizSourceId;
    }

    public void setBizSourceId(String bizSourceId) {
        this.bizSourceId = bizSourceId;
    }

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<OpWDeviceEntity> opw = new ArrayList<>();

    public List<OpWDeviceEntity> getOpw() {
        return opw;
    }

    public void setOpw(List<OpWDeviceEntity> opw) {
        this.opw = opw;
    }

    public String getCreatedby() {
        return createdby;
    }

    public void setCreatedby(String createdby) {
        this.createdby = createdby;
    }

    public String getUpdatedby() {
        return updatedby;
    }

    public void setUpdatedby(String updatedby) {
        this.updatedby = updatedby;
    }

    public Integer getCheckpointid() {
        return checkpointid;
    }

    public void setCheckpointid(Integer checkpointid) {
        this.checkpointid = checkpointid;
    }

    public Integer getCheckpointtype() {
        return checkpointtype;
    }

    public void setCheckpointtype(Integer checkpointtype) {
        this.checkpointtype = checkpointtype;
    }

    public String getCheckpointdesc() {
        return checkpointdesc;
    }

    public void setCheckpointdesc(String checkpointdesc) {
        this.checkpointdesc = checkpointdesc;
    }

    public String getSourcename() {
        return sourcename;
    }

    public void setSourcename(String sourcename) {
        this.sourcename = sourcename;
    }

    public String getSourceenvhead() {
        return sourceenvhead;
    }

    public void setSourceenvhead(String sourceenvhead) {
        this.sourceenvhead = sourceenvhead;
    }

    public String getSourceenvheadinfo() {
        return sourceenvheadinfo;
    }

    public void setSourceenvheadinfo(String sourceenvheadinfo) {
        this.sourceenvheadinfo = sourceenvheadinfo;
    }

    public String getOpcompanyname() {
        return opcompanyname;
    }

    public void setOpcompanyname(String opcompanyname) {
        this.opcompanyname = opcompanyname;
    }

    public String getOpcompanytype() {
        return opcompanytype;
    }

    public void setOpcompanytype(String opcompanytype) {
        this.opcompanytype = opcompanytype;
    }

    public String getOpcompanyhead() {
        return opcompanyhead;
    }

    public void setOpcompanyhead(String opcompanyhead) {
        this.opcompanyhead = opcompanyhead;
    }

    public String getOpcompanyheadinfo() {
        return opcompanyheadinfo;
    }

    public void setOpcompanyheadinfo(String opcompanyheadinfo) {
        this.opcompanyheadinfo = opcompanyheadinfo;
    }

    public String getCheckpointname() {
        return checkpointname;
    }

    public void setCheckpointname(String checkpointname) {
        this.checkpointname = checkpointname;
    }

    public String getOppersonname() {
        return oppersonname;
    }

    public void setOppersonname(String oppersonname) {
        this.oppersonname = oppersonname;
    }

    public String getOppersontel() {
        return oppersontel;
    }

    public void setOppersontel(String oppersontel) {
        this.oppersontel = oppersontel;
    }

    public String getOpstartdate() {
        return DateUtils.formatDateStr(opstartdate);
    }

    public void setOpstartdate(String opstartdate) {
        this.opstartdate = opstartdate;
    }

    public String getOpenddate() {
        return DateUtils.formatDateStr(openddate);
    }

    public void setOpenddate(String openddate) {
        this.openddate = DateUtils.formatDateStr(openddate);
    }

    public String getOplicensenum() {
        return oplicensenum;
    }

    public void setOplicensenum(String oplicensenum) {
        this.oplicensenum = oplicensenum;
    }

    public String getOplicissueunit() {
        return oplicissueunit;
    }

    public void setOplicissueunit(String oplicissueunit) {
        this.oplicissueunit = oplicissueunit;
    }

    public String getOplicissuedate() {
        return DateUtils.formatDateStr(oplicissuedate);
    }

    public void setOplicissuedate(String oplicissuedate) {
        this.oplicissuedate = oplicissuedate;
    }

    public String getOpnolicreason() {
        return opnolicreason;
    }

    public void setOpnolicreason(String opnolicreason) {
        this.opnolicreason = opnolicreason;
    }
}
