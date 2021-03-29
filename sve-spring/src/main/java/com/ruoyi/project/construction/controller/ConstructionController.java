package com.ruoyi.project.construction.controller;

import com.ruoyi.common.domain.ResultEntity;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.project.construction.domain.ConstructionEntity;
import com.ruoyi.project.construction.service.IConstructionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.ruoyi.project.common.domain.TaskPrefix.getTagHeadId;

@RestController
@RequestMapping("/{prefix}/construction")
public class ConstructionController extends BaseController {

    @Autowired
    private IConstructionService service;

    /**
     * 施工单位信息
     */
    @GetMapping("/model/{sourceId}")
    public ResultEntity model(@PathVariable String prefix, @PathVariable String sourceId) {
        int tagHeadId = getTagHeadId(prefix);
        return service.getConstructionModel(tagHeadId, sourceId);
    }

    /**
     * 修改施工单位
     */
    @Log(title = "修改施工单位", businessType = BusinessType.UPDATE)
    @PostMapping("/update")
    public ResultEntity update(@PathVariable String prefix, @RequestBody ConstructionEntity entity) {
        int tagHeadId = getTagHeadId(prefix);
        entity.setTagHeadId(tagHeadId);
        return service.mergeConstruction(entity);
    }

    /**
     * 联网完成
     */
    @Log(title = "联网完成", businessType = BusinessType.UPDATE)
    @PostMapping("/complete")
    public ResultEntity complete(@PathVariable String prefix, @RequestBody() ConstructionEntity entity) {
        int tagHeadId = getTagHeadId(prefix);
        entity.setTagHeadId(tagHeadId);
        return service.completeConstruction(entity);
    }

    /**
     * 验收成功
     */
    @Log(title = "验收成功", businessType = BusinessType.UPDATE)
    @PostMapping("/accept")
    public ResultEntity accept(@PathVariable String prefix, @RequestBody() ConstructionEntity entity) {
        int tagHeadId = getTagHeadId(prefix);
        entity.setTagHeadId(tagHeadId);
        return service.acceptanceConstruction(entity);
    }

    /**
     * 合同是否存在
     */
    @GetMapping("/has_contract")
    public ResultEntity hasContract(@PathVariable String prefix) {
        int tagHeadId = getTagHeadId(prefix);
        return ResultEntity.success(service.hasContract(tagHeadId));
    }
}
