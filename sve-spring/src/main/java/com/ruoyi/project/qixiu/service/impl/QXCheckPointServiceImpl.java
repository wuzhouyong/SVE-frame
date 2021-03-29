package com.ruoyi.project.qixiu.service.impl;

import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.framework.aspectj.lang.annotation.DataSource;
import com.ruoyi.framework.aspectj.lang.enums.DataSourceType;
import com.ruoyi.project.qixiu.domain.QXCheckPointEntity;
import com.ruoyi.project.qixiu.domain.QXDeleteEntity;
import com.ruoyi.project.qixiu.domain.QXNameIdEntity;
import com.ruoyi.project.qixiu.domain.QXSourceParam;
import com.ruoyi.project.qixiu.mapper.QXCheckPointMapper;
import com.ruoyi.project.qixiu.service.QXCheckPointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@DataSource(value = DataSourceType.QIXIU)
@Service
public class QXCheckPointServiceImpl implements QXCheckPointService {

    @Autowired
    private QXCheckPointMapper checkPointMapper;

    @Override
    public List<QXNameIdEntity> selectSourceList(QXSourceParam entity) {
        return checkPointMapper.selectSourceList(entity);
    }

    @Override
    public List<QXNameIdEntity> selectCPList(String sourceId) {
        return checkPointMapper.selectCPList(sourceId);
    }

    @Override
    public int insertCheckPoint(QXCheckPointEntity entity) {
        return checkPointMapper.insertCheckPoint(entity);
    }

    @Override
    public int modifyPassWord(Map<String,String> param) {
        return checkPointMapper.modifyPassWord(param);
    }

    @Override
    public int updateCheckPoint(QXCheckPointEntity entity) {
        return checkPointMapper.updateCheckPoint(entity);
    }

    @Override
    public int deleteCheckPoint(String[] collectIds) {
        return checkPointMapper.deleteCheckPoint(new QXDeleteEntity(collectIds, SecurityUtils.getUsername()));
    }

    @Override
    public List<QXCheckPointEntity> selectCheckPoint(QXCheckPointEntity entity) {
        return checkPointMapper.selectCheckPoint(entity);
    }

    @Override
    public QXNameIdEntity selectBizSourceId(String sourceId) {
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
