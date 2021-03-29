package com.ruoyi.common.domain;

import java.io.Serializable;

public class DataSearchColEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private String title;

    private String dataIndex;

    private String groupName;

    private String width;

    private String minWidth = "150";

    private int orderBy;

    public DataSearchColEntity() {
    }

    public DataSearchColEntity(String title, String dataIndex) {
        this(title, dataIndex, null);
    }

    public DataSearchColEntity(String title, String dataIndex, String width) {
        this.title = title;
        this.dataIndex = dataIndex;
        this.width = width;
    }

    public DataSearchColEntity(String title, String dataIndex, String width, String minWidth) {
        this.title = title;
        this.dataIndex = dataIndex;
        this.width = width;
        this.minWidth = minWidth;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDataIndex() {
        return dataIndex;
    }

    public void setDataIndex(String dataIndex) {
        this.dataIndex = dataIndex;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getMinWidth() {
        return minWidth;
    }

    public void setMinWidth(String minWidth) {
        this.minWidth = minWidth;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public int getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(int orderBy) {
        this.orderBy = orderBy;
    }
}
