package com.ruoyi.project.dgom.service.impl;

import com.ruoyi.common.core.lang.UUID;
import com.ruoyi.common.domain.ResultEntity;
import com.ruoyi.common.utils.CollectUtils;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.framework.aspectj.lang.annotation.DataSource;
import com.ruoyi.framework.aspectj.lang.enums.DataSourceType;
import com.ruoyi.project.dgom.domain.DevApplyDetail;
import com.ruoyi.project.dgom.domain.DevApplyHead;
import com.ruoyi.project.dgom.domain.EntSmsNewEntity;
import com.ruoyi.project.dgom.mapper.DevChangeMapper;
import com.ruoyi.project.dgom.service.DevChangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 【请填写功能名称】Service业务层处理
 *
 * @author ruoyi
 * @date 2020-08-25
 */
@Service
@DataSource(value = DataSourceType.HBONLINE)
public class DevChangeServiceImpl implements DevChangeService {

    @Autowired
    private DevChangeMapper mapper;

    @Override
    public List<DevApplyHead> selectDevChangeApplyList(DevApplyHead entity) {
        return mapper.selectDevChangeApplyList(entity);
    }

    @Override
    public List<DevApplyDetail> selectDevApplyDetailList(String applyId) {
        return mapper.selectDevApplyDetailList(applyId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertDevChangeApply(DevApplyHead entity) throws SQLException {
        entity.setCreatedBy(SecurityUtils.getUsername());
        entity.setUpdatedBy(SecurityUtils.getUsername());
        Integer isAcc = mapper.insertDevApplyHead(entity);
        if (isAcc < 1) throw new SQLException("设备停用维修申请失败");
        List<DevApplyDetail> oldList = mapper.selectDevApplyDetailList(entity.getApplyId());
        List<DevApplyDetail> newList = entity.getDetail();
        Map<String, List<DevApplyDetail>> map = CollectUtils.toOperate(newList, oldList, "getDetailid");
        List<DevApplyDetail> add = map.get("add");
        for (DevApplyDetail detail : add) {
            detail.setDetailId(UUID.randomUUID().toString().replace("-", ""));
            detail.setApplyId(entity.getApplyId());
            Integer count = mapper.insertDevApplyDetail(detail);
            if (count < 1) throw new SQLException("设备新增失败");
        }
        List<DevApplyDetail> delete = map.get("delete");
        for (DevApplyDetail detail : delete) {
            Integer count = mapper.deleteDevApplyDetail(detail);
            if (count < 1) throw new SQLException("设备删除失败");
        }
        return isAcc;
    }

    @Override
    public ResultEntity deleteDevChangeApply(String[] applyids) {
        if (applyids == null || applyids.length == 0) {
            return ResultEntity.error("参数不能为空");
        }
        List<Integer> status = mapper.selectDevApplyStatus(applyids);
        for (Integer statu : status) {
            if (statu != null && statu != 1) return ResultEntity.error("只有草稿状态才能删除");
        }
        String updatedBy = SecurityUtils.getUsername();
        int isAcc = mapper.deleteDevApplyHead(applyids, updatedBy);
        if (isAcc < 0) return ResultEntity.error("设备维修申请删除失败");
        int count = mapper.deleteDevApplyDetailForIds(applyids);
        if (count < 0) return ResultEntity.error("排放口设备维修删除失败");
        return isAcc > 0 ? ResultEntity.success("成功删除" + isAcc + "条") : ResultEntity.error("删除失败");
    }

    @Override
    public ResultEntity acceptDevChangeApply(DevApplyHead entity) {
        DevApplyHead head = mapper.selectDevApplyHeadById(entity.getApplyId());
        if (head.getStatus() != 2) return ResultEntity.error("该状态下不能受理");
        entity.setUpdatedBy(SecurityUtils.getUsername());
        entity.setAcceptDate(DateUtils.dateTimeS(new Date()));
        int isAcc = mapper.updateDevChangeApply(entity);
        if (isAcc == 1 && entity.getStatus().equals("4")) {
            String sourceName = mapper.selectSourceNameBySourceId(head.getSourceId());
            EntSmsNewEntity entity_t = new EntSmsNewEntity();
            entity_t.setSmsId(UUID.randomUUID().toString().replace("-", ""));
            entity_t.setPhoneNumber(head.getContactTel());
            entity_t.setContent("【东莞市重点污染源在线监控平台】（设备维修、停用、拆除或者更换申请）" + sourceName + "，您于" + DateUtils.formatTimeStr(head.getApplyDate()) +
                    "申请的设备维修、停用、拆除或者更换申请未能受理。建议您根据生态环境分局的指引或重新发起设备维修、停用、拆除或者更换申请");
            entity_t.setSmsType("3");
            Integer count = mapper.insertEntSmsNew(entity_t);
            if (count < 0) return ResultEntity.error("信息插入失败");
        }
        return isAcc > 0 ? ResultEntity.success("受理成功") : ResultEntity.error("受理失败");
    }

    @Override
    public ResultEntity approvalDevChangeApply(DevApplyHead entity) {
        DevApplyHead head = mapper.selectDevApplyHeadById(entity.getApplyId());
        if (head.getStatus() != 3) return ResultEntity.error("该状态下不能审批");
        entity.setUpdatedBy(SecurityUtils.getUsername());
        entity.setApprovedDate(DateUtils.dateTimeS(new Date()));
        int isAcc = mapper.updateDevChangeApply(entity);
        if (isAcc == 1) {
            String sourceName = mapper.selectSourceNameBySourceId(head.getSourceId());
            EntSmsNewEntity entity_t = new EntSmsNewEntity();
            entity_t.setSmsId(UUID.randomUUID().toString().replace("-", ""));
            entity_t.setPhoneNumber(head.getContactTel());
            entity_t.setSmsType("2");
            if (entity.getStatus().equals("5")) {
                entity_t.setContent("【东莞市重点污染源在线监控平台】（设备维修、停用、拆除或者更换申请）" + sourceName + "，您于" + DateUtils.formatTimeStr(head.getApplyDate()) +
                        "申请的设备维修、停用、拆除或者更换申请，审批已通过");
                Integer count = mapper.insertEntSmsNew(entity_t);
                if (count < 0) return ResultEntity.error("信息插入失败");
            }
            if (entity.getStatus().equals("6")) {
                entity_t.setContent("【东莞市重点污染源在线监控平台】（设备维修、停用、拆除或者更换申请）" + sourceName + "，您于" + DateUtils.formatTimeStr(head.getApplyDate()) +
                        "申请的设备维修、停用、拆除或者更换申请，由于" + head.getApprovalDesc() + "，审批未能通过。建议您根据生态环境分局的指引或重新发起设备维修、停用、拆除或者更换申请");
                Integer count = mapper.insertEntSmsNew(entity_t);
                if (count < 0) return ResultEntity.error("信息插入失败");
            }
        }
        return isAcc > 0 ? ResultEntity.success("审批成功") : ResultEntity.error("审批失败");
    }

    @Override
    public ResultEntity dutyDevChangeApply(DevApplyHead entity) {
        DevApplyHead head = mapper.selectDevApplyHeadById(entity.getApplyId());
        if (head.getStatus() != 5) return ResultEntity.error("该状态下不能值守");
        entity.setUpdatedBy(SecurityUtils.getUsername());
        entity.setOperatedDate(DateUtils.dateTimeS(new Date()));
        int isAcc = mapper.updateDevChangeApply(entity);
        return isAcc > 0 ? ResultEntity.success("审批成功") : ResultEntity.error("审批失败");
    }


}
