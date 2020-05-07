package com.ruoyi.project.system.controller;

import java.util.List;

import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.system.domain.LawTaskDetail;
import com.ruoyi.project.system.domain.SysRole;
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
import com.ruoyi.project.system.domain.LawTaskHead;
import com.ruoyi.project.system.service.ILawTaskHeadService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 移动执法任务主Controller
 * 
 * @author ruoyi
 * @date 2020-05-07
 */
@RestController
@RequestMapping("/system/head")
public class LawTaskHeadController extends BaseController
{
    @Autowired
    private ILawTaskHeadService lawTaskHeadService;
    /**
     * 查询移动执法任务主列表
     */
    @PreAuthorize("@ss.hasPermi('system:head:list')")
    @GetMapping("/list")
    public TableDataInfo list(LawTaskHead lawTaskHead) {
        startPage();
        List<LawTaskHead> list = lawTaskHeadService.selectLawTaskHeadList(lawTaskHead);
        return getDataTable(list);
    }

    /**
     * 导出移动执法任务主列表
     */
    @PreAuthorize("@ss.hasPermi('system:head:export')")
    @Log(title = "移动执法任务主", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(LawTaskHead lawTaskHead)
    {
        List<LawTaskHead> list = lawTaskHeadService.selectLawTaskHeadList(lawTaskHead);
        ExcelUtil<LawTaskHead> util = new ExcelUtil<LawTaskHead>(LawTaskHead.class);
        return util.exportExcel(list, "head");
    }

    /**
     * 获取移动执法任务主详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:head:query')")
    @GetMapping(value = "/{taskHeadId}")
    public AjaxResult getInfo(@PathVariable("taskHeadId") String taskHeadId)
    {
        return AjaxResult.success(lawTaskHeadService.selectLawTaskHeadById(taskHeadId));
    }

    /**
     * 新增移动执法任务主
     */
    @PreAuthorize("@ss.hasPermi('system:head:add')")
    @Log(title = "移动执法任务主", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody LawTaskHead lawTaskHead)
    {
        return toAjax(lawTaskHeadService.insertLawTaskHead(lawTaskHead));
    }

    /**
     * 修改移动执法任务主
     */
    @PreAuthorize("@ss.hasPermi('system:head:edit')")
    @Log(title = "移动执法任务主", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody LawTaskHead lawTaskHead)
    {
        return toAjax(lawTaskHeadService.updateLawTaskHead(lawTaskHead));
    }

    /**
     * 删除移动执法任务主
     */
    @PreAuthorize("@ss.hasPermi('system:head:remove')")
    @Log(title = "移动执法任务主", businessType = BusinessType.DELETE)
	@DeleteMapping("/{taskHeadIds}")
    public AjaxResult remove(@PathVariable String[] taskHeadIds){
        return toAjax(lawTaskHeadService.deleteLawTaskHeadByIds(taskHeadIds));
    }

}
