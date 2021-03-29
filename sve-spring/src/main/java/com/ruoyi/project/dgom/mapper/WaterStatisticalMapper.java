package com.ruoyi.project.dgom.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


public interface WaterStatisticalMapper {

    List<Map> getTownWastewater(@Param("startDate") String startDate, @Param("endDate") String endDate);
    List<Map> getMonthWastewater(@Param("startDate") String startDate, @Param("endDate") String endDate);
    List<Map> getTownMonthWastewater(Map<String, String> param);

}
