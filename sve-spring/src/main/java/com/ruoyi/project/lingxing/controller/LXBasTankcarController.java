package com.ruoyi.project.lingxing.controller;

import com.ruoyi.common.domain.ResultEntity;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.project.lingxing.domain.LXBasTankcar;
import com.ruoyi.project.lingxing.domain.LXBasTrucktank;
import com.ruoyi.project.lingxing.service.LXBasTankcarService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;

import java.sql.SQLException;
import java.util.List;

/**
 * 【请填写功能名称】Controller
 *
 * @author ruoyi
 * @date 2020-08-25
 */
@RestController
@RequestMapping("/lingxing/tankcar")
public class LXBasTankcarController extends BaseController {
    @Autowired
    private LXBasTankcarService basTankcarService;

    //查找处置企业  /lingxing/tankcar/enterprise
    @GetMapping("/enterprise")
    public ResultEntity enterprise() {
        return ResultEntity.success(basTankcarService.selectEnterpriseList());
    }

    // 新增【运输车辆】  /lingxing/tankcar/add
    @PostMapping("/add")
    public ResultEntity tankCarAdd(@RequestBody LXBasTankcar basTankcar) {
        int isAcc = 0;
        try {
            isAcc = basTankcarService.insertBasTankcar(basTankcar);
        } catch (SQLException e) {
            return ResultEntity.error(e.getMessage());
        }
        return ResultEntity.success("成功添加" + isAcc + "条");
    }

    // 查询【运输车辆】列表  /lingxing/tankcar/list
    @PostMapping("/list")
    public TableDataInfo tankcarList(@RequestBody LXBasTankcar basTankcar) {
        startPage();
        List<LXBasTankcar> list = basTankcarService.selectBasTankcarList(basTankcar);
        return getDataTable(list);
    }

    // 修改【请填写功能名称】 /lingxing/tankcar/edit
    @PostMapping("/edit")
    public ResultEntity tankcarEdit(@RequestBody LXBasTankcar basTankcar) {
        int isAcc = 0;
        try {
            isAcc = basTankcarService.updateBasTankcar(basTankcar);
        } catch (SQLException e) {
            return ResultEntity.error(e.getMessage());
        }
        return ResultEntity.success("成功修改" + isAcc + "条");
    }

    // 删除【运输车辆】  /lingxing/tankcar/delete/
    @DeleteMapping("/delete/{carIds}")
    public ResultEntity tankcarRemove(@PathVariable Integer[] carIds) {
        int isAcc = 0;
        try {
            isAcc = basTankcarService.deleteBasTankcarByIds(carIds);
        } catch (SQLException e) {
            return ResultEntity.error(e.getMessage());
        }
        return ResultEntity.success("成功删除" + isAcc + "条");
    }

    /**
     * 导出【请填写功能名称】列表
     */
    @GetMapping("/export")
    public AjaxResult export(LXBasTankcar basTankcar) {
        List<LXBasTankcar> list = basTankcarService.selectBasTankcarList(basTankcar);
        ExcelUtil<LXBasTankcar> util = new ExcelUtil<LXBasTankcar>(LXBasTankcar.class);
        return util.exportExcel(list, "tankcar");
    }

    /**
     * 获取【请填写功能名称】详细信息
     */
    @GetMapping(value = "/{carId}")
    public AjaxResult getInfo(@PathVariable("carId") Integer carId) {
        return AjaxResult.success(basTankcarService.selectBasTankcarById(carId));
    }

    // 查询【车辆水箱】列表  /lingxing/tankcar/truck_list
    @PostMapping("/truck_list")
    public TableDataInfo trucktankList(@RequestBody LXBasTrucktank trucktank) {
        startPage();
        List<LXBasTrucktank> list = basTankcarService.selectTrucktankList(trucktank);
        return getDataTable(list);
    }

    // 新增【车辆水箱】列表  /lingxing/tankcar/truck_add
    @PostMapping("/truck_add")
    public ResultEntity trucktankAdd(@RequestBody LXBasTrucktank trucktank) {
        int isAcc = 0;
        try {
            isAcc = basTankcarService.insertTrucktank(trucktank);
        } catch (SQLException e) {
            return ResultEntity.error(e.getMessage());
        }
        return ResultEntity.success("成功添加" + isAcc + "条");
    }

    // 编辑【车辆水箱】  /lingxing/tankcar/truck_edit
    @PostMapping("/truck_edit")
    public ResultEntity trucktankEdit(@RequestBody LXBasTrucktank trucktank) {
        int isAcc = 0;
        try {
            isAcc = basTankcarService.updateTrucktank(trucktank);
        } catch (SQLException e) {
            return ResultEntity.error(e.getMessage());
        }
        return ResultEntity.success("成功添加" + isAcc + "条");
    }

    // 删除【车辆水箱】  /lingxing/tankcar/truck_delete/
    @DeleteMapping("/truck_delete/{tankIds}")
    public ResultEntity removeTrucktank(@PathVariable Integer[] tankIds) {
        int isAcc = 0;
        try {
            isAcc = basTankcarService.deleteBasTrucktankByIds(tankIds);
        } catch (SQLException e) {
            return ResultEntity.error(e.getMessage());
        }
        return ResultEntity.success("成功删除" + isAcc + "条");
    }

    // 查找车牌  /lingxing/tankcar/list/{sourceId}
    @GetMapping("/list/{sourceId}")
    public ResultEntity transList(@PathVariable String sourceId) {
        return ResultEntity.success(basTankcarService.selectTankcarList(sourceId));
    }

    // 查找工作单id  /lingxing/tankcar/trans
    @GetMapping("/trans")
    public ResultEntity transjobheadList(String receiveDate,Integer carId) {
        return ResultEntity.success(basTankcarService.selectTransHeadList(receiveDate, carId));
    }

    // 查询【车辆轨迹】列表  /lingxing/tankcar/track_list/
    @GetMapping("/track_list/{jobHeadid}")
    public ResultEntity truckTrackList(@PathVariable String jobHeadid) {
        return ResultEntity.success(basTankcarService.selectTruckTrackList(jobHeadid));
    }

}
