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
import com.ruoyi.project.outwater.domain.DatConstructionEntity;
import com.ruoyi.project.zaozhi.domain.ZZCheckPointEntity;
import com.ruoyi.project.zaozhi.domain.ZZNameIdEntity;
import com.ruoyi.project.zaozhi.service.ZZCheckPointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/zaozhi/checkpoint")
public class ZZCheckPointController extends BaseController {

    @Autowired
    private ZZCheckPointService checkPointService;

    @PreAuthorize("@ss.hasPermi('zaozhi:checkpoint:add')")
    @Log(title = "监测点新增", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult add(@RequestBody ZZCheckPointEntity entity) {
        if (!StringUtils.isEmpty(SecurityUtils.getLoginUser().getUser().getSourceId())) {
            //entity.setBizSourceId(checkPointService.selectBizSourceId(SecurityUtils.getLoginUser().getUser().getSourceId()).getId());
            entity.setSourceId(SecurityUtils.getLoginUser().getUser().getSourceId());
        }
        entity.setCollectId(java.util.UUID.randomUUID().toString());
        entity.setCreatedBy(SecurityUtils.getUsername());
        entity.setUpdatedBy(SecurityUtils.getUsername());
        int c = checkPointService.checkConstruction(entity.getSourceId());
        if (c > 0)
            return toAjax(checkPointService.insertCheckPoint(entity));
        else
            return AjaxResult.error("请先到进度登记页面登记施工单位信息");
    }

    @Log(title = "修改数采密码", businessType = BusinessType.INSERT)
    @PostMapping("/modify_pass")
    public AjaxResult modifyPassWord(@RequestBody Map<String,String> param) {
        if (param.get("collectPass") == null) {
            return AjaxResult.error("请选择数采");
        }
        int c = checkPointService.modifyPassWord(param);
        if (c <= 0){
            return AjaxResult.error("修改密码失败");
        }
        return AjaxResult.success("修改密码成功");
    }

    @GetMapping("/checkcon")
    public ResultEntity checkConstruction() {
        String sourceId = null;
        if (!StringUtils.isEmpty(SecurityUtils.getLoginUser().getUser().getSourceId())) {
            sourceId = SecurityUtils.getLoginUser().getUser().getSourceId();
        }
        int c = checkPointService.checkConstruction(sourceId);
        return ResultEntity.success(c > 0);
    }

    @PostMapping("/list")
    @DataScope(regionAlias = "s")
    public TableDataInfo list(@RequestBody ZZCheckPointEntity entity) {
        startPage();
        if (!StringUtils.isEmpty(SecurityUtils.getLoginUser().getUser().getSourceId())) {
            entity.setSourceId(SecurityUtils.getLoginUser().getUser().getSourceId());
        }
        List<ZZCheckPointEntity> list = checkPointService.selectCheckPoint(entity);
        return getDataTable(list);
    }

    @PreAuthorize("@ss.hasPermi('zaozhi:checkpoint:edit')")
    @Log(title = "监测点编辑", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    public AjaxResult edit(@RequestBody ZZCheckPointEntity entity) {
        entity.setUpdatedBy(SecurityUtils.getUsername());
        return toAjax(checkPointService.updateCheckPoint(entity));
    }

    @PreAuthorize("@ss.hasPermi('zaozhi:checkpoint:del')")
    @Log(title = "监测点删除", businessType = BusinessType.UPDATE)
    @DeleteMapping("/{collectIds}")
    public AjaxResult delete(@PathVariable String[] collectIds) {
        return toAjax(checkPointService.deleteCheckPoint(collectIds));
    }

    @GetMapping("/{sourceId}")
    public TableDataInfo cpList(@PathVariable String sourceId) {
        if (!StringUtils.isEmpty(SecurityUtils.getLoginUser().getUser().getSourceId())) {
            //sourceId = checkPointService.selectBizSourceId(SecurityUtils.getLoginUser().getUser().getSourceId()).getId();
            sourceId = SecurityUtils.getLoginUser().getUser().getSourceId();
        }
        startPage();
        List<ZZNameIdEntity> list = checkPointService.selectCPList(sourceId);
        return getDataTable(list);
    }

    @GetMapping("/has_datCon")
    public ResultEntity has_datCon(DatConstructionEntity entity){
        if (!StringUtils.isEmpty(SecurityUtils.getLoginUser().getUser().getSourceId())){
            entity.setSourceId(SecurityUtils.getLoginUser().getUser().getSourceId());
        }
        int isAcc = checkPointService.isExitCon(entity.getSourceId());
        return ResultEntity.success(isAcc > 0);
    }
}
