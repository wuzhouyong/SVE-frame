package com.ruoyi.project.outwater.controller;

import com.ruoyi.common.domain.*;
import com.ruoyi.common.utils.HashMapUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.common.poi.MapDocumentUtil;
import com.ruoyi.project.outwater.domain.entity.ElemDataEntity;
import com.ruoyi.project.outwater.service.DataSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController("OWDSController")
@RequestMapping("/outwater/data_search")
public class DataSearchController extends BaseController {

    @Autowired
    private DataSearchService dataSearchService;

    @PostMapping("/real")
    public ResultEntity real(@RequestBody DataSearchParam entity) {
        if (!StringUtils.isEmpty(SecurityUtils.getLoginUser().getUser().getSourceId())) {
            entity.setSourceId(SecurityUtils.getLoginUser().getUser().getSourceId());
        }
        return ResultEntity.success(dataSearchService.getRealDataList(entity));
    }

    @PostMapping("/minute")
    public ResultEntity minute(@RequestBody DataSearchParam entity) {
        if (!StringUtils.isEmpty(SecurityUtils.getLoginUser().getUser().getSourceId())) {
            entity.setSourceId(SecurityUtils.getLoginUser().getUser().getSourceId());
        }
        if (!StringUtils.isEmpty(entity.getCheckPointId())) {
            DataSearchResultT<ElemDataEntity> dataList = dataSearchService.getMinuteDataList(entity);
            return dataList != null ? ResultEntity.success(dataList) : ResultEntity.error("企业与排放口不能为空");
        } else return ResultEntity.error("检测井不能为空");
    }

    @PostMapping("/export_minute")//动态表头导出
    public AjaxResult minuteExport(@RequestBody DataSearchParam entity) {
        if (!StringUtils.isEmpty(SecurityUtils.getLoginUser().getUser().getSourceId())) {
            entity.setSourceId(SecurityUtils.getLoginUser().getUser().getSourceId());
        }
        if (StringUtils.isEmpty(entity.getCheckPointId())) return AjaxResult.success("检测井不能为空");
        String fullName = dataSearchService.getFullNameBySourceId(entity.getSourceId());
        DataSearchResultT<ElemDataEntity> dataList = dataSearchService.getMinuteDataList(entity);
        List<Map<String, Object>> cols = HashMapUtils.beanToMap(dataList.getCols());
        List<Map<String, Object>> t_data = HashMapUtils.beanToMap(dataList.getData());
        List<Map> data = HashMapUtils.toTypeOf(HashMapUtils.mapReMap(t_data), "yyyy-MM-dd HH:mm");
        //第一个参数data,第二个参数cols，第三个参数：表头中"title"值相同的就有子集合，第四个参数:表头中"dataIndex"的值对应数据中动态的值
        MapDocumentUtil doc = new MapDocumentUtil(HashMapUtils.mapToMap(data), cols, "title", "dataIndex");
        String fileName = "排水户行业_" + fullName + "_分钟数据";
        return doc.exportExcel(fileName);
    }

    @PostMapping("/hour")
    public ResultEntity hour(@RequestBody DataSearchParam entity) {
        if (!StringUtils.isEmpty(SecurityUtils.getLoginUser().getUser().getSourceId())) {
            entity.setSourceId(SecurityUtils.getLoginUser().getUser().getSourceId());
        }
        if (!StringUtils.isEmpty(entity.getCheckPointId())) {
            DataSearchResultT<ElemDataEntity> dataList = dataSearchService.getHourDataList(entity);
            return dataList != null ? ResultEntity.success(dataList) : ResultEntity.error("企业与排放口不能为空");
        } else return ResultEntity.error("检测井不能为空");
    }

