package com.ruoyi.project.dgom.controller;

import com.ruoyi.common.domain.ResultEntity;
import com.ruoyi.common.utils.HashMapUtils;
import com.ruoyi.framework.config.RuoYiConfig;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.common.poi.DocumentUtil;
import com.ruoyi.project.dgom.domain.DailyAvgStatistic;
import com.ruoyi.project.dgom.domain.DutyExport;
import com.ruoyi.project.dgom.domain.OutpaceTransStatistic;
import com.ruoyi.project.dgom.service.DutyService;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/dgom/duty")
public class DutyController extends BaseController {
    @Autowired
    private DutyService service;

    @GetMapping("/substation_weekly_report")
    public ResultEntity getSubStationWeeklyReport(@RequestParam Map<String, String> param) {
        return ResultEntity.success(service.getSubStationWeeklyReport(param));
    }

    //传输率与超标统计导出 substation_weekly_report_export
    @GetMapping("/substation_weekly_report_export")
    public AjaxResult getSubStationWeeklyReportExport(@RequestParam Map<String, String> param) {
        List<OutpaceTransStatistic> data = service.getSubStationWeeklyReport(param);
        DocumentUtil doc = new DocumentUtil(data);
        doc.addColumn("街镇", "townName", 15);
        doc.addColumn("企业数", "sourceCount", 15);
        doc.addColumn("监控点数", "checkpointCount", 15);
        doc.addColumn("传输率", "transportRate", 15);
        doc.addColumn("传输率低于90%企业数", "lessthanCount", 15);
        doc.addColumn("超标任务(条)", "overCount", 15);
        doc.addColumn("缺失任务(条)", "lostCount", 15);
        doc.addColumn("零值任务(条)", "zeroCount", 15);
        doc.addColumn("负值任务(条)", "negativeCount", 15);
        doc.addColumn("恒值任务(条)", "staticCount", 15);
        doc.addColumn("企业未处理数", "unhandleCount", 15);
        doc.addColumn("分局完成", "finishCount", 15);
        doc.addColumn("分局完成率", "finishRate", 15);
        String fileName = "超标任务统计列表";
        return doc.exportExcel(fileName);
    }

    @GetMapping("/enterprise_weekly_report")
    public ResultEntity getEnterpriseWeeklyReport(@RequestParam Map<String, String> param) {
        return ResultEntity.success(service.getEnterpriseWeeklyReport(param));
    }

    //超标任务统计导出 enterprise_weekly_report_export
    @GetMapping("/enterprise_weekly_report_export")
    public AjaxResult getEnterpriseWeeklyReportExport(@RequestParam Map<String, String> param) {
        List<DutyExport> data = service.getEnterpriseWeeklyReportExport(param);
        DocumentUtil doc = new DocumentUtil(data);
       doc.addColumn("街镇", "townName", 15);
       doc.addColumn("企业名称", "sourceName", 45);
       doc.addColumn("超标任务条数", "overCount", 15);
        return doc.exportExcel("超标任务统计列表");
    }

    @GetMapping("/duty_diary")
    public ResultEntity getDutyDiary(@RequestParam Map<String, String> param) {
        return ResultEntity.success(service.getDutyDiary(param));
    }

