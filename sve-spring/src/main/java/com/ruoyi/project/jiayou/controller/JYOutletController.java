package com.ruoyi.project.jiayou.controller;

import com.ruoyi.common.domain.ResultEntity;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.aspectj.lang.annotation.DataScope;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.jiayou.domain.JYDeleteEntity;
import com.ruoyi.project.jiayou.domain.JYOutletEntity;
import com.ruoyi.project.jiayou.service.JYOutletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Generated;

@RestController
@RequestMapping("/jiayou/outlet")
public class JYOutletController extends BaseController {

    @Autowired
    private JYOutletService outletService;

    //    @PreAuthorize("@ss.hasPermi('jiayou:discharge:add')")
    @Log(title = "加油枪新增", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult add(@RequestBody JYOutletEntity entity) {
        if (!StringUtils.isEmpty(SecurityUtils.getLoginUser().getUser().getSourceId())) {
            entity.setSourceId(SecurityUtils.getLoginUser().getUser().getSourceId());
        }
        entity.setOutletId(java.util.UUID.randomUUID().toString().replace("-", ""));
        entity.setUpdatedBy(SecurityUtils.getUsername());
        return toAjax(outletService.insertOutlet(entity));
    }

    @PostMapping("/list")
    @DataScope(regionAlias = "s")
    public TableDataInfo list(@RequestBody JYOutletEntity entity) {
        startPage();
        if (!StringUtils.isEmpty(SecurityUtils.getLoginUser().getUser().getSourceId())) {
            entity.setSourceId(SecurityUtils.getLoginUser().getUser().getSourceId());
        }
        return getDataTable(outletService.selectOutlet(entity));
    }

    //    @PreAuthorize("@ss.hasPermi('jiayou:discharge:edit')")
    @Log(title = "加油枪编辑", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    public AjaxResult edit(@RequestBody JYOutletEntity entity) {
        entity.setUpdatedBy(SecurityUtils.getUsername());
        return toAjax(outletService.updateOutlet(entity));
    }

    //    @PreAuthorize("@ss.hasPermi('jiayou:discharge:del')")
    @Log(title = "加油枪删除", businessType = BusinessType.UPDATE)
    @DeleteMapping("/{outletIds}")
    public AjaxResult delete(@PathVariable String[] outletIds) {
        return toAjax(outletService.deleteOutlet(new JYDeleteEntity(outletIds, SecurityUtils.getUsername())));
    }

    @Log(title = "加油枪列表", businessType = BusinessType.INSERT)
    @GetMapping("/{sourceId}")
    public ResultEntity outletList(@PathVariable String sourceId) {
        String source_id = SecurityUtils.getLoginUser().getUser().getSourceId();
        if (StringUtils.isNotEmpty(source_id)) sourceId = source_id;
        if (StringUtils.isEmpty(sourceId)) return ResultEntity.error("企业不能为空");
        return ResultEntity.success(outletService.selectOutletList(sourceId));
    }

}
