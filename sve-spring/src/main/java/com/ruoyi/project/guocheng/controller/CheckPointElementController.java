package com.ruoyi.project.guocheng.controller;

import com.ruoyi.common.domain.ResultEntity;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.aspectj.lang.annotation.DataScope;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.guocheng.domain.CPElementEntity;
import com.ruoyi.project.guocheng.domain.GCNameIdEntity;
import com.ruoyi.project.guocheng.service.CheckPointElementService;
import org.apache.commons.collections4.map.LinkedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/guocheng/cp_element")
public class CheckPointElementController extends BaseController{

    @Autowired
    private CheckPointElementService cpElementService;

    @PostMapping("/list")
    @DataScope(regionAlias = "s")
    public TableDataInfo list(@RequestBody CPElementEntity entity)
    {
        startPage();
        if (!StringUtils.isEmpty(SecurityUtils.getLoginUser().getUser().getSourceId())){
            entity.setSourceId(SecurityUtils.getLoginUser().getUser().getSourceId());
        }
        List<CPElementEntity> list = cpElementService.selectCPElement(entity);
        return getDataTable(list);
    }

    @PostMapping("/dropdown")
    public ResultEntity cpDropdown(@RequestBody CPElementEntity entity) {
        if (!StringUtils.isEmpty(SecurityUtils.getLoginUser().getUser().getSourceId())){
            entity.setSourceId(SecurityUtils.getLoginUser().getUser().getSourceId());
        }
        List<CPElementEntity> list = cpElementService.selectCPElement(entity);
        List<Map<String, Object>> mapList = new ArrayList<>();
        for (CPElementEntity item : list) {
            Map<String, Object> map = new LinkedMap<>();
            map.put("value", item.getCpElemId());
            map.put("text", item.getEleDesc());
            map.put("type", item.getEleTypeId());
            mapList.add(map);
        }
        return ResultEntity.success(mapList);
    }

    @PostMapping("/ele_type")
    public TableDataInfo ele_type(@RequestBody GCNameIdEntity entity)
    {
        startPage();
        List<GCNameIdEntity> list = cpElementService.selectElement(entity);
        return getDataTable(list);
    }

    @PreAuthorize("@ss.hasPermi('guocheng:element:add')")
    @Log(title = "因子新增", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult add(@RequestBody CPElementEntity entity)
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

    @PreAuthorize("@ss.hasPermi('guocheng:element:edit')")
    @Log(title = "因子编辑", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    public AjaxResult edit(@RequestBody CPElementEntity entity)
    {
        entity.setUpdatedBy(SecurityUtils.getUsername());
        return toAjax(cpElementService.updateCPElement(entity));
    }

    @PreAuthorize("@ss.hasPermi('guocheng:element:del')")
    @Log(title = "因子删除", businessType = BusinessType.UPDATE)
    @DeleteMapping("/{cpElemIds}")
    public AjaxResult delete(@PathVariable String[] cpElemIds)
    {
        return toAjax(cpElementService.deleteCPElement(cpElemIds));
    }

}
