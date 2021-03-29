package com.ruoyi.project.lingxing.controller;

import com.ruoyi.common.domain.ResultEntity;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.aspectj.lang.annotation.DataScope;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.enterprise.domain.PfBasElementRef;
import com.ruoyi.project.enterprise.service.PfBasElementRefService;
import com.ruoyi.project.lingxing.domain.LXCPElementEntity;
import com.ruoyi.project.lingxing.domain.LXCPElementModel;
import com.ruoyi.project.lingxing.domain.LXNameIdEntity;
import com.ruoyi.project.lingxing.service.LXCheckPointElementService;
import org.apache.commons.collections4.map.LinkedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/lingxing/cp_element")
public class LXCheckPointElementController extends BaseController{

    @Autowired
    private LXCheckPointElementService cpElementService;

    @Autowired
    private PfBasElementRefService elementRefService;

    @PostMapping("/list")
    @DataScope(regionAlias = "s")
    public TableDataInfo list(@RequestBody LXCPElementEntity entity)
    {
        startPage();
        if (!StringUtils.isEmpty(SecurityUtils.getLoginUser().getUser().getSourceId())){
            entity.setSourceId(SecurityUtils.getLoginUser().getUser().getSourceId());
        }
        List<LXCPElementEntity> list = cpElementService.selectCPElement(entity);
        return getDataTable(list);
    }

    @PostMapping("/dropdown")
    public ResultEntity cpDropdown(@RequestBody LXCPElementEntity entity) {
        if (!StringUtils.isEmpty(SecurityUtils.getLoginUser().getUser().getSourceId())){
            entity.setSourceId(SecurityUtils.getLoginUser().getUser().getSourceId());
        }
        List<LXCPElementEntity> list = cpElementService.selectCPElement(entity);
        List<Map<String, Object>> mapList = new ArrayList<>();
        for (LXCPElementEntity item : list) {
            Map<String, Object> map = new LinkedMap<>();
            map.put("value", item.getCpElemId());
            map.put("text", item.getEleDesc());
            mapList.add(map);
        }
        return ResultEntity.success(mapList);
    }

    @PostMapping("/ele_type")
    public TableDataInfo ele_type(@RequestBody LXNameIdEntity entity)
    {
        startPage();
        List<LXNameIdEntity> list = cpElementService.selectElement(entity);
        return getDataTable(list);
    }

    @GetMapping("/ele_ref/{cpElemId}")
    public ResultEntity ele_ref(@PathVariable int cpElemId) {
        PfBasElementRef entity = new PfBasElementRef();
        entity.setDstTagHeadId(1003);
        entity.setDstElemId(cpElemId);
        List<PfBasElementRef> list = elementRefService.selectPfBasElementRef(entity);
        LXCPElementModel model = new LXCPElementModel();
        if (list.size() == 1) {
            PfBasElementRef eleRef = list.get(0);
            model.setIsOther(true);
            model.setSrcTagHeadId(eleRef.getSrcTagHeadId());
            model.setSrcCollectId(eleRef.getSrcMN());
            model.setSrcElemId(eleRef.getSrcElemId());
        }
        return ResultEntity.success(model);
    }

    @PreAuthorize("@ss.hasPermi('lingxing:element:add')")
    @Log(title = "因子新增", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult add(@RequestBody LXCPElementModel entity)
    {
        int has = cpElementService.repeatCheck(entity);
        if (has == 0) {
            entity.setElementId(java.util.UUID.randomUUID().toString());
            entity.setCreatedBy(SecurityUtils.getUsername());
            entity.setUpdatedBy(SecurityUtils.getUsername());
            int res = cpElementService.insertCPElement(entity);
            if (res > 0 && entity.getIsOther()) {
                relElement(entity);
            }
            return toAjax(res);
        }
        return AjaxResult.error("因子编码不能重复");
    }

    @PreAuthorize("@ss.hasPermi('lingxing:element:edit')")
    @Log(title = "因子编辑", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    public AjaxResult edit(@RequestBody LXCPElementModel entity)
    {
        entity.setUpdatedBy(SecurityUtils.getUsername());
        int res = cpElementService.updateCPElement(entity);
        if (res > 0) {
            elementRefService.deletePfBasElementRef(1003, entity.getCpElemId());
            if (entity.getIsOther()) {
                relElement(entity);
            }
        }
        return toAjax(res);
    }

    private void relElement(LXCPElementModel entity) {
        PfBasElementRef model = new PfBasElementRef();
        model.setSrcTagHeadId(entity.getSrcTagHeadId());
        model.setSrcMN(entity.getSrcCollectId());
        model.setSrcElemId(entity.getSrcElemId());
        model.setDstTagHeadId(1003);
        model.setDstElemId(entity.getCpElemId());
        model.setCreatedBy(SecurityUtils.getUsername());
        model.setUpdatedBy(SecurityUtils.getUsername());
        elementRefService.insertPfBasElementRef(model);
    }

    @PreAuthorize("@ss.hasPermi('lingxing:element:del')")
    @Log(title = "因子删除", businessType = BusinessType.UPDATE)
    @DeleteMapping("/{cpElemIds}")
    public AjaxResult delete(@PathVariable String[] cpElemIds)
    {
        return toAjax(cpElementService.deleteCPElement(cpElemIds));
    }

}
