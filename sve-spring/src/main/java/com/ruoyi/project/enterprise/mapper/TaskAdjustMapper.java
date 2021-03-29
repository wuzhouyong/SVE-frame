package com.ruoyi.project.enterprise.mapper;

import com.ruoyi.project.enterprise.domain.ReportEntity;
import com.ruoyi.project.enterprise.domain.TaskAdjustDetail;
import com.ruoyi.project.enterprise.domain.TaskAdjustEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TaskAdjustMapper {

    public int insertTaskAdjust(TaskAdjustEntity entity);

    public int insertTaskAdjustDetail(TaskAdjustDetail detail);

    public int insertReport(ReportEntity entity);
    public int editReportAudit(ReportEntity entity);
    public List<ReportEntity> selectReportList(ReportEntity entity);

    public int agree(TaskAdjustEntity entity);

    public int updateConStatus(TaskAdjustEntity entity);

    public int selectTaskAdjustDetailCount(TaskAdjustEntity entity);

    public TaskAdjustEntity selectEntity(@Param("taskAdjustId") String taskAdjustId);

    public TaskAdjustDetail selectTaskAdjustDetail(@Param("taskAdjustId") String taskAdjustId);

    public List<TaskAdjustDetail> selectTaskAdjustDetailList(@Param("taskAdjustId") String taskAdjustId);

    public List<TaskAdjustEntity> selectTaskAdjustList(@Param("pfSourceId") String pfSourceId, @Param("tagHeadId") String tagHeadId);
}
