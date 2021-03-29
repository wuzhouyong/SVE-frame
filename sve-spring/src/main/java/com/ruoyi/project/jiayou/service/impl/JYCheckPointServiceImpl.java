package com.ruoyi.project.jiayou.service.impl;

import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.framework.aspectj.lang.annotation.DataSource;
import com.ruoyi.framework.aspectj.lang.enums.DataSourceType;
import com.ruoyi.project.jiayou.domain.JYCheckPointEntity;
import com.ruoyi.project.jiayou.domain.JYDeleteEntity;
import com.ruoyi.project.jiayou.domain.JYNameIdEntity;
import com.ruoyi.project.jiayou.domain.JYSourceParam;
import com.ruoyi.project.jiayou.mapper.JYCheckPointMapper;
import com.ruoyi.project.jiayou.service.JYCheckPointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@DataSource(value = DataSourceType.JIAYOU)
@Service
public class JYCheckPointServiceImpl implements JYCheckPointService {

    @Autowired
    private JYCheckPointMapper checkPointMapper;

    @Override
    public List<JYNameIdEntity> selectSourceList(JYSourceParam entity) {
        return checkPointMapper.selectSourceList(entity);
    }

    @Override
    public List<JYNameIdEntity> selectCPList(String sourceId) {
        return checkPointMapper.selectCPList(sourceId);
    }

    @Override
    public int insertCheckPoint(JYCheckPointEntity entity) {
        return checkPointMapper.insertCheckPoint(entity);
    }

    @Override
    public int modifyPassWord(Map<String,String> param) {
        return checkPointMapper.modifyPassWord(param);
    }

    @Override
    public int updateCheckPoint(JYCheckPointEntity entity) {
        return checkPointMapper.updateCheckPoint(entity);
    }

    @Override
    public int deleteCheckPoint(String[] collectIds) {
        return checkPointMapper.deleteCheckPoint(new JYDeleteEntity(collectIds, SecurityUtils.getUsername()));
    }

    @Override
    public List<JYCheckPointEntity> selectCheckPoint(JYCheckPointEntity entity) {
        return checkPointMapper.selectCheckPoint(entity);
    }

    @Override
    public JYNameIdEntity selectBizSourceId(String sourceId) {
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
