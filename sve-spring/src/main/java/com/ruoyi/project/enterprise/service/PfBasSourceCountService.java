package com.ruoyi.project.enterprise.service;

import com.ruoyi.project.enterprise.domain.*;
import com.ruoyi.project.outwater.domain.entity.CollectCheckPointEntity;
import com.ruoyi.project.outwater.domain.entity.OutwaterCheckPointEntity;

import java.util.List;

public interface PfBasSourceCountService {
    public List<PfBasSourceCount> getCountList(MonitorTaskParam param);

    public List<PfBasSourceList> getSourceList(MonitorTaskParam param);

    //查找镇街企业是否被监控
    public List<ExportCountEntity> getExportCountData(MonitorTaskParam param) throws Exception;

    //查询排水户信息
    public List<OutwaterCheckPointEntity> getExportOutwaterData(CollectCheckPointEntity entity);

    //查询企业信息
    public List<TaskSourceEntity> selectEnterpriseInformation(EnterpriseEntity entity);

    public List<TaskSourceEntity> constructionAccepts(EnterpriseEntity entity);

    public List<TaskSourceEntity> taskChanges(EnterpriseEntity entity);

    //更新合同审核
    public int editConstructioninfo(String tagHeadId, String pfSourceId) throws Exception;

    //更新验收审核
    public int modifyConstructioninfo(String tagHeadId, String pfSourceId) throws Exception;

    public int insertDisallowance(DisallowanceEntity entity) throws Exception;

    public List<DisallowanceEntity> getDisallowanceList(DisallowanceEntity entity);

}
