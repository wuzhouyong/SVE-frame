package com.ruoyi.project.youyan.service.impl;

import java.util.List;
import java.util.Map;

import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.framework.aspectj.lang.annotation.DataSource;
import com.ruoyi.framework.aspectj.lang.enums.DataSourceType;
import com.ruoyi.project.youyan.domain.YYCheckPointEntity;
import com.ruoyi.project.youyan.domain.YYDeleteEntity;
import com.ruoyi.project.youyan.domain.YYNameIdEntity;
import com.ruoyi.project.youyan.domain.YYSourceParam;
import com.ruoyi.project.youyan.mapper.YYCheckPointMapper;
import com.ruoyi.project.youyan.service.YYCheckPointService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@DataSource(value = DataSourceType.YOUYAN)
@Service
public class YYCheckPointServiceImpl implements YYCheckPointService {

    @Autowired
    private YYCheckPointMapper checkPointMapper;

    @Override
    public List<YYNameIdEntity> selectSourceList(YYSourceParam entity) {
        return checkPointMapper.selectSourceList(entity);
    }

    @Override
    public List<YYNameIdEntity> selectCPList(String sourceId) {
        return checkPointMapper.selectCPList(sourceId);
    }

    @Override
    public int insertCheckPoint(YYCheckPointEntity entity) {
        return checkPointMapper.insertCheckPoint(entity);
    }

    @Override
    public int modifyPassWord(Map<String,String> param) {
        return checkPointMapper.modifyPassWord(param);
    }

    @Override
    public int updateCheckPoint(YYCheckPointEntity entity) {
        return checkPointMapper.updateCheckPoint(entity);
    }

    @Override
    public int deleteCheckPoint(String[] collectIds) {
        return checkPointMapper.deleteCheckPoint(new YYDeleteEntity(collectIds, SecurityUtils.getUsername()));
    }

    @Override
    public List<YYCheckPointEntity> selectCheckPoint(YYCheckPointEntity entity) {
        return checkPointMapper.selectCheckPoint(entity);
    }

    @Override
    public YYNameIdEntity selectBizSourceId(String sourceId) {
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
