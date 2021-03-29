package com.ruoyi.project.dgom.service;

import com.ruoyi.project.dgom.domain.check_report.CheckReportModel;
import com.ruoyi.project.dgom.domain.check_report.CheckReportParam;
import com.ruoyi.project.dgom.domain.check_report.CheckReportResult;

import java.util.List;

public interface ICheckReportService {
    List<CheckReportResult> getCheckReportList(CheckReportParam param);
    CheckReportModel getCheckReportModel(int reportId);
    int updateCheckReport(CheckReportModel model);
}
