package com.ruoyi.project.lingxing.service.impl;

import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.framework.aspectj.lang.annotation.DataSource;
import com.ruoyi.framework.aspectj.lang.enums.DataSourceType;
import com.ruoyi.project.lingxing.domain.*;
import com.ruoyi.project.lingxing.mapper.LXCheckPointMapper;
import com.ruoyi.project.lingxing.service.LXCheckPointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@DataSource(value = DataSourceType.LINGXING)
@Service
public class LXCheckPointServiceImpl implements LXCheckPointService {

    @Autowired
    private LXCheckPointMapper checkPointMapper;

    @Override
    public List<LXNameIdEntity> selectSourceList(LXSourceParam entity) {
        return checkPointMapper.selectSourceList(entity);
    }

    @Override
    public List<LXTransbillinfo> selectTransbillinfoList(LXTransbillinfo info) {
        return checkPointMapper.selectTransbillinfoList(info);
    }

    @Override
    public List<LXNameIdEntity> selectCPList(String sourceId) {
        return checkPointMapper.selectCPList(sourceId);
    }

    @Override
    public int insertCheckPoint(LXCheckPointEntity entity) {
        return checkPointMapper.insertCheckPoint(entity);
    }

    @Override
    public int modifyPassWord(Map<String,String> param) {
        return checkPointMapper.modifyPassWord(param);
    }

    @Override
    public int updateCheckPoint(LXCheckPointEntity entity) {
        return checkPointMapper.updateCheckPoint(entity);
    }

    @Override
    public int deleteCheckPoint(String[] collectIds) {
        return checkPointMapper.deleteCheckPoint(new LXDeleteEntity(collectIds, SecurityUtils.getUsername()));
    }

    @Override
    public List<LXCheckPointEntity> selectCheckPoint(LXCheckPointEntity entity) {
        return checkPointMapper.selectCheckPoint(entity);
    }

    @Override
    public LXNameIdEntity selectBizSourceId(String sourceId) {
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
