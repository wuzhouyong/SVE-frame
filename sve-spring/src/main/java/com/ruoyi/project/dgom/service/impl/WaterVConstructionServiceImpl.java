package com.ruoyi.project.dgom.service.impl;

import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.project.dgom.domain.OMConstructionEntity;
import com.ruoyi.project.dgom.mapper.WaterVConstructionMapper;
import com.ruoyi.project.dgom.service.WaterVConstructionService;
import com.ruoyi.project.enterprise.domain.DisallowanceEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("WaterV_ConstructionService")
public class WaterVConstructionServiceImpl implements WaterVConstructionService {
    @Autowired
    private WaterVConstructionMapper constructionMapper;

    @Override
    public int isExitDat(OMConstructionEntity entity) {
        return constructionMapper.isExitDat(entity);
    }

    @Override
    public int insertDatConstructionInfo(OMConstructionEntity entity) {
        return constructionMapper.insertDatConstructionInfo(entity);
    }

    @Override
    public int updateDatCon(OMConstructionEntity entity) {
        return constructionMapper.updateDatCon(entity);
    }

    @Override
    public int updateConstructioninfo(OMConstructionEntity entity) {
        return constructionMapper.updateConstructioninfo(entity);
    }

    @Override
    public int updateComplete(OMConstructionEntity entity) {
        return constructionMapper.updateComplete(entity);
    }

    @Override
    public int updateAcceptance(OMConstructionEntity entity) {
        return constructionMapper.updateAcceptance(entity);
    }

    @Override
    public List<OMConstructionEntity> getDatConstructionList(String sourceId) {
        List<OMConstructionEntity> list = constructionMapper.getDatConstructionList(sourceId);
        List<DisallowanceEntity> disaList = constructionMapper.getDisallowanceList(sourceId);
        for (OMConstructionEntity entity : list) {
            if (StringUtils.isEmpty(entity.getAcceptDate()) && StringUtils.isNotEmpty(disaList)) {
                entity.setFlag(true);
            } else entity.setFlag(false);
        }
        return list;
    }

    @Override
    public int getStatus(String sourceId) {
        return constructionMapper.getStatus(sourceId);
    }

    @Override
    public int isAcc(String sourceId) {
        return constructionMapper.isAcc(sourceId);
    }

    @Override
    public int isAttach(String sourceId) {
        return constructionMapper.isAttach(sourceId);
    }
}
