package com.ruoyi.project.dgom.mapper;

import com.ruoyi.project.dgom.domain.DailyAvgStatistic;
import com.ruoyi.project.dgom.domain.OutpaceTransStatistic;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface DutyMapper {
    List<OutpaceTransStatistic> getSubStationWeeklyReport(Map<String, String> param);
    List<Map> getEnterpriseWeeklyReport(Map<String, String> param);
    List<Map> getSrcNumByType(Map<String, String> param);
    List<Map> getOverNumByType(Map<String, String> param);
    List<Map> getWaterHourDevNumByType(Map<String, String> param);
    Map getGasHourDevNumByType(Map<String, String> param);
    List<Map> getWaterDayDevNumByType(Map<String, String> param);
    Map getGasDayDevNumByType(Map<String, String> param);
    Integer getSrcNumByTransRate(Map<String, String> param);
    List<Map> getWasteWaterDevNumByTransRate(Map<String, String> param);
    Integer getWasteGasDevNumByTransRate(Map<String, String> param);
    Integer getZeroNumByAbnor(Map<String, String> param);
    List<Map> getZeroWasteWaterDev(Map<String, String> param);
    Integer getZeroWasteGasDev(Map<String, String> param);
    List<Map> getAbnorProcessResult(Map<String, String> param);
    List<Map> getAbnorSummaryBySubcategory(Map<String, String> param);
    Map getAbnorSummaryByOther(Map<String, String> param);
    Map getAbnorSummaryByZhongDianOther(Map<String, String> param);
    List<Map> getAbnorSummaryByTown(Map<String, String> param);
    Map getAbnorSummaryAll(Map<String, String> param);
    List<Map> getAbnorEnterpriseSummary(Map<String, String> param);
    List<Map> getTransRateLess90(Map<String, String> param);
    List<DailyAvgStatistic> getDailyAvgStatistic(Map<String, String> param);
}
