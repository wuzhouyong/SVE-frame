package com.ruoyi.project.youyan.mapper;


import com.ruoyi.project.youyan.domain.YYFacility;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 油烟治理设施信息Mapper接口
 *
 * @author ruoyi
 * @date 2020-09-22
 */
public interface YYFacilityMapper {

    //查询治理设施
    public List<YYFacility> selectBasFacilityPointList(YYFacility facility);

    //新增治理设施
    public int insertBasFacility(YYFacility facility);

    //获取排放口id
    public String selectFacilityId(@Param("facilityId") String facilityId);

    //修改治理设施
    public int updateBasFacility(YYFacility facility);

    //给数据库表中因子id赋值
    public int updateBasFacilityElemId(YYFacility facility);

    //删除治理设施
    public int deleteBasFacilityById(YYFacility facility);

    //删除关联关系
    public int deleteBasFacilityRelation(@Param("facilityId") Integer facilityId, @Param("updatedBy") String updatedBy);

    //查找父id
    public Integer selectFacilityPid(@Param("facilityId") Integer facilityId);

    //根据父id和等级寻找它的下一级
    public List<YYFacility> selectEmissionByfacilityId(Integer facilityId, Integer depthLevel);

    //根据id查找父级对象
    public List<YYFacility> selectBasFacilityById(@Param("facilityId") Integer facilityId, @Param("sourceId") String sourceId);

    //查询关联是否存在
    public int selectBasFacilityRelation(@Param("facilityPid") Integer facilityPid, @Param("facilityId") Integer facilityId);

    //查询旧关联Pids
    public Integer[] selectFacilityPidById(@Param("facilityId") Integer facilityId);

    //新增关联表
    public int insertBasFacilityRelation(YYFacility relation);

    //更新关联表
    public int updateBasFacilityRelation(YYFacility relation);







    /**
     * 根据企业ID查询企业相关设施
     *
     * @param sourceID
     * @return
     */
    public List<Map> selectBasFacilityBySourceId(String sourceID);

    /**
     * 根据设施查询子设施
     *
     * @param facID
     * @return
     */
    public List<Map> selectBasFacilityByFacilityId(int facID);

    /**
     * 根据设施获取设施所有因子实时数据
     *
     * @param facID
     * @return
     */
    public List<Map> selectFacilityRealElementData(int facID);

    /**
     * 根据设施获取所有设备运行情况
     *
     * @param facID
     * @return
     */
    public List<Map> selectFacilityRunStatus(int facID);
}
