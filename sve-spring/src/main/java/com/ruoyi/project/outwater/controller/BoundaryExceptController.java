package com.ruoyi.project.outwater.controller;

import com.ruoyi.common.domain.ResultEntity;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.aspectj.lang.annotation.DataScope;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.common.poi.DocumentUtil;
import com.ruoyi.project.outwater.domain.ExceptformulaEntity;
import com.ruoyi.project.outwater.domain.SpecwarnmsgEntity;
import com.ruoyi.project.outwater.domain.entity.SpecwarnmsgExport;
import com.ruoyi.project.outwater.service.BoundaryExceptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * @author solang
 * @date 2020-11-23 9:29
 */

@RestController
@RequestMapping("/outwater/except")
public class BoundaryExceptController extends BaseController {

    @Autowired
    private BoundaryExceptService service;

    @PostMapping("/boundaryList")  // /outwater/except/boundaryList
    public ResultEntity selectBoundaryList(@RequestBody ExceptformulaEntity entity) {
        return ResultEntity.success(service.selectBoundaryExceptList(entity));
    }

    @PostMapping("/boundaryList1")  // /outwater/except/boundaryList1
    public ResultEntity selectBoundaryList1(@RequestBody ExceptformulaEntity entity) {
        return ResultEntity.success(service.selectBoundaryExceptList1(entity));
    }

    @PostMapping("/edit")  // /outwater/except/edit
    public ResultEntity editBoundaryExcept(@RequestBody List<ExceptformulaEntity> entity) {
        return service.editBoundaryExcept(entity);
    }

    @PostMapping("/specList")  // /outwater/except/specList
    @DataScope(regionAlias = "s")
    public TableDataInfo selectSpecwarnmsgList(@RequestBody SpecwarnmsgEntity entity) {
        startPage();
        if (!StringUtils.isEmpty(SecurityUtils.getLoginUser().getUser().getSourceId())) {
            entity.setSourceId(SecurityUtils.getLoginUser().getUser().getSourceId());
        }
        List<SpecwarnmsgEntity> list = service.selectSpecwarnmsgList(entity);
        return getDataTable(list);
    }

    @PostMapping("/specList_export")    //  /outwater/except/specList_export
    @DataScope(regionAlias = "s")
    public AjaxResult outwater(@RequestBody SpecwarnmsgEntity entity) {
        if (!StringUtils.isEmpty(SecurityUtils.getLoginUser().getUser().getSourceId())) {
            entity.setSourceId(SecurityUtils.getLoginUser().getUser().getSourceId());
        }
        List<SpecwarnmsgExport> data = service.selectSpecwarnmsgExport(entity);
        DocumentUtil doc = new DocumentUtil(data);
        doc.addColumn("污染源", "fullName", 35);
        doc.addColumn("街镇", "townName", 10);
        doc.addColumn("品牌", "brand", 15);
        doc.addColumn("施工单位", "constructionUnit", 30);
        doc.addColumn("异常开始时间", "startDate", 15);
        doc.addColumn("异常结束时间", "endDate", 15);
        doc.addColumn("异常类型", "warnTypeName", 25);
        doc.addColumn("异常描述", "warnDesc", 60);
        return doc.exportExcel("排水户监控不报警异常");
    }

}
