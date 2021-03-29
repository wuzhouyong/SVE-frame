package com.ruoyi.project.dgom.mapper;

import com.ruoyi.project.dgom.domain.check_report.CheckReportModel;
import com.ruoyi.project.dgom.domain.check_report.CheckReportParam;
import com.ruoyi.project.dgom.domain.check_report.CheckReportResult;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CheckReportMapper {
    List<CheckReportResult> getCheckReportList(CheckReportParam param);
    CheckReportModel getCheckReportModel(@Param("reportId") int reportId);
    int updateCheckReport(CheckReportModel model);
}
