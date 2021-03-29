package com.ruoyi.project.system.mapper;

import com.ruoyi.project.system.domain.UserRelationEntity;
import org.apache.ibatis.annotations.Param;

public interface SysDDLoginMapper {

    public UserRelationEntity selectUserId(@Param("bizUserId") String bizUserId, @Param("tagHeadId") String tagHeadId);

    public int addUserRelation(UserRelationEntity userRelation);
}
