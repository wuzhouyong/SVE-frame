package com.ruoyi.project.jiayou.controller;

import com.ruoyi.common.domain.ResultEntity;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.guocheng.domain.GCSourceAbnorEntity;
import com.ruoyi.project.jiayou.domain.JYSourceAbnorEntity;
import com.ruoyi.project.jiayou.service.JYGatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author solang
 * @date 2020-11-03 10:13
 */
@RestController
@RequestMapping("/jiayou/statistical")
public class JYGatherController extends BaseController {

    @Autowired
    private JYGatherService gatherService;

    @GetMapping("/calculate")  //企业异常统计
    public ResultEntity getCalculate() {
        return ResultEntity.success(gatherService.getCalculate());
    }

    @GetMapping("/equipment")  //设备异常统计
    public ResultEntity getEquipment() {
        return ResultEntity.success(gatherService.getEquipment());
    }

    @GetMapping("/construction")  //施工单位异常统
    public ResultEntity getConstruction() {
        return ResultEntity.success(gatherService.getConstruction());
    }

    @PostMapping("/source")  //企业异常统计
    public TableDataInfo getSourceAbnor(@RequestBody JYSourceAbnorEntity entity) {
        startPage();
        List<JYSourceAbnorEntity> list = gatherService.getSourceAbnor(entity);
        return getDataTable(list);
    }

}
