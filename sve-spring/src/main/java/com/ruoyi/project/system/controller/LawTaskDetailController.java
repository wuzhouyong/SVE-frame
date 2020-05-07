package com.ruoyi.project.system.controller;

import java.util.List;

import com.ruoyi.framework.web.page.TableDataInfo;
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
import com.ruoyi.project.system.domain.LawTaskDetail;
import com.ruoyi.project.system.service.ILawTaskDetailService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 移动执法任务 - 明细Controller
 * 
 * @author ruoyi
 * @date 2020-05-07
 */
@RestController
@RequestMapping("/system/detail")
public class LawTaskDetailController extends BaseController
{
    @Autowired
    private ILawTaskDetailService lawTaskDetailService;
    /**
     * 查询【请填写功能名称】列表
     */
    @PreAuthorize("@ss.hasPermi('system:detail:list')")
    @GetMapping("/list")
    public TableDataInfo list(LawTaskDetail lawTaskDetail) {
        startPage();
        List<LawTaskDetail> list = lawTaskDetailService.selectLawTaskDetailList(lawTaskDetail);
        return getDataTable(list);
    }

    /**
     * 导出移动执法任务 - 明细列表
     */
    @PreAuthorize("@ss.hasPermi('system:detail:export')")
    @Log(title = "移动执法任务 - 明细", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(LawTaskDetail lawTaskDetail)
    {
        List<LawTaskDetail> list = lawTaskDetailService.selectLawTaskDetailList(lawTaskDetail);
        ExcelUtil<LawTaskDetail> util = new ExcelUtil<LawTaskDetail>(LawTaskDetail.class);
        return util.exportExcel(list, "detail");
    }

    /**
     * 获取移动执法任务 - 明细详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:detail:query')")
    @GetMapping(value = "/{taskDetailId}")
    public AjaxResult getInfo(@PathVariable("taskDetailId") String taskDetailId)
    {
        return AjaxResult.success(lawTaskDetailService.selectLawTaskDetailById(taskDetailId));
    }

    /**
     * 新增移动执法任务 - 明细
     */
    @PreAuthorize("@ss.hasPermi('system:detail:add')")
    @Log(title = "移动执法任务 - 明细", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody LawTaskDetail lawTaskDetail)
    {
        return toAjax(lawTaskDetailService.insertLawTaskDetail(lawTaskDetail));
    }

    /**
     * 修改移动执法任务 - 明细
     */
    @PreAuthorize("@ss.hasPermi('system:detail:edit')")
    @Log(title = "移动执法任务 - 明细", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody LawTaskDetail lawTaskDetail)
    {
        return toAjax(lawTaskDetailService.updateLawTaskDetail(lawTaskDetail));
    }

    /**
     * 删除移动执法任务 - 明细
     */
    @PreAuthorize("@ss.hasPermi('system:detail:remove')")
    @Log(title = "移动执法任务 - 明细", businessType = BusinessType.DELETE)
	@DeleteMapping("/{taskDetailIds}")
    public AjaxResult remove(@PathVariable String[] taskDetailIds)
    {
        return toAjax(lawTaskDetailService.deleteLawTaskDetailByIds(taskDetailIds));
    }
}
