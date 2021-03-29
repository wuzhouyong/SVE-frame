package com.ruoyi.project.guocheng.service.impl;

import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.framework.aspectj.lang.annotation.DataSource;
import com.ruoyi.framework.aspectj.lang.enums.DataSourceType;
import com.ruoyi.project.guocheng.domain.CheckPointEntity;
import com.ruoyi.project.guocheng.domain.DeleteEntity;
import com.ruoyi.project.guocheng.domain.GCNameIdEntity;
import com.ruoyi.project.guocheng.domain.GCSourceParam;
import com.ruoyi.project.guocheng.mapper.CheckPointMapper;
import com.ruoyi.project.guocheng.service.CheckPointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@DataSource(value = DataSourceType.GUOCHENG)
@Service
public class CheckPointServiceImpl implements CheckPointService{

    @Autowired
    private CheckPointMapper checkPointMapper;

    @Override
    public List<GCNameIdEntity> selectSourceList(GCSourceParam entity) {
        return checkPointMapper.selectSourceList(entity);
    }

    @Override
    public List<GCNameIdEntity> selectCPList(String sourceId) {
        return checkPointMapper.selectCPList(sourceId);
    }

    @Override
    public int insertCheckPoint(CheckPointEntity entity) {
        return checkPointMapper.insertCheckPoint(entity);
    }

    @Override
    public int modifyPassWord(Map<String,String> param) {
        return checkPointMapper.modifyPassWord(param);
    }

    @Override
    public int updateCheckPoint(CheckPointEntity entity) {
        return checkPointMapper.updateCheckPoint(entity);
    }

    @Override
    public int deleteCheckPoint(String[] collectIds) {
        return checkPointMapper.deleteCheckPoint(new DeleteEntity(collectIds, SecurityUtils.getUsername()));
    }



    @Override
    public List<CheckPointEntity> selectCheckPoint(CheckPointEntity entity) {
        return checkPointMapper.selectCheckPoint(entity);
    }

    @Override
    public GCNameIdEntity selectBizSourceId(String sourceId) {
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
