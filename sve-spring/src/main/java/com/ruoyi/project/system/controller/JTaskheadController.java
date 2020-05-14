package com.ruoyi.project.system.controller;

import java.util.List;

import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.framework.security.LoginUser;
import io.swagger.annotations.ApiOperation;
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
import com.ruoyi.project.system.domain.JTaskhead;
import com.ruoyi.project.system.service.IJTaskheadService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 任务Controller
 * 
 * @author ruoyi
 * @date 2020-05-12
 */
@RestController
@RequestMapping("/system/taskhead")
public class JTaskheadController extends BaseController
{
    @Autowired
    private IJTaskheadService jTaskheadService;

    /**
     * 查询任务列表
     */
    @PreAuthorize("@ss.hasPermi('system:taskhead:list')")
    @GetMapping("/list")
    public TableDataInfo list(JTaskhead jTaskhead)
    {
        startPage();
        List<JTaskhead> list = jTaskheadService.selectJTaskheadList(jTaskhead);
        return getDataTable(list);
    }

    /**
     * 导出任务列表
     */
    @PreAuthorize("@ss.hasPermi('system:taskhead:export')")
    @Log(title = "任务", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(JTaskhead jTaskhead)
    {
        List<JTaskhead> list = jTaskheadService.selectJTaskheadList(jTaskhead);
        ExcelUtil<JTaskhead> util = new ExcelUtil<JTaskhead>(JTaskhead.class);
        return util.exportExcel(list, "taskhead");
    }

    /**
     * 获取任务详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:taskhead:query')")
    @GetMapping(value = "/{ztaskhid}")
    public AjaxResult getInfo(@PathVariable("ztaskhid") String ztaskhid)
    {
        return AjaxResult.success(jTaskheadService.selectJTaskheadById(ztaskhid));
    }

    /**
     * 新增任务
     */
    @PreAuthorize("@ss.hasPermi('system:taskhead:add')")
    @Log(title = "任务", businessType = BusinessType.INSERT)
    @PostMapping("/task")
    public AjaxResult add(@RequestBody JTaskhead jTaskhead)
    {
        return toAjax(jTaskheadService.insertJTaskhead(jTaskhead));
    }

    // 新增移动执法任务主 post -> /system/taskhead --------------886---------
    @ApiOperation("新增执法任务")
    @PostMapping
    public AjaxResult increase(@RequestBody JTaskhead jTaskhead) {
      try{
          jTaskheadService.insertJTaskheadAndJTaskDetail(jTaskhead);

      }catch (Exception e) {

          return AjaxResult.error(e.getMessage());
      }
        return AjaxResult.success("添加任务成功");
    }

    /**
     * 修改任务
     */
    @PreAuthorize("@ss.hasPermi('system:taskhead:edit')")
    @Log(title = "任务", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody JTaskhead jTaskhead)
    {
        return toAjax(jTaskheadService.updateJTaskhead(jTaskhead));
    }

    /**
     * 删除任务
     */
    @PreAuthorize("@ss.hasPermi('system:taskhead:remove')")
    @Log(title = "任务", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ztaskhids}")
    public AjaxResult remove(@PathVariable String[] ztaskhids)
    {
        return toAjax(jTaskheadService.deleteJTaskheadByIds(ztaskhids));
    }
}
