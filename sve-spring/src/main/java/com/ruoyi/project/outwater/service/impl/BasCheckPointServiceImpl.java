package com.ruoyi.project.outwater.service.impl;

import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.framework.aspectj.lang.annotation.DataSource;
import com.ruoyi.framework.aspectj.lang.enums.DataSourceType;
import com.ruoyi.project.outwater.domain.BasCheckPoint;
import com.ruoyi.project.outwater.domain.entity.CheckPointElementEntity;
import com.ruoyi.project.outwater.domain.entity.CollectCheckPointEntity;
import com.ruoyi.project.outwater.domain.entity.NameAndIdEntity;
import com.ruoyi.project.outwater.domain.entity.SqlInEntity;
import com.ruoyi.project.outwater.mapper.BasCheckPointElementMapper;
import com.ruoyi.project.outwater.mapper.BasCheckPointMapper;
import com.ruoyi.project.outwater.mapper.BasCollectMapper;
import com.ruoyi.project.outwater.service.BasCheckPointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@DataSource(value = DataSourceType.OUTWATER)
@Service
public class BasCheckPointServiceImpl implements BasCheckPointService {

    @Autowired
    private BasCheckPointMapper checkPointMapper;

    @Autowired
    private BasCollectMapper collectMapper;

    @Autowired
    private BasCheckPointElementMapper checkPointElementMapper;

    @Override
    public int insertCheckPoint(BasCheckPoint entity) {
        return checkPointMapper.insertCheckPoint(entity);
    }

    @Override
    public int deleteCheckPointById(String[] checkPointIds) {
        int res = 0;
        String userName = SecurityUtils.getUsername();
        SqlInEntity entity = new SqlInEntity(checkPointIds, userName);
        res += checkPointMapper.deleteCheckPointById(entity);
        List<BasCheckPoint> pointList = checkPointMapper.getCollectIdList(entity);
        List<CheckPointElementEntity> elementIdList = collectMapper.selectCpElementId(entity);
        for (int i = 0; i < pointList.size(); i++) {
            res += collectMapper.deleteBasCollectById(pointList.get(i).getCollectId(), userName);
        }
        for (int i = 0; i < elementIdList.size(); i++) {
            res += checkPointElementMapper.deleteCheckPointElementById(elementIdList.get(i).getCpElementId(), userName);
        }
        return res;
    }

    @Override
    public int updateCheckPoint(CollectCheckPointEntity entity) {
        return checkPointMapper.updateCheckPoint(entity);
    }

    @Override
    public List<NameAndIdEntity> selectCheckPointIdNameList(String checkPointName, String checkPointId, String sourceId) {
        return checkPointMapper.selectCheckPointIdNameList(checkPointName, checkPointId, sourceId);
    }

    @Override
    public int getTotal(CollectCheckPointEntity entity) {
        return checkPointMapper.getTotal(entity);
    }

    @Override
    public List<CollectCheckPointEntity> selectCheckPointList(CollectCheckPointEntity entity) {
        return checkPointMapper.selectCheckPointList(entity);
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
