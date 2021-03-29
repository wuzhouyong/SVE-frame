package com.ruoyi.project.lwjh.controller;

import com.ruoyi.common.domain.ResultEntity;
import com.ruoyi.common.utils.BreakUpUtils;
import com.ruoyi.framework.aspectj.lang.annotation.DataScope;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.lwjh.domain.SourceListEntity;
import com.ruoyi.project.lwjh.service.LWJHSourceService;
import com.ruoyi.project.outwater.domain.entity.NameAndIdEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lwjh/source")
public class LWJHSourceController extends BaseController {

    @Autowired
    private LWJHSourceService sourceService;

    @PreAuthorize("@ss.hasPermi('networkplan:buiness:add')")
    @Log(title = "污染源新增", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public ResultEntity add(@RequestBody SourceListEntity entity) {
        return sourceService.insertSource(entity);
    }

    @PreAuthorize("@ss.hasPermi('networkplan:buiness:edit')")
    @Log(title = "污染源编辑", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    public ResultEntity edit(@RequestBody SourceListEntity entity) {
        return sourceService.updateSource(entity);
    }

    @PreAuthorize("@ss.hasPermi('networkplan:buiness:remove')")
    @Log(title = "污染源删除", businessType = BusinessType.DELETE)
    @DeleteMapping("/{sourceIds}")
    public ResultEntity remove(@PathVariable String[] sourceIds) {
        return sourceService.removeSource(sourceIds);
    }

    @PostMapping("/list")
    @DataScope(regionAlias = "s")
    public TableDataInfo list(@RequestBody SourceListEntity entity) {
        startPage();
        List<SourceListEntity> sourceList = sourceService.selectSource(entity);
        for (SourceListEntity s : sourceList) {
            s.setPolluteTypes(BreakUpUtils.breakUp(s.getPolluteType()));
        }
        return getDataTable(sourceList);
    }

    @PostMapping("/sourceInfo")
    public ResultEntity sourceInfo(@RequestBody SourceListEntity entity) {
        List<SourceListEntity> list = sourceService.selectSourceInfo(entity);
        if (list != null)
            return ResultEntity.success(list);
        else
            return ResultEntity.error("");
    }

    @PostMapping("/addList")
    @DataScope(regionAlias = "s")
    public ResultEntity addList(@RequestBody SourceListEntity entity) {
        List<NameAndIdEntity> list = sourceService.selectSourceAddList(entity);
        if (list != null)
            return ResultEntity.success(list);
        else
            return ResultEntity.error("");
    }

}
