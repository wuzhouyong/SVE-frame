package com.ruoyi.project.dgom.mapper;

import com.ruoyi.project.dgom.domain.abnor_task.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface AbnorTaskMapper {
    List<AbnorTaskResult> getAbnorTaskList(AbnorTaskParam entity);

    List<TaskElementResult> getElementListByTaskIds(@Param("taskHeadIds") String[] taskHeadIds);

    void getAbnorHeadId(Map<String, String> param);

    int addManualTask(ManualTaskModel model);

    int enterpriseProcess(EnterpriseProcessModel model);

    int branchProcess(BranchProcessModel model);
    int branchReturn(BranchReturnModel model);
    int cityReturn(@Param("taskHeadId") String taskHeadId, @Param("updatedBy") String updatedBy);

    String getSourceNameById(@Param("dataSourId") String dataSourId);

    List<ProcDetailModel> getProcDetailList(@Param("taskHeadId") String taskHeadId);

    int addAbnorTaskProcDetail(ProcDetailModel model);
}
