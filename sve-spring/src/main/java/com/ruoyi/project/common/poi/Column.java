package com.ruoyi.project.common.poi;

import java.util.List;

public class Column extends TreeNode<Column> {

    public Column(String name, String field, int width) {
        this.name = name;
        this.field = field;
        this.width = width;
    }

    public Column(String name, List<Column> childrens) {
        this.name = name;
        this.setChildrens(childrens);
    }

    private String field;

    private String name;

    private int width;

    private int firstRow;

    private int lastRow;

    private int firstCol;

    private int lastCol;

//    private List<Column> childrens;

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }
    public int getFirstRow() {
        return firstRow;
    }

    public void setFirstRow(int firstRow) {
        this.firstRow = firstRow;
    }

    public int getLastRow() {
        return lastRow;
    }

    public void setLastRow(int lastRow) {
        this.lastRow = lastRow;
    }

    public int getFirstCol() {
        return firstCol;
    }

    public void setFirstCol(int firstCol) {
        this.firstCol = firstCol;
    }

    public int getLastCol() {
        return lastCol;
    }

    public void setLastCol(int lastCol) {
        this.lastCol = lastCol;
    }
//    public List<Column> getChildrens() {
//        return childrens;
//    }
//
//    public void setChildrens(List<Column> childrens) {
//        this.childrens = childrens;
//    }
}
