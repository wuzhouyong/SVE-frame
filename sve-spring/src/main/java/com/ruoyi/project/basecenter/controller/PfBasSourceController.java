package com.ruoyi.project.basecenter.controller;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.ruoyi.common.constant.HttpStatus;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.basecenter.domain.PfBasSource;
import com.ruoyi.project.basecenter.service.IPfBasSourceService;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
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
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 【请填写功能名称】Controller
 * 
 * @author ruoyi
 * @date 2020-05-09
 */
@RestController
@RequestMapping("/system/source")
public class PfBasSourceController extends BaseController
{
    @Autowired
    private IPfBasSourceService pfBasSourceService;
    /**
     * 查询【请填写功能名称】列表  -----------886------------
     */

    @ApiOperation("获取30家污染源")
    @GetMapping("/listPfBasSource")
    public TableDataInfo listPfBasSource(String fullName) {
        startPage();

        if(StringUtils.isEmpty(fullName)){
            TableDataInfo rspData = new TableDataInfo();
            rspData.setCode(HttpStatus.SUCCESS);
            rspData.setRows(null);
            rspData.setTotal(0);
            return rspData;
        }

        List<PfBasSource> list = pfBasSourceService.selectPfBasSourceListByFullName(fullName);
        return getDataTable(list);
    }

    /**
     * 查询【请填写功能名称】列表
     */

    @PreAuthorize("@ss.hasPermi('system:source:query')")
    @GetMapping("/list")
    public TableDataInfo list(PfBasSource pfBasSource) {
        startPage();
        List<PfBasSource> list = pfBasSourceService.selectPfBasSourceList(pfBasSource);
        return getDataTable(list);
    }

    /**
     * 导出【请填写功能名称】列表
     */

    @PreAuthorize("@ss.hasPermi('system:source:export')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(PfBasSource pfBasSource)
    {
        List<PfBasSource> list = pfBasSourceService.selectPfBasSourceList(pfBasSource);
        ExcelUtil<PfBasSource> util = new ExcelUtil<PfBasSource>(PfBasSource.class);
        return util.exportExcel(list, "source");
    }

    /**
     * 获取【请填写功能名称】详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:source:query')")
    @GetMapping(value = "/{sourceId}")
    public AjaxResult getInfo(@PathVariable("sourceId") String sourceId)
    {
        return AjaxResult.success(pfBasSourceService.selectPfBasSourceById(sourceId));
    }

    /**
     * 新增【请填写功能名称】
     */
    @PreAuthorize("@ss.hasPermi('system:source:add')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PfBasSource pfBasSource)
    {
        return toAjax(pfBasSourceService.insertPfBasSource(pfBasSource));
    }

    /**
     * 修改【请填写功能名称】
     */
    @PreAuthorize("@ss.hasPermi('system:source:edit')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PfBasSource pfBasSource)
    {
        return toAjax(pfBasSourceService.updatePfBasSource(pfBasSource));
    }

    /**
     * 删除【请填写功能名称】
     */
    @PreAuthorize("@ss.hasPermi('system:source:remove')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.DELETE)
	@DeleteMapping("/{sourceIds}")
    public AjaxResult remove(@PathVariable String[] sourceIds)
    {
        return toAjax(pfBasSourceService.deletePfBasSourceByIds(sourceIds));
    }
}
