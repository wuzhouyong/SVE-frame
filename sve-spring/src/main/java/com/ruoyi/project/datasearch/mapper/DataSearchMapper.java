package com.ruoyi.project.datasearch.mapper;

import com.ruoyi.project.datasearch.domain.DataElementEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface DataSearchMapper {
    List<DataElementEntity> getElementList(@Param("sourceId") String sourceId);
    List<Map> getRealList();
    List<Map> getMinuteList();
    List<Map> getHourList();
    List<Map> getDayList();
}
