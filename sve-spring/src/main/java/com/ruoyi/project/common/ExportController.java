package com.ruoyi.project.common;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.HashMapUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.aspectj.lang.annotation.DataScope;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.common.poi.DocumentUtil;
import com.ruoyi.project.common.poi.MapDocumentUtil;
import com.ruoyi.project.dgom.domain.ExportSuperviseEntity;
import com.ruoyi.project.dgom.domain.SuperviseEntity;
import com.ruoyi.project.dgom.service.SuperviseTaskService;
import com.ruoyi.project.enterprise.domain.*;
import com.ruoyi.project.enterprise.service.PfBasSourceCountService;
import com.ruoyi.project.outwater.domain.entity.CollectCheckPointEntity;
import com.ruoyi.project.outwater.domain.entity.OutwaterCheckPointEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/com/export")
public class ExportController extends BaseController {

    @Autowired
    private PfBasSourceCountService pfBasSourceCountService;

    @Autowired
    private SuperviseTaskService taskService;
//
//    @Autowired
//    private OWWarnmsgHeadsService warnmsgHeadsService;

    @GetMapping("/summary")
    @DataScope(regionAlias = "a")
    public AjaxResult exportCity(MonitorTaskParam param) {
        if (param.getListType().equals("-1"))
            param.setListTypes(new String[]{"1", "0"});
        else
            param.setListTypes(new String[]{param.getListType()});
        List<PfBasSourceCount> data = pfBasSourceCountService.getCountList(param);
        for (int i = 0; i < data.size(); i++) {
            data.get(i).setNumber(i + 1 + "");
        }
        DocumentUtil doc = new DocumentUtil(data);
        doc.addColumn("序号", "number", 10);
        doc.addColumn("镇街", "townName", 15);
        doc.addColumn("总任务数", "qyzs", 30);
        doc.addColumn("各监控任务数",
                doc.createColumn("涉水重点排污单位", "shuizhongdian", 20),
                doc.createColumn("涉气重点排污单位", "qizhongdian", 20),
                doc.createColumn("重点排水户", "paishui", 20),
                doc.createColumn("零散废水产生单位", "lsprod", 20),
                doc.createColumn("零散废水处理企业", "lsprocess", 20),
                doc.createColumn("四大流域重点涉水排污企业", "guocheng", 20),
                doc.createColumn("造纸行业整治", "zaozhi", 20),
                doc.createColumn("金属表面处理行业整治(基地外)", "jinshu", 20),
                doc.createColumn("樟村断面一体化污水处理项目", "yitihua", 20),
                doc.createColumn("水重点排污监控(加装视频)", "waterv", 20),
                doc.createColumn("市级以上VOCs重点企业", "voc", 20),
                doc.createColumn("汽修行业", "qixiu", 20),
                doc.createColumn("餐饮行业", "youyan", 20),
                doc.createColumn("加油站", "jiayou", 20),
                doc.createColumn("VOCs重点排放源自动监控", "gasv", 20));
        return doc.exportExcel("监控任务汇总");
    }

    @GetMapping("/count")
    public AjaxResult count(MonitorTaskParam param) {
        List<ExportCountEntity> data = null;
        if (param.getListType().equals("-1"))
            param.setListTypes(new String[]{"1", "0"});
        else
            param.setListTypes(new String[]{param.getListType()});
        try {
            data = pfBasSourceCountService.getExportCountData(param);
        } catch (Exception e) {
            e.printStackTrace();
        }
        DocumentUtil doc = new DocumentUtil(data);
        doc.addColumn("监控任务", "tagHeadName", 20);
        doc.addColumn("镇街", "townName", 15);
        doc.addColumn("监控企业总数", "factorycnt", 30);
        doc.addColumn("未开展",
                doc.createColumn("数量", "accessQuantity", 10),
                doc.createColumn("比例", "accessRatio", 10));
        doc.addColumn("已施工",
                doc.createColumn("数量", "constructionQuantity", 10),
                doc.createColumn("比例", "constructionRatio", 10));
        doc.addColumn("已接入",
                doc.createColumn("数量", "connectedQuantity", 10),
                doc.createColumn("比例", "connectedRatio", 10));
        doc.addColumn("已验收",
                doc.createColumn("数量", "acceptedQuantity", 10),
                doc.createColumn("比例", "acceptedratio", 10));
        doc.addColumn("已取消",
                doc.createColumn("数量", "cancelQuantity", 10),
                doc.createColumn("比例", "cancelRatio", 10));
        doc.addColumn("已暂缓",
                doc.createColumn("数量", "suspendQuantity", 10),
                doc.createColumn("比例", "suspendRatio", 10));
        return doc.exportExcel("监控任务统计");
    }

