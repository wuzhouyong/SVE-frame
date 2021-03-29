package com.ruoyi.project.enterprise.controller;

import com.ruoyi.common.domain.ResultEntity;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.aspectj.lang.annotation.DataScope;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.enterprise.domain.work_status.*;
import com.ruoyi.project.enterprise.service.IWorkStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/work_status")
public class WorkStatusController extends BaseController {
    @Autowired
    private IWorkStatusService service;

    @DataScope(regionAlias = "s")
    @PostMapping("/list")
    public TableDataInfo getWorkStatusList(@RequestBody WorkStatusParam param) {
        String sourceId = SecurityUtils.getLoginUser().getUser().getSourceId();
        if (!StringUtils.isEmpty(sourceId)) {
            param.setSourceId(sourceId);
        }
        startPage();
        List<WorkStatusResult> list = service.getWorkStatusList(param);
        list.forEach(e -> {
            param.setWorkId(e.getWorkId());
            e.setChanges(service.getChangeTimeList(param));
        });
        return getDataTable(list);
    }

    @PostMapping("/add")
    public ResultEntity addWorkStatus(@RequestBody WorkStatusEntity entity) {
        String sourceId = SecurityUtils.getLoginUser().getUser().getSourceId();
        if (!StringUtils.isEmpty(sourceId)) {
            entity.setSourceId(sourceId);
        }
        String username = SecurityUtils.getLoginUser().getUsername();
        entity.setCreatedBy(username);
        entity.setUpdatedBy(username);
        List<WorkStatusEntity> list = service.getWorkStatusInTime(entity);
        if (list.size() > 0) {
            return ResultEntity.error("已存在包含该时间段的申请");
        }
        int res = service.addWorkStatus(entity);
        if (res > 0) {
            return ResultEntity.success(entity.getWorkId(), "工况申请提交成功");
        }
        return ResultEntity.error("工况申请提交失败");
    }

    @PostMapping("/edit")
    public ResultEntity updateWorkStatus(@RequestBody WorkStatusEntity entity) {
        // 判断是否为待审核状态
        WorkStatusEntity model = service.getModelById(entity.getWorkId());
        if (model == null) {
            return ResultEntity.error("工况数据不存在或已删除");
        }
        if (model.getAuditStatus() > 1) {
            return ResultEntity.error("记录已审批,不允许修改");
        }
        String updatedBy = SecurityUtils.getLoginUser().getUsername();
        entity.setUpdatedBy(updatedBy);
        List<WorkStatusEntity> list = service.getWorkStatusInTime(entity);
        if (list.size() > 0) {
            return ResultEntity.error("已存在包含该时间段的申请");
        }
        int res = service.updateWorkStatus(entity);
        if (res > 0) {
            return ResultEntity.success("工况申请提交成功");
        }
        return ResultEntity.error("工况申请提交失败");
    }

    @PostMapping("/audit")
    public ResultEntity auditWorkStatus(@RequestBody WorkStatusEntity entity) {
        WorkStatusEntity model = service.getModelById(entity.getWorkId());
        if (model == null) {
            return ResultEntity.error("工况数据不存在或已删除");
        }
        if (model.getAuditStatus() > 1) {
            return ResultEntity.error("记录已审批,不允许重复审批");
        }
        String auditBy = SecurityUtils.getLoginUser().getUsername();
        entity.setAuditBy(auditBy);
        List<WorkStatusEntity> list = service.getWorkStatusInTime(entity);
        if (list.size() > 0) {
            return ResultEntity.error("已存在包含该时间段的申请");
        }
        int res = service.auditWorkStatus(entity);
        if (res > 0) {
            return ResultEntity.success("工况审核成功");
        }
        return ResultEntity.error("工况审核失败");
    }

    @DeleteMapping("/remove/{workId}")
    public ResultEntity removeWorkStatus(@PathVariable String workId) {
        // 判断是否为待审核状态
        WorkStatusEntity model = service.getModelById(Integer.parseInt(workId));
        if (model == null) {
            return ResultEntity.error("工况数据不存在或已删除");
        }
        if (model.getAuditStatus() > 1) {
            return ResultEntity.error("记录已审批,不允许删除");
        }
        String updatedBy = SecurityUtils.getLoginUser().getUsername();
        int res = service.removeWorkStatus(workId, updatedBy);
        if (res > 0) {
            return ResultEntity.success("工况申请删除成功");
        }
        return ResultEntity.error("工况申请删除失败");
    }

    @PostMapping("/change_list")
    public TableDataInfo getChangeTimeList(@RequestBody WorkStatusParam param) {
        startPage();
        List<WorkStatusChangeResult> list = service.getChangeTimeList(param);
        return getDataTable(list);
    }

    @PostMapping("/apply_change_time")
    public ResultEntity applyChangeTime(@RequestBody WorkStatusChangeEntity entity) {
        WorkStatusEntity model = service.getModelById(entity.getWorkId());
        if (model == null) {
            return ResultEntity.error("工况数据不存在或已删除");
        }
        if (model.getAuditStatus() <= 1) {
            return ResultEntity.error("记录未审批,请直接修改");
        }
        String username = SecurityUtils.getLoginUser().getUsername();
        entity.setCreatedBy(username);
        entity.setUpdatedBy(username);
        int res = service.applyChangeTime(entity);
        if (res > 0) {
            return ResultEntity.success("工况变更提交成功");
        }
        return ResultEntity.error("工况变更提交失败");
    }

    @PostMapping("/audit_change_time")
    public ResultEntity auditChangeTime(@RequestBody WorkStatusChangeEntity entity) {
        WorkStatusEntity wsEntity = new WorkStatusEntity();
        String sourceId = SecurityUtils.getLoginUser().getUser().getSourceId();
        if (!StringUtils.isEmpty(sourceId)) {
            wsEntity.setSourceId(sourceId);
        }
        wsEntity.setWorkId(entity.getWorkId());
        wsEntity.setStartTime(entity.getStartTime());
        wsEntity.setEndTime(entity.getEndTime());
        List<WorkStatusEntity> list = service.getWorkStatusInTime(wsEntity);
        if (list.size() > 0) {
            return ResultEntity.error("已存在包含该时间段的申请");
        }
        int res = service.auditChangeTime(entity);
        if (res > 0) {
            service.syncChangeToMaster(entity.getChangeId());
            return ResultEntity.success("工况变更审核成功");
        }
        return ResultEntity.error("工况变更审核失败");
    }
}
