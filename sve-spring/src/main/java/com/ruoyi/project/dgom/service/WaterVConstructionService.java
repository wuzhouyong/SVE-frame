package com.ruoyi.project.dgom.service;

import com.ruoyi.project.dgom.domain.OMConstructionEntity;

import java.util.List;

public interface WaterVConstructionService {

    public int isExitDat(OMConstructionEntity entity);

    public int insertDatConstructionInfo(OMConstructionEntity entity);

    public int updateDatCon(OMConstructionEntity entity);

    public int updateConstructioninfo(OMConstructionEntity entity);

    public int updateComplete(OMConstructionEntity entity);

    public  int updateAcceptance(OMConstructionEntity entity);

    public List<OMConstructionEntity> getDatConstructionList(String sourceId);

    //查询建设状况
    public int getStatus(String sourceId);

    public int isAcc(String sourceId);
    public int isAttach(String sourceId);
}
