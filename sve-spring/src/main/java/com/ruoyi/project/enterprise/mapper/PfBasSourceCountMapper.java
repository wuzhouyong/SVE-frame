package com.ruoyi.project.enterprise.mapper;

import com.ruoyi.project.enterprise.domain.*;
import com.ruoyi.project.outwater.domain.entity.CollectCheckPointEntity;
import com.ruoyi.project.outwater.domain.entity.OutwaterCheckPointEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("pfBasSourceCountMapper")
public interface PfBasSourceCountMapper {

    public List<PfBasSourceCount> getCountList(MonitorTaskParam param);

    public List<PfBasSourceList> getSourceList(MonitorTaskParam param);

    public int getTotal(String fullName, String townCode);

    public List<ExportCountEntity> getExportCountData(MonitorTaskParam param);

    public List<ExportCountEntity> getExportCountData1(MonitorTaskParam param);

    // 查询排水户信息
    public List<OutwaterCheckPointEntity> getExportOutwaterData(CollectCheckPointEntity entity);

    //查询企业信息
    public List<TaskSourceEntity> selectEnterpriseInformation(EnterpriseEntity entity);

    public List<TaskSourceEntity> constructionAccepts(EnterpriseEntity entity);

    public List<TaskSourceEntity> taskChanges(EnterpriseEntity entity);

    //更新合同审核
    public int editConstructioninfo(@Param("tagHeadId") String tagHeadId, @Param("pfSourceId") String pfSourceId, @Param("username") String username);

    //更新验收审核
    public int modifyConstructioninfo(@Param("tagHeadId") String tagHeadId, @Param("pfSourceId") String pfSourceId, @Param("acceptResult") String acceptResult, @Param("username") String username);

    public int insertDisallowance(DisallowanceEntity entity);

    public int updateConstructioninfo(DisallowanceEntity entity);

    public List<DisallowanceEntity> getDisallowanceList(DisallowanceEntity entity);

}
