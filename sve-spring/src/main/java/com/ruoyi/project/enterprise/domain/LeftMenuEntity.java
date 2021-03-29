package com.ruoyi.project.enterprise.domain;

public class LeftMenuEntity {

    private int count;

    private String type;

    private String ratio;

    private String desc;

    private int orderBy;

    public int getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(int orderBy) {
        this.orderBy = orderBy;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public LeftMenuEntity() {
    }

    public LeftMenuEntity(String type, int count, String ratio) {
        this(type, count, ratio, 0);
    }

    public LeftMenuEntity(String type, int count, String ratio, int orderBy) {
        this.count = count;
        this.type = type;
        this.ratio = ratio;
    }

    public String getRatio() {
        return ratio;
    }

    public void setRatio(String ratio) {
        this.ratio = ratio;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
