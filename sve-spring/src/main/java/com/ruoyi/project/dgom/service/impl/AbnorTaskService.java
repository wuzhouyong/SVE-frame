package com.ruoyi.project.dgom.service.impl;

import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.aspectj.lang.annotation.DataSource;
import com.ruoyi.framework.aspectj.lang.enums.DataSourceType;
import com.ruoyi.project.dgom.domain.abnor_task.*;
import com.ruoyi.project.dgom.mapper.AbnorTaskMapper;
import com.ruoyi.project.dgom.service.IAbnorTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@DataSource(value = DataSourceType.HBONLINE)
public class AbnorTaskService implements IAbnorTaskService {

    @Autowired
    private AbnorTaskMapper mapper;

    @Override
    public List<AbnorTaskResult> getAbnorTaskList(AbnorTaskParam entity) {
        List<AbnorTaskResult> list = mapper.getAbnorTaskList(entity);
        list.forEach(e -> {
            e.setAbnorReasonName(AbnorDictType.getReasonName(e.getAbnorReason()));
            e.setAlarmLevelName(AbnorDictType.getAlarmLevel(e.getAlarmLevel()));
            e.setAbnorTaskTypeName(AbnorDictType.getAbnorType(e.getAbnorTaskType()));
            e.setTaskStatusName(AbnorDictType.getTaskStatus(e.getTaskStatus()));
        });
        return list;
    }

    @Override
    public List<TaskElementResult> getElementListByTaskIds(String[] taskHeadIds) {
        return mapper.getElementListByTaskIds(taskHeadIds);
    }

    @Override
    public String getAbnorHeadId(String keyword) {
        Map<String, String> param = new LinkedHashMap<>();
        param.put("keyword", keyword);
        param.put("complexid", "");
        mapper.getAbnorHeadId(param);
        return param.get("complexid");
    }

    @Override
    public int addManualTask(ManualTaskModel model) {
        model.setTaskHeadId(getAbnorHeadId("zAbnorTaskHead"));
        return mapper.addManualTask(model);
    }

    @Override
    @Transactional
    public int enterpriseProcBatch(String[] taskHeadIds, String sourceId, EnterpriseProcessModel model) {
        for (String taskHeadId : taskHeadIds) {
            model.setTaskHeadId(taskHeadId);
            int res = enterpriseProcess(model);
            if (res > 0) {
                if (model.getTaskStatus() == 2) {
                    String sourceName = getSourceNameById(sourceId);
                    String procDesc = StringUtils.format("[{}]{}", AbnorDictType.getReasonName(model.getAbnorReason()), model.getEntProcessMemo());
                    addAbnorTaskProcDetail(1, model.getTaskHeadId(), procDesc, sourceName, model.getProcUserName());
                }
            } else {
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                return -1;
            }
        }
        return taskHeadIds.length;
    }

    @Override
    public int enterpriseProcess(EnterpriseProcessModel model) {
        return mapper.enterpriseProcess(model);
    }

    @Override
    @Transactional
    public int branchProcBatch(String[] taskHeadIds, BranchProcessModel model) {
        for (String taskHeadId : taskHeadIds) {
            model.setTaskHeadId(taskHeadId);
            int res = branchProcess(model);
            if (res > 0) {
                addAbnorTaskProcDetail(2, taskHeadId, model.getAdminUnitProcDesc(), model.getAdminUnitName(), SecurityUtils.getLoginUser().getUser().getNickName());
            } else {
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                return -1;
            }
        }
        return taskHeadIds.length;
    }

    @Override
    public int branchProcess(BranchProcessModel model) {
        return mapper.branchProcess(model);
    }

    @Override
    @Transactional
    public int branchReturnBatch(String[] taskHeadIds, BranchReturnModel model) {
        for (String taskHeadId : taskHeadIds) {
            model.setTaskHeadId(taskHeadId);
            int res = branchReturn(model);
            if (res > 0) {
                String procDesc = StringUtils.format("[分局退回]{}", model.getReturnReason());
                addAbnorTaskProcDetail(2, taskHeadId, procDesc, model.getReturnUnit(), SecurityUtils.getLoginUser().getUser().getNickName());
            } else {
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                return -1;
            }
        }
        return taskHeadIds.length;
    }

    @Override
    public int branchReturn(BranchReturnModel model) {
        return mapper.branchReturn(model);
    }

    @Override
    @Transactional
    public int cityReturnBatch(String[] taskHeadIds, String returnReason) {
        for (String taskHeadId : taskHeadIds) {
            int res = cityReturn(taskHeadId);
            if (res > 0) {
                String procDesc = StringUtils.format("[市局退回]{}", returnReason);
                addAbnorTaskProcDetail(3, taskHeadId, procDesc, "市局在线室", SecurityUtils.getLoginUser().getUser().getNickName());
            } else {
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                return -1;
            }
        }
        return taskHeadIds.length;
    }

    @Override
    public int cityReturn(String taskHeadId) {
        return mapper.cityReturn(taskHeadId, SecurityUtils.getUsername());
    }

    @Override
    public String getSourceNameById(String dataSourId) {
        return mapper.getSourceNameById(dataSourId);
    }

    @Override
    public List<ProcDetailModel> getProcDetailList(String taskHeadId) {
        return mapper.getProcDetailList(taskHeadId);
    }

    @Override
    public int addAbnorTaskProcDetail(Integer userType, String taskHeadId, String procDesc, String procUnit, String procUser) {
        ProcDetailModel model = new ProcDetailModel();
        model.setProcDetailId(UUID.randomUUID().toString().replace("-", ""));
        model.setUserType(userType);
        model.setTaskHeadId(taskHeadId);
        model.setProcDesc(procDesc);
        model.setProcUnit(procUnit);
        model.setProcUserName(procUser);
        return mapper.addAbnorTaskProcDetail(model);
    }

    private String getReasonName(Integer reason)
    {

        //0 '',1断电,2通讯中断,3采集故障,4设备故障,5企业停产,6停用在线设备,7设备维修,8设备更换,9其他情况,10生产排放原因
        String reasonName = "";
        switch (reason)
        {
            case 0:
                reasonName = "";
                break;
            case 1:
                reasonName = "断电";
                break;
            case 2:
                reasonName = "通讯中断";
                break;
            case 3:
                reasonName = "采集故障";
                break;
            case 4:
                reasonName = "设备故障";
                break;
            case 5:
                reasonName = "企业停产";
                break;
            case 6:
                reasonName = "停用在线设备";
                break;
            case 7:
                reasonName = "设备维修";
                break;
            case 8:
                reasonName = "设备更换";
                break;
            case 9:
                reasonName = "其他情况";
                break;
            case 10:
                reasonName = "生产排放原因";
                break;
            default:
                break;
        }
        return reasonName;
    }
}
