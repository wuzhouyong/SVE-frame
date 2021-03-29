package com.ruoyi.project.dgom.service;

import com.ruoyi.project.dgom.domain.abnor_task.*;

import java.util.List;

public interface IAbnorTaskService {

    List<AbnorTaskResult> getAbnorTaskList(AbnorTaskParam entity);

    List<TaskElementResult> getElementListByTaskIds(String[] taskHeadIds);

    String getAbnorHeadId(String keyword);

    int addManualTask(ManualTaskModel model);

    int enterpriseProcBatch(String[] taskHeadIds, String sourceId, EnterpriseProcessModel model);
    int enterpriseProcess(EnterpriseProcessModel model);

    int branchProcBatch(String[] taskHeadIds, BranchProcessModel model);
    int branchProcess(BranchProcessModel model);

    int branchReturnBatch(String[] taskHeadIds, BranchReturnModel model);
    int branchReturn(BranchReturnModel model);

    int cityReturnBatch(String[] taskHeadIds, String returnReason);
    int cityReturn(String taskHeadId);

    String getSourceNameById(String dataSourId);

    List<ProcDetailModel> getProcDetailList(String taskHeadId);
    int addAbnorTaskProcDetail(Integer userType, String taskHeadId, String procDesc, String procUnit, String procUser);
}
