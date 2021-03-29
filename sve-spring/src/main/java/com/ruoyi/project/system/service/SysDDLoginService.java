package com.ruoyi.project.system.service;

import com.ruoyi.project.system.domain.UserRelationEntity;

public interface SysDDLoginService {

    public UserRelationEntity selectUserId(String bizUserId, String tagHeadId);

    public int addUserRelation(UserRelationEntity userRelation);
}
