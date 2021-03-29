package com.ruoyi.project.enterprise.mapper;

import com.ruoyi.framework.aspectj.lang.annotation.DataSource;
import com.ruoyi.framework.aspectj.lang.enums.DataSourceType;
import com.ruoyi.project.enterprise.domain.dashboard.DashboardParam;
import com.ruoyi.project.enterprise.domain.dashboard.SourceSearchParam;
import com.ruoyi.project.enterprise.domain.dashboard.SourceSearchResult;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
@DataSource(value = DataSourceType.MASTER)
public interface DashboardMapper {
    List<SourceSearchResult> getSourceList(SourceSearchParam param);
    List<Map> getSourceStatistics(DashboardParam param);
    List<Map> getWarnStatistics(DashboardParam param);
}
