package com.ruoyi.project.ops.controller;

import com.ruoyi.common.domain.ResultEntity;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.ops.domain.OpSourceEntity;
import com.ruoyi.project.ops.service.DevopsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

/**
 * 【请填写功能名称】Controller
 *
 * @author ruoyi
 * @date 2020-08-25
 */
@RestController
@RequestMapping("/devops")
public class DevopsController extends BaseController {
    @Autowired
    private DevopsService service;

    //运维单位基础信息列表   /devops/list
    @PostMapping("/list")
    public TableDataInfo selectBasDevopsList(@RequestBody OpSourceEntity entity) {
        entity.setIsOnList(1);
        startPage();
        List<OpSourceEntity> list = service.selectBasDevopsList(entity);
        return getDataTable(list);
    }

    @PostMapping("/add")
    public ResultEntity insertBasDevops(@RequestBody OpSourceEntity entity) {
        entity.setIsOnList(1);
        int isAcc = 0;
        try {
            isAcc = service.insertBasDevops(entity);
        } catch (SQLException e) {
            return ResultEntity.error(e.getMessage());
        }
        return ResultEntity.success("成功添加" + isAcc + "条");
    }

    @PostMapping("/edit")
    public ResultEntity updateBasDevops(@RequestBody OpSourceEntity entity) {
        int isAcc = 0;
        try {
            isAcc = service.updateBasDevops(entity);
        } catch (SQLException e) {
            return ResultEntity.error(e.getMessage());
        }
        return ResultEntity.success("成功修改" + isAcc + "条");
    }

    @DeleteMapping("/delete/{opSourceIds}")
    public ResultEntity deleteBasDevopsByIds(@PathVariable Integer[] opSourceIds) {
        int isAcc = 0;
        try {
            isAcc = service.deleteBasDevopsByIds(opSourceIds);
        } catch (SQLException e) {
            return ResultEntity.error(e.getMessage());
        }
        return ResultEntity.success("成功删除" + isAcc + "条");
    }


}
