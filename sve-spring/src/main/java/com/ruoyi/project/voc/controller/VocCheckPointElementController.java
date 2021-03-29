package com.ruoyi.project.voc.controller;

import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.aspectj.lang.annotation.DataScope;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.voc.domain.VocCPElementEntity;
import com.ruoyi.project.voc.domain.VocNameIdEntity;
import com.ruoyi.project.voc.service.VocCheckPointElementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/voc/cp_element")
public class VocCheckPointElementController extends BaseController{

    @Autowired
    private VocCheckPointElementService cpElementService;

    @PostMapping("/list")
    @DataScope(regionAlias = "s")
    public TableDataInfo list(@RequestBody VocCPElementEntity entity)
    {
        startPage();
        if (!StringUtils.isEmpty(SecurityUtils.getLoginUser().getUser().getSourceId())){
            entity.setSourceId(SecurityUtils.getLoginUser().getUser().getSourceId());
        }
        List<VocCPElementEntity> list = cpElementService.selectCPElement(entity);
        return getDataTable(list);
    }

    @PostMapping("/ele_type")
    public TableDataInfo ele_type(@RequestBody VocNameIdEntity entity)
    {
        startPage();
        List<VocNameIdEntity> list = cpElementService.selectElement(entity);
        return getDataTable(list);
    }

    @PreAuthorize("@ss.hasPermi('voc:element:add')")
    @Log(title = "因子新增", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult add(@RequestBody VocCPElementEntity entity)
    {
        int has = cpElementService.repeatCheck(entity);
        if (has == 0) {
            entity.setElementId(java.util.UUID.randomUUID().toString());
            entity.setCreatedBy(SecurityUtils.getUsername());
            entity.setUpdatedBy(SecurityUtils.getUsername());
            return toAjax(cpElementService.insertCPElement(entity));
        }
        return AjaxResult.error("因子编码不能重复");
    }

    @PreAuthorize("@ss.hasPermi('voc:element:edit')")
    @Log(title = "因子编辑", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    public AjaxResult edit(@RequestBody VocCPElementEntity entity)
    {
        entity.setUpdatedBy(SecurityUtils.getUsername());
        return toAjax(cpElementService.updateCPElement(entity));
    }

    @PreAuthorize("@ss.hasPermi('voc:element:del')")
    @Log(title = "因子删除", businessType = BusinessType.UPDATE)
    @DeleteMapping("/{cpElemIds}")
    public AjaxResult delete(@PathVariable String[] cpElemIds)
    {
        return toAjax(cpElementService.deleteCPElement(cpElemIds));
    }

}
