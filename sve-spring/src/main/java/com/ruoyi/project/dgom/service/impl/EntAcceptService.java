package com.ruoyi.project.dgom.service.impl;

import com.ruoyi.common.domain.ResultEntity;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.aspectj.lang.annotation.DataSource;
import com.ruoyi.framework.aspectj.lang.enums.DataSourceType;
import com.ruoyi.project.dgom.domain.ent_accept.*;
import com.ruoyi.project.dgom.mapper.AttachMapper;
import com.ruoyi.project.dgom.mapper.EntAcceptMapper;
import com.ruoyi.project.dgom.service.IEntAcceptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@DataSource(value = DataSourceType.HBONLINE)
public class EntAcceptService implements IEntAcceptService {
    @Autowired
    private EntAcceptMapper mapper;

    @Autowired
    private AttachMapper attachMapper;

    @Autowired
    private SmsService smsService;

    @Override
    public List<EntAcceptResult> getEntAcceptList(EntAcceptParam param) {
        List<EntAcceptResult> list = mapper.getEntAcceptList(param);
        for (EntAcceptResult item : list) {
            List<EntAcceptDetailResult> details = mapper.getEntAcceptDetailList(item.getApplyId());
            item.setDetails(details);
        }
        return list;
    }

    @Override
    public List<EntAcceptDetailResult> getEntAcceptDeviceList(String applyId) {
        return mapper.getEntAcceptDetailList(applyId);
    }

    @Override
    public int addEntAcceptHead(EntAcceptEntity entity) {
        return mapper.addEntAcceptHead(entity);
    }

    @Override
    public int addEntAcceptDetail(EntAcceptDetailEntity entity) {
        return mapper.addEntAcceptDetail(entity);
    }

    @Override
    public int editEntAcceptHead(EntAcceptEntity entity) {
        return mapper.editEntAcceptHead(entity);
    }

    @Override
    public int editEntAcceptDetail(EntAcceptDetailEntity entity) {
        return mapper.editEntAcceptDetail(entity);
    }

    @Override
    public ResultEntity submitEntAccept(String applyId) {
        List<Map> attachList = attachMapper.getAttachList(new String[] {applyId}, "Ent_AcceptApplyHead");
        if (attachList.size() == 0) {
            return ResultEntity.error("请上传附件后再提交");
        }
        int res = mapper.submitEntAccept(applyId, SecurityUtils.getUsername());
        if (res > 0) {
            EntAcceptResult model = mapper.getEntAcceptModel(applyId);
            String acceptDate = DateUtils.parseDate(model.getAcceptDate(), "yyyy-MM-dd");
            String content = StringUtils.format("【东莞市重点污染源在线监控平台】（验收备案）{}，您于{}申请的验收备案申请已提交，请耐心等待", model.getSourceName(), acceptDate);
            smsService.sendSms(model.getChargeManTel(), content);
            return ResultEntity.success("验收备案提交成功");
        }
        return ResultEntity.error("验收备案提交失败");
    }

    @Override
    public ResultEntity deleteEntAccept(String applyId) {
        EntAcceptResult model = mapper.getEntAcceptModel(applyId);
        if (model.getStatus() != 1) {
            return ResultEntity.error("非草稿状态,不允许删除");
        }
        int res = mapper.deleteEntAccept(applyId, SecurityUtils.getUsername());
        if (res > 0) {
            return ResultEntity.success("验收备案删除成功");
        }
        return ResultEntity.error("验收备案删除失败");
    }

    @Override
    public ResultEntity deleteEntAcceptDetail(String detailId) {
        int res = mapper.deleteEntAcceptDetail(detailId);
        if (res > 0) {
            return ResultEntity.success("验收备案设备删除成功");
        }
        return ResultEntity.error("验收备案设备删除失败");
    }

    @Override
    public ResultEntity approval(EntApprovalEntity entity) {
        int res = mapper.approval(entity);
        if (res > 0) {
            EntAcceptResult model = mapper.getEntAcceptModel(entity.getApplyId());
            String acceptDate = DateUtils.parseDate(model.getAcceptDate(), "yyyy-MM-dd");
            String content;
            if (entity.getStatus() == 3) {
                content = StringUtils.format("【东莞市重点污染源在线监控平台】（验收备案）{}，您于{}申请的验收备案，审核已通过", model.getSourceName(), acceptDate);
            } else {
                content = StringUtils.format("【东莞市重点污染源在线监控平台】（验收备案）{}，您于{}申请的验收备案，由于{}，审核未能通过。" +
                        "建议您按照验收技术规范文件的要求或重新发起验收备案申请", model.getSourceName(), acceptDate, model.getApprovalDesc());
            }
            smsService.sendSms(model.getChargeManTel(), content);
            return ResultEntity.success("操作成功");
        }
        return ResultEntity.error("操作失败");
    }
}
