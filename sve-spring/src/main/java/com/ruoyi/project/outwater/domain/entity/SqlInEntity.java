package com.ruoyi.project.outwater.domain.entity;

public class SqlInEntity {

    private String[] array;

    private String updatedBy;

    public String[] getArray() {
        return array;
    }

    public void setArray(String[] array) {
        this.array = array;
    }

    public String getUpdateBy() {
        return updatedBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updatedBy = updateBy;
    }

    public SqlInEntity(String[] array, String updateBy) {
        this.array = array;
        this.updatedBy = updateBy;
    }
}
