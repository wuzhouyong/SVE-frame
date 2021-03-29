package com.ruoyi.project.dgom.controller;

import com.ruoyi.common.domain.ResultEntity;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.dgom.domain.DevApplyDetail;
import com.ruoyi.project.dgom.domain.DevApplyHead;
import com.ruoyi.project.dgom.mapper.DataSearchMapper;
import com.ruoyi.project.dgom.mapper.SuperviseTaskMapper;
import com.ruoyi.project.dgom.service.DevChangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;


/**
 * 【设备维修】Controller
 * *
 *
 * @author ruoyi
 * @date 2020-08-25
 */
@RestController
@RequestMapping("/dgom/device_replace")
public class DevChangeController extends BaseController {

    @Autowired
    private DevChangeService service;

    @Autowired
    private SuperviseTaskMapper taskMapper;

    @Autowired
    private DataSearchMapper dataSearchMapper;

    // 设备维修停用列表  /dgom/device_replace/list 中心端不显示状态为1的
    @PostMapping("/list")
    public TableDataInfo selectDevChangeApplyList(@RequestBody DevApplyHead entity) {
        String sourceId = SecurityUtils.getLoginUser().getUser().getSourceId();
        if (sourceId != null) {
            String bizSourceId = dataSearchMapper.checkBizSourceId(sourceId);
            entity.setSourceId(bizSourceId);
            entity.setIsAcc(0);
        } else {
            entity.setIsAcc(1);
        }
        if (entity.getTownCode() != null) {
            entity.setTownId(String.valueOf(taskMapper.getOMTownIdByCode(entity.getTownCode())));
        }
        startPage();
        List<DevApplyHead> list = service.selectDevChangeApplyList(entity);
        return getDataTable(list);
    }

    // 当前申请下的排放口设备列表  /dgom/device_replace/detail_list/{applyid}
    @GetMapping("/detail_list/{applyid}")
    public ResultEntity selectDevApplyDetailList(@PathVariable String applyid) {
        List<DevApplyDetail> list = service.selectDevApplyDetailList(applyid);
        return ResultEntity.success(list);
    }

    // 设备维修停新增编辑  /dgom/device_replace/add 新增状态1，只有在1的状态下才能编辑，保存/编辑之后状态变为2
    @PostMapping("/add")
    public ResultEntity insertDevChangeApply(@Validated @RequestBody DevApplyHead entity) {
        String sourceId = SecurityUtils.getLoginUser().getUser().getSourceId();
        if (sourceId != null) {
            String bizSourceId = dataSearchMapper.checkBizSourceId(sourceId);
            entity.setSourceId(bizSourceId);
        }
        int isAcc = 0;
        try {
            isAcc = service.insertDevChangeApply(entity);
        } catch (SQLException e) {
            return ResultEntity.error(e.getMessage());
        }
        return ResultEntity.success("成功新增" + isAcc + "条");
    }

    // 设备维修停删除  /dgom/device_replace/delete/{applyids}
    @DeleteMapping("/delete/{applyids}")
    public ResultEntity deleteDevChangeApply(@PathVariable String[] applyids) {
        return service.deleteDevChangeApply(applyids);
    }

    // 受理  /dgom/device_replace/accept 状态为2(待受理)时可以点击受理:受理后状态为3,不受理为4
    @PostMapping("/accept")
    public ResultEntity acceptDevChangeApply(@RequestBody DevApplyHead entity) {
        if (entity.getStatus() == null) return ResultEntity.error("状态不能为空");
        if (entity.getApplyId() == null) return ResultEntity.error("主键不能为空");
        return service.acceptDevChangeApply(entity);
    }

    // 审批  /dgom/device_replace/approval 状态为3(受理)时可以点击审批:审批通过后状态为5,不通过为6
    @PostMapping("/approval")
    public ResultEntity approvalDevChangeApply(@RequestBody DevApplyHead entity) {
        if (entity.getApplyId() == null) return ResultEntity.error("主键不能为空");
        return service.approvalDevChangeApply(entity);
    }

    // 值守  /dgom/device_replace/duty 状态为5(已受理)时可以点击值守:值守通过后状态为7
    @PostMapping("/duty")
    public ResultEntity dutyDevChangeApply(@RequestBody DevApplyHead entity) {
        if (entity.getApplyId() == null) return ResultEntity.error("主键不能为空");
        return service.dutyDevChangeApply(entity);
    }

}
