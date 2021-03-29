package com.ruoyi.project.construction.mapper;

import com.ruoyi.framework.aspectj.lang.annotation.DataSource;
import com.ruoyi.framework.aspectj.lang.enums.DataSourceType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

@Mapper
public interface ConstructionElementMapper {
    Map getNoFacilityElement(@Param("tagHeadId") String tagHeadId, @Param("sourceId") String sourceId);
    Map getHasDataElement(@Param("tagHeadId") String tagHeadId, @Param("sourceId") String sourceId);
    Map getNoDataElement(@Param("tagHeadId") String tagHeadId, @Param("sourceId") String sourceId);
    @DataSource(value = DataSourceType.VOC)
    Map getNoFacilityElementForVOC(@Param("tagHeadId") String tagHeadId, @Param("sourceId") String sourceId);
    @DataSource(value = DataSourceType.QIXIU)
    Map getNoFacilityElementForQiXiu(@Param("tagHeadId") String tagHeadId, @Param("sourceId") String sourceId);
}
