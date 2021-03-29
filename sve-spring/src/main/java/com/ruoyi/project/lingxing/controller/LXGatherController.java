package com.ruoyi.project.lingxing.controller;

import com.ruoyi.common.domain.ResultEntity;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.aspectj.lang.annotation.DataScope;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.common.poi.DocumentUtil;
import com.ruoyi.project.enterprise.domain.EnterpriseNetworkEntity;
import com.ruoyi.project.lingxing.domain.LXExportGatherEntity;
import com.ruoyi.project.lingxing.domain.LXGatherEntity;
import com.ruoyi.project.lingxing.domain.LXSourceAbnorEntity;
import com.ruoyi.project.lingxing.service.LXGatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author solang
 * @date 2020-11-03 10:13
 */
@RestController
@RequestMapping("/lingxing/statistical")
public class LXGatherController extends BaseController {

    @Autowired
    private LXGatherService gatherService;

    @PostMapping("/enterprise")
    public ResultEntity enterpriseList(@RequestBody LXGatherEntity entity) {
        return ResultEntity.success(gatherService.getEnterpriseList(entity));
    }

    @PostMapping("/export_enterprise")
    public AjaxResult AbnormalEnterprise(@RequestBody LXGatherEntity entity) {
        List<LXExportGatherEntity> data = gatherService.getEnterpriseList(entity);
        DocumentUtil doc = new DocumentUtil(data);
        doc.addColumn("污染源", "fullName", 30);
        doc.addColumn("所属街镇", "townName", 30);
        doc.addColumn("施工单位", "constructionUnit", 30);
        doc.addColumn("异常等级", "levelName", 30);
        doc.addColumn("产生异常数", "warnCount", 30);
        doc.addColumn("企业未处理", "factoryCount", 30);
        doc.addColumn("核收未核实", "dutyCount", 30);
        doc.addColumn("分局未处理", "substationCount", 30);
        return doc.exportExcel("企业异常情况汇总");
    }

    @PostMapping("/town")
    public ResultEntity townAbnormalList(@RequestBody LXGatherEntity entity) {
        return ResultEntity.success(gatherService.getTownAbnormalList(entity));
    }

    @PostMapping("/export_town")
    public AjaxResult townAbnormal(@RequestBody LXGatherEntity entity) {
        List<LXExportGatherEntity> data = gatherService.getTownAbnormalList(entity);
        DocumentUtil doc = new DocumentUtil(data);
         doc.addColumn("街镇", "townName", 30);
         doc.addColumn("异常等级", "levelName", 30);
         doc.addColumn("产生异常数", "warnCount", 30);
         doc.addColumn("企业未处理", "factoryCount", 30);
         doc.addColumn("核收未核实", "dutyCount", 30);
         doc.addColumn("分局未处理", "substationCount", 30);
        return doc.exportExcel("街镇异常情况汇总");
    }

    @GetMapping("/calculate")  //企业异常统计
    public ResultEntity getCalculate() {
        return ResultEntity.success(gatherService.getCalculate());
    }

    @GetMapping("/equipment")  //设备异常统计
    public ResultEntity getEquipment() {
        return ResultEntity.success(gatherService.getEquipment());
    }

    @GetMapping("/construction")  //施工单位异常统计
    public ResultEntity getConstruction() {

        return ResultEntity.success(gatherService.getConstruction());
    }

    @PostMapping("/source") //企业异常统计
    public TableDataInfo getSourceAbnor(@RequestBody LXSourceAbnorEntity entity) {
        startPage();
        List<LXSourceAbnorEntity> list = gatherService.getSourceAbnor(entity);
        return getDataTable(list);
    }

    //各镇街污水排放量
    @GetMapping("/town_waste")
    public ResultEntity getTownWastewater(@RequestParam("startDate") String startDate, @RequestParam("endDate") String endDate) {
        return ResultEntity.success(gatherService.getTownWastewater(startDate, endDate));
    }

    @GetMapping("/month_waste")
    public ResultEntity getMonthWastewater(@RequestParam("startDate") String startDate, @RequestParam("endDate") String endDate) {
        return ResultEntity.success(gatherService.getMonthWastewater(startDate, endDate));
    }

    @GetMapping("/town_month_waste")
    public ResultEntity getTownMonthWastewater(@RequestParam Map<String, String> param) {
        return ResultEntity.success(gatherService.getTownMonthWastewater(param));
    }

    //各镇街污水排放量
    @GetMapping("/town_water")
    public ResultEntity getTownWater(@RequestParam("startDate") String startDate, @RequestParam("endDate") String endDate) {
        return ResultEntity.success(gatherService.getTownWater(startDate, endDate));
    }

