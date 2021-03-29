package com.ruoyi.project.zaozhi.controller;

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
import com.ruoyi.project.jinshu.domain.JSCPElementModel;
import com.ruoyi.project.zaozhi.domain.ZZCPElementEntity;
import com.ruoyi.project.zaozhi.domain.ZZCPElementModel;
import com.ruoyi.project.zaozhi.domain.ZZNameIdEntity;
import com.ruoyi.project.zaozhi.service.ZZCheckPointElementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/zaozhi/cp_element")
public class ZZCheckPointElementController extends BaseController {
    @Autowired
    private ZZCheckPointElementService cpElementService;

    @Autowired
    private PfBasElementRefService elementRefService;

    @PostMapping("/list")
    @DataScope(regionAlias = "s")
    public TableDataInfo list(@RequestBody ZZCPElementEntity entity) {
        startPage();
        if (!StringUtils.isEmpty(SecurityUtils.getLoginUser().getUser().getSourceId())) {
            entity.setSourceId(SecurityUtils.getLoginUser().getUser().getSourceId());
        }
        List<ZZCPElementEntity> list = cpElementService.selectCPElement(entity);
        return getDataTable(list);
    }

    @PostMapping("/ele_type")
    public TableDataInfo ele_type(@RequestBody ZZNameIdEntity entity) {
        startPage();
        List<ZZNameIdEntity> list = cpElementService.selectElement(entity);
        return getDataTable(list);
    }

    @GetMapping("/ele_ref/{cpElemId}")
    public ResultEntity ele_ref(@PathVariable int cpElemId) {
        PfBasElementRef entity = new PfBasElementRef();
        entity.setDstTagHeadId(1019);
        entity.setDstElemId(cpElemId);
        List<PfBasElementRef> list = elementRefService.selectPfBasElementRef(entity);
        JSCPElementModel model = new JSCPElementModel();
        if (list.size() == 1) {
            PfBasElementRef eleRef = list.get(0);
            model.setIsOther(true);
            model.setSrcTagHeadId(eleRef.getSrcTagHeadId());
            model.setSrcCollectId(eleRef.getSrcMN());
            model.setSrcElemId(eleRef.getSrcElemId());
        }
        return ResultEntity.success(model);
    }

    @PreAuthorize("@ss.hasPermi('zaozhi:element:add')")
    @Log(title = "因子新增", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult add(@RequestBody ZZCPElementModel entity) {
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

    @PreAuthorize("@ss.hasPermi('zaozhi:element:edit')")
    @Log(title = "因子编辑", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    public AjaxResult edit(@RequestBody ZZCPElementModel entity) {
        entity.setUpdatedBy(SecurityUtils.getUsername());
        int res = cpElementService.updateCPElement(entity);
        if (res > 0) {
            elementRefService.deletePfBasElementRef(1019, entity.getCpElemId());
            if (entity.getIsOther()) {
                relElement(entity);
            }
        }
        return toAjax(res);
    }

    private void relElement(ZZCPElementModel entity) {
        PfBasElementRef model = new PfBasElementRef();
        model.setSrcTagHeadId(entity.getSrcTagHeadId());
        model.setSrcMN(entity.getSrcCollectId());
        model.setSrcElemId(entity.getSrcElemId());
        model.setDstTagHeadId(1019);
        model.setDstElemId(entity.getCpElemId());
        model.setCreatedBy(SecurityUtils.getUsername());
        model.setUpdatedBy(SecurityUtils.getUsername());
        elementRefService.insertPfBasElementRef(model);
    }

    @PreAuthorize("@ss.hasPermi('zaozhi:element:del')")
    @Log(title = "因子删除", businessType = BusinessType.UPDATE)
    @DeleteMapping("/{cpElemIds}")
    public AjaxResult delete(@PathVariable String[] cpElemIds) {
        int res = cpElementService.deleteCPElement(cpElemIds);
        if (res > 0) {
            for (String cpElemId: cpElemIds) {
                elementRefService.deletePfBasElementRef(1019, Integer.parseInt(cpElemId));
            }
        }
        return toAjax(res);
    }


}
