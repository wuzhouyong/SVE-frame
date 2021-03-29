package com.ruoyi.project.lingxing.controller;

import com.ruoyi.common.domain.ResultEntity;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.project.lingxing.domain.LXDriverinfo;
import com.ruoyi.project.lingxing.domain.LXTransjobdetail;
import com.ruoyi.project.lingxing.domain.LXTransjobhead;
import com.ruoyi.project.lingxing.service.LXTransjobheadService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;

import java.sql.SQLException;
import java.util.List;

/**
 * 司机联单Controller及运输车司机Controller
 *
 * @author ruoyi
 * @date 2020-08-26
 */
@RestController
@RequestMapping("/lingxing")
public class LXTransjobheadController extends BaseController {
    @Autowired
    private LXTransjobheadService transjobheadService;

    //查询工作单列表 /lingxing/trans_jobhead/list
    @PostMapping("/trans_jobhead/list")
    public TableDataInfo jobheadList(@RequestBody LXTransjobhead transjobhead) {
        startPage();
        List<LXTransjobhead> list = transjobheadService.selectTransjobheadList(transjobhead);
        return getDataTable(list);
    }

    //查询司机联单列表 /lingxing/trans_jobdetail/{jobHeadid}
    @GetMapping("/trans_jobdetail/{jobHeadid}")
    public ResultEntity jobdetailList(@PathVariable String jobHeadid) {
        return ResultEntity.success(transjobheadService.selectTransjobdetailList(jobHeadid));
    }

    //查询产污企业联单列表 /lingxing/trans_jobdetail/list
    @GetMapping("/trans_jobdetail/list")
    public ResultEntity transJobdetailList(LXTransjobdetail jobDetail) {
        return ResultEntity.success(transjobheadService.selectJobDetailListBySourceId(jobDetail));
    }

    //查询产污企业运水联单详情 /lingxing/trans_billinfo/{transbillid}
    @GetMapping("/trans_billinfo/{transbillid}")
    public ResultEntity transBillinfo(@PathVariable String transbillid) {
        return ResultEntity.success(transjobheadService.selectTransBillinfo(transbillid));
    }

    //零星废水工况图 /lingxing/working_condition/{sourceId}
    @GetMapping("/working_condition/{sourceId}")
    public ResultEntity workingConditionInfo(@PathVariable String sourceId) {
        return ResultEntity.success(transjobheadService.selectWorkingConditionInfo(sourceId));
    }

    //零星废水工况分钟数 /lingxing/working_minute/{cpElemId}
    @GetMapping("/working_minute/{cpElemId}")
    public ResultEntity workingMinuteInfo(@PathVariable String cpElemId) {
        return ResultEntity.success(transjobheadService.selectWorkingMinuteInfo(cpElemId));
    }

    /**
     * 查询运输车司机列表  /lingxing/driverinfo/list
     */
    @PostMapping("/driverinfo/list")
    public TableDataInfo list(@RequestBody LXDriverinfo driverinfo) {
        startPage();
        List<LXDriverinfo> list = transjobheadService.selectDriverinfoList(driverinfo);
        return getDataTable(list);
    }

    /**
     * 新增运输车司机  /lingxing/driverinfo/add
     */
    @PostMapping("/driverinfo/add")
    public ResultEntity add(@RequestBody LXDriverinfo driverinfo) {
        int isAcc = 0;
        try {
            isAcc = transjobheadService.insertDriverinfo(driverinfo);
        } catch (SQLException e) {
            return ResultEntity.error(e.getMessage());
        }
        return ResultEntity.success("成功添加" + isAcc + "条");
    }

    /**
     * 修改运输车司机  /lingxing/driverinfo/edit
     */
    @PostMapping("/driverinfo/edit")
    public ResultEntity edit(@RequestBody LXDriverinfo driverinfo) {
        int isAcc = 0;
        try {
            isAcc = transjobheadService.updateDriverinfo(driverinfo);
        } catch (SQLException e) {
            return ResultEntity.error(e.getMessage());
        }
        return ResultEntity.success("成功修改" + isAcc + "条");
    }

    /**
     * 删除运输车司机  /lingxing/driverinfo/{driverIds}
     */
    @DeleteMapping("/driverinfo/{driverIds}")
    public ResultEntity remove(@PathVariable String[] driverIds) {
        int isAcc = 0;
        try {
            isAcc = transjobheadService.deleteDriverinfoByIds(driverIds);
        } catch (SQLException e) {
            return ResultEntity.error(e.getMessage());
        }
        return ResultEntity.success("成功删除" + isAcc + "条");
    }

    /**
     * 导出运输车司机列表
     */
    @GetMapping("/export")
    public ResultEntity export(LXDriverinfo driverinfo) {
        List<LXDriverinfo> list = transjobheadService.selectDriverinfoList(driverinfo);
        ExcelUtil<LXDriverinfo> util = new ExcelUtil<LXDriverinfo>(LXDriverinfo.class);
        return ResultEntity.success(util.exportExcel(list, "driverinfo"));
    }

    /**
     * 获取运输车司机详细信息
     */
    @GetMapping(value = "/{driverId}")
    public ResultEntity getInfo(@PathVariable("driverId") String driverId) {
        return ResultEntity.success(transjobheadService.selectDriverinfoById(driverId));
    }
}
