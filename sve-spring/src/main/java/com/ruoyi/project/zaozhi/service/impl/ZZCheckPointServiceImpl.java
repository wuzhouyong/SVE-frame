package com.ruoyi.project.zaozhi.service.impl;

import java.util.List;
import java.util.Map;

import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.framework.aspectj.lang.annotation.DataSource;
import com.ruoyi.framework.aspectj.lang.enums.DataSourceType;
import com.ruoyi.project.zaozhi.domain.ZZCheckPointEntity;
import com.ruoyi.project.zaozhi.domain.ZZDeleteEntity;
import com.ruoyi.project.zaozhi.domain.ZZNameIdEntity;
import com.ruoyi.project.zaozhi.domain.ZZSourceParam;
import com.ruoyi.project.zaozhi.mapper.ZZCheckPointMapper;
import com.ruoyi.project.zaozhi.service.ZZCheckPointService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@DataSource(value = DataSourceType.ZAOZHI)
@Service
public class ZZCheckPointServiceImpl implements ZZCheckPointService {
    @Autowired
    private ZZCheckPointMapper checkPointMapper;

    @Override
    public List<ZZNameIdEntity> selectSourceList(ZZSourceParam entity) {
        return checkPointMapper.selectSourceList(entity);
    }

    @Override
    public List<ZZNameIdEntity> selectCPList(String sourceId) {
        return checkPointMapper.selectCPList(sourceId);
    }

    @Override
    public int insertCheckPoint(ZZCheckPointEntity entity) {
        return checkPointMapper.insertCheckPoint(entity);
    }

    @Override
    public int modifyPassWord(Map<String,String> param) {
        return checkPointMapper.modifyPassWord(param);
    }

    @Override
    public int updateCheckPoint(ZZCheckPointEntity entity) {
        return checkPointMapper.updateCheckPoint(entity);
    }

    @Override
    public int deleteCheckPoint(String[] collectIds) {
        return checkPointMapper.deleteCheckPoint(new ZZDeleteEntity(collectIds, SecurityUtils.getUsername()));
    }

    @Override
    public List<ZZCheckPointEntity> selectCheckPoint(ZZCheckPointEntity entity) {
        return checkPointMapper.selectCheckPoint(entity);
    }

    @Override
    public ZZNameIdEntity selectBizSourceId(String sourceId) {
        return checkPointMapper.selectBizSourceId(sourceId);
    }

    @Override
    public int isExitCon(String sourceId){
        return checkPointMapper.isExitCon(sourceId);
    }

    @Override
    public int checkConstruction(String sourceId) {
        return checkPointMapper.checkConstruction(sourceId);
    }
}
