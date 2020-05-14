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
import com.ruoyi.project.system.domain.JTaskdetail;
import com.ruoyi.project.system.service.IJTaskdetailService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 任务明细Controller
 * 
 * @author ruoyi
 * @date 2020-05-12
 */
@RestController
@RequestMapping("/system/taskdetail")
public class JTaskdetailController extends BaseController
{
    @Autowired
    private IJTaskdetailService jTaskdetailService;

    /**
     * 查询 移动执法任务 - 明细 列表
     */

    @PreAuthorize("@ss.hasPermi('system:detail:list')")
    @GetMapping("/list")
    public TableDataInfo list(JTaskdetail JTaskdetail) {
        startPage();
        List<JTaskdetail> list = jTaskdetailService.selectJTaskdetailList(JTaskdetail);
        return getDataTable(list);
    }

    /**
     * 导出任务明细列表
     */
    @PreAuthorize("@ss.hasPermi('system:taskdetail:export')")
    @Log(title = "任务明细", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(JTaskdetail jTaskdetail)
    {
        List<JTaskdetail> list = jTaskdetailService.selectJTaskdetailList(jTaskdetail);
        ExcelUtil<JTaskdetail> util = new ExcelUtil<JTaskdetail>(JTaskdetail.class);
        return util.exportExcel(list, "taskdetail");
    }

    /**
     * 获取任务明细详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:taskdetail:query')")
    @GetMapping(value = "/{ztaskdid}")
    public AjaxResult getInfo(@PathVariable("ztaskdid") Long ztaskdid)
    {
        return AjaxResult.success(jTaskdetailService.selectJTaskdetailById(ztaskdid));
    }

    /**
     * 新增任务明细
     */
    @PreAuthorize("@ss.hasPermi('system:taskdetail:add')")
    @Log(title = "任务明细", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody JTaskdetail jTaskdetail)
    {
        return toAjax(jTaskdetailService.insertJTaskdetail(jTaskdetail));
    }

    /**
     * 修改任务明细
     */
    @PreAuthorize("@ss.hasPermi('system:taskdetail:edit')")
    @Log(title = "任务明细", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody JTaskdetail jTaskdetail)
    {
        return toAjax(jTaskdetailService.updateJTaskdetail(jTaskdetail));
    }

    /**
     * 删除任务明细
     */
    @PreAuthorize("@ss.hasPermi('system:taskdetail:remove')")
    @Log(title = "任务明细", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ztaskdids}")
    public AjaxResult remove(@PathVariable Long[] ztaskdids)
    {
        return toAjax(jTaskdetailService.deleteJTaskdetailByIds(ztaskdids));
    }
}
