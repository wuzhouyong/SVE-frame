package com.ruoyi.project.dgom.service.impl;

import com.ruoyi.framework.aspectj.lang.annotation.DataSource;
import com.ruoyi.framework.aspectj.lang.enums.DataSourceType;
import com.ruoyi.project.dgom.domain.check_report.CheckReportModel;
import com.ruoyi.project.dgom.domain.check_report.CheckReportParam;
import com.ruoyi.project.dgom.domain.check_report.CheckReportResult;
import com.ruoyi.project.dgom.mapper.CheckReportMapper;
import com.ruoyi.project.dgom.service.ICheckReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@DataSource(value = DataSourceType.HBONLINE)
public class CheckReportService implements ICheckReportService {
    @Autowired
    private CheckReportMapper mapper;

    @Override
    public List<CheckReportResult> getCheckReportList(CheckReportParam param) {
        return mapper.getCheckReportList(param);
    }

    @Override
    public CheckReportModel getCheckReportModel(int reportId) {
        return mapper.getCheckReportModel(reportId);
    }

    @Override
    public int updateCheckReport(CheckReportModel model) {
        return mapper.updateCheckReport(model);
    }
}
