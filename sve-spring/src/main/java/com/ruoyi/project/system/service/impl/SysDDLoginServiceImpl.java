package com.ruoyi.project.system.service.impl;

import com.ruoyi.framework.aspectj.lang.annotation.DataSource;
import com.ruoyi.framework.aspectj.lang.enums.DataSourceType;
import com.ruoyi.project.system.domain.UserRelationEntity;
import com.ruoyi.project.system.mapper.SysDDLoginMapper;
import com.ruoyi.project.system.service.SysDDLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@DataSource(value = DataSourceType.MASTER)
public class SysDDLoginServiceImpl implements SysDDLoginService{

    @Autowired
    private SysDDLoginMapper sysDDLoginMapper;

    @Override
    public UserRelationEntity selectUserId(String bizUserId, String tagHeadId) {
        return sysDDLoginMapper.selectUserId(bizUserId, tagHeadId);
    }

    @Override
    public int addUserRelation(UserRelationEntity userRelation) {
        return sysDDLoginMapper.addUserRelation(userRelation);
    }
}
