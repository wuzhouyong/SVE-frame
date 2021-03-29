package com.ruoyi.project.construction.mapper;

import com.ruoyi.project.construction.domain.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface ConstructionMapper {
    List<ConstructionResult> getConstructionList(ConstructionParam param);
    ConstructionEntity getConstructionModel(@Param("tagHeadId") String tagHeadId, @Param("sourceId") String sourceId);
    List<Map> getAttachCountList(@Param("sourceId") String sourceId, @Param("refType") String refType);
    List<Map> getAcceptReturnList(@Param("tagHeadId") String tagHeadId, @Param("sourceId") String sourceId);
    int mergeConstruction(ConstructionEntity entity);
    int completeConstruction(ConstructionEntity entity);
    int acceptanceConstruction(ConstructionEntity entity);
    int acceptConstruction(AcceptParam param);
    int addAcceptRecord(AcceptRecordEntity entity);
}
