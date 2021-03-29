package com.ruoyi.project.enterprise.service.impl;

import com.ruoyi.framework.aspectj.lang.annotation.DataSource;
import com.ruoyi.framework.aspectj.lang.enums.DataSourceType;
import com.ruoyi.project.enterprise.domain.work_status.*;
import com.ruoyi.project.enterprise.mapper.WorkStatusMapper;
import com.ruoyi.project.enterprise.service.IWorkStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@DataSource(value = DataSourceType.MASTER)
public class WorkStatusService implements IWorkStatusService {
    @Autowired
    private WorkStatusMapper mapper;

    @Override
    public List<WorkStatusResult> getWorkStatusList(WorkStatusParam param) {
        return mapper.getWorkStatusList(param);
    }

    @Override
    public List<WorkStatusEntity> getWorkStatusInTime(WorkStatusEntity entity) {
        return mapper.getWorkStatusInTime(entity);
    }

    @Override
    public List<WorkStatusDateResult> getWorkStatusDate(WorkStatusDateParam param) {
        return mapper.getWorkStatusDate(param);
    }

    @Override
    public WorkStatusEntity getModelById(int workId) {
        return mapper.getModelById(workId);
    }

    @Override
    public int addWorkStatus(WorkStatusEntity entity) {
        return mapper.addWorkStatus(entity);
    }

    @Override
    public int updateWorkStatus(WorkStatusEntity entity) {
        return mapper.updateWorkStatus(entity);
    }

    @Override
    public int auditWorkStatus(WorkStatusEntity entity) {
        return mapper.auditWorkStatus(entity);
    }

    @Override
    public int removeWorkStatus(String workId, String updatedBy) {
        return mapper.removeWorkStatus(workId, updatedBy);
    }

    @Override
    public List<WorkStatusChangeResult> getChangeTimeList(WorkStatusParam param) {
        return mapper.getChangeTimeList(param);
    }

    @Override
    public int applyChangeTime(WorkStatusChangeEntity entity) {
        return mapper.applyChangeTime(entity);
    }

    @Override
    public int auditChangeTime(WorkStatusChangeEntity entity) {
        return mapper.auditChangeTime(entity);
    }

    @Override
    public int syncChangeToMaster(int changeId) {
        return mapper.syncChangeToMaster(changeId);
    }
}
