package com.ruoyi.common.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author solang
 * @date 2020-07-17 18:57
 */
public class DataSearchResultT<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<?> cols = new ArrayList<>();

    private List<T> data;

    public List<?> getCols() {
        return cols;
    }

    public void setCols(List<?> cols) {
        this.cols = cols;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public DataSearchResultT() {
    }

    public DataSearchResultT(List<?> cols, List<T> data) {
        this.cols = cols;
        this.data = data;
    }

    @Override
    public String toString() {
        return "DataSearchResult{" + "cols=" + cols + ", data=" + data + '}';
    }
}
