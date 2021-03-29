package com.ruoyi.project.enterprise.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author solang
 * @date 2020-12-03 18:07
 */
public interface ComplexCountMapper {

    List<Map> getTownComplexCount(@Param("tagHeadId") String tagHeadId);



}
