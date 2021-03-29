package com.ruoyi.project.youyan.controller;

import com.ruoyi.common.domain.*;
import com.ruoyi.common.utils.DateStringUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.PageDomain;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.framework.web.page.TableSupport;
import com.ruoyi.project.common.poi.MapDocumentUtil;
import com.ruoyi.project.youyan.domain.FacilityRelationEntity;
import com.ruoyi.project.youyan.service.DataSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController("YYDSController")
@RequestMapping("/youyan/data_search")
public class DataSearchController extends BaseController {
    
    @Autowired
    private DataSearchService dataSearchService;

    /**
     * 显示数据列表
     */
    @PostMapping("/list")
    public ResultEntity list(@RequestBody DataSearchParam entity) {
        PageDomain pageDomain = TableSupport.buildPageRequest();
        int pageNum = pageDomain.getPageNum();
        if (!StringUtils.isEmpty(SecurityUtils.getLoginUser().getUser().getSourceId())) {
            entity.setSourceId(SecurityUtils.getLoginUser().getUser().getSourceId());
        }
        Calendar calendar = new GregorianCalendar();
        calendar.add(Calendar.DAY_OF_MONTH, -6);
        Date sDate = calendar.getTime();

        Calendar endCal = new GregorianCalendar();
        endCal.add(Calendar.DAY_OF_MONTH, 1);
        Date eDate = endCal.getTime();

        String starTime = DateStringUtils.dateToString("yyyy-MM-dd", sDate);
        String endTime = DateStringUtils.dateToString("yyyy-MM-dd", eDate);
        entity.setStartDate(starTime);
        entity.setEndDate(endTime);

        List<DataSearchEntity> getList = dataSearchService.getElemList(entity);
        int count = dataSearchService.getTotal(entity);
        return getDataList(getList, pageNum, count);
    }

    @GetMapping("/facility")
    public ResultEntity facility(String sourceId) {
        if (sourceId.isEmpty()) {
            if (!StringUtils.isEmpty(SecurityUtils.getLoginUser().getUser().getSourceId())) {
                sourceId = SecurityUtils.getLoginUser().getUser().getSourceId();
            }
        }
        List<FacilityRelationEntity> list = dataSearchService.selectFacility(sourceId);
        return ResultEntity.success(list);
    }

    @PostMapping("/lastdata")
    public ResultEntity lastData(String sourceId) {
        if (!StringUtils.isEmpty(SecurityUtils.getLoginUser().getUser().getSourceId())) {
            sourceId = SecurityUtils.getLoginUser().getUser().getSourceId();
        }
        List<DataSearchEntity> getLastData = dataSearchService.getLastData(sourceId);
        return ResultEntity.success(getLastData);
    }

    @PostMapping("/real")
    public ResultEntity real(@RequestBody DataSearchParam entity) {
        if (!StringUtils.isEmpty(SecurityUtils.getLoginUser().getUser().getSourceId())) {
            entity.setSourceId(SecurityUtils.getLoginUser().getUser().getSourceId());
        }
        return ResultEntity.success(dataSearchService.getReal(entity));
    }

    @PostMapping("/export_real") //动态表头导出
    public AjaxResult realExport(@RequestBody DataSearchParam entity) {
        if (!StringUtils.isEmpty(SecurityUtils.getLoginUser().getUser().getSourceId())) {
            entity.setSourceId(SecurityUtils.getLoginUser().getUser().getSourceId());
        }
        String fullName = dataSearchService.getFullNameBySourceId(entity.getSourceId());
        DataSearchResult result = dataSearchService.getReal(entity);
        List<Map<String, Object>> cols = (List<Map<String, Object>>) result.getCols();
        List<Map<String, Object>> data = result.getData();
        //第一个参数data,第二个参数cols，第三个参数：表头中"title"值相同的就有子集合，第四个参数:表头中"dataIndex"的值对应数据中动态的值
        MapDocumentUtil doc = new MapDocumentUtil(data, cols, "title", "dataIndex");
        String fileName = "油烟行业_" + fullName + "_实时数据";
        return doc.exportExcel(fileName);
    }

    @PostMapping("/minute")
    public ResultEntity minute(@RequestBody DataSearchParam entity) {
        if (!StringUtils.isEmpty(SecurityUtils.getLoginUser().getUser().getSourceId())) {
            entity.setSourceId(SecurityUtils.getLoginUser().getUser().getSourceId());
        }
        return ResultEntity.success(dataSearchService.getMinute(entity));
    }

