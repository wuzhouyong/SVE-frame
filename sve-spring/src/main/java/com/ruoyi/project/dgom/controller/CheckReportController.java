package com.ruoyi.project.dgom.controller;

import com.ruoyi.common.domain.ResultEntity;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.dgom.domain.check_report.CheckReportModel;
import com.ruoyi.project.dgom.domain.check_report.CheckReportParam;
import com.ruoyi.project.dgom.domain.check_report.CheckReportResult;
import com.ruoyi.project.dgom.mapper.SuperviseTaskMapper;
import com.ruoyi.project.dgom.service.impl.CheckReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/dgom/check_report")
public class CheckReportController extends BaseController {
    @Autowired
    private CheckReportService service;

    @Autowired
    private SuperviseTaskMapper taskMapper;

    @PostMapping("/list")
    public TableDataInfo getCheckPointList(@RequestBody CheckReportParam param) {
        if (param.getTownCode() != null) {
            param.setTownId(taskMapper.getOMTownIdByCode(param.getTownCode()));
        }
        startPage();
        List<CheckReportResult> list = service.getCheckReportList(param);
        return getDataTable(list);
    }

    @PostMapping("/edit")
    public ResultEntity updateCheckReport(@RequestBody CheckReportModel model) {
        CheckReportModel origin = service.getCheckReportModel(model.getReportId());
        if (origin.getStatus() == 1) {
            return ResultEntity.error("信息已录入,不允许修改");
        }
        int res = service.updateCheckReport(model);
        if (res > 0) {
            return ResultEntity.success("检查信息录入成功");
        }
        return ResultEntity.error("检查信息录入失败");
    }
}
