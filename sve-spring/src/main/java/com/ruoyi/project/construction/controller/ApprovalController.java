package com.ruoyi.project.construction.controller;

import com.ruoyi.common.domain.ResultEntity;
import com.ruoyi.framework.aspectj.lang.annotation.DataScope;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.construction.domain.AcceptParam;
import com.ruoyi.project.construction.domain.AcceptRecordEntity;
import com.ruoyi.project.construction.domain.ConstructionParam;
import com.ruoyi.project.construction.domain.ConstructionResult;
import com.ruoyi.project.construction.service.IConstructionService;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/construction")
public class ApprovalController extends BaseController {

    @Autowired
    private IConstructionService service;

    @PostMapping("/list")
    @DataScope(regionAlias = "s")
    public TableDataInfo list(@RequestBody ConstructionParam param) {
        startPage();
        List<ConstructionResult> list = service.getConstructionList(param);
        return getDataTable(list);
    }

    @GetMapping("/accept_record/{tagHeadId}/{sourceId}")
    public ResultEntity getAcceptRecords(@PathVariable String tagHeadId, @PathVariable String sourceId) {
        List<Map> list = service.getAcceptReturnList(tagHeadId, sourceId);
        return ResultEntity.success(list);
    }

    @PostMapping("/confirm")
    @Log(title = "施工验收确认", businessType = BusinessType.UPDATE)
    public ResultEntity confirm(@RequestBody AcceptParam param, @RequestBody AcceptRecordEntity entity) {
        return service.confirmConstruction(param, entity);
    }

    @PostMapping("/retreat")
    @Log(title = "施工验收退回", businessType = BusinessType.UPDATE)
    public ResultEntity retreat(@RequestBody AcceptParam param, @RequestBody AcceptRecordEntity entity) {
        return service.retreatConstruction(param, entity);
    }
}
