package com.ruoyi.project.dgom.controller;

import com.ruoyi.common.domain.ResultEntity;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.dgom.domain.ent_accept.*;
import com.ruoyi.project.dgom.mapper.DataSearchMapper;
import com.ruoyi.project.dgom.mapper.SuperviseTaskMapper;
import com.ruoyi.project.dgom.service.IEntAcceptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dgom/ent_accept")
public class EntAcceptController extends BaseController {
    @Autowired
    private IEntAcceptService service;

    @Autowired
    private SuperviseTaskMapper taskMapper;

    @Autowired
    private DataSearchMapper dataSearchMapper;

    @PostMapping("/list")
    public TableDataInfo getEntAcceptList(@RequestBody EntAcceptParam param) {
        String sourceId = SecurityUtils.getLoginUser().getUser().getSourceId();
        if (sourceId != null) {
            String bizSourceId = dataSearchMapper.checkBizSourceId(sourceId);
            param.setSourceId(Integer.valueOf(bizSourceId));
        }
        if (param.getTownCode() != null) {
            param.setTownId(taskMapper.getOMTownIdByCode(param.getTownCode()));
        }
        startPage();
        List<EntAcceptResult> list = service.getEntAcceptList(param);
        return getDataTable(list);
    }

    @GetMapping("/devices/{applyId}")
    public ResultEntity getEntAcceptDeviceList(@PathVariable String applyId) {
        List<EntAcceptDetailResult> list = service.getEntAcceptDeviceList(applyId);
        return ResultEntity.success(list);
    }

    @PostMapping("/add/head")
    public ResultEntity addEntAcceptHead(@RequestBody EntAcceptEntity entity) {
        String sourceId = SecurityUtils.getLoginUser().getUser().getSourceId();
        if (sourceId != null) {
            String bizSourceId = dataSearchMapper.checkBizSourceId(sourceId);
            entity.setSourceId(Integer.valueOf(bizSourceId));
        }
        int res = service.addEntAcceptHead(entity);
        if (res > 0) {
            return ResultEntity.success("验收备案添加成功");
        }
        return ResultEntity.error("验收备案添加失败");
    }

    @PostMapping("/add/device")
    public ResultEntity addEntAcceptDetail(@RequestBody EntAcceptDetailEntity entity) {
        int res = service.addEntAcceptDetail(entity);
        if (res > 0) {
            return ResultEntity.success("验收备案设备添加成功");
        }
        return ResultEntity.error("验收备案设备添加失败");
    }

    @PostMapping("/edit/head")
    public ResultEntity editEntAcceptHead(@RequestBody EntAcceptEntity entity) {
        int res = service.editEntAcceptHead(entity);
        if (res > 0) {
            return ResultEntity.success("验收备案修改成功");
        }
        return ResultEntity.error("验收备案修改失败");
    }

    @PostMapping("/edit/device")
    public ResultEntity editEntAcceptDetail(@RequestBody EntAcceptDetailEntity entity) {
        int res = service.editEntAcceptDetail(entity);
        if (res > 0) {
            return ResultEntity.success("验收备案设备修改成功");
        }
        return ResultEntity.error("验收备案设备修改失败");
    }

    @PutMapping("/submit/{applyId}")
    public ResultEntity submitEntAccept(@PathVariable String applyId) {
        return service.submitEntAccept(applyId);
    }

    @DeleteMapping("/delete/head/{applyId}")
    public ResultEntity deleteEntAccept(@PathVariable String applyId) {
        return service.deleteEntAccept(applyId);
    }

    @DeleteMapping("/delete/device/{detailId}")
    public ResultEntity deleteEntAcceptDetail(@PathVariable String detailId) {
        return service.deleteEntAcceptDetail(detailId);
    }

    @PostMapping("/approval")
    public ResultEntity approval(@RequestBody EntApprovalEntity entity) {
        return service.approval(entity);
    }
}
