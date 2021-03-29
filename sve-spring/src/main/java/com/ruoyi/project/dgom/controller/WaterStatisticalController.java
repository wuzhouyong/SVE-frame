package com.ruoyi.project.dgom.controller;

import com.ruoyi.common.domain.ResultEntity;
import com.ruoyi.project.dgom.service.WaterStatisticalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author solang
 * @date 2020-12-04 10:30
 */
@RestController
@RequestMapping("/dgom/statistical")
public class WaterStatisticalController {

    @Autowired
    private WaterStatisticalService service;

    //各镇街污水排放量
    @GetMapping("/town_waste")
    public ResultEntity getTownWastewater(@RequestParam("startDate") String startDate, @RequestParam("endDate") String endDate) {
        return ResultEntity.success(service.getTownWastewater(startDate, endDate));
    }

    @GetMapping("/month_waste")
    public ResultEntity getMonthWastewater(@RequestParam("startDate") String startDate, @RequestParam("endDate") String endDate) {
        return ResultEntity.success(service.getMonthWastewater(startDate, endDate));
    }

    @GetMapping("/town_month_waste")
    public ResultEntity getTownMonthWastewater(@RequestParam Map<String, String> param) {
        return ResultEntity.success(service.getTownMonthWastewater(param));
    }

}
