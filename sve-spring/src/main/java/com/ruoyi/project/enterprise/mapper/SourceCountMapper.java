package com.ruoyi.project.enterprise.mapper;

import com.ruoyi.framework.web.domain.ExportScoreGasEntity;
import com.ruoyi.framework.web.domain.ExportScoreListEntity;
import com.ruoyi.framework.web.domain.ExportScoreWaterEntity;
import com.ruoyi.project.enterprise.domain.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface SourceCountMapper {

    //任务进度总览
    TaskCountEntity selectTaskSummary(MonitorTaskParam entity);

    //获取镇街任务统计
    List<TaskCountEntity> selectTownTaskCount(Long[] tagHeadIds);

    //任务进度统计
    List<TaskCountEntity> selectScheduleTaskCount(MonitorTaskParam entity);

    //施工单位任务统计
    List<TaskCountEntity> selectConstructTaskCount(MonitorTaskParam entity);

    //分局排名
    List<Map> selectCompletedSummary(MonitorTaskParam entity);

    //任务总数
    List<Map> getMissionRank(MonitorTaskParam entity);

    //任务总数计算
    Map getMissionRankCount(MonitorTaskParam entity);

    //已接收任务总数计算
    Map getMissionAcceptedCount(MonitorTaskParam entity);

    //总排名
    List<Map> selectTownLeaderboard(MonitorTaskParam entity);

    //未登录过企业总数
    Integer getCountNotlogin(@Param("townCode") String townCode);

    //责考评分
    List<Map> getBlameScore(MonitorTaskParam entity);

    //总责考评分列表
    List<ExportScoreListEntity> getBlameScoreList();

    //涉气责考评分列表
    List<ExportScoreGasEntity> getBlameScoreGasList();

    //涉气责考评分列表合计
    ExportScoreGasEntity getBlameScoreGasTotal();
    ExportScoreWaterEntity getBlameScoreWaterTotal();
    ExportScoreListEntity getBlameScoreTotal();

    //涉水责考评分列表
    List<ExportScoreWaterEntity> getBlameScoreWaterList();

    //统计状态数
    Map getStatusCount(MonitorTaskParam entity);

    //已登录企业数
    Integer getLoggedinForTownCode(MonitorTaskParam entity);

    //总任务数
    List<Map> getAdditionNumber(MonitorTaskParam entity);

    //总计
    List<Map> getAggregateNumber(MonitorTaskParam entity);

    List<TaskSourceEntity> getTotalAggregate(MonitorTaskParam entity);


}
