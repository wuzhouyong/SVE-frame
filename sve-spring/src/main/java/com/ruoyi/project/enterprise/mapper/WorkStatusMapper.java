package com.ruoyi.project.enterprise.mapper;

import com.ruoyi.project.enterprise.domain.work_status.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface WorkStatusMapper {
    List<WorkStatusResult> getWorkStatusList(WorkStatusParam param);
    List<WorkStatusEntity> getWorkStatusInTime(WorkStatusEntity entity);
    List<WorkStatusDateResult> getWorkStatusDate(WorkStatusDateParam param);
    WorkStatusEntity getModelById(int workId);
    int addWorkStatus(WorkStatusEntity entity);
    int updateWorkStatus(WorkStatusEntity entity);
    int auditWorkStatus(WorkStatusEntity entity);
    int removeWorkStatus(@Param("workId") String workId, @Param("updatedBy") String updatedBy);
    List<WorkStatusChangeResult> getChangeTimeList(WorkStatusParam param);
    int applyChangeTime(WorkStatusChangeEntity entity);
    int auditChangeTime(WorkStatusChangeEntity entity);
    int syncChangeToMaster(@Param("changeId") int changeId);
}
