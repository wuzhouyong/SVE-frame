package com.ruoyi.project.inspect.mapper;

import com.ruoyi.project.inspect.domain.DatInspectStat;
import com.ruoyi.project.inspect.domain.DatInspectParm;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface DatInspectStatMapper {

    Map selectTownIdByTownName(@Param("townName") String townName);
    String selectTownNameByTownCode(@Param("townCode") String townCode);
    int insertInspectStatTask(DatInspectStat entity);
    List<DatInspectStat> selectInspectStatTaskList(DatInspectParm entity);
    int editInspectStatRecordTask(DatInspectStat entity);
    int editInspectStatTask(DatInspectStat entity);
    int addInspectStatRecord(DatInspectStat entity);
    List<DatInspectStat> selectInspectStatTaskById(@Param("statId") Integer statId);
    int deleteInspectStatById(@Param("statId") Integer statId,@Param("createdBy") String createdBy);
    List<Map> selectStatrRecordTaskListById(@Param("statId") Integer statId);


}
