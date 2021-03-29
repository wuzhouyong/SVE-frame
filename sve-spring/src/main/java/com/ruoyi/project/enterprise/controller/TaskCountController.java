package com.ruoyi.project.enterprise.controller;

import com.ruoyi.common.domain.ResultEntity;
import com.ruoyi.framework.aspectj.lang.annotation.DataScope;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.enterprise.domain.MonitorTaskParam;
import com.ruoyi.project.enterprise.domain.TaskSourceEntity;
import com.ruoyi.project.enterprise.service.TaskCountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author solang
 * @date 2020-07-22 10:19 统计 statistics
 */

@RestController
@RequestMapping("/task/count")
public class TaskCountController extends BaseController {

    @Autowired
    private TaskCountService taskCountService;

    @GetMapping("/summary")
    @DataScope(regionAlias = "s") //任务进度总览   /task/count/summary 汇总
    public ResultEntity getTaskSummary(MonitorTaskParam entity) {
        return ResultEntity.success(taskCountService.getTaskSummary(entity));
    }

    @GetMapping("/town")  //获取镇街任务统计 /task/count/town
    public ResultEntity getTownTaskCount(Long[] tagHeadIds) {
        if (tagHeadIds != null && tagHeadIds.length != 0) {
            return ResultEntity.success(taskCountService.getTownTaskCount(tagHeadIds));
        }
        return ResultEntity.success(new Long[0]);
    }

    @GetMapping("/schedule")
    @DataScope(regionAlias = "d") //任务进度统计 /task/count/schedule  参数entity只为了数据权限
    public ResultEntity getScheduleTaskCount(MonitorTaskParam entity) {
        return ResultEntity.success(taskCountService.getScheduleTaskCount(entity));
    }

    @GetMapping("/construct")
    @DataScope(regionAlias = "s")//施工单位任务统计 /task/count/construct       tagHeadIds='1002,1003'
    public ResultEntity getConstructTaskCount(MonitorTaskParam entity) {
        if (entity.getTagHeadIds() != null && entity.getTagHeadIds().length != 0) {
            return ResultEntity.success(taskCountService.getConstructTaskCount(entity));
        }
        return ResultEntity.success(new Long[0]);
    }

    @GetMapping("/completed")
    @DataScope(regionAlias = "d")//分局排名 /task/count/completed
    public ResultEntity getCompletedSummary(MonitorTaskParam entity) {
        return ResultEntity.success(taskCountService.getCompletedSummary(entity));
    }

    @GetMapping("/mission")
    @DataScope(regionAlias = "d")//任务总数 /task/count/mission
    public ResultEntity getMissionRank(MonitorTaskParam entity) {
        return ResultEntity.success(taskCountService.getMissionRank(entity));
    }

    @PostMapping("/mission_blame")
    @DataScope(regionAlias = "s")//任务总数计算 /task/count/mission_blame
    public ResultEntity getMissionRankCount(@RequestBody MonitorTaskParam entity) {
        return ResultEntity.success(taskCountService.getMissionRankCount(entity));
    }

    @PostMapping("/accepted")
    @DataScope(regionAlias = "s")//已接收任务总数计算 /task/count/accepted
    public ResultEntity getMissionAcceptedCount(@RequestBody MonitorTaskParam entity) {
        return ResultEntity.success(taskCountService.getMissionAcceptedCount(entity));
    }

    @GetMapping("/leaderboard")
    @DataScope(regionAlias = "s")//总排名 /task/count/leaderboard  参数entity只为了数据权限
    public ResultEntity getTownLeaderboard(MonitorTaskParam entity) {
        return ResultEntity.success(taskCountService.getTownLeaderboard(entity));
    }

    @GetMapping("/notlogin")
    @DataScope(regionAlias = "s")//未登录过企业总数 /task/count/notlogin
    public ResultEntity getCountNotlogin(String townCode) {
        return ResultEntity.success(taskCountService.getCountNotlogin(townCode));
    }

    @GetMapping("/score")
    @DataScope(regionAlias = "s")//责考评分  /task/count/score
    public ResultEntity getBlameScore(MonitorTaskParam entity) {
        return ResultEntity.success(taskCountService.getBlameScore(entity));
    }

    @GetMapping("/score_list") //总责考评分列表  /task/count/score_list
    public ResultEntity getBlameScoreLis() {
        return ResultEntity.success(taskCountService.getBlameScoreList());
    }

    @GetMapping("/score_gas_list") //涉气责考评分列表  /task/count/score_gas_list
    public ResultEntity getBlameScoreGasList() {
        return ResultEntity.success(taskCountService.getBlameScoreGasList());
    }

    @GetMapping("/score_water_list") //涉水责考评分列表  /task/count/score_water_list
    public ResultEntity getBlameScoreWaterList() {
        return ResultEntity.success(taskCountService.getBlameScoreWaterList());
    }

    @GetMapping("/status_count")
    @DataScope(regionAlias = "s")//统计状态数  /task/count/score
    public ResultEntity getStatusCount(MonitorTaskParam entity) {
        return ResultEntity.success(taskCountService.getStatusCount(entity));
    }

    @GetMapping("/addition_number") //总任务数  /task/count/addition_number
    @DataScope(regionAlias = "s")
    public ResultEntity getAdditionNumber(MonitorTaskParam entity) {
        return ResultEntity.success(taskCountService.getAdditionNumber(entity));
    }

    @PostMapping("/aggregate_number") //总计  /task/count/aggregate_number
    @DataScope(regionAlias = "s")
    public ResultEntity getAggregateNumber(@RequestBody MonitorTaskParam entity) {
        return ResultEntity.success(taskCountService.getAggregateNumber(entity));
    }

    @PostMapping("/aggregate")  //所有状态总计  /task/count/aggregate
    public TableDataInfo getTotalAggregate(@RequestBody MonitorTaskParam entity) {
        startPage();
        List<TaskSourceEntity> list = taskCountService.getTotalAggregate(entity);
        return getDataTable(list);
    }


}
