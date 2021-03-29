package com.ruoyi.common.domain;

import java.util.List;

public class WarnResult {
    private int code;
    private Object rows;
    private int total;
    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getRows() {
        return rows;
    }

    public void setRows(Object rows) {
        this.rows = rows;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static WarnResult getDataTable(List<?> list, int total) {
        WarnResult rspData = new WarnResult();
        rspData.setCode(200);
        rspData.setRows(list);
        rspData.setMsg("");
        rspData.setTotal(total);
        return rspData;
    }
}
