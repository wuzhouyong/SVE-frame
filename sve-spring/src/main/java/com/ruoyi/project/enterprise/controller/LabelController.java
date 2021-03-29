package com.ruoyi.project.enterprise.controller;

import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.enterprise.domain.label.LabelDetail;
import com.ruoyi.project.enterprise.domain.label.LabelHead;
import com.ruoyi.project.enterprise.domain.label.LabelSourceEntity;
import com.ruoyi.project.enterprise.service.LabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/label")
public class LabelController extends BaseController {
    @Autowired
    private LabelService service;

    @GetMapping("/by_user")
    public AjaxResult byUser() {
        String username = SecurityUtils.getLoginUser().getUsername();
        List<LabelHead> list = service.getLabelListByUser(username);
        List<LabelHead> result = list.stream().filter(p -> p.getParentId() == null).collect(Collectors.toList());
        List<LabelHead> secondList = list.stream().filter(p -> p.getParentId() != null).collect(Collectors.toList());
        for (LabelHead head : result) {
            List<LabelHead> children = secondList.stream().filter(p -> p.getParentId().equals(head.getLabelHeadId())).collect(Collectors.toList());
            if (children.size() > 0) head.setChildren(children);
        }
        return AjaxResult.success(result);
    }

    @PostMapping("/source_list")
    public TableDataInfo sourceList(@RequestBody Map<String, String> param) {
        startPage();
        param.put("username", SecurityUtils.getLoginUser().getUsername());
        List<LabelSourceEntity> list = service.getSourceList(param);
        return getDataTable(list);
    }

    @PostMapping("/source_list_by_label")
    public TableDataInfo sourceListByLabel(@RequestBody Map<String, String> param) {
        startPage();
        param.put("username", SecurityUtils.getLoginUser().getUsername());
        List<LabelSourceEntity> list = service.getSourceListByLabel(param);
        return getDataTable(list);
    }

    @PostMapping("/add")
    public AjaxResult addLabel(@RequestBody LabelHead model) {
        if (StringUtils.isEmpty(model.getLabelHeadName())) {
            return AjaxResult.error("标签名称不能为空");
        }
        if (model.getParentId() != null && !service.existsTopLevel(model.getParentId())) {
            return AjaxResult.error("不允许嵌套标签");
        }
        model.setUserName(SecurityUtils.getLoginUser().getUsername());
        int res = service.addLabelHead(model);
        if (res > 0) {
            return AjaxResult.success("标签添加成功");
        }
        return AjaxResult.error("标签添加失败");
    }

    @PostMapping("/edit")
    public AjaxResult editLabel(@RequestBody LabelHead model) {
        if (StringUtils.isEmpty(model.getLabelHeadName())) {
            return AjaxResult.error("标签名称不能为空");
        }
        int res = service.editLabelHead(model);
        if (res > 0) {
            return AjaxResult.success("标签修改成功");
        }
        return AjaxResult.error("标签修改失败");
    }

    @DeleteMapping("/remove/{labelHeadId}")
    public AjaxResult removeLabel(@PathVariable int labelHeadId) {
        if (service.hasChildren(labelHeadId)) {
            return AjaxResult.error("标签删除失败:存在子标签");
        }
        int res = service.removeLabelHead(labelHeadId);
        if (res > 0) {
            return AjaxResult.success("标签删除成功");
        }
        return AjaxResult.error("标签删除失败");
    }

    @Transactional
    @PostMapping("/relation_source/{labelHeadId}")
    public AjaxResult relationSource(@PathVariable int labelHeadId, @RequestBody List<String> sourceIds) {
        if (sourceIds.size() > 0) {
            for (String sourceId : sourceIds) {
                LabelDetail model = new LabelDetail();
                model.setLabelHeadId(labelHeadId);
                model.setSourceId(sourceId);
                service.addLabelDetail(model);
            }
            return AjaxResult.success("关联企业成功");
        }
        return AjaxResult.error("关联企业失败");
    }

    @Transactional
    @PostMapping("/unrelation_source/{labelHeadId}")
    public AjaxResult unrelationSource(@PathVariable int labelHeadId, @RequestBody List<String> sourceIds) {
        if (sourceIds.size() > 0) {
            for (String sourceId : sourceIds) {
                LabelDetail model = new LabelDetail();
                model.setLabelHeadId(labelHeadId);
                model.setSourceId(sourceId);
                service.removeLabelDetail(model);
            }
            return AjaxResult.success("取消关联企业成功");
        }
        return AjaxResult.error("取消关联企业失败");
    }

    @Transactional
    @PostMapping("/relation_label/{sourceId}")
    public AjaxResult relationLabel(@PathVariable String sourceId, @RequestBody List<Integer> labelHeadIds) {
        if (labelHeadIds.size() > 0) {
            for (int labelHeadId : labelHeadIds) {
                LabelDetail model = new LabelDetail();
                model.setLabelHeadId(labelHeadId);
                model.setSourceId(sourceId);
                service.addLabelDetail(model);
            }
            return AjaxResult.success("关联标签成功");
        }
        return AjaxResult.error("关联标签失败");
    }

    @Transactional
    @PostMapping("/unrelation_label/{sourceId}")
    public AjaxResult unrelationLabel(@PathVariable String sourceId, @RequestBody List<Integer> labelHeadIds) {
        if (labelHeadIds.size() > 0) {
            for (int labelHeadId : labelHeadIds) {
                LabelDetail model = new LabelDetail();
                model.setLabelHeadId(labelHeadId);
                model.setSourceId(sourceId);
                service.removeLabelDetail(model);
            }
            return AjaxResult.success("取消关联标签成功");
        }
        return AjaxResult.error("取消关联标签失败");
    }
}
