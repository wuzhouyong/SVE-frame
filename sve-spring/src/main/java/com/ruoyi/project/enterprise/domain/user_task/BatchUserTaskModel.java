package com.ruoyi.project.enterprise.domain.user_task;

import com.ruoyi.common.utils.SecurityUtils;

public class BatchUserTaskModel {
    private Integer type;
    private Integer tagHeadId;
    private Integer userId;
    private final String createdBy = SecurityUtils.getUsername();

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getTagHeadId() {
        return tagHeadId;
    }

    public void setTagHeadId(Integer tagHeadId) {
        this.tagHeadId = tagHeadId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
