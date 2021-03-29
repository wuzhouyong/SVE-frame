package com.ruoyi.project.dgom.service;

import com.ruoyi.project.dgom.domain.AnnexEntity;
import com.ruoyi.project.dgom.domain.LabelEntity;

import java.util.List;
import java.util.Map;

public interface OMUtilService {


    List<LabelEntity> getLabelsByParentId(Integer parentId);

    List<Map> datadevList(String checkpointid);

    List<Map> checkpointidList(String bizSourceId);

    List<AnnexEntity> selectAnnexList(AnnexEntity entity);

    Integer deleteAnnex(String[] attachfileids, String updatedby);

    AnnexEntity selectAnnexFileById(String attachfileid);

}
