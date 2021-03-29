package com.ruoyi.project.voc.mapper;

import com.ruoyi.project.voc.domain.VocCPElementEntity;
import com.ruoyi.project.voc.domain.VocDeleteEntity;
import com.ruoyi.project.voc.domain.VocNameIdEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface VocCheckPointElementMapper {

    public List<VocCPElementEntity> selectCPElement(VocCPElementEntity entity);

    public List<VocCPElementEntity> selectCPElementList(@Param("facilityId") Integer facilityId);

    public List<VocCPElementEntity> selectCPElementListByTechId(@Param("facilityId") Integer facilityId, @Param("techId") String techId);

    public List<VocNameIdEntity> selectElement(VocNameIdEntity entity);

    public int insertCPElement(VocCPElementEntity entity);

    public int updateCPElement(VocCPElementEntity entity);

    public int deleteCPElement(VocDeleteEntity entity);

    //查询旧因子
    public List<VocCPElementEntity> selectCPElemIdByIdfacilityId(Integer facilityId);

    //查询旧因子
    public VocCPElementEntity selectCPElementByElemId(String cpElemId);

    //删除因子
    public int deleteCPElementByFacilityIds(@Param("facilityIds") Integer[] facilityIds, @Param("updatedBy") String updatedBy);

    //删除工艺下的因子
    public int deleteCPElementByTechIds(@Param("facilityIds") Integer[] facilityIds, @Param("updatedBy") String updatedBy);

    //删除电表下的因子
    public int deleteCPElementByElecmeterIds(@Param("facilityIds") Integer[] facilityIds, @Param("updatedBy") String updatedBy);

    //删除因子
    public int deleteCPElementById(VocCPElementEntity entity);

    //查询被当前企业绑定的因子
    public List<VocCPElementEntity> selectCPElementBySourceId(@Param("sourceId") String sourceId);

    //把无关联且已经重复的因子关联到生产线上
    public int updateCPElementByFacilityId(VocCPElementEntity entity);

    //查询未被当前企业绑定的因子
    public List<VocCPElementEntity> selectCPElementByCondition(@Param("sourceId") String sourceId);

    public int repeatCheck(VocCPElementEntity entity);


}
