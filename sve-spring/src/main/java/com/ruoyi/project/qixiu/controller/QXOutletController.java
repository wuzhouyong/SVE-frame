package com.ruoyi.project.qixiu.controller;

import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.aspectj.lang.annotation.DataScope;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.qixiu.domain.QXDeleteEntity;
import com.ruoyi.project.qixiu.domain.QXOutletEntity;
import com.ruoyi.project.qixiu.service.QXOutletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/qixiu/outlet")
public class QXOutletController extends BaseController {

    @Autowired
    private QXOutletService outletService;

    @PreAuthorize("@ss.hasPermi('qixiu:discharge:add')")
    @Log(title = "排放口新增", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult add(@RequestBody QXOutletEntity entity) {
        if (!StringUtils.isEmpty(SecurityUtils.getLoginUser().getUser().getSourceId())) {
            entity.setSourceId(SecurityUtils.getLoginUser().getUser().getSourceId());
        }
        entity.setOutletId(java.util.UUID.randomUUID().toString());
        entity.setUpdatedBy(SecurityUtils.getUsername());
        return toAjax(outletService.insertOutlet(entity));
    }

    @PostMapping("/list")
    @DataScope(regionAlias = "s")
    public TableDataInfo list(@RequestBody QXOutletEntity entity) {
        startPage();
        if (!StringUtils.isEmpty(SecurityUtils.getLoginUser().getUser().getSourceId())) {
            entity.setSourceId(SecurityUtils.getLoginUser().getUser().getSourceId());
        }
        return getDataTable(outletService.selectOutlet(entity));
    }

    @PreAuthorize("@ss.hasPermi('qixiu:discharge:edit')")
    @Log(title = "排放口编辑", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    public AjaxResult edit(@RequestBody QXOutletEntity entity) {
        entity.setUpdatedBy(SecurityUtils.getUsername());
        return toAjax(outletService.updateOutlet(entity));
    }

    @PreAuthorize("@ss.hasPermi('qixiu:discharge:del')")
    @Log(title = "排放口删除", businessType = BusinessType.UPDATE)
    @DeleteMapping("/{outletIds}")
    public AjaxResult delete(@PathVariable String[] outletIds) {
        return toAjax(outletService.deleteOutlet(new QXDeleteEntity(outletIds, SecurityUtils.getUsername())));
    }

}
