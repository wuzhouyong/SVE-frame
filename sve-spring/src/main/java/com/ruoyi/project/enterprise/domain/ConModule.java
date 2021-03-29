package com.ruoyi.project.enterprise.domain;

import java.util.List;

public class ConModule {

    private LeftMenuEntity title;

    private List<LeftMenuEntity> data;

    public LeftMenuEntity getTitle() {
        return title;
    }

    public void setTitle(LeftMenuEntity title) {
        this.title = title;
    }

    public List<LeftMenuEntity> getData() {
        return data;
    }

    public void setData(List<LeftMenuEntity> data) {
        this.data = data;
    }
}
