package com.ruoyi.project.outwater.controller;

import com.ruoyi.common.domain.ResultEntity;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.PageDomain;
import com.ruoyi.framework.web.page.TableSupport;
import com.ruoyi.project.outwater.domain.BasCheckPointElement;
import com.ruoyi.project.outwater.domain.entity.BasElementEntity;
import com.ruoyi.project.outwater.domain.entity.NameAndIdEntity;
import com.ruoyi.project.outwater.service.BasCheckPointElementService;
import org.apache.commons.collections4.map.LinkedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/outwater/cp_element")
public class BasCheckPointElementController extends BaseController {

    @Autowired
    private BasCheckPointElementService checkPointElementService;

    /**
     * 新增因子
     */
    @PreAuthorize("@ss.hasPermi('outwater:element:add')")
    @Log(title = "因子新增", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult add(@RequestBody BasCheckPointElement entity) {
        int has = checkPointElementService.repeatCheck(entity);
        int res = 0;
        if (has == 0) {
            entity.setCreatedBy(SecurityUtils.getUsername());
            entity.setUpdatedBy(SecurityUtils.getUsername());
            res = checkPointElementService.insertCheckPointElement(entity);
        }
        return toAjax(res);
    }

    /**
     * 编辑因子
     */
    @PreAuthorize("@ss.hasPermi('outwater:element:edit')")
    @Log(title = "因子编辑", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    public AjaxResult edit(@RequestBody BasCheckPointElement entity) {
        entity.setUpdatedBy(SecurityUtils.getUsername());
        return toAjax(checkPointElementService.updateCheckPointElement(entity));
    }

    /**
     * 删除因子
     */
    @PreAuthorize("@ss.hasPermi('outwater:element:remove')")
    @Log(title = "因子删除", businessType = BusinessType.UPDATE)
    @DeleteMapping("/{cpElementIds}")
    public ResultEntity delete(@PathVariable String[] cpElementIds) {
        //entity.setUpdatedBy(SecurityUtils.getUsername());
        String cpElementId = "";
        for (int i = 0; i < cpElementIds.length; i++) {
            if (i == cpElementIds.length - 1) {
                cpElementId += "'" + cpElementIds[i] + "'";
            } else {
                cpElementId += "'" + cpElementIds[i] + "',";
            }
        }
        int res = checkPointElementService.deleteCheckPointElementById(cpElementId, SecurityUtils.getUsername());
        return (res > 0 ? ResultEntity.success("数采删除成功") : ResultEntity.error("数采删除失败"));

    }

    @PostMapping("/elementIdNameList")
    public ResultEntity elementIdNameList(@RequestBody BasElementEntity entity) {

        List<NameAndIdEntity> list = checkPointElementService.selectElementIdNameListList(entity.getElemTypeDesc(), entity.getElemTypeId());
        return ResultEntity.success(list);
    }

    @PreAuthorize("@ss.hasPermi('outwater:element:list')")
    @PostMapping("/list")
    public ResultEntity list(@RequestBody BasCheckPointElement entity) {
        PageDomain pageDomain = TableSupport.buildPageRequest();
        int pageNum = pageDomain.getPageNum();
        if (!StringUtils.isEmpty(SecurityUtils.getLoginUser().getUser().getSourceId())) {
            entity.setSourceId(SecurityUtils.getLoginUser().getUser().getSourceId());
        }
        List<BasCheckPointElement> list = checkPointElementService.selectCheckPointElementList(entity);
        int count = checkPointElementService.getTotal(entity);
        return getDataList(list, pageNum, count);
    }

    @PostMapping("/dropdown")
    public ResultEntity cpDropdown(@RequestBody BasCheckPointElement entity) {
        if (!StringUtils.isEmpty(SecurityUtils.getLoginUser().getUser().getSourceId())) {
            entity.setSourceId(SecurityUtils.getLoginUser().getUser().getSourceId());
        }
        List<BasCheckPointElement> list = checkPointElementService.selectCheckPointElementList(entity);
        List<Map<String, Object>> mapList = new ArrayList<>();
        for (BasCheckPointElement item : list) {
            Map<String, Object> map = new LinkedMap<>();
            map.put("value", item.getCpElementId());
            map.put("text", item.getElemName());
            mapList.add(map);
        }
        return ResultEntity.success(mapList);
    }
}
