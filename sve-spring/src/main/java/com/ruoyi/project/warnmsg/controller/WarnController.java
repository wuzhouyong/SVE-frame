package com.ruoyi.project.warnmsg.controller;

import com.ruoyi.common.domain.ResultEntity;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.framework.aspectj.lang.annotation.DataScope;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.common.poi.DocumentUtil;
import com.ruoyi.project.warnmsg.domain.WarnEntity;
import com.ruoyi.project.warnmsg.domain.WarnParam;
import com.ruoyi.project.warnmsg.domain.WarnProcEntity;
import com.ruoyi.project.warnmsg.domain.WarnProcResult;
import com.ruoyi.project.warnmsg.service.impl.WarnService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/warn")
public class WarnController extends BaseController {
    @Resource
    private WarnService service;

    @PostMapping("/list")
    @DataScope(regionAlias = "s")
    public TableDataInfo getWarnList(@RequestBody WarnParam param) {
        String sourceId = SecurityUtils.getSourceId();
        if (sourceId != null) {
            param.setSourceId(sourceId);
        }
        startPage();
        List<WarnEntity> list = service.getWarnList(param);
        return getDataTable(list);
    }

    @PostMapping("/export")
    @DataScope(regionAlias = "s")
    public AjaxResult exportWarnList(@RequestBody WarnParam param) {
        String sourceId = SecurityUtils.getSourceId();
        if (sourceId != null) {
            param.setSourceId(sourceId);
        }
        List<WarnEntity> list = service.getWarnList(param);
        DocumentUtil doc = new DocumentUtil(list);
        doc.addColumn("任务", "tagHeadName", 30);
        doc.addColumn("污染源", "sourceName", 50);
        doc.addColumn("镇街", "townName", 10);
        doc.addColumn("品牌", "acquisitionBrand", 10);
        doc.addColumn("施工单位", "constructionUnit", 20);
        doc.addColumn("异常级别", "warnLevelName", 10);
        doc.addColumn("处理状态", "procStatusName", 10);
        doc.addColumn("异常开始时间", "startDate", 10);
        doc.addColumn("异常结束时间", "endDate", 10);
        doc.addColumn("异常类型", "warnTypeName", 15);
        doc.addColumn("异常描述", "warnDesc", 120);
        return doc.exportExcel("异常任务列表");
    }

    @GetMapping("/detail/{warnId}")
    public ResultEntity getWarnProcDetail(@PathVariable String warnId) {
        List<WarnProcResult> list = service.getWarnProcDetail(warnId);
        List<Map> attaches = service.getAttachList("warn_attach", warnId);
        list.forEach(e -> {
            e.setAttaches(attaches.stream().filter(f -> f.get("attachmentType").toString().equals(e.getUserType())).collect(Collectors.toList()));
        });
        return ResultEntity.success(list);
    }

    @GetMapping("/history/{warnId}")
    public ResultEntity getWarnProcHistory(@PathVariable String warnId) {
        List<WarnProcResult> list = service.getWarnProcHistory(warnId);
        return ResultEntity.success(list);
    }

    @PostMapping("/proc")
    public ResultEntity warnProc(@RequestBody WarnProcEntity entity) {
        int res = service.warnProc(entity);
        if (res > 0) {
            return ResultEntity.success("异常处理成功");
        }
        return ResultEntity.error("异常处理失败");
    }

    @PatchMapping("/upgrade_level/{warnId}")
    public ResultEntity upgradeLevel(@PathVariable String warnId) {
        int res = service.upgradeLevel(warnId);
        if (res > 0) {
            return ResultEntity.success("异常级别提升成功");
        }
        return ResultEntity.error("异常级别提升失败");
    }
}
