package com.ruoyi.project.dgom.service;

import com.ruoyi.project.dgom.domain.DailyAvgStatistic;
import com.ruoyi.project.dgom.domain.DutyExport;
import com.ruoyi.project.dgom.domain.OutpaceTransStatistic;

import java.util.List;
import java.util.Map;

public interface DutyService {
    List<OutpaceTransStatistic> getSubStationWeeklyReport(Map<String, String> param);
    List<Map> getEnterpriseWeeklyReport(Map<String, String> param);
    List<DutyExport> getEnterpriseWeeklyReportExport(Map<String, String> param);
    Map<String, String> getDutyDiary(Map<String, String> param);
    List<Map> getAbnorProcessResult(Map<String, String> param);
    List<DutyExport> getAbnorProcessResultExport(Map<String, String> param);
    Map<String, List<Map>> getAbnorSummary(Map<String, String> param);
    List<Map> getAbnorEnterpriseSummary(Map<String, String> param);
    List<DutyExport> getAbnorEnterpriseSummaryExport(Map<String, String> param);
    List<Map> getTransRateLess90(Map<String, String> param);
    List<DutyExport> getTransRateLess90Export(Map<String, String> param);
    List<DailyAvgStatistic> getDailyAvgStatistic(Map<String, String> param);


}