    @PostMapping("/outwater")    //  /com/export/outwater
    @DataScope(regionAlias = "s")
    public AjaxResult outwater(@RequestBody CollectCheckPointEntity entity) {
        if (!StringUtils.isEmpty(SecurityUtils.getLoginUser().getUser().getSourceId())) {
            entity.setSourceId(SecurityUtils.getLoginUser().getUser().getSourceId());
        }
        List<OutwaterCheckPointEntity> data = pfBasSourceCountService.getExportOutwaterData(entity);
        DocumentUtil doc = new DocumentUtil(data);
        doc.addColumn("污染源", "fullName", 30);
        doc.addColumn("所属街镇", "townName", 15);
        doc.addColumn("监测井", "checkPointName", 15);
        doc.addColumn("监测井类型", "checkPointType", 10);
        doc.addColumn("MN码", "mn", 30);
        doc.addColumn("品牌", "brandName", 30);
        doc.addColumn("经度", "longitude", 15);
        doc.addColumn("纬度", "latitude", 15);
        doc.addColumn("IP地址", "ipAddress", 30);
        doc.addColumn("施工单位", "constructionUnit", 30);
        doc.addColumn("检测时间", "updatedBy", 15);
        doc.addColumn("是否在线", "isOnline", 10);
        return doc.exportExcel("排水户监测井查询记录");
    }
//
//    @PostMapping("/outwater_warn")    //  /com/export/outwater_warn
//    @DataScope(regionAlias = "s")
//    public AjaxResult outwaterWarn(@RequestBody OWWarnmsgHeadsEntity entity) {
//        if (!StringUtils.isEmpty(SecurityUtils.getLoginUser().getUser().getSourceId())) {
//            entity.setSourceId(SecurityUtils.getLoginUser().getUser().getSourceId());
//        }
//        List<OWWarnmsgHeadsEntity> data = warnmsgHeadsService.selectWarnmsgHeads(entity);
//        DocumentUtil doc = new DocumentUtil(data);
//        doc.addColumn("污染源", "sourceName", 30);
//        doc.addColumn("街镇", "townName", 10);
//        doc.addColumn("品牌", "brand", 15);
//        doc.addColumn("施工单位", "constructionUnit", 30);
//        doc.addColumn("异常级别", "levelName", 12);
//        doc.addColumn("处理状态", "procName", 12);
//        doc.addColumn("异常开始时间", "startDate", 15);
//        doc.addColumn("异常结束时间", "endDate", 15);
//        doc.addColumn("异常类型", "typeName", 15);
//        doc.addColumn("异常描述", "warnDesc", 30);
//        return doc.exportExcel("排水异常查询记录");
//    }

    @GetMapping("/task")
    public AjaxResult task(MonitorTaskParam param) {
        if (param.getListType().equals("-1"))
            param.setListTypes(new String[]{"1", "0"});
        else
            param.setListTypes(new String[]{param.getListType()});
        List<PfBasSourceList> data = pfBasSourceCountService.getSourceList(param);
        List<Map<String, Object>> maps = HashMapUtils.beanToMapF(data);
        for (Map<String, Object> map : maps) {
            if (map.get("sourceStatus") != null) {
                String status = getSourceStatus(map.get("sourceStatus").toString());
                map.replace("sourceStatus", status);
            }
            if (!map.get("fullName").equals("合计")) {
                Set<Map.Entry<String, Object>> entries = map.entrySet();
                entries.stream().filter(e -> e.getValue() != null).forEach(e -> {
                    if (e.getValue().getClass().getSimpleName().equals("Integer")) {
                        map.replace(e.getKey(), getStructionStatus(e.getValue().toString()));
                    }
                });
            }
        }
        MapDocumentUtil doc = new MapDocumentUtil(maps);
        doc.addColumn("序号", "number");
        doc.addColumn("企业名称", "fullName");
        doc.addColumn("所属镇街", "townName");
        doc.addColumn("所属行业", "industryName");
        doc.addColumn("经营状态", "sourceStatus");
        doc.addColumn("联系人", "envContactName");
        doc.addColumn("联系电话", "envContactPhone");
        doc.addColumn("涉水重点排污单位", "shuizhongdian");
        doc.addColumn("涉气重点排污单位", "qizhongdian");
        doc.addColumn("重点排水户末端监控", "paishui");
        doc.addColumn("四大流域过程监控", "guocheng");
        doc.addColumn("零散废水产生单位监控", "lsprod");
        doc.addColumn("零散废水处理单位监控", "lsprocess");
        doc.addColumn("金属表面处理行业过程监控", "jinshu");
        doc.addColumn("造纸行业过程监控", "zaozhi");
        doc.addColumn("一体化污水处理设施在线监控", "yitihua");
        doc.addColumn("市级以上VOCs重点企业过程监控", "voc");
        doc.addColumn("汽修行业过程监控", "qixiu");
        doc.addColumn("餐饮行业过程监控", "youyan");
        doc.addColumn("加油站油气回收装置过程监控", "jiayou");
        return doc.exportExcel("企业任务汇总");
    }

