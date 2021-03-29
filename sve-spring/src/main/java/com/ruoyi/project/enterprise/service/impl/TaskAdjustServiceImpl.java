package com.ruoyi.project.enterprise.service.impl;

import com.ruoyi.common.domain.ResultEntity;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.ServletUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.spring.SpringUtils;
import com.ruoyi.framework.aspectj.lang.annotation.DataSource;
import com.ruoyi.framework.aspectj.lang.enums.DataSourceType;
import com.ruoyi.framework.security.LoginUser;
import com.ruoyi.framework.security.service.TokenService;
import com.ruoyi.project.enterprise.domain.ReportEntity;
import com.ruoyi.project.enterprise.domain.TaskAdjustDetail;
import com.ruoyi.project.enterprise.domain.TaskAdjustEntity;
import com.ruoyi.project.enterprise.mapper.PfBasSourceMapper;
import com.ruoyi.project.enterprise.mapper.TaskAdjustMapper;
import com.ruoyi.project.enterprise.service.TaskAdjustService;
import com.ruoyi.project.system.domain.SysRole;
import com.ruoyi.project.system.domain.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@DataSource(value = DataSourceType.MASTER)
public class TaskAdjustServiceImpl implements TaskAdjustService {

    @Autowired
    private TaskAdjustMapper adjustMapper;

    @Autowired
    private PfBasSourceMapper pfBasSourceMapper;

    @Override
    public int insertTaskAdjust(TaskAdjustEntity entity) {
        return adjustMapper.insertTaskAdjust(entity);
    }

    @Override
    public int insertReport(ReportEntity entity) {
        int report = adjustMapper.insertReport(entity);
        if (report >= 1) return entity.getReportId();
        return 0;
    }

    @Override
    public int editReportAudit(ReportEntity entity) {
        int report = adjustMapper.editReportAudit(entity);
        if (report < 1) return 0;
        return report;
    }

    @Override
    public List<ReportEntity> selectReportList(ReportEntity entity) {
        return adjustMapper.selectReportList(entity);
    }

    @Override
    public int selectTaskAdjust(TaskAdjustEntity entity) {
        return pfBasSourceMapper.selectTaskAdjust(entity.getPfSourceId(), entity.getTagHeadId());
    }

    @Override
    public int agree(TaskAdjustEntity entity) {
        entity.setUpdatedBy(SecurityUtils.getUsername());
        int res = 0;
        res = adjustMapper.agree(entity);
        if (res > 0 && entity.getAduitStatus().equals("1")) {
            if (entity.getAdjustItem().equals("0"))
                entity.setVerifyStatus("1");
            else
                entity.setVerifyStatus("2");
            adjustMapper.updateConStatus(entity);
        }
        return res;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultEntity taskApproval(TaskAdjustEntity entity) {
        entity.setUpdatedBy(SecurityUtils.getUsername());
        if (!StringUtils.isEmpty(SecurityUtils.getLoginUser().getUser().getSourceId())) {
            entity.setPfSourceId(SecurityUtils.getLoginUser().getUser().getSourceId());
        }
        TaskAdjustDetail detail = adjustMapper.selectTaskAdjustDetail(entity.getTaskAdjustId()); //查询当前保存的账户信息
        if (entity.getAduitStatus().equals("2")) { //如果审批结果为不同意 2为不同意
            if (detail != null) {  //第一次点击不同意,deail为null
                // 获取当前的用户
                LoginUser loginUser = SpringUtils.getBean(TokenService.class).getLoginUser(ServletUtils.getRequest());
                SysUser currentUser = loginUser.getUser();
                if (detail.getCreatedBy().equals(currentUser.getUserName())) return ResultEntity.error("同一账户不能重复审批");
            }
            int res = insertTaskAdjustDetail(entity);
            if (res < 0) return ResultEntity.error("增加申请失败");
            entity.setAduitStatus("2");
            adjustMapper.agree(entity);
            return ResultEntity.success("审核不同意");
        }
        if (entity.getAduitStatus().equals("1")) { //如果审批结果为同意 1为同意
            int count = adjustMapper.selectTaskAdjustDetailCount(entity);
            if (count >= 2) return ResultEntity.success("申请已处理完成");
            if (count == 0) {
                int res = insertTaskAdjustDetail(entity);
                if (res > 0) return ResultEntity.success("审核成功");
            }
            if (count == 1) {
                // 获取当前的用户
                LoginUser loginUser = SpringUtils.getBean(TokenService.class).getLoginUser(ServletUtils.getRequest());
                SysUser currentUser = loginUser.getUser();
                if (detail.getCreatedBy().equals(currentUser.getUserName())) return ResultEntity.error("同一账户不能重复审批");
                int res = insertTaskAdjustDetail(entity);  //新增任务明细
                if (res < 0) return ResultEntity.error("增加申请失败");
                entity.setAduitStatus("1");
                adjustMapper.agree(entity); //修改任务主记录
                if (entity.getAdjustItem().equals("0")) entity.setVerifyStatus("1");
                else {
                    entity.setVerifyStatus("2");
                }
                adjustMapper.updateConStatus(entity);//修改任务状态
                return ResultEntity.success("审核成功");
            }
        }
        return ResultEntity.error("审批状态不正确");
    }

    @Override
    public TaskAdjustEntity selectEntity(String taskAdjustId) {
        return adjustMapper.selectEntity(taskAdjustId);
    }

    @Override
    public TaskAdjustDetail selectTaskAdjustDetail(String taskAdjustId) {
        return adjustMapper.selectTaskAdjustDetail(taskAdjustId);
    }

    @Override
    public List<TaskAdjustEntity> selectTaskAdjustEntity(String pfSourceId, String tagHeadId) {
        List<TaskAdjustEntity> entityList = adjustMapper.selectTaskAdjustList(pfSourceId, tagHeadId);
        if (entityList == null) return new ArrayList<>();
        for (TaskAdjustEntity entity : entityList) {
            List<TaskAdjustDetail> details = adjustMapper.selectTaskAdjustDetailList(entity.taskAdjustId);
            entity.setDetails(details);
        }
        return entityList;
    }

    @Override
    public List<TaskAdjustEntity> selectTaskAdjustList(String pfSourceId, String tagHeadId) {
        return adjustMapper.selectTaskAdjustList(pfSourceId, tagHeadId);
    }

    public int insertTaskAdjustDetail(TaskAdjustEntity entity) {
        // 获取当前的用户
        LoginUser loginUser = SpringUtils.getBean(TokenService.class).getLoginUser(ServletUtils.getRequest());
        SysUser currentUser = loginUser.getUser();
        SysRole sysRole = currentUser.getRoles().get(0);
        TaskAdjustDetail detail = new TaskAdjustDetail();
        String detailId = java.util.UUID.randomUUID().toString().replace("-", "");
        detail.setDetailId(detailId);
        detail.setTaskAdjustId(entity.taskAdjustId);
        detail.setAduitStatus(entity.aduitStatus);
        detail.setAduitReason(entity.aduitReason);
        detail.setCreatedBy(currentUser.getUserName());
        detail.setUserName(currentUser.getNickName());
        detail.setRoleName(sysRole.getRoleName());
        int res = adjustMapper.insertTaskAdjustDetail(detail);
        return res;
    }


}
