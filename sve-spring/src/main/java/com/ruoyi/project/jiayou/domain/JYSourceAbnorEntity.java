package com.ruoyi.project.jiayou.domain;

import com.ruoyi.common.utils.LabelQueryUtils;
import com.ruoyi.framework.web.domain.BaseEntity;

import java.util.List;


public class JYSourceAbnorEntity extends BaseEntity {

    private String fullName;
    private String socialCreditCode;
    private String townName;
    private Integer townCode;
    private String dtmon;
    private String totalcnt1;
    private String proccnt1;
    private String factcnt1;
    private String totalcnt2;
    private String proccnt2;
    private String factcnt2;
    private String totalcnt3;
    private String proccnt3;
    private String factcnt3;
    private String othercnt;
    private String labelQuery;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getSocialCreditCode() {
        return socialCreditCode;
    }

    public void setSocialCreditCode(String socialCreditCode) {
        this.socialCreditCode = socialCreditCode;
    }

    public String getTownName() {
        return townName;
    }

    public void setTownName(String townName) {
        this.townName = townName;
    }

    public String getDtmon() {
        return dtmon;
    }

    public void setDtmon(String dtmon) {
        this.dtmon = dtmon;
    }

    public String getTotalcnt1() {
        return totalcnt1;
    }

    public void setTotalcnt1(String totalcnt1) {
        this.totalcnt1 = totalcnt1;
    }

    public String getProccnt1() {
        return proccnt1;
    }

    public void setProccnt1(String proccnt1) {
        this.proccnt1 = proccnt1;
    }

    public String getFactcnt1() {
        return factcnt1;
    }

    public void setFactcnt1(String factcnt1) {
        this.factcnt1 = factcnt1;
    }

    public String getTotalcnt2() {
        return totalcnt2;
    }

    public void setTotalcnt2(String totalcnt2) {
        this.totalcnt2 = totalcnt2;
    }

    public String getProccnt2() {
        return proccnt2;
    }

    public void setProccnt2(String proccnt2) {
        this.proccnt2 = proccnt2;
    }

    public String getFactcnt2() {
        return factcnt2;
    }

    public void setFactcnt2(String factcnt2) {
        this.factcnt2 = factcnt2;
    }

    public String getTotalcnt3() {
        return totalcnt3;
    }

    public void setTotalcnt3(String totalcnt3) {
        this.totalcnt3 = totalcnt3;
    }

    public String getProccnt3() {
        return proccnt3;
    }

    public void setProccnt3(String proccnt3) {
        this.proccnt3 = proccnt3;
    }

    public String getFactcnt3() {
        return factcnt3;
    }

    public void setFactcnt3(String factcnt3) {
        this.factcnt3 = factcnt3;
    }

    public String getOthercnt() {
        return othercnt;
    }

    public void setOthercnt(String othercnt) {
        this.othercnt = othercnt;
    }

    public Integer getTownCode() {
        return townCode;
    }

    public void setTownCode(Integer townCode) {
        this.townCode = townCode;
    }

    public void setLabelIds(List<List<Integer>> labelIds) {
        labelQuery = LabelQueryUtils.getQuery(labelIds, "s");
    }

    public String getLabelQuery() {
        return labelQuery;
    }
}
