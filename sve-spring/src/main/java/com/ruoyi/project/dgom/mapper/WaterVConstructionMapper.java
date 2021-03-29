package com.ruoyi.project.dgom.mapper;

import com.ruoyi.project.dgom.domain.OMConstructionEntity;
import com.ruoyi.project.enterprise.domain.DisallowanceEntity;

import java.util.List;

public interface WaterVConstructionMapper {

    public int isExitDat(OMConstructionEntity entity);

    public int insertDatConstructionInfo(OMConstructionEntity entity);

    public int updateDatCon(OMConstructionEntity entity);

    public int updateConstructioninfo(OMConstructionEntity entity);

    public int updateComplete(OMConstructionEntity entity);

    public  int updateAcceptance(OMConstructionEntity entity);

    public List<OMConstructionEntity> getDatConstructionList(String sourceId);

    List<DisallowanceEntity> getDisallowanceList(String sourceId);

    public  int isAcc(String sourceId);

    public int getStatus(String sourceId);

    public int isAttach(String sourceId);
}
