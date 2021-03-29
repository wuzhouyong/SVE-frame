package com.ruoyi.project.enterprise.controller;

import com.ruoyi.common.domain.ResultEntity;
import com.ruoyi.common.utils.HashMapUtils;
import com.ruoyi.framework.aspectj.lang.annotation.DataScope;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.domain.BaseEntity;
import com.ruoyi.project.common.poi.MapDocumentUtil;
import com.ruoyi.project.enterprise.service.ITestScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/test_score")
public class TestScoreController {
    @Autowired
    private ITestScoreService service;

    @DataScope(regionAlias = "w")
    @GetMapping("/score")
    public ResultEntity getTownScore(BaseEntity entity) {
        return ResultEntity.success(service.getTownScore(entity));
    }

    @GetMapping("/town_water_summary")
    public ResultEntity getTownWaterTaskSummary() {
        return ResultEntity.success(service.getTownWaterTaskSummary());
    }

    @GetMapping("/export_town_water_summary")
    public AjaxResult exportTownWaterTaskSummary() {
        List<Map> data = service.getTownWaterTaskSummary();
        MapDocumentUtil doc = new MapDocumentUtil(HashMapUtils.mapToMap(data));
        doc.addColumn("镇街", "town_name");
        doc.addColumn("指标分值", "norm");
        doc.addColumn("涉水在线监测监控系统建设",
                doc.createColumn("考核名单任务量", "in_total"),
                doc.createColumn("考核名单完成量", "finish_cnt"),
                doc.createColumn("考核名单量", "check_cnt"),
                doc.createColumn("完成率%", "rate"),
                doc.createColumn("考核名单未完成量", "unfinish_cnt"),
                doc.createColumn("考核限值", "limit"),
                doc.createColumn("扣分", "score"));
        Map<Integer, String> tags = new LinkedHashMap<>();
        tags.put(1002, "四大流域");
        tags.put(1003, "零星废水");
        tags.put(1012, "排水户");
        tags.put(1015, "金属表面");
        tags.put(1016, "樟村一体化");
        tags.put(1017, "水重点排污");
        tags.put(1019, "造纸");
        for (Map.Entry<Integer, String> tag : tags.entrySet()) {
            doc.addColumn(tag.getValue(),
                    doc.createColumn("考核名单任务量", "in_total" + tag.getKey()),
                    doc.createColumn("考核名单完成量", "in_list" + tag.getKey()),
                    doc.createColumn("完成率%", "rate" + tag.getKey()),
                    doc.createColumn("考核名单未完成量", "unfinish" + tag.getKey()),
                    doc.createColumn("非考核名单完成量", "out_list" + tag.getKey()));
        }
        return doc.exportExcel("涉水责考评分");
    }

    @GetMapping("/town_gas_summary")
    public ResultEntity getTownGasTaskSummary() {
        return ResultEntity.success(service.getTownGasTaskSummary());
    }

    @GetMapping("/export_town_gas_summary")
    public AjaxResult exportTownGasTaskSummary() {
        List<Map> data = service.getTownGasTaskSummary();
        MapDocumentUtil doc = new MapDocumentUtil(HashMapUtils.mapToMap(data));
        doc.addColumn("镇街", "town_name");
        doc.addColumn("指标分值", "norm");
        doc.addColumn("VOCs固定源在线监控建设（加油站、汽修企业、餐饮单位、涉VOCs重点工业企业）（3分）",
                doc.createColumn("考核名单任务量", "in_total"),
                doc.createColumn("考核名单完成量", "finish_cnt"),
                doc.createColumn("考核名单量", "check_cnt"),
                doc.createColumn("完成率%", "rate"),
                doc.createColumn("考核名单未完成量", "unfinish_cnt"),
                doc.createColumn("非考核名单完成量", "out_list"),
                doc.createColumn("考核限值", "limit"),
                doc.createColumn("得分", "score"));
        Map<Integer, String> tags = new LinkedHashMap<>();
        tags.put(1004, "涉VOCs重点工业企业");
        tags.put(1006, "餐饮单位");
        tags.put(1013, "汽修企业");
        tags.put(1014, "加油站");
        for (Map.Entry<Integer, String> tag : tags.entrySet()) {
            doc.addColumn(tag.getValue(),
                    doc.createColumn("考核名单任务量", "in_total" + tag.getKey()),
                    doc.createColumn("考核名单完成量", "in_list" + tag.getKey()),
                    doc.createColumn("完成率%", "rate" + tag.getKey()),
                    doc.createColumn("考核名单未完成量", "unfinish" + tag.getKey()),
                    doc.createColumn("非考核名单完成量", "out_list" + tag.getKey()));
        }
        return doc.exportExcel("涉气责考评分");
    }

    @GetMapping("/town_all_summary")
    public ResultEntity getTownAllTaskSummary() {
        return ResultEntity.success(service.getTownAllTaskSummary());
    }

    @GetMapping("/export_town_all_summary")
    public AjaxResult exportTownAllTaskSummary() {
        List<Map> data = service.getTownAllTaskSummary();
        MapDocumentUtil doc = new MapDocumentUtil(HashMapUtils.mapToMap(data));
        doc.addColumn("镇街", "town_name");
        doc.addColumn("合计",
                doc.createColumn("考核名单任务量", "in_total"),
                doc.createColumn("考核名单完成量", "in_list"),
                doc.createColumn("完成率%", "rate"),
                doc.createColumn("考核名单未完成量", "unfinish_cnt"),
                doc.createColumn("非考核名单完成量", "out_list"));
        Map<Integer, String> tags = new LinkedHashMap<>();
        tags.put(1002, "四大流域");
        tags.put(1003, "零星废水");
        tags.put(1004, "涉VOCs重点工业企业");
        tags.put(1006, "餐饮单位");
        tags.put(1012, "排水户");
        tags.put(1013, "汽修企业");
        tags.put(1014, "加油站");
        tags.put(1015, "金属表面");
        tags.put(1016, "樟村一体化");
        tags.put(1017, "水重点排污");
        tags.put(1019, "造纸");
        for (Map.Entry<Integer, String> tag : tags.entrySet()) {
            List<Map<String, Object>> tag_list = new ArrayList<>();
            tag_list.add(doc.createColumn("考核名单任务量", "in_total" + tag.getKey()));
            tag_list.add(doc.createColumn("考核名单完成量", "in_list" + tag.getKey()));
            tag_list.add(doc.createColumn("完成率%", "rate" + tag.getKey()));
            tag_list.add(doc.createColumn("考核名单未完成量", "unfinish" + tag.getKey()));
            tag_list.add(doc.createColumn("非考核名单完成量", "out_list" + tag.getKey()));
            doc.addColumn(tag.getValue(),
                    doc.createColumn("考核名单任务量", "in_total" + tag.getKey()),
                    doc.createColumn("考核名单完成量", "in_list" + tag.getKey()),
                    doc.createColumn("完成率%", "rate" + tag.getKey()),
                    doc.createColumn("考核名单未完成量", "unfinish" + tag.getKey()),
                    doc.createColumn("非考核名单完成量", "out_list" + tag.getKey()));
        }
        return doc.exportExcel("全部责考评分");
    }
}
