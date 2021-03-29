package com.ruoyi.project.enterprise.service;

import com.ruoyi.project.enterprise.domain.dashboard.DashboardParam;
import com.ruoyi.project.enterprise.domain.dashboard.SourceSearchParam;
import com.ruoyi.project.enterprise.domain.dashboard.SourceSearchResult;

import java.util.List;
import java.util.Map;

public interface IDashboardService {
    List<SourceSearchResult> getSourceList(SourceSearchParam param);
    Map<String, Object> getSourceStatistics(DashboardParam param);
    Map<String, Object> getWarnStatistics(DashboardParam param);
}