    @PostMapping("/export_hour") //动态表头导出
    public AjaxResult hourExport(@RequestBody DataSearchParam entity) {
        if (!StringUtils.isEmpty(SecurityUtils.getLoginUser().getUser().getSourceId())) {
            entity.setSourceId(SecurityUtils.getLoginUser().getUser().getSourceId());
        }
        if (StringUtils.isEmpty(entity.getCheckPointId())) return AjaxResult.success("检测井不能为空");
        String fullName = dataSearchService.getFullNameBySourceId(entity.getSourceId());
        DataSearchResultT<ElemDataEntity> dataList = dataSearchService.getHourDataList(entity);
        List<Map<String, Object>> cols = HashMapUtils.beanToMap(dataList.getCols());
        List<Map<String, Object>> t_data = HashMapUtils.beanToMap(dataList.getData());
        List<Map> data = HashMapUtils.toTypeOf(HashMapUtils.mapReMap(t_data), "yyyy-MM-dd HH:mm");
        //第一个参数data,第二个参数cols，第三个参数：表头中"title"值相同的就有子集合，第四个参数:表头中"dataIndex"的值对应数据中动态的值
        MapDocumentUtil doc = new MapDocumentUtil(HashMapUtils.mapToMap(data), cols, "title", "dataIndex");
        String fileName = "排水户行业_" + fullName + "_小时数据";
        return doc.exportExcel(fileName);
    }

    @PostMapping("/day")
    public ResultEntity day(@RequestBody DataSearchParam entity) {
        if (!StringUtils.isEmpty(SecurityUtils.getLoginUser().getUser().getSourceId())) {
            entity.setSourceId(SecurityUtils.getLoginUser().getUser().getSourceId());
        }
        if (!StringUtils.isEmpty(entity.getCheckPointId())) {
            DataSearchResultT<ElemDataEntity> dataList = dataSearchService.getDayDataList(entity);
            return dataList != null ? ResultEntity.success(dataList) : ResultEntity.error("企业与排放口不能为空");
        } else return ResultEntity.error("污染源与检测井不能为空");
    }

    @PostMapping("/export_day")
    public AjaxResult dayExport(@RequestBody DataSearchParam entity) {
        if (!StringUtils.isEmpty(SecurityUtils.getLoginUser().getUser().getSourceId())) {
            entity.setSourceId(SecurityUtils.getLoginUser().getUser().getSourceId());
        }
        if (StringUtils.isEmpty(entity.getCheckPointId())) return AjaxResult.success("检测井不能为空");
        String fullName = dataSearchService.getFullNameBySourceId(entity.getSourceId());
        DataSearchResultT<ElemDataEntity> dataList = dataSearchService.getDayDataList(entity);
        List<Map<String, Object>> cols = HashMapUtils.beanToMap(dataList.getCols());
        List<Map<String, Object>> t_data = HashMapUtils.beanToMap(dataList.getData());
        List<Map> data = HashMapUtils.toTypeOf(HashMapUtils.mapReMap(t_data), "yyyy-MM-dd");
        //第一个参数data,第二个参数cols，第三个参数：表头中"title"值相同的就有子集合，第四个参数:表头中"dataIndex"的值对应数据中动态的值
        MapDocumentUtil doc = new MapDocumentUtil(HashMapUtils.mapToMap(data), cols, "title", "dataIndex");
        String fileName = "排水户行业_" + fullName + "_日数据";
        return doc.exportExcel(fileName);
    }

    @PostMapping("/transfer")  // /outwater/data_search/transfer   sourceId=100009706 日传输率
    public TableDataInfo getTransferDayRate(@RequestBody DataSearchParam entity) {

        startPage();
        List<TransferRateEntity> list = dataSearchService.getTransferDayRateList(entity);
        return getDataTable(list);
    }

    @PostMapping("/transfer_month")  // /outwater/data_search/transfer_month   sourceId=100009706 月传输率
    public TableDataInfo getTransferMonthRate(@RequestBody DataSearchParam entity) {

        startPage();
        List<TransferRateEntity> list = dataSearchService.getTransferMonthRateList(entity);
        return getDataTable(list);
    }

}
