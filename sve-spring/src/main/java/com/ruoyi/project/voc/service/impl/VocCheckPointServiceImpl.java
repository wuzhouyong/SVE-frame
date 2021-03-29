package com.ruoyi.project.voc.service.impl;

import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.framework.aspectj.lang.annotation.DataSource;
import com.ruoyi.framework.aspectj.lang.enums.DataSourceType;
import com.ruoyi.project.voc.domain.VocCheckPointEntity;
import com.ruoyi.project.voc.domain.VocDeleteEntity;
import com.ruoyi.project.voc.domain.VocNameIdEntity;
import com.ruoyi.project.voc.domain.VocSourceParam;
import com.ruoyi.project.voc.mapper.VocCheckPointMapper;
import com.ruoyi.project.voc.service.VocCheckPointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@DataSource(value = DataSourceType.VOC)
@Service
public class VocCheckPointServiceImpl implements VocCheckPointService {

    @Autowired
    private VocCheckPointMapper checkPointMapper;

    @Override
    public List<VocNameIdEntity> selectSourceList(VocSourceParam entity) {
        return checkPointMapper.selectSourceList(entity);
    }

    @Override
    public List<VocNameIdEntity> selectCPList(String sourceId) {
        return checkPointMapper.selectCPList(sourceId);
    }

    @Override
    public int insertCheckPoint(VocCheckPointEntity entity) {
        return checkPointMapper.insertCheckPoint(entity);
    }

    @Override
    public int modifyPassWord(Map<String,String> param) {
        return checkPointMapper.modifyPassWord(param);
    }

    @Override
    public int updateCheckPoint(VocCheckPointEntity entity) {
        return checkPointMapper.updateCheckPoint(entity);
    }

    @Override
    public int deleteCheckPoint(String[] collectIds) {
        return checkPointMapper.deleteCheckPoint(new VocDeleteEntity(collectIds, SecurityUtils.getUsername()));
    }

    @Override
    public List<VocCheckPointEntity> selectCheckPoint(VocCheckPointEntity entity) {
        return checkPointMapper.selectCheckPoint(entity);
    }

    @Override
    public VocNameIdEntity selectBizSourceId(String sourceId) {
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
