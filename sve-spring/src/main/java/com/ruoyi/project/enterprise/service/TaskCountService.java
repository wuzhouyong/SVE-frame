package com.ruoyi.project.enterprise.service;

import com.ruoyi.framework.web.domain.ExportScoreGasEntity;
import com.ruoyi.framework.web.domain.ExportScoreListEntity;
import com.ruoyi.framework.web.domain.ExportScoreWaterEntity;
import com.ruoyi.project.enterprise.domain.*;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author solang
 * @date 2020-07-22 10:26
 */
@Component
public interface TaskCountService {

      //任务进度总览
      TaskCountEntity getTaskSummary(MonitorTaskParam entity);

    //获取镇街任务统计
    List<TaskCountEntity> getTownTaskCount(Long[] tagHeadIds);

    //任务进度统计
    List<TaskCountEntity> getScheduleTaskCount(MonitorTaskParam entity);

    //施工单位任务统计
    List<TaskCountEntity> getConstructTaskCount(MonitorTaskParam entity);

    //分局排名
    List<Map> getCompletedSummary(MonitorTaskParam entity);

    //任务总数
    List<Map> getMissionRank(MonitorTaskParam entity);

    //任务总数计算
    Map getMissionRankCount(MonitorTaskParam entity);

    //已接收任务总数计算
    Map getMissionAcceptedCount(MonitorTaskParam entity);

    //总排名
    List<Map> getTownLeaderboard(MonitorTaskParam entity);

    //未登录过企业总数
    Integer getCountNotlogin(String townCode);

    //责考评分
    List<Map> getBlameScore(MonitorTaskParam entity);

    //总责考评分列表
    List<ExportScoreListEntity> getBlameScoreList();

    //涉气责考评分列表
    List<ExportScoreGasEntity> getBlameScoreGasList();

    //涉水责考评分列表
    List<ExportScoreWaterEntity> getBlameScoreWaterList();

    //统计状态数
    Map getStatusCount(MonitorTaskParam entity);
    //总任务数
    List<Map> getAdditionNumber(MonitorTaskParam entity);
    //总计
    List<Map> getAggregateNumber(MonitorTaskParam entity);
    //所有状态总计
    List<TaskSourceEntity> getTotalAggregate(MonitorTaskParam entity);
}
