package com.ruoyi.project.jiayou.domain;

import com.ruoyi.framework.web.domain.BaseEntity;

public class JYNameIdEntity extends BaseEntity {

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
