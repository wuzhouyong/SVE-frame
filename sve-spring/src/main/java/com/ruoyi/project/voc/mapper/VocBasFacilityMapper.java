package com.ruoyi.project.voc.mapper;

import java.util.List;
import java.util.Map;

import com.ruoyi.project.voc.domain.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 【请填写功能名称】Mapper接口
 *
 * @author ruoyi
 * @date 2020-08-12
 */
@Mapper
public interface VocBasFacilityMapper {

    //新增关联排放口
    public int insertBasFacilityrelation(VocBasFacilityrelation vocRelation);

    //查询生产线管理
    public List<VocBasFacility> selectProductionList(VocBasFacility basFacility);

    //查询处理设施
    public List<VocBasFacility> selectHandleList(VocBasFacility basFacility);

    //删除处理设施
    public int deleteBasFacilityByIds(VocBasFacility basFacility);

    //新增工艺
    public int insertTechnology(VocBasTechnology technology);

    //获取工艺列表
    public List<VocBasTechnology> selectVocBasTechnologyById(Integer facilityId);

    // 修改处理工艺
    public int updateTechnology(VocBasTechnology techno);

    // 删除工艺
    public int deleteTechnologyByFacilityIds(@Param("facilityIds") Integer[] facilityIds, @Param("updatedBy") String updatedBy);

    // 删除工艺
    public int deleteTechnologyById(VocBasTechnology entity);

    //新增电表
    public int insertFacilityElecmeter(VocBasFacilityElecmeter elecm);

    //查询电表
    public List<VocBasFacilityElecmeter> selectFacilityElecmeterById(Integer facilityId);

    // 删除电表
    public int deleteFacilityElecmeterByIds(@Param("facilityIds") Integer[] facilityIds, @Param("updatedBy") String updatedBy);

    // 删除电表
    public int deleteFacilityElecmeterById(VocBasFacilityElecmeter entity);

    //编辑电表
    public int updateFacilityElecmeter(VocBasFacilityElecmeter elecm);

    //关联pid
    public Integer[] selectFacilityPidById(Integer facilityId);

    //关联pid对应的名字
    public String[] selectFacilityPidNameById(Integer[] facilitypid);

    //更新关联表
    public int updateBasFacilityRelation(VocBasFacilityrelation vocRelation);

    //删除关联关系
    public int deleteBasFacilityRelation(@Param("facilityIds") Integer[] facilityIds, @Param("updatedBy") String updatedBy);

    //查询被删除的关联关系
    public int selectBasFacilityRelation(Integer facilityId, Integer facilityPid);

    //查询收集风机管理
    public List<VocBasFacility> selectCollectList(VocBasFacility basFacility);

    //查询排放口管理
    public List<VocBasFacility> selectEmissionPointList(VocBasFacility basFacility);

    //查询排放风机管理
    public List<VocBasFacility> selectBlowerList(VocBasFacility basFacility);

    // 新增排放风机管理
    public int insertBasFacility(VocBasFacility basFacility);

    // 修改生产线管理
    public int updateBasFacility(VocBasFacility basFacility);

    // 查询治理设施工况详情列表
    public List<VocBasFacility> selectFacilityBySourceId(@Param("sourceId") String sourceId);

    // 查询治理设施工艺类型
    public List<Technology> selectBasTechnologyByFacilityId(@Param("facilityId") Integer facilityId);

    // 查询治理设施生产线
    public List<Technology> selectLineMeterByFacilityId(@Param("facilityId") Integer facilityId);

    // 查询治理设施收集风机
    public List<Technology> selectGatherFanByFacilityId(@Param("facilityId") Integer facilityId);

    // 查询治理设施排放口
    public List<Technology> selectDischargeByFacilityId(@Param("facilityId") Integer facilityId);

    // 查询治理设施排放风机
    public List<Technology> selectDischargeFanByFacilityId(@Param("facilityId") Integer facilityId);

    //生产线电表24小时均值
    public List<Map> selectLineMeterByCondition(@Param("sourceId") String sourceId, @Param("time") String time);

    //排放风机开关状态
    public List<Map> selectGatherFanByCondition(@Param("sourceId") String sourceId, @Param("time") String time);

    //排放口
    public List<Map> selectDischargeByCondition(@Param("sourceId") String sourceId, @Param("time") String time);

    //治理设施工况 UV光解+喷淋
    public List<Map> selectFacilityByCondition(@Param("sourceId") String sourceId, @Param("time") String time);

    //治理设施工况 活性炭+催化燃烧
    public List<Map> selectTechnoByCondition(@Param("sourceId") String sourceId, @Param("time") String time);


    //根据企业查询所有设施
    public List<VocBasFacility> selectAllFacilityBySourceId(@Param("sourceId") String sourceId);

    //根据设施编号查询所有车间
    public List<VocBasFacility> selectWorkshopByFacilityId(@Param("facilityId") Integer facilityId);

    //根据设施编号查找生产风机
    public List<Map> selectWorkFanByFacilityId(@Param("facilityId") Integer facilityId);

    //根据治理设施获取相关工艺
    public List<Map> selectTechnologyByFacilityId(@Param("facilityId") Integer facilityId);

    //根据治理设施查找排放口与排放风机
    public List<Map> selctOutAndFanByFacilityId(@Param("facilityId") Integer facilityId);

    //根据设施查找所有标签值
    public List<Map> selectVocElemntTagVal(@Param("facilityId") Integer facilityId);

    //获取所有车间收集风机状态
    public List<Map> getSFanRunStatus(@Param("facilityId") Integer facilityId);

    //获取排放口与排放风机状态
    public List<Map> getOutFanStatus(@Param("facilityId") Integer facilityId);
}

