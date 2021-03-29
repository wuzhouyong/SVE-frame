package com.ruoyi.project.voc.service;

import com.ruoyi.project.common.domain.flowChart;
import com.ruoyi.project.voc.domain.VocBasFacility;
import com.ruoyi.project.voc.domain.VocBasTechnology;
import com.ruoyi.project.voc.domain.VocCPElementEntity;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * 【请填写功能名称】Service接口
 * 
 * @author ruoyi
 * @date 2020-08-12
 */
public interface VocBasFacilityService
{

    //查询生产线管理
    public List<VocBasFacility> selectProductionList(VocBasFacility basFacility);

    // 新增生产线管理
    public int insertProduction(VocBasFacility basFacility,List<VocCPElementEntity> vocCPElementEntity) throws SQLException;

    // 修改生产线管理
    public int updateProduction(VocBasFacility basFacility,List<VocCPElementEntity> vocCPElementEntity) throws SQLException;

    // 删除生产线管理
    public int deleteProduction(Integer[] facilityIds) throws SQLException;

    //查询处理设施
    public List<VocBasFacility> selectHandleList(VocBasFacility basFacility);

    // 新增处理设施
    public int insertHandleFacility(VocBasFacility basFacility,List<VocBasTechnology> techno) throws SQLException;

    // 修改治理设施管理
    public int updateHandle(VocBasFacility basFacility,List<VocBasTechnology> techno) throws SQLException;

    // 删除治理设施管理
    public int deleteHandle(Integer[] facilityIds) throws SQLException;

    //查询收集风机管理
    public List<VocBasFacility> selectCollectList(VocBasFacility basFacility);

    // 新增收集风机管理
    public int insertCollect(VocBasFacility basFacility,List<VocCPElementEntity> vocCPElementEntity) throws SQLException;

    // 删除收集风机管理
    public int deleteCollectBlower(Integer[] facilityIds) throws SQLException;

    // 修改收集风机管理
    public int updateCollectBlowerModify(VocBasFacility basFacility,List<VocCPElementEntity> vocCPElementEntity) throws SQLException;

    //查询排放口管理
    public List<VocBasFacility> selectEmissionPointList(VocBasFacility basFacility);

    // 新增排放口管理
    public int insertEmissionPoint(VocBasFacility basFacility,List<VocCPElementEntity> vocCPElementEntity) throws SQLException;

    // 修改排放口管理
    public int updateEmissionPointrModify(VocBasFacility basFacility,List<VocCPElementEntity> vocCPElementEntity) throws SQLException;

    // 删除排放口管理
    public int deleteEmissionPointr(Integer[] facilityIds) throws SQLException;

    //查询排放风机管理
    public List<VocBasFacility> selectBlowerList(VocBasFacility basFacility);

    // 新增排放风机管理
    public int insertBlower(VocBasFacility basFacility,List<VocCPElementEntity> vocCPElementEntity) throws SQLException;

    // 修改排放风机管理
    public int updateEmissionBlowerModify(VocBasFacility basFacility,List<VocCPElementEntity> vocCPElementEntity) throws SQLException;

    // 删除排放风机管理
    public int deleteEmissionBlower(Integer[] facilityIds) throws SQLException;

    // 查询治理设施工况详情列表
    public List<Map<String, Object>> workingConditionDetails(String sourceId);

     // 查询治理设施工况详情
    public Map<String,Object> workingConditionGetinfo(String sourceId, String time);

    /**
     * 获取voc企业相关设施结构图
     * @param sourceId
     * @return
     */
    public List<flowChart> getFlowFacilityBySourceId(String sourceId);

    /**
     * 设施获取所有因子与实时值
     * @param
     * @return
     */
    public List<Map> selectVocElemntTagVal(String facId);

    /**
     * 获取设施所甩设备状态
     * @param facId
     * @return
     */
    public List<Map> getVocFacilityRunStatus(String facId);
}
