package com.ruoyi.project.outwater.domain.entity;

import java.io.Serializable;

public class NameAndIdEntity implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private String Name;

    private String Id;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }
}
