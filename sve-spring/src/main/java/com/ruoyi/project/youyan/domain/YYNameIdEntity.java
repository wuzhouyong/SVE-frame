package com.ruoyi.project.youyan.domain;

import com.ruoyi.framework.web.domain.BaseEntity;

public class YYNameIdEntity extends BaseEntity {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private String id;

    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
