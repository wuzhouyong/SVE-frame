package com.ruoyi.project.system.domain;

public class UserRelationEntity {
    private String tagHeadId;
    private String pfUserId;
    private String bizUserId;
    private Integer userType;

    public String getTagHeadId() {
        return tagHeadId;
    }

    public void setTagHeadId(String tagHeadId) {
        this.tagHeadId = tagHeadId;
    }

    public String getPfUserId() {
        return pfUserId;
    }

    public void setPfUserId(String pfUserId) {
        this.pfUserId = pfUserId;
    }

    public String getBizUserId() {
        return bizUserId;
    }

    public void setBizUserId(String bizUserId) {
        this.bizUserId = bizUserId;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }
}
