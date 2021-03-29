package com.ruoyi.project.lingxing.domain;


public class LXCPElementModel extends LXCPElementEntity {
    private boolean isOther;
    private int srcTagHeadId;
    private String srcCollectId;
    private String srcElemId;

    public boolean getIsOther() {
        return isOther;
    }

    public void setIsOther(boolean isOther) {
        this.isOther = isOther;
    }

    public int getSrcTagHeadId() {
        return srcTagHeadId;
    }

    public void setSrcTagHeadId(int srcTagHeadId) {
        this.srcTagHeadId = srcTagHeadId;
    }

    public String getSrcCollectId() {
        return srcCollectId;
    }

    public void setSrcCollectId(String srcCollectId) {
        this.srcCollectId = srcCollectId;
    }

    public String getSrcElemId() {
        return srcElemId;
    }

    public void setSrcElemId(String srcElemId) {
        this.srcElemId = srcElemId;
    }
}
