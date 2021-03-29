package com.ruoyi.project.enterprise.controller;

import com.ruoyi.common.domain.ResultEntity;
import com.ruoyi.framework.aspectj.lang.annotation.DataScope;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.enterprise.domain.dashboard.DashboardParam;
import com.ruoyi.project.enterprise.domain.dashboard.SourceSearchParam;
import com.ruoyi.project.enterprise.domain.dashboard.SourceSearchResult;
import com.ruoyi.project.enterprise.service.IDashboardService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/dashboard")
public class DashboardController extends BaseController {
    @Resource
    private IDashboardService service;

    @DataScope(regionAlias = "s")
    @PostMapping("/source/list")
    public TableDataInfo getSourceList(@RequestBody SourceSearchParam param) {
        startPage();
        List<SourceSearchResult> list = service.getSourceList(param);
        return getDataTable(list);
    }

    @DataScope(regionAlias = "s")
    @GetMapping("/statistics/source")
    public ResultEntity getSourceStatistics(DashboardParam param) {
        Map<String, Object> map = service.getSourceStatistics(param);
        return ResultEntity.success(map);
    }

    @DataScope(regionAlias = "s")
    @PostMapping("/statistics/warn")
    public ResultEntity getWarnStatistics(@RequestBody DashboardParam param) {
        Map<String, Object> map = service.getWarnStatistics(param);
        return ResultEntity.success(map);
    }
}