    @GetMapping("/export_duty_diary")
    public AjaxResult exportDutyDiary(@RequestParam Map<String, String> param) {
        try {
            File sourceFile = ResourceUtils.getFile("classpath:template/duty_diary_report.xlsx");
            String path = sourceFile.getPath();
            InputStream is = new FileInputStream(path);
            Workbook wb = WorkbookFactory.create(is);
            is.close();
            Map<String, String> result = service.getDutyDiary(param);
            CellStyle cellStyle = wb.createCellStyle();
            cellStyle.setWrapText(true);
//            cellStyle.setAlignment(HorizontalAlignment.CENTER);
            cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
            cellStyle.setBorderRight(BorderStyle.DASHED);
            Sheet sheet = wb.getSheet("Sheet1");
            Cell cell = sheet.getRow(1).getCell(0);
            cell.setCellValue(param.get("startDate"));
            cell = sheet.getRow(3).getCell(2);
            cell.setCellValue(result.get("h_w_num"));
            cell = sheet.getRow(3).getCell(3);
            cell.setCellValue(result.get("h_src_num"));
            cell = sheet.getRow(3).getCell(4);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(result.get("h_w_dev_info"));
            cell = sheet.getRow(4).getCell(2);
            cell.setCellValue(result.get("h_g_num"));
            cell = sheet.getRow(4).getCell(4);
            cell.setCellValue(result.get("h_g_dev_info"));
            cell = sheet.getRow(5).getCell(2);
            cell.setCellValue(result.get("d_w_num"));
            cell = sheet.getRow(5).getCell(3);
            cell.setCellValue(result.get("d_src_num"));
            cell = sheet.getRow(5).getCell(4);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(result.get("d_w_dev_info"));
            cell = sheet.getRow(6).getCell(2);
            cell.setCellValue(result.get("d_g_num"));
            cell = sheet.getRow(6).getCell(4);
            cell.setCellValue(result.get("d_g_dev_info"));
            cell = sheet.getRow(7).getCell(3);
            cell.setCellValue(result.get("trans_rate_num"));
            cell = sheet.getRow(7).getCell(4);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(result.get("trans_rate_info"));
            cell = sheet.getRow(8).getCell(3);
            cell.setCellValue(result.get("zero_record_num"));
            cell = sheet.getRow(8).getCell(4);
            cell.setCellValue(result.get("zero_record_info"));
            String filename = "自动监控数据平台值守日记.xlsx";
            String downloadPath = RuoYiConfig.getDownloadPath() + filename;
            File desc = new File(downloadPath);
            if (!desc.getParentFile().exists()) {
                desc.getParentFile().mkdirs();
            }
            OutputStream out = new FileOutputStream(downloadPath);
            wb.write(out);
            return AjaxResult.success(filename);
        } catch (IOException | InvalidFormatException e) {
            e.printStackTrace();
        }
        return AjaxResult.error("导出失败");
    }

    @GetMapping("/abnor_process_result")
    public ResultEntity getAbnorProcessResult(@RequestParam Map<String, String> param) {
        return ResultEntity.success(service.getAbnorProcessResult(param));
    }

    //异常处理结果导出 abnor_process_result_export
    @GetMapping("/abnor_process_result_export")
    public AjaxResult getAbnorProcessResultExport(@RequestParam Map<String, String> param) {
        List<DutyExport> data = service.getAbnorProcessResultExport(param);
        DocumentUtil doc = new DocumentUtil(data);
        doc.addColumn("企业名称", "sourceName", 45);
        doc.addColumn("街镇", "townName", 18);
        doc.addColumn("异常开始时间", "startDate", 18);
        doc.addColumn("异常原因", "abnorDesc", 80);
        doc.addColumn("是否已报告", "isReported", 18);
        doc.addColumn("报告时间", "dateReported", 18);
        doc.addColumn("是否恢复正常", "isReturnNormal", 18);
        doc.addColumn("恢复正常时间", "returnNormalDate", 18);
        doc.addColumn("备注", "processMemo", 100);
        return doc.exportExcel("异常处理结果列表");
    }

    @GetMapping("/abnor_summary")
    public ResultEntity getAbnorSummary(@RequestParam Map<String, String> param) {
        return ResultEntity.success(service.getAbnorSummary(param));
    }