    @GetMapping("/month_water")
    public ResultEntity getMonthWater(@RequestParam("startDate") String startDate, @RequestParam("endDate") String endDate) {
        return ResultEntity.success(gatherService.getMonthWater(startDate, endDate));
    }

    @GetMapping("/town_month_water")
    public ResultEntity getTownMonthWater(@RequestParam Map<String, String> param) {
        return ResultEntity.success(gatherService.getTownMonthWater(param));
    }

    @GetMapping("/town_network")
    public ResultEntity getTownNetworkCount() {
        return ResultEntity.success(gatherService.getTownNetworkCount());
    }

    //企业联网情况汇总
    @PostMapping("/enterprise_network")
    public TableDataInfo getEnterpriseNetwork(@RequestBody EnterpriseNetworkEntity entity) {
        startPage();
        List<EnterpriseNetworkEntity> list = gatherService.getEnterpriseNetwork(entity);
        return getDataTable(list);
    }

    //企业联网情况汇总导出
    @PostMapping("/export_enterprise_network")
    public AjaxResult enterpriseNetwork(@RequestBody EnterpriseNetworkEntity entity) {
        List<EnterpriseNetworkEntity> data = gatherService.getEnterpriseNetwork(entity);
        DocumentUtil doc = new DocumentUtil(data);
        doc.addColumn("企业名称", "fullName", 30);
        doc.addColumn("所属街镇", "townName", 15);
        doc.addColumn("统一社会信用代码", "socialCreditCode", 30);
        doc.addColumn("施工单位简称", "shortName", 15);
        doc.addColumn("施工单位状态", "statusName", 15);
        doc.addColumn("分钟传输率", "mcnt", 15);
        doc.addColumn("小时传输率", "hcnt", 15);
        doc.addColumn("日传输率", "dcnt", 15);
        return doc.exportExcel("传输率不合格企业列表");
    }

    @PostMapping("/transfer_amount")
    @DataScope(regionAlias = "s")
    public TableDataInfo getTransferAmount(@Validated @RequestBody LXSourceAbnorEntity entity) {
        startPage();
        List<LXSourceAbnorEntity> list = gatherService.getTransferAmount(entity);
        return getDataTable(list);
    }

    //企业生产用水统计 /lingxing/statistical/enterprise_water
    @GetMapping("/enterprise_water")
    public ResultEntity getEnterpriseWater(@RequestParam Map<String, String> param) {
        if (StringUtils.isEmpty(param.get("sourceId"))) {
            return ResultEntity.error("请先选择企业");
        }
        if (StringUtils.isEmpty(param.get("dataDate"))) {
            return ResultEntity.error("请先选择日期");
        }
        List<Map> list = gatherService.getEnterpriseWater(param);
        return ResultEntity.success(changeGroup(list));
    }

    @GetMapping("/storage_rate")
    public ResultEntity getStorageRate(@RequestParam Map<String, String> param) {
        return ResultEntity.success(gatherService.getStorageRate(param));
    }

    public List<Map<String, Object>> changeGroup(List<Map> list) {
        List<Map<String, Object>> result = new ArrayList<>();
        LinkedHashMap<String, List<Map>> group = list.stream().collect(
                Collectors.groupingBy(g -> g.get("element_code").toString(), LinkedHashMap::new, Collectors.toList()));
        for (Map.Entry<String, List<Map>> entry : group.entrySet()) {
            String name = entry.getKey();
            for (Map map : entry.getValue()) {
                String date = DateUtils.dateTimeS((Date) map.get("datadate"));
                BigDecimal cou_amt = (BigDecimal) map.get("cou_amt");
                Double amt = 0.0;
                if (cou_amt != null) amt = Double.parseDouble(cou_amt.toString());
                Map<String, Object> m = new LinkedHashMap<>();
                Optional<Map<String, Object>> optional = result.stream().filter(a -> a.get("日期").equals(date)).findFirst();
                if (optional.isPresent()) {
                    m = optional.get();
                } else {
                    m.put("日期", date);
                    result.add(m);
                }
                m.put(map.get("element_desc") + "(" + name + ")", amt);
            }
        }
        return result;
    }

    // 末端排放因子
    @GetMapping("/element_end_discharge/{collectId}")
    public ResultEntity getElementByEndDischarge(@PathVariable String collectId) {
        List<Map> list = gatherService.getElementByEndDischarge(collectId);
        return ResultEntity.success(list);
    }

    // 末端排放因子
    @GetMapping("/discharge_trend")
    public ResultEntity getDischargeTrend(@RequestParam Map<String, String> param) {
        List<Map> list = gatherService.getDischargeTrend(param);
        return ResultEntity.success(list);
    }
}
