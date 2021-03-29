package com.ruoyi.project.enterprise.domain;

import java.io.Serializable;

public class TagHeadEntity implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private String tagHeadName;

    private String tagHeadId;

    private Integer orderBy;

    private String status;

    public String getTagHeadId() {
        return tagHeadId;
    }

    public void setTagHeadId(String tagHeadId) {
        this.tagHeadId = tagHeadId;
    }

    public String getTagHeadName() {
        return tagHeadName;
    }

    public void setTagHeadName(String tagHeadName) {
        this.tagHeadName = tagHeadName;
    }

    public Integer getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(Integer orderBy) {
        this.orderBy = orderBy;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
