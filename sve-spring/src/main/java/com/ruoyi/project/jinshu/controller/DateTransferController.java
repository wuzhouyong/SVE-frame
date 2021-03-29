package com.ruoyi.project.jinshu.controller;

import com.ruoyi.common.domain.ResultEntity;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.jinshu.service.DataSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("js1_jinshu")
@RequestMapping("/jinshu/transfer")
public class DateTransferController extends BaseController {

    @Autowired
    private DataSearchService dataSearchService;

    // 金属近30天传输率曲线图数据-Real
    @GetMapping("/real")  //   /jinshu/transfer/real
    public ResultEntity getReal() {

        if (!StringUtils.isEmpty(SecurityUtils.getLoginUser().getUser().getSourceId())) {
            String sourceId = SecurityUtils.getLoginUser().getUser().getSourceId();
            return dataSearchService.getRealDataList(sourceId);
        } else
             return ResultEntity.error("污染源不能为空");

    }

    // 金属近30天传输率曲线图数据-Day
    @GetMapping("/day")  //   /jinshu/transfer/day
    public ResultEntity getDay() {

        if (!StringUtils.isEmpty(SecurityUtils.getLoginUser().getUser().getSourceId())) {
            String sourceId = SecurityUtils.getLoginUser().getUser().getSourceId();
            return dataSearchService.getDayDataList(sourceId);
        } else
             return ResultEntity.error("污染源不能为空");
    }

    // 金属近30天传输率曲线图数据-Hour
    @GetMapping("/hour")  //   /jinshu/transfer/hour
    public ResultEntity getHour() {

        if (!StringUtils.isEmpty(SecurityUtils.getLoginUser().getUser().getSourceId())) {
            String sourceId = SecurityUtils.getLoginUser().getUser().getSourceId();
            return dataSearchService.getHourDataList(sourceId);
        } else
             return ResultEntity.error("污染源不能为空");
    }

    // 金属近30天传输率曲线图数据-Minute
    @GetMapping("/minute")  //   /jinshu/transfer/minute
    public ResultEntity getMinute() {

        if (!StringUtils.isEmpty(SecurityUtils.getLoginUser().getUser().getSourceId())) {
            String sourceId = SecurityUtils.getLoginUser().getUser().getSourceId();
            return dataSearchService.getMinuteDataList(sourceId);
        } else
             return ResultEntity.error("污染源不能为空");
    }

    // 获取金属近30天传输率图总数据,分钟,小时,天 数据
    @GetMapping("/chart")  //   /jinshu/transfer/chart
    public ResultEntity getTransferCharts() {
        if (!StringUtils.isEmpty(SecurityUtils.getLoginUser().getUser().getSourceId())) {
            String sourceId = SecurityUtils.getLoginUser().getUser().getSourceId();
            return dataSearchService.getTransferCharts(sourceId);
        } else
            return ResultEntity.error("污染源不能为空");
    }

    // 获取企业联网状态总条数
    @GetMapping("/procstate")  //   /jinshu/transfer/procstate
    public ResultEntity getProcStateCount() {
        if (!StringUtils.isEmpty(SecurityUtils.getLoginUser().getUser().getSourceId())) {
            String sourceId = SecurityUtils.getLoginUser().getUser().getSourceId();
            Integer isAcc = dataSearchService.getProcStateCount(sourceId);
            return (isAcc >= 0 ? ResultEntity.success(isAcc):ResultEntity.error("查询数据失败"));
        } else
            return ResultEntity.error("污染源不能为空");
    }

}
