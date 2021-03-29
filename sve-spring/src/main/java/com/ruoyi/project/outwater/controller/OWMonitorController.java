package com.ruoyi.project.outwater.controller;

import com.ruoyi.common.domain.ResultEntity;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.project.outwater.service.OWMonitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/outwater/monitor")
public class OWMonitorController extends BaseController {

    @Autowired
    private OWMonitorService monitorService;

    //设备数量  /outwater/monitor/equipment_quantity
    @GetMapping("/equipment_quantity")
    public ResultEntity equipmentQuantity() {
        return ResultEntity.success(monitorService.getEquipmentQuantity());
    }

    //预警数  /outwater/monitor/warning_quantity
    @GetMapping("/warning_quantity")
    public ResultEntity warningQuantity() {
        return ResultEntity.success(monitorService.getWarningQuantity());
    }

    //近30天预警数  /outwater/monitor/warning_month
    @GetMapping("/warning_month")
    public ResultEntity warningQuantityMonth() {
        return ResultEntity.success(monitorService.getWarningQuantityMonth());
    }

    //预警产生趋势  /outwater/monitor/warning_trend
    @GetMapping("/warning_trend")
    public ResultEntity warningTrend() {
        return ResultEntity.success(monitorService.getWarningTrend());
    }

    //街镇报警排名  /outwater/monitor/warning_rank
    @GetMapping("/warning_rank")
    public ResultEntity warningRank() {
        return ResultEntity.success(monitorService.getWarningRank());
    }

    //污染最严重企业top10  /outwater/monitor/warning_pollution
    @GetMapping("/warning_pollution")
    public ResultEntity warningPollution() {
        return ResultEntity.success(monitorService.getWarningPollution());
    }

    //污染程度分析  /outwater/monitor/pollution_analysis
    @GetMapping("/pollution_analysis")
    public ResultEntity pollutionAnalysis() {
        return ResultEntity.success(monitorService.getPollutionAnalysis());
    }

}