    @GetMapping("/abnor_summary_export")
    public AjaxResult getAbnorSummaryExport(@RequestParam Map<String, String> param) {
        try {
            File sourceFile = ResourceUtils.getFile("classpath:template/duty_abnor_report.xlsx");
            String path = sourceFile.getPath();
            InputStream is = new FileInputStream(path);
            Workbook wb = WorkbookFactory.create(is);
            is.close();
            Map<String, List<Map>> map = service.getAbnorSummary(param);
            CellStyle cellStyle = wb.createCellStyle();
            cellStyle.setWrapText(true);
            cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
            cellStyle.setBorderRight(BorderStyle.DASHED);
            CellStyle style_left = wb.createCellStyle();
            style_left.setAlignment(HorizontalAlignment.RIGHT);
            Sheet sheet = wb.getSheet("Sheet1");
            Cell cell = sheet.getRow(1).getCell(0);
            cell.setCellValue(param.get("startDate"));
            cell.setCellStyle(style_left);
            List<Map> cates = map.get("cates");
            List<Map> cates1 = cates.stream().filter(p -> p.get("zsubcategoryid") != null).collect(Collectors.toList());
            Map laji = cates1.stream().filter(p -> p.get("zsubcategoryid").toString().equals("5042")).findFirst().get();
            Map wushui = cates1.stream().filter(p -> p.get("zsubcategoryid").toString().equals("201")).findFirst().get();
            Map other = cates1.stream().filter(p -> p.get("zsubcategoryid").toString().equals("other")).findFirst().get();
            Map zhongdian_other = cates1.stream().filter(p -> p.get("zsubcategoryid").toString().equals("zhongdian_other")).findFirst().get();
            CellStyle dataStyle = wb.createCellStyle();
            Font font = wb.createFont();
            font.setFontHeightInPoints((short) 11);
            font.setFontName("宋体");
            dataStyle.setFont(font);
            dataStyle.setAlignment(HorizontalAlignment.CENTER);
            dataStyle.setVerticalAlignment(VerticalAlignment.CENTER);
            dataStyle.setBorderRight(BorderStyle.THIN);
            dataStyle.setBorderBottom(BorderStyle.THIN);
            dataStyle.setBorderLeft(BorderStyle.THIN);
            dataStyle.setBorderTop(BorderStyle.THIN);
            dataStyle.setRightBorderColor(IndexedColors.BLACK.index);
            dataStyle.setBottomBorderColor(IndexedColors.BLACK.index);
            dataStyle.setLeftBorderColor(IndexedColors.BLACK.index);
            dataStyle.setTopBorderColor(IndexedColors.BLACK.index);
            insertCell(sheet, laji, 4, dataStyle);
            insertCell(sheet, wushui, 5, dataStyle);
            insertCell(sheet, other, 6, dataStyle);
            insertCell(sheet, zhongdian_other, 7, dataStyle);
            List<Map> towns = HashMapUtils.orderValue(map.get("towns"), "zsuperiorid");
            short rowHeight = sheet.getRow(8).getHeight();
            for (int i = 0; i < towns.size(); i++) {
                Map town = towns.get(i);
                int rowIndex = 8 + i;
                try {
                    cell = sheet.getRow(rowIndex).getCell(1);
                } catch (Exception ex) {
                    Row row = sheet.createRow(rowIndex);
                    row.setHeight(rowHeight);
                    cell = row.createCell(1);
                }
                String townName = town.get("town_name").toString();
                cell.setCellValue(townName);
                cell.setCellStyle(dataStyle);
                try {
                    sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex, 1, 2));
                } catch (Exception ignored) {
                }
                insertCell(sheet, town, rowIndex, dataStyle);
            }
            sheet.addMergedRegion(new CellRangeAddress(8, 8 + towns.size() - 1, 0, 0));
            Map summary = cates.stream().filter(p -> p.get("town_name") != null).findFirst().get();
            Row row = sheet.createRow(8 + towns.size());
            row.setHeight(rowHeight);
            cell = row.createCell(0);
            cell.setCellValue(summary.get("town_name").toString());
            sheet.addMergedRegion(new CellRangeAddress(8 + towns.size(), 8 + towns.size(), 0, 2));
            cell.setCellStyle(dataStyle);
            insertCell(sheet, summary, 8 + towns.size(), dataStyle);
            String filename = "自动监控数据平台异常汇总.xlsx";
            String downloadPath = RuoYiConfig.getDownloadPath() + filename;
            File desc = new File(downloadPath);
            if (!desc.getParentFile().exists()) {
                desc.getParentFile().mkdirs();
            }
            OutputStream out = new FileOutputStream(downloadPath);
            wb.write(out);
            return AjaxResult.success(filename);
        } catch (IOException | InvalidFormatException e) {
            e.printStackTrace();
        }
        return AjaxResult.error("导出失败");
    }

    private void insertCell(Sheet sheet, Map map, int row, CellStyle style) {
        Cell cell = sheet.getRow(row).createCell(3);
        cell.setCellValue(map.get("day_cnt").toString());
        cell.setCellStyle(style);
        cell = sheet.getRow(row).createCell(4);
        cell.setCellValue(map.get("two_day_cnt").toString());
        cell.setCellStyle(style);
        cell = sheet.getRow(row).createCell(5);
        cell.setCellValue(map.get("four_day_cnt").toString());
        cell.setCellStyle(style);
        cell = sheet.getRow(row).createCell(6);
        cell.setCellValue(map.get("rate_less_90").toString());
        cell.setCellStyle(style);
    }

    @GetMapping("/abnor_enterprise_summary")
    public ResultEntity getAbnorEnterpriseSummary(@RequestParam Map<String, String> param) {
        return ResultEntity.success(service.getAbnorEnterpriseSummary(param));
    }

    //异常企业统计导出 /abnor_enterprise_summary_export
    @GetMapping("/abnor_enterprise_summary_export")
    public AjaxResult getAbnorEnterpriseSummaryExport(@RequestParam Map<String, String> param) {
        List<DutyExport> data = service.getAbnorEnterpriseSummaryExport(param);
        DocumentUtil doc = new DocumentUtil(data);
        doc.addColumn("街镇", "townName", 18);
        doc.addColumn("企业名称", "zdatasourname", 18);
        doc.addColumn("数据异常线索", "rateLess90", 80);
        doc.addColumn("责任单位", "sourceName", 18);
        doc.addColumn("备注", "dateReported", 18);
        return doc.exportExcel("异常企业统计列表");
    }

    @GetMapping("/trans_rate_less_90")
    public ResultEntity getTransRateLess90(@RequestParam Map<String, String> param) {
        return ResultEntity.success(service.getTransRateLess90(param));
    }

    //完整率低于90%导出  trans_rate_less_90_export
    @GetMapping("/trans_rate_less_90_export")
    public AjaxResult getTransRateLess90Export(@RequestParam Map<String, String> param) {
        List<DutyExport> data = service.getTransRateLess90Export(param);
        DocumentUtil doc = new DocumentUtil(data);
        doc.addColumn("企业名称", "zdatasourname", 30);
        doc.addColumn("街镇", "townName", 15);
        doc.addColumn("传输完整率", "rate", 15);
        return doc.exportExcel("完整率列表");
    }

    @GetMapping("/daily_avg_statistic")
    public TableDataInfo getDailyAvgStatistic(@RequestParam Map<String, String> param) {
        startPage();
        List<DailyAvgStatistic> list = service.getDailyAvgStatistic(param);
        return getDataTable(list);
    }

    //日均值超标统计 daily_avg_statistic_export
    @GetMapping("/daily_avg_statistic_export")
    public AjaxResult getDailyAvgStatisticExport(@RequestParam Map<String, String> param) {
        List<DailyAvgStatistic> data = service.getDailyAvgStatistic(param);
        DocumentUtil doc = new DocumentUtil(data);
        doc.addColumn("街镇", "townName", 15);
        doc.addColumn("企业名称", "sourceName", 45);
        doc.addColumn("责任单位", "deptName", 45);
        doc.addColumn("检测点", "checkpointName", 20);
        doc.addColumn("因子", "elementName", 20);
        doc.addColumn("执行标准", "standard", 20);
        doc.addColumn("检测日期", "dataDate", 20);
        doc.addColumn("检测值", "amount", 20);
        doc.addColumn("倍数", "multiple", 15);
        return doc.exportExcel("监控任务清单记录");
    }


}
