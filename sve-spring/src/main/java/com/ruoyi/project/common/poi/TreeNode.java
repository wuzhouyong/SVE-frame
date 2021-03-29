package com.ruoyi.project.common.poi;

import java.util.List;

public abstract class TreeNode<T> {
    private int depth;

    private List<T> childrens;

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public List<T> getChildrens() {
        return childrens;
    }

    public void setChildrens(List<T> childrens) {
        this.childrens = childrens;
    }
}
