package com.ruoyi.project.dgom.service;

import com.ruoyi.project.dgom.domain.DataReturnEntity;
import com.ruoyi.project.dgom.domain.DataSearchPar;
import com.ruoyi.project.dgom.domain.DataTableEntity;

import java.util.List;
import java.util.Map;

/**
 * 【请填写功能名称】Service接口
 *
 * @author ruoyi
 * @date 2020-08-25
 */
public interface DataSearchService {
    //查找企业列表
    public List<Map> selectEnterpriseList(String sourceId, String fullName);

    //查找监控端排放口
    public List<Map> selectEmissionList(String sourceId);

    //查找因子
    public List<Map> selectElementList();
    public List<Map> getElementListByCheckpointId(String checkpointId);

    //查找街镇
    public List<Map> selectTownNameList();

    //查找任务列表
    public List<DataSearchPar> selectTasklistByCondition(DataSearchPar entity);

    //查找任务详情
    public Map selectAbnortaskheadInfo(String taskheadid);

    //表头实时数据
    public List<DataTableEntity> getHeaderReal(DataSearchPar entity);

    //表头分钟数据
    public List<DataTableEntity> getHeaderMinute(DataSearchPar entity);

    // 表头小时数据
    public List<DataTableEntity> getHeaderHour(DataSearchPar entity);

    // 表头日数据
    public List<DataTableEntity> getHeaderDay(DataSearchPar entity);

    // 实时数据
    public List<Map<String, Object>> getReal(DataSearchPar entity);

    // 分钟数据
    public List<Map<String, Object>> getMinute(DataSearchPar entity);

    // 小时数据
    public List<Map<String, Object>> getHour(DataSearchPar entity);

    // 日数据
    public List<Map<String, Object>> getDay(DataSearchPar entity);

    // 缺失数据(小时和日)
    public List<DataReturnEntity> getMissingHour(DataSearchPar entity);

    // 零值数据和超标数据(小时和日)
    public List<DataReturnEntity> getZerovalueHour(DataSearchPar entity);


}
