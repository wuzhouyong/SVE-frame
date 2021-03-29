package com.ruoyi.common.domain;

import java.util.List;

public class ResultEntityData {

    private int page;

    private List<?> pageList;

    private int total;

    public void setPage(int page) {
        this.page = page;
    }

    public void setPageList(List<?> pageList) {
        this.pageList = pageList;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPage() {
        return page;
    }

    public List<?> getPageList() {
        return pageList;
    }

    public int getTotal() {
        return total;
    }

    public ResultEntityData() {
    }

    public ResultEntityData(int page, List<?> pageList, int total) {
        this.page = page;
        this.pageList = pageList;
        this.total = total;
    }
}

