package com.ruoyi.project.enterprise.controller;

import com.ruoyi.common.domain.ResultEntity;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.aspectj.lang.annotation.DataScope;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.enterprise.domain.ReportEntity;
import com.ruoyi.project.enterprise.domain.TaskAdjustDetail;
import com.ruoyi.project.enterprise.domain.TaskAdjustEntity;
import com.ruoyi.project.enterprise.service.TaskAdjustService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/enterprise/adjust")
public class TaskAdjustController extends BaseController {

    @Autowired
    private TaskAdjustService adjustService;

    @Log(title = "任务调整申请新增", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public ResultEntity add(@RequestBody TaskAdjustEntity entity) {
        if (entity.taskAdjustId == null) {
            String askAdjustIdt = java.util.UUID.randomUUID().toString();
            entity.setTaskAdjustId(askAdjustIdt);
        }
        entity.setUpdatedBy(SecurityUtils.getUsername());
        if (!StringUtils.isEmpty(SecurityUtils.getLoginUser().getUser().getSourceId())) {
            entity.setPfSourceId(SecurityUtils.getLoginUser().getUser().getSourceId());
        }
        int count = adjustService.selectTaskAdjust(entity);
        if (count > 0) return ResultEntity.error("该企业已经待审批，不能重复添加");
        int res = adjustService.insertTaskAdjust(entity);
        if (res > 0) {
            if (entity.getAduitStatus() != null)
                adjustService.agree(entity);
            return ResultEntity.success(entity.getTaskAdjustId(), "新增申请成功");
        } else
            return ResultEntity.success("", "新增申请失败");
    }

    //审核明细
    @GetMapping("/model")
    public ResultEntity getModel(String taskAdjustId) {
        TaskAdjustEntity model = adjustService.selectEntity(taskAdjustId);
        if (model != null)
            return ResultEntity.success(model);
        else
            return ResultEntity.success(new TaskAdjustEntity());
    }

    @PostMapping("/agree")
    public ResultEntity agree(@RequestBody TaskAdjustEntity entity) {
        return ResultEntity.toAjax(adjustService.agree(entity));
    }

    @GetMapping("/list")
    public ResultEntity getList(String pfSourceId, String tagHeadId) {
        return ResultEntity.success(adjustService.selectTaskAdjustList(pfSourceId, tagHeadId));
    }

    //拆分零星废水任务调整审批
    @PostMapping("/audit")
    public ResultEntity taskApproval(@RequestBody TaskAdjustEntity entity) {
        return adjustService.taskApproval(entity);
    }

    //零星废水任务明细返回数据结构
    @GetMapping("/detail")
    public ResultEntity selectTaskAdjustEntity(String pfSourceId, String tagHeadId) {
        return ResultEntity.success(adjustService.selectTaskAdjustEntity(pfSourceId, tagHeadId));
    }

    //零星废水任务明细详情 只有1条或者0条
    @GetMapping("/result/{taskAdjustId}")
    public ResultEntity selectTaskAdjustDetail(@PathVariable String taskAdjustId) {
        TaskAdjustDetail model = adjustService.selectTaskAdjustDetail(taskAdjustId);
        if (model != null)
            return ResultEntity.success(model);
        else
            return ResultEntity.success(new TaskAdjustDetail());
    }

    @GetMapping("/report_add") //报告审核 /enterprise/adjust/report_add
    public ResultEntity reportAdd() {
        ReportEntity entity = new ReportEntity();
        entity.setUpdatedBy(SecurityUtils.getUsername());
        entity.setCreatedBy(SecurityUtils.getUsername());
        if (!StringUtils.isEmpty(SecurityUtils.getLoginUser().getUser().getSourceId())) {
            entity.setSourceId(SecurityUtils.getLoginUser().getUser().getSourceId());
        } else return ResultEntity.error("企业信息不能为空");
        int count = adjustService.insertReport(entity);
        if (count < 1) return ResultEntity.error("该企业下的报告添加失败");
        return ResultEntity.success(count);
    }

    @PostMapping("/report_list") //报告审核 /enterprise/adjust/report_list
    @DataScope(regionAlias = "s")
    public TableDataInfo reportList(@RequestBody ReportEntity entity) {
        if (!StringUtils.isEmpty(SecurityUtils.getLoginUser().getUser().getSourceId())) {
            entity.setSourceId(SecurityUtils.getLoginUser().getUser().getSourceId());
        }
        startPage();
        List<ReportEntity> list = adjustService.selectReportList(entity);
        return getDataTable(list);
    }

    @PostMapping("/report_audit") //报告审核 /enterprise/adjust/report_audit  2是同意，1是退回，0是待审核
    public ResultEntity editReportAudit(@RequestBody ReportEntity entity) {
        List<Integer> list = Arrays.asList(new Integer[]{1, 2});
        if (!list.contains(entity.getAuditStatus())) return ResultEntity.error("审核状态不能为待审核");
        int count = adjustService.editReportAudit(entity);
        if (count < 1) return ResultEntity.error("修改状态失败");
        return ResultEntity.success("修改状态成功");
    }

}
