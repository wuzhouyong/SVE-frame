package com.ruoyi.project.lwjh.domain;

import com.ruoyi.project.common.poi.TreeNode;

public class ElementData extends TreeNode<ElementData> {

    private String elementName;

    private String lawStandard;

    private String memo = "";

    public String getElementName() {
        return elementName;
    }

    public void setElementName(String elementName) {
        this.elementName = elementName;
    }

    public String getLawStandard() {
        return lawStandard;
    }

    public void setLawStandard(String lawStandard) {
        this.lawStandard = lawStandard;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

}
