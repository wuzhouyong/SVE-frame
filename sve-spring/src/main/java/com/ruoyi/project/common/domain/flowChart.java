package com.ruoyi.project.common.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 流程图数据结构
 */
public class flowChart {
    private static final long serialVersionUID = 1L;

    private String id;
    private String type;
    private String name;
    private List<flowChart> list = new ArrayList<>();
    private List<Map> other = new ArrayList<>();
    private String flag;

    private Object extend;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<flowChart> getList() {
        return list;
    }

    public void setList(List<flowChart> list) {
        this.list = list;
    }

    public void addForList(flowChart point) {
        this.list.add(point);
    }

    public List<Map> getOther() {
        return other;
    }

    public void setOther(List<Map> other) {
        this.other = other;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public Object getExtend() {
        return extend;
    }

    public void setExtend(Object extend) {
        this.extend = extend;
    }
}