    @PostMapping("/monitor_task")    //  /com/export/monitor_task
    @DataScope(regionAlias = "s")
    public AjaxResult monitorTaskList(@RequestBody EnterpriseEntity entity) {

        if (entity.getListType().equals("-1"))
            entity.setListTypes(new String[]{"1", "0"});
        else
            entity.setListTypes(new String[]{entity.getListType()});

        List<TaskSourceEntity> data = pfBasSourceCountService.selectEnterpriseInformation(entity);
        for (TaskSourceEntity entity_t : data) {
            if (entity_t.getVerifyStatus() != null) { //解决空指针异常
                switch (entity_t.getVerifyStatus()) {
                    case "0":
                        entity_t.setVerifyStatus("正常");
                        break;
                    case "1":
                        entity_t.setVerifyStatus("不安装");
                        break;
                    case "2":
                        entity_t.setVerifyStatus("暂缓");
                        break;
                }
            }
            if (entity_t.getLoginFlag() != null) { //解决空指针异常
                switch (entity_t.getLoginFlag()) {
                    case "0":
                        entity_t.setLoginFlag("未登录");
                        break;
                    case "1":
                        entity_t.setLoginFlag("已登录");
                        break;
                }
            }
        }
        DocumentUtil doc = new DocumentUtil(data);
        doc.addColumn("监控任务", "tagHeadName", 30);
        doc.addColumn("企业名称", "fullName", 30);
        doc.addColumn("所属街镇", "townName", 30);
        doc.addColumn("联系人", "envContactName", 30);
        doc.addColumn("联系电话", "envContactPhone", 30);
        doc.addColumn("登录状态", "loginFlag", 30);
        doc.addColumn("运行状态", "sourceStatus", 30);
        doc.addColumn("任务状态", "verifyStatus", 30);
        doc.addColumn("任务进度", "status", 30);
        doc.addColumn("施工单位名称", "constructionUnit", 30);
        doc.addColumn("施工单位联系人", "constructionPerson", 30);
        doc.addColumn("施工单位联系电话", "constructionPhone", 30);
        return doc.exportExcel("监控任务清单记录");
    }

    @PostMapping("/supervise")    //  /com/export/supervise
    public AjaxResult ExportSupervise(@RequestBody SuperviseEntity entity) {
        List<SuperviseEntity> data = taskService.selectSuperviseTaskList(entity);
        DocumentUtil doc = new DocumentUtil(data);
        String min = null, max = null;
        Optional<Date> minStartDate = data.stream().map(SuperviseEntity::getStartDate).filter(Objects::nonNull).min(Date::compareTo);
        if (minStartDate.isPresent()) min = DateUtils.parseDate(minStartDate.get(), "yyyy年MM月dd日");
        Optional<Date> maxEndDate = data.stream().map(SuperviseEntity::getEndDate).filter(Objects::nonNull).max(Date::compareTo);
        if (maxEndDate.isPresent()) max = DateUtils.parseDate(maxEndDate.get(), "yyyy年MM月dd日");
        String title = "督办严重超标明细表";
        if (min != null && max != null) {
            title = StringUtils.format("督办严重超标明细表(统计时段：{}-{})", min, max);
        }
//        if (min == null && max == null) s = "督办严重超标明细表";
        doc.addColumn(title,
                doc.createColumn("严重超标情况明细",
                        doc.createColumn("序号", "no", 5),
                        doc.createColumn("地市", "cityName", 8),
                        doc.createColumn("督办类型", "taskType", 8),
                        doc.createColumn("镇街", "townName", 8),
                        doc.createColumn("企业名称", "factoryName", 30),
                        doc.createColumn("监控点名称", "checkpointName", 20),
                        doc.createColumn("监测因子", "elementName", 15),
                        doc.createColumn("超标天数", "overDays", 10),
                        doc.createColumn("检测时间", "monitorTime", 12),
                        doc.createColumn("排放浓度", "amount", 10),
                        doc.createColumn("排放标准", "standardval", 10),
                        doc.createColumn("超标倍数", "overTimes", 10),
                        doc.createColumn("任务状态", "taskStatusName", 10),
                        doc.createColumn("任务开始时间", "startDate", 12),
                        doc.createColumn("任务结束时间", "endDate", 12)),
                doc.createColumn("超标核实反馈",
                        doc.createColumn("超标是否属实", "isTrueName", 15),
                        doc.createColumn("超标原因", "reason", 30),
                        doc.createColumn("整改情况", "recityinfo", 30),
                        doc.createColumn("查处情况", "situation", 30)));
        return doc.exportExcel("东莞市督办任务列表");
    }

    private static Map<String, String> constructionStatus() {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("0", null);
        map.put("1", "已取消");
        map.put("2", "未开展");
        map.put("3", "已施工");
        map.put("4", "已接入");
        map.put("5", "已接入");
        map.put("6", "已接入");
        map.put("8", "已验收");
        return map;
    }

    public static String getStructionStatus(String key) {
        return constructionStatus().get(key);
    }

    private static Map<String, String> sourceStatus() {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("0", null);
        map.put("1", "国家未要求接入");
        map.put("2", "暂缓安装");
        map.put("3", "在产");
        map.put("4", "关停");
        map.put("5", "关闭");
        map.put("6", "其他");
        return map;
    }

    public static String getSourceStatus(String key) {
        return sourceStatus().get(key);
    }

}
