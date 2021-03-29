package com.ruoyi.project.enterprise.service.impl;

import com.ruoyi.common.utils.HashMapUtils;
import com.ruoyi.framework.aspectj.lang.annotation.DataSource;
import com.ruoyi.framework.aspectj.lang.enums.DataSourceType;
import com.ruoyi.framework.web.domain.ExportScoreGasEntity;
import com.ruoyi.framework.web.domain.ExportScoreListEntity;
import com.ruoyi.framework.web.domain.ExportScoreWaterEntity;
import com.ruoyi.project.enterprise.domain.*;
import com.ruoyi.project.enterprise.mapper.SourceCountMapper;
import com.ruoyi.project.enterprise.service.TaskCountService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * @author solang
 * @date 2020-07-22 10:27
 */
@Service
@DataSource(value = DataSourceType.MASTER)
public class TaskCountServiceImpl implements TaskCountService {

    @Autowired
    private SourceCountMapper sourceCountMapper;

    //任务进度总览
    @Override
    public TaskCountEntity getTaskSummary(MonitorTaskParam entity) {
        return sourceCountMapper.selectTaskSummary(entity);
    }

    //获取镇街任务统计
    @Override
    public List<TaskCountEntity> getTownTaskCount(Long[] tagHeadIds) {
        return sourceCountMapper.selectTownTaskCount(tagHeadIds);
    }

    //任务进度统计
    @Override
    public List<TaskCountEntity> getScheduleTaskCount(MonitorTaskParam entity) {
        return sourceCountMapper.selectScheduleTaskCount(entity);
    }

    //施工单位任务统计
    @Override
    public List<TaskCountEntity> getConstructTaskCount(MonitorTaskParam entity) {
        return sourceCountMapper.selectConstructTaskCount(entity);
    }

    //分局排名
    @Override
    public List<Map> getCompletedSummary(MonitorTaskParam entity) {
        return HashMapUtils.toCamel(sourceCountMapper.selectCompletedSummary(entity));
    }

    //任务总数
    @Override
    public List<Map> getMissionRank(MonitorTaskParam entity) {
        return HashMapUtils.toCamel(sourceCountMapper.getMissionRank(entity));
    }

    //任务总数计算
    @Override
    public Map getMissionRankCount(MonitorTaskParam entity) {
        return HashMapUtils.toCamel(sourceCountMapper.getMissionRankCount(entity));
    }

    //已接收任务总数计算
    @Override
    public Map getMissionAcceptedCount(MonitorTaskParam entity) {
        return HashMapUtils.toCamel(sourceCountMapper.getMissionAcceptedCount(entity));
    }

    //总排名
    @Override
    public List<Map> getTownLeaderboard(MonitorTaskParam entity) {
        return HashMapUtils.toCamel(sourceCountMapper.selectTownLeaderboard(entity));
    }

    //未登录过企业总数
    @Override
    public Integer getCountNotlogin(String townCode) {
        return sourceCountMapper.getCountNotlogin(townCode);
    }

    //责考评分
    @Override
    public List<Map> getBlameScore(MonitorTaskParam entity) {
        return HashMapUtils.toCamel(sourceCountMapper.getBlameScore(entity));
    }

    //总责考评分列表
    @Override
    public List<ExportScoreListEntity> getBlameScoreList() {
        List<ExportScoreListEntity> list = sourceCountMapper.getBlameScoreList();
        ExportScoreListEntity entity = sourceCountMapper.getBlameScoreTotal();
        list.add(list.size(), entity);
        return list;
    }

    //涉气责考评分列表
    @Override
    public List<ExportScoreGasEntity> getBlameScoreGasList() {
        List<ExportScoreGasEntity> list = sourceCountMapper.getBlameScoreGasList();
        ExportScoreGasEntity entity = sourceCountMapper.getBlameScoreGasTotal();
        list.add(list.size(), entity);//添加到最后一个位置
        list.subList(0, list.size()).forEach(a -> {
            a.setTmax(a.getMaxScore());
            a.setRate(a.getRate().setScale(2, BigDecimal.ROUND_HALF_UP));
        });
        return list;
    }

    //涉水责考评分列表
    @Override
    public List<ExportScoreWaterEntity> getBlameScoreWaterList() {
        List<ExportScoreWaterEntity> list = sourceCountMapper.getBlameScoreWaterList();
        ExportScoreWaterEntity entity = sourceCountMapper.getBlameScoreWaterTotal();
        list.add(list.size(), entity);//添加到最后一个位置
        list.subList(0, list.size()).forEach(a ->
                a.setRate(a.getRate().setScale(2, BigDecimal.ROUND_HALF_UP)));
        return list;
    }


    //统计状态数
    @Override
    public Map getStatusCount(MonitorTaskParam entity) {
        Map map = HashMapUtils.toCamel(sourceCountMapper.getStatusCount(entity));
        Integer loggedin = sourceCountMapper.getLoggedinForTownCode(entity); //已登录企业数
        map.put("loggedin", loggedin);
        return map;
    }

    @Override
    public List<Map> getAdditionNumber(MonitorTaskParam entity) {
        return HashMapUtils.toCamel(sourceCountMapper.getAdditionNumber(entity));
    }

    @Override
    public List<Map> getAggregateNumber(MonitorTaskParam entity) {
        return HashMapUtils.toCamel(sourceCountMapper.getAggregateNumber(entity));
    }

    @Override
    public List<TaskSourceEntity> getTotalAggregate(MonitorTaskParam entity) {
        return sourceCountMapper.getTotalAggregate(entity);
    }

}
