package com.ruoyi.project.dgom.controller;

import com.ruoyi.common.domain.ResultEntity;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.framework.aspectj.lang.annotation.DataScope;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.common.poi.DocumentUtil;
import com.ruoyi.project.dgom.domain.abnor_task.*;
import com.ruoyi.project.dgom.mapper.DataSearchMapper;
import com.ruoyi.project.dgom.mapper.SuperviseTaskMapper;
import com.ruoyi.project.dgom.service.IAbnorTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/dgom/abnor_task")
public class AbnorTaskController extends BaseController {
    @Autowired
    private DataSearchMapper dataSearchMapper;

    @Autowired
    private SuperviseTaskMapper taskMapper;

    @Autowired
    private IAbnorTaskService service;

    @PostMapping("/list")
    public TableDataInfo getAbnorTaskList(@RequestBody AbnorTaskParam entity) {
        String sourceId = SecurityUtils.getLoginUser().getUser().getSourceId();
        if (sourceId != null) {
            sourceId = dataSearchMapper.checkBizSourceId(sourceId);
            if (sourceId == null) {
                return errorForDataTable("企业未关联或关联错误");
            }
            entity.setSourceId(sourceId);
        }
        if (entity.getTownCode() != null) {
            entity.setTownId(taskMapper.getOMTownIdByCode(entity.getTownCode()));
        }
        startPage();
        List<AbnorTaskResult> list = service.getAbnorTaskList(entity);
        return getDataTable(list);
    }

    @PostMapping("/export_excel")
    public AjaxResult exportAbnorTaskList(@RequestBody AbnorTaskParam entity) {
        String sourceId = SecurityUtils.getLoginUser().getUser().getSourceId();
        if (sourceId != null) {
            sourceId = dataSearchMapper.checkBizSourceId(sourceId);
            if (sourceId == null) {
                return AjaxResult.error("企业未关联或关联错误");
            }
            entity.setSourceId(sourceId);
        }
        if (entity.getTownCode() != null) {
            entity.setTownId(taskMapper.getOMTownIdByCode(entity.getTownCode()));
        }
        List<AbnorTaskResult> list = service.getAbnorTaskList(entity);
        DocumentUtil doc = new DocumentUtil(list);
        doc.addColumn("企业名称", "sourceName", 30);
        doc.addColumn("镇街", "townName", 10);
        doc.addColumn("任务编号", "taskHeadId", 10);
        doc.addColumn("任务状态", "taskStatusName", 10);
        doc.addColumn("异常类型", "abnorTaskTypeName", 10);
        doc.addColumn("异常因子", "elementDesc", 30);
        doc.addColumn("任务发出时间", "taskSentDate", 15);
        doc.addColumn("开始时间", "startDate", 15);
        doc.addColumn("结束时间", "endDate", 15);
        doc.addColumn("监测点", "checkpointDesc", 10);
        doc.addColumn("报警级别", "alarmLevelName", 10);
        doc.addColumn("报告时间", "dateReported", 15);
        doc.addColumn("是否恢复正常", "isReturnNormal", 10);
        doc.addColumn("恢复正常时间", "returnNormalDate", 15);
        String fileName = "异常任务列表";
        return doc.exportExcel(fileName);
    }

    @GetMapping("/elements/{taskHeadIds}")
    public ResultEntity getElementListByTaskId(@PathVariable String[] taskHeadIds) {
        if (taskHeadIds.length == 0) {
            return ResultEntity.error("请选择异常记录后再试");
        }
        List<TaskElementResult> list = service.getElementListByTaskIds(taskHeadIds);
        return ResultEntity.success(list);
    }

    @GetMapping("/proc_details/{taskHeadId}")
    public ResultEntity getProcDetailList(@PathVariable String taskHeadId) {
        return ResultEntity.success(service.getProcDetailList(taskHeadId));
    }

    @PostMapping("/add")
    public ResultEntity addManualTask(@RequestBody ManualTaskModel model) {
        int res = service.addManualTask(model);
        if (res > 0) {
            return ResultEntity.success("手工任务添加成功");
        }
        return ResultEntity.error("手工任务添加失败");
    }

    @PostMapping("/enterprise_proc/{taskHeadIds}")
    public ResultEntity enterpriseProcess(@PathVariable String[] taskHeadIds, @RequestBody EnterpriseProcessModel model) {
        String sourceId = SecurityUtils.getLoginUser().getUser().getSourceId();
        if (sourceId != null) {
            sourceId = dataSearchMapper.checkBizSourceId(sourceId);
            if (sourceId == null) {
                return ResultEntity.error("企业未关联或关联错误");
            }
        }
        int res = service.enterpriseProcBatch(taskHeadIds, sourceId, model);
        if (res > 0) {
            return ResultEntity.success("异常处理成功");
        }
        return ResultEntity.error("异常处理失败");
    }

    @PostMapping("/branch_proc/{taskHeadIds}")
    public ResultEntity branchProcess(@PathVariable String[] taskHeadIds, @RequestBody BranchProcessModel model) {
        if (taskHeadIds.length == 0) {
            return ResultEntity.error("请选择异常记录后再试");
        }
        int res = service.branchProcBatch(taskHeadIds, model);
        if (res > 0) {
            return ResultEntity.success("异常处理成功");
        }
        return ResultEntity.error("异常处理失败");
    }

    @PostMapping("/branch_return/{taskHeadIds}")
    public ResultEntity branchReturn(@PathVariable String[] taskHeadIds, @RequestBody BranchReturnModel model) {
        if (taskHeadIds.length == 0) {
            return ResultEntity.error("请选择异常记录后再试");
        }
        int res = service.branchReturnBatch(taskHeadIds, model);
        if (res > 0) {
            return ResultEntity.success("异常处理成功");
        }
        return ResultEntity.error("异常处理失败");
    }

    @PostMapping("/city_return/{taskHeadIds}")
    public ResultEntity cityReturn(@PathVariable String[] taskHeadIds, String returnReason) {
        if (taskHeadIds.length == 0) {
            return ResultEntity.error("请选择异常记录后再试");
        }
        int res = service.cityReturnBatch(taskHeadIds, returnReason);
        if (res > 0) {
            return ResultEntity.success("异常处理成功");
        }
        return ResultEntity.error("异常处理失败");
    }
}
