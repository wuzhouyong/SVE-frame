package com.ruoyi.project.jinshu.service.impl;

import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.framework.aspectj.lang.annotation.DataSource;
import com.ruoyi.framework.aspectj.lang.enums.DataSourceType;
import com.ruoyi.project.jinshu.domain.JSCheckPointEntity;
import com.ruoyi.project.jinshu.domain.JSDeleteEntity;
import com.ruoyi.project.jinshu.domain.JSNameIdEntity;
import com.ruoyi.project.jinshu.domain.JSSourceParam;
import com.ruoyi.project.jinshu.mapper.JSCheckPointMapper;
import com.ruoyi.project.jinshu.service.JSCheckPointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@DataSource(value = DataSourceType.JINSHU)
@Service
public class JSCheckPointServiceImpl implements JSCheckPointService {

    @Autowired
    private JSCheckPointMapper checkPointMapper;

    @Override
    public List<JSNameIdEntity> selectSourceList(JSSourceParam entity) {
        return checkPointMapper.selectSourceList(entity);
    }

    @Override
    public List<JSNameIdEntity> selectCPList(String sourceId) {
        return checkPointMapper.selectCPList(sourceId);
    }

    @Override
    public int insertCheckPoint(JSCheckPointEntity entity) {
        return checkPointMapper.insertCheckPoint(entity);
    }

    @Override
    public int modifyPassWord(Map<String,String> param) {
        return checkPointMapper.modifyPassWord(param);
    }

    @Override
    public int updateCheckPoint(JSCheckPointEntity entity) {
        return checkPointMapper.updateCheckPoint(entity);
    }

    @Override
    public int deleteCheckPoint(String[] collectIds) {
        return checkPointMapper.deleteCheckPoint(new JSDeleteEntity(collectIds, SecurityUtils.getUsername()));
    }

    @Override
    public List<JSCheckPointEntity> selectCheckPoint(JSCheckPointEntity entity) {
        return checkPointMapper.selectCheckPoint(entity);
    }

    @Override
    public JSNameIdEntity selectBizSourceId(String sourceId) {
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
