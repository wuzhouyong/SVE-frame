package com.ruoyi.project.warnmsg.controller;

import com.ruoyi.common.domain.ResultEntity;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.framework.aspectj.lang.annotation.DataScope;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.common.poi.DocumentUtil;
import com.ruoyi.project.warnmsg.domain.WarnProcessEntity;
import com.ruoyi.project.warnmsg.domain.WarnProcessResult;
import com.ruoyi.project.warnmsg.domain.WarnmsgParam;
import com.ruoyi.project.warnmsg.domain.WarnmsgResult;
import com.ruoyi.project.warnmsg.service.IWarnmsgService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static com.ruoyi.project.common.domain.TaskPrefix.getTagHeadId;
import static com.ruoyi.project.common.domain.TaskPrefix.getTypePrefix;

@RestController
@RequestMapping("/{prefix}/warnmsg")
public class WarnmsgController extends BaseController {
    @Resource(name = "default")
    private IWarnmsgService service;

    @Resource(name = "lingxingp")
    private IWarnmsgService lxpService;

    @PostMapping("/list")
    @DataScope(regionAlias = "s")
    public TableDataInfo getWarnmsgList(@RequestBody WarnmsgParam param, @PathVariable String prefix) {
        String sourceId = SecurityUtils.getSourceId();
        if (sourceId != null) {
            param.setSourceId(sourceId);
        }
        int tagHeadId = getTagHeadId(prefix);
        param.setTagHeadId(String.valueOf(tagHeadId));
        param.setDictPrefix(getTypePrefix(tagHeadId));
        startPage();
        List<WarnmsgResult> list;
        if (prefix.equals("lingxingp")) {
            list = lxpService.getWarnmsgList(prefix, param);
        } else {
            list = service.getWarnmsgList(prefix, param);
        }
        return getDataTable(list);
    }

    @PostMapping("/export")
    @DataScope(regionAlias = "s")
    public AjaxResult exportWarnmsgList(@RequestBody WarnmsgParam param, @PathVariable String prefix) {
        String sourceId = SecurityUtils.getSourceId();
        if (sourceId != null) {
            param.setSourceId(sourceId);
        }
        int tagHeadId = getTagHeadId(prefix);
        param.setTagHeadId(String.valueOf(tagHeadId));
        param.setDictPrefix(getTypePrefix(tagHeadId));
        List<WarnmsgResult> list;
        if (prefix.equals("lingxingp")) {
            list = lxpService.getWarnmsgList(prefix, param);
        } else {
            list = service.getWarnmsgList(prefix, param);
        }
        DocumentUtil doc = new DocumentUtil(list);
        doc.addColumn("污染源", "sourceName", 50);
        doc.addColumn("镇街", "townName", 10);
        doc.addColumn("品牌", "acquisitionBrand", 10);
        doc.addColumn("施工单位", "constructionUnit", 20);
        doc.addColumn("异常级别", "warnLevelName", 10);
        doc.addColumn("处理状态", "procStateName", 10);
        doc.addColumn("异常开始时间", "startDate", 10);
        doc.addColumn("异常结束时间", "endDate", 10);
        doc.addColumn("异常类型", "warnTypeName", 15);
        doc.addColumn("异常描述", "warnDesc", 120);
        return doc.exportExcel(prefix + "_异常任务列表");
    }

    @PostMapping("/enterprise_list")
    public TableDataInfo getEnterpriseWarnmsgList(@PathVariable String prefix, @RequestBody WarnmsgParam param) {
        String sourceId = SecurityUtils.getSourceId();
        if (sourceId != null) {
            param.setSourceId(sourceId);
        }
        int tagHeadId = getTagHeadId(prefix);
        param.setTagHeadId(String.valueOf(tagHeadId));
        param.setDictPrefix(getTypePrefix(tagHeadId));
        param.setProcStates(Arrays.asList(0,1));
        startPage();
        List<WarnmsgResult> list;
        if (prefix.equals("lingxingp")) {
            list = lxpService.getWarnmsgList(prefix, param);
        } else {
            list = service.getWarnmsgList(prefix, param);
        }
        return getDataTable(list);
    }

    @PostMapping("/duty_list")
    public TableDataInfo getDutyWarnmsgList(@PathVariable String prefix, @RequestBody WarnmsgParam param) {
        int tagHeadId = getTagHeadId(prefix);
        param.setTagHeadId(String.valueOf(tagHeadId));
        param.setDictPrefix(getTypePrefix(tagHeadId));
        param.setProcStates(Arrays.asList(2,3,4));
        startPage();
        List<WarnmsgResult> list;
        if (prefix.equals("lingxingp")) {
            list = lxpService.getDutyWarnmsgList(prefix, param);
        } else {
            list = service.getDutyWarnmsgList(prefix, param);
        }
        return getDataTable(list);
    }

    @GetMapping("/process/list/{warnId}")
    public ResultEntity getProcessList(@PathVariable String prefix, @PathVariable String warnId) {
        List<WarnProcessResult> list;
        if (prefix.equals("lingxingp")) {
            list = lxpService.getProcessList(prefix, warnId);
        } else {
            list = service.getProcessList(prefix, warnId);
        }
        return ResultEntity.success(list);
    }

    @PostMapping("/enterprise_proc")
    public ResultEntity enterpriseProc(@PathVariable String prefix, @RequestBody WarnProcessEntity entity) {
        entity.setProcId(UUID.randomUUID().toString());
        if (prefix.equals("lingxingp")) {
            return lxpService.enterpriseProc(prefix, entity);
        } else {
            return service.enterpriseProc(prefix, entity);
        }
    }

    @PostMapping("/duty_proc")
    public ResultEntity dutyProc(@PathVariable String prefix, @RequestBody WarnProcessEntity entity) {
        entity.setProcId(UUID.randomUUID().toString());
        if (prefix.equals("lingxingp")) {
            return lxpService.dutyProc(prefix, entity);
        } else {
            return service.dutyProc(prefix, entity);
        }
    }

    @PostMapping("/sub_station_proc")
    public ResultEntity subStationProc(@PathVariable String prefix, @RequestBody WarnProcessEntity entity) {
        entity.setProcId(UUID.randomUUID().toString());
        if (prefix.equals("lingxingp")) {
            return lxpService.subStationProc(prefix, entity);
        } else {
            return service.subStationProc(prefix, entity);
        }
    }
}
