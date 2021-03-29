package com.ruoyi.project.outwater.controller;

import com.ruoyi.common.domain.ResultEntity;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.aspectj.lang.annotation.DataScope;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.outwater.domain.BasCheckPoint;
import com.ruoyi.project.outwater.domain.DatConstructionEntity;
import com.ruoyi.project.outwater.domain.entity.BasCollectEntity;
import com.ruoyi.project.outwater.domain.entity.CollectCheckPointEntity;
import com.ruoyi.project.outwater.domain.entity.NameAndIdEntity;
import com.ruoyi.project.outwater.service.BasCheckPointService;
import com.ruoyi.project.outwater.service.BasCollectService;
import org.apache.commons.collections4.map.LinkedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/outwater/checkpoint")
public class BasCheckPointController extends BaseController {

    @Autowired
    private BasCheckPointService checkPointService;

    @Autowired
    private BasCollectService collectService;

    /**
     * 新增监测点
     */
    @PreAuthorize("@ss.hasPermi('outwater:point:add')")
    @Log(title = "监测点新增", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult add(@RequestBody CollectCheckPointEntity entity) {
        if (!StringUtils.isEmpty(SecurityUtils.getLoginUser().getUser().getSourceId())) {
            entity.setSourceId(SecurityUtils.getLoginUser().getUser().getSourceId());
            entity.setMn(collectService.getMN().getMn());
        }
        entity.setCollectId(java.util.UUID.randomUUID().toString());
        entity.setCheckPointId(java.util.UUID.randomUUID().toString());
        entity.setCreatedBy(SecurityUtils.getUsername());
        entity.setUpdatedBy(SecurityUtils.getUsername());
        List<BasCollectEntity> list = collectService.checkMN(entity.getMn());
        int c = checkPointService.checkConstruction(entity.getSourceId());

        if (list.size() > 0)
            return AjaxResult.error("该MN码已存在");
        else {
            if (c > 0)
                return toAjax(collectService.insertData(entity));
            else
                return AjaxResult.error("请先到进度登记页面登记施工单位信息");
        }
    }

    @Log(title = "修改数采密码", businessType = BusinessType.INSERT)
    @PostMapping("/modify_pass")
    public AjaxResult modifyPassWord(@RequestBody Map<String,String> param) {
        if (param.get("collectPass") == null) {
            return AjaxResult.error("请选择数采");
        }
        int c = collectService.modifyPassWord(param);
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

    @GetMapping("/mn")
    public AjaxResult getMN() {
        String mn = collectService.getMN().getMn();
        return AjaxResult.success("", mn);
    }

    /**
     * 编辑监测点
     */
    @PreAuthorize("@ss.hasPermi('outwater:point:edit')")
    @Log(title = "监测点编辑", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    public AjaxResult edit(@RequestBody CollectCheckPointEntity entity) {
        if (!StringUtils.isEmpty(SecurityUtils.getLoginUser().getUser().getSourceId())) {
            entity.setSourceId(SecurityUtils.getLoginUser().getUser().getSourceId());
        }
        entity.setUpdatedBy(SecurityUtils.getUsername());
        List<BasCollectEntity> list = collectService.checkMN(entity.getMn());
        if (list.size() > 0) {
            if (!list.get(0).getCollectId().equals(entity.getCollectId()))
                return AjaxResult.error("该MN码已存在");
            else
                return toAjax(collectService.updateData(entity));
        } else {
            return toAjax(collectService.updateData(entity));
        }
    }

    @PreAuthorize("@ss.hasPermi('outwater:point:list')")
    @PostMapping("/list")
    @DataScope(regionAlias = "s")    //  /outwater/checkpoint/list
    public TableDataInfo list(@RequestBody CollectCheckPointEntity entity) {
        startPage();
        if (!StringUtils.isEmpty(SecurityUtils.getLoginUser().getUser().getSourceId())) {
            entity.setSourceId(SecurityUtils.getLoginUser().getUser().getSourceId());
        }
        List<CollectCheckPointEntity> list = checkPointService.selectCheckPointList(entity);
        //int count = checkPointService.getTotal(entity);
        return getDataTable(list);
    }

    @PostMapping("/dropdown")
    public ResultEntity cpDropdown(@RequestBody CollectCheckPointEntity entity) {
        if (!StringUtils.isEmpty(SecurityUtils.getLoginUser().getUser().getSourceId())) {
            entity.setSourceId(SecurityUtils.getLoginUser().getUser().getSourceId());
        }
        List<CollectCheckPointEntity> list = checkPointService.selectCheckPointList(entity);
        List<Map<String, Object>> mapList = new ArrayList<>();
        for (CollectCheckPointEntity item : list) {
            Map<String, Object> map = new LinkedMap<>();
            map.put("value", item.getCollectId());
            map.put("text", item.getCollectName());
            mapList.add(map);
        }
        return ResultEntity.success(mapList);
    }

    /**
     * 删除监测点
     */
    @PreAuthorize("@ss.hasPermi('outwater:point:remove')")
    @Log(title = "监测点删除", businessType = BusinessType.UPDATE)
    @DeleteMapping("/{checkPointIds}")
    public AjaxResult delete(@PathVariable String[] checkPointIds) {
        return toAjax(checkPointService.deleteCheckPointById(checkPointIds));
    }

    @PostMapping("/idNameList")
    public TableDataInfo idNameList(@RequestBody BasCheckPoint entity) {
        if (!StringUtils.isEmpty(SecurityUtils.getLoginUser().getUser().getSourceId())) {
            entity.setSourceId(SecurityUtils.getLoginUser().getUser().getSourceId());
        }
        List<NameAndIdEntity> list = checkPointService.selectCheckPointIdNameList(entity.getCheckPointName(), entity.getCheckPointId(), entity.getSourceId());
        return getDataTable(list);
    }

    @GetMapping("/has_datCon")
    public ResultEntity has_datCon(DatConstructionEntity entity) {
        if (!StringUtils.isEmpty(SecurityUtils.getLoginUser().getUser().getSourceId())) {
            entity.setSourceId(SecurityUtils.getLoginUser().getUser().getSourceId());
        }
        int isAcc = checkPointService.isExitCon(entity.getSourceId());
        return ResultEntity.success(isAcc > 0);
    }
}
