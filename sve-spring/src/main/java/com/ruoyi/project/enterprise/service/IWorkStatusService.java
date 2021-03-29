package com.ruoyi.project.enterprise.service;

import com.ruoyi.project.enterprise.domain.work_status.*;

import java.util.List;

public interface IWorkStatusService {
    List<WorkStatusResult> getWorkStatusList(WorkStatusParam param);
    List<WorkStatusEntity> getWorkStatusInTime(WorkStatusEntity entity);
    List<WorkStatusDateResult> getWorkStatusDate(WorkStatusDateParam param);
    WorkStatusEntity getModelById(int workId);
    int addWorkStatus(WorkStatusEntity entity);
    int updateWorkStatus(WorkStatusEntity entity);
    int auditWorkStatus(WorkStatusEntity entity);
    int removeWorkStatus(String workId, String updatedBy);
    List<WorkStatusChangeResult> getChangeTimeList(WorkStatusParam param);
    int applyChangeTime(WorkStatusChangeEntity entity);
    int auditChangeTime(WorkStatusChangeEntity entity);

    int syncChangeToMaster(int changeId);
}
