package com.ruoyi.project.enterprise.controller;


import com.ruoyi.common.domain.ResultEntity;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.enterprise.domain.PfBasSource;
import com.ruoyi.project.enterprise.domain.user_task.BatchUserTaskModel;
import com.ruoyi.project.enterprise.domain.user_task.SysUserTask;
import com.ruoyi.project.enterprise.service.ISysUserTaskService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;

import java.sql.SQLException;
import java.util.List;

/**
 * 【请填写功能名称】Controller
 * 
 * @author ruoyi
 * @date 2020-08-04
 */
@RestController
@RequestMapping("/user/task")
public class SysUserTaskController extends BaseController
{
    @Autowired
    private ISysUserTaskService sysUserTaskService;

    // 参数 {userId:"xxx", tagId:1002, sourceIds:[xxx,xxx,xxx]}
    @PostMapping("/left_list")
    public TableDataInfo leftList(@RequestBody SysUserTask sysUserTask)
    {
        startPage();
        List<PfBasSource> list = sysUserTaskService.selectPlatformLeftDate(sysUserTask);
        return getDataTable(list);
    }

    @PostMapping("/right_list")
    public TableDataInfo rightList(@RequestBody SysUserTask sysUserTask)
    {
        startPage();
        List<PfBasSource> list = sysUserTaskService.selectPlatformRightDate(sysUserTask);
        return getDataTable(list);
    }

    /**
     * 获取【请填写功能名称】详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:task:query')")
    @GetMapping(value = "/{userSourceId}")
    public AjaxResult getInfo(@PathVariable("userSourceId") String userSourceId)
    {
        return AjaxResult.success(sysUserTaskService.selectSysUserTaskById(userSourceId));
    }

    @PostMapping("/add")
    public ResultEntity add(@RequestBody SysUserTask sysUserTask)
    {   int isAcc =0;
        try {
            isAcc = sysUserTaskService.insertSysUserTask(sysUserTask);
        } catch (SQLException e) {
            return ResultEntity.error(e.getMessage());
        }
        return ResultEntity.success("成功添加" + isAcc +"条");
    }

    @PostMapping("/update")
    public ResultEntity update(@RequestBody SysUserTask sysUserTask)
    {
        int isAcc = sysUserTaskService.removeSysUserTask(sysUserTask);
        return ResultEntity.success("成功移除" + isAcc +"条");
    }

    /**
     * 修改【请填写功能名称】
     */
    @PreAuthorize("@ss.hasPermi('system:task:edit')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysUserTask sysUserTask)
    {
        return toAjax(sysUserTaskService.updateSysUserTask(sysUserTask));
    }

    /**
     * 删除【请填写功能名称】
     */
	@DeleteMapping("/{userSourceIds}")
    public ResultEntity remove(@PathVariable String[] userSourceIds)
    {
        return ResultEntity.toAjax(sysUserTaskService.deleteSysUserTaskByIds(userSourceIds));
    }

    @PostMapping("/batch")
    public ResultEntity batch(@RequestBody BatchUserTaskModel model) {
	    int res = sysUserTaskService.batchUserTask(model);
	    if (res > 0) {
            return ResultEntity.success("关联操作成功");
        }
        return ResultEntity.error("关联操作失败");
    }
}
