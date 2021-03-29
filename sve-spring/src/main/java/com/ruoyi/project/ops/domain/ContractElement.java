package com.ruoyi.project.ops.domain;

/**
 * 【请填写功能名称】对象 pf_bas_devops
 *
 * @author ruoyi
 * @date 2020-08-25
 */


public class ContractElement {

    private Integer opElementId;
    private Integer opProjectId;
    private Integer elementId;
    private String createdBy;

    public Integer getOpElementId() {
        return opElementId;
    }

    public void setOpElementId(Integer opElementId) {
        this.opElementId = opElementId;
    }

    public Integer getOpProjectId() {
        return opProjectId;
    }

    public void setOpProjectId(Integer opProjectId) {
        this.opProjectId = opProjectId;
    }

    public Integer getElementId() {
        return elementId;
    }

    public void setElementId(Integer elementId) {
        this.elementId = elementId;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
}
