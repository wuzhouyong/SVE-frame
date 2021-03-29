package com.ruoyi.project.construction.service;

import com.ruoyi.common.domain.ResultEntity;
import com.ruoyi.project.construction.domain.*;

import java.util.List;
import java.util.Map;

public interface IConstructionService {
    List<ConstructionResult> getConstructionList(ConstructionParam param);
    ResultEntity getConstructionModel(int tagHeadId, String sourceId);
    List<Map> getAcceptReturnList(String tagHeadId, String sourceId);
    List<Map> getAttachCountList(String sourceId, String refType);
    ResultEntity mergeConstruction(ConstructionEntity entity);
    ResultEntity completeConstruction(ConstructionEntity entity);
    ResultEntity acceptanceConstruction(ConstructionEntity entity);
    ResultEntity confirmConstruction(AcceptParam param, AcceptRecordEntity entity);
    ResultEntity retreatConstruction(AcceptParam param, AcceptRecordEntity entity);
    boolean hasContract(int tagHeadId);

}
