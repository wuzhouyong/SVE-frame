package com.ruoyi.project.enterprise.service;

import com.ruoyi.common.domain.ResultEntity;
import com.ruoyi.project.enterprise.domain.ReportEntity;
import com.ruoyi.project.enterprise.domain.TaskAdjustDetail;
import com.ruoyi.project.enterprise.domain.TaskAdjustEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TaskAdjustService {

    public int insertTaskAdjust(TaskAdjustEntity entity);
    public int selectTaskAdjust(TaskAdjustEntity entity);
    public int insertReport(ReportEntity entity);
    public int editReportAudit(ReportEntity entity);
    public List<ReportEntity> selectReportList(ReportEntity entity);

    public int agree(TaskAdjustEntity entity);

    //拆分零星废水任务调整审批
    public ResultEntity taskApproval(TaskAdjustEntity entity);

    public TaskAdjustEntity selectEntity(String taskAdjustId);

    //零星废水任务明细详情
    public TaskAdjustDetail selectTaskAdjustDetail(String detailId);

    //零星废水任务明细返回数据结构
    public List<TaskAdjustEntity> selectTaskAdjustEntity(String pfSourceId, String tagHeadId);

    public List<TaskAdjustEntity> selectTaskAdjustList(String pfSourceId, String tagHeadId);
}