    @PostMapping("/export_minute")
    public AjaxResult minuteExport(@RequestBody DataSearchParam entity) {
        if (!StringUtils.isEmpty(SecurityUtils.getLoginUser().getUser().getSourceId())) {
            entity.setSourceId(SecurityUtils.getLoginUser().getUser().getSourceId());
        }
        String fullName = dataSearchService.getFullNameBySourceId(entity.getSourceId());
        DataSearchResult result = dataSearchService.getMinute(entity);
        List<Map<String, Object>> cols = (List<Map<String, Object>>) result.getCols();
        List<Map<String, Object>> data = result.getData();
        //第一个参数data,第二个参数cols，第三个参数：表头中"title"值相同的就有子集合，第四个参数:表头中"dataIndex"的值对应数据中动态的值
        MapDocumentUtil doc = new MapDocumentUtil(data, cols, "title", "dataIndex");
        String fileName = "油烟行业_" + fullName + "_分钟数据";
        return doc.exportExcel(fileName);
    }

    @PostMapping("/hour")
    public ResultEntity hour(@RequestBody DataSearchParam entity) {
        if (!StringUtils.isEmpty(SecurityUtils.getLoginUser().getUser().getSourceId())) {
            entity.setSourceId(SecurityUtils.getLoginUser().getUser().getSourceId());
        }
        return ResultEntity.success(dataSearchService.getHour(entity));
    }

    @PostMapping("/export_hour")
    public AjaxResult hourExport(@RequestBody DataSearchParam entity) {
        if (!StringUtils.isEmpty(SecurityUtils.getLoginUser().getUser().getSourceId())) {
            entity.setSourceId(SecurityUtils.getLoginUser().getUser().getSourceId());
        }
        String fullName = dataSearchService.getFullNameBySourceId(entity.getSourceId());
        DataSearchResult result = dataSearchService.getHour(entity);
        List<Map<String, Object>> cols = (List<Map<String, Object>>) result.getCols();
        List<Map<String, Object>> data = result.getData();
        //第一个参数data,第二个参数cols，第三个参数：表头中"title"值相同的就有子集合，第四个参数:表头中"dataIndex"的值对应数据中动态的值
        MapDocumentUtil doc = new MapDocumentUtil(data, cols, "title", "dataIndex");
        String fileName = "油烟行业_" + fullName + "_小时数据";
        return doc.exportExcel(fileName);
    }

    @PostMapping("/day")
    public ResultEntity day(@RequestBody DataSearchParam entity) {
        if (!StringUtils.isEmpty(SecurityUtils.getLoginUser().getUser().getSourceId())) {
            entity.setSourceId(SecurityUtils.getLoginUser().getUser().getSourceId());
        }
        return ResultEntity.success(dataSearchService.getDay(entity));
    }

    @PostMapping("/export_day")
    public AjaxResult dayExport(@RequestBody DataSearchParam entity) {
        if (!StringUtils.isEmpty(SecurityUtils.getLoginUser().getUser().getSourceId())) {
            entity.setSourceId(SecurityUtils.getLoginUser().getUser().getSourceId());
        }
        String fullName = dataSearchService.getFullNameBySourceId(entity.getSourceId());
        DataSearchResult result = dataSearchService.getDay(entity);
        List<Map<String, Object>> cols = (List<Map<String, Object>>) result.getCols();
        List<Map<String, Object>> data = result.getData();
        //第一个参数data,第二个参数cols，第三个参数：表头中"title"值相同的就有子集合，第四个参数:表头中"dataIndex"的值对应数据中动态的值
        MapDocumentUtil doc = new MapDocumentUtil(data, cols, "title", "dataIndex");
        String fileName = "油烟行业_" + fullName + "_日数据";
        return doc.exportExcel(fileName);
    }


    @PostMapping("hoursummary")
    public ResultEntity hourSummary(@RequestBody DataSearchParam entity) {
        if (!StringUtils.isEmpty(SecurityUtils.getLoginUser().getUser().getSourceId())) {
            entity.setSourceId(SecurityUtils.getLoginUser().getUser().getSourceId());
        }
        return dataSearchService.getHourSummary(entity);
    }

    @PostMapping("daysummary")
    public ResultEntity daySummary(@RequestBody DataSearchParam entity) {
        if (!StringUtils.isEmpty(SecurityUtils.getLoginUser().getUser().getSourceId())) {
            entity.setSourceId(SecurityUtils.getLoginUser().getUser().getSourceId());
        }
        return dataSearchService.getDaySummary(entity);
    }

    @PostMapping("/transfer")  // /youyan/data_search/transfer   sourceId=158951 日传输率
    public TableDataInfo getTransferDayRate(@RequestBody DataSearchParam entity) {
        startPage();
        List<TransferRateEntity> list = dataSearchService.getTransferDayRateList(entity);
        return getDataTable(list);
    }
    @PostMapping("/transfer_month")  // /youyan/data_search/transfer_month   sourceId=158951 月传输率
    public TableDataInfo getTransferMonthRate(@RequestBody DataSearchParam entity) {
        startPage();
        List<TransferRateEntity> list = dataSearchService.getTransferMonthRateList(entity);
        return getDataTable(list);
    }
}
