package com.ruoyi.project.guocheng.controller;

import com.ruoyi.common.domain.ResultEntity;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.common.poi.Column;
import com.ruoyi.project.common.poi.DocumentUtil;
import com.ruoyi.project.enterprise.domain.EnterpriseNetworkEntity;
import com.ruoyi.project.guocheng.domain.GCExportGatherEntity;
import com.ruoyi.project.guocheng.domain.GCGatherEntity;
import com.ruoyi.project.guocheng.domain.GCSourceAbnorEntity;
import com.ruoyi.project.guocheng.service.GCGatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author solang
 * @date 2020-11-03 10:13
 */
@RestController
@RequestMapping("/guocheng/statistical")
public class GCGatherController extends BaseController {

    @Autowired
    private GCGatherService gatherService;

    @PostMapping("/enterprise") //企业异常汇总
    public ResultEntity enterpriseList(@RequestBody GCGatherEntity entity) {
        return ResultEntity.success(gatherService.getEnterpriseList(entity));
    }

    @PostMapping("/export_enterprise")    //  /guocheng/abnormal/export_enterprise
    public AjaxResult AbnormalEnterprise(@RequestBody GCGatherEntity entity) {

        List<GCExportGatherEntity> data = gatherService.getEnterpriseList(entity);
        DocumentUtil doc = new DocumentUtil(data);
       doc.addColumn("污染源", "fullName", 30);
       doc.addColumn("所属街镇", "townName", 30);
       doc.addColumn("调研单位", "unitName", 30);
       doc.addColumn("施工单位", "constructionUnit", 30);
       doc.addColumn("异常等级", "levelName", 30);
       doc.addColumn("产生异常数", "warnCount", 30);
       doc.addColumn("企业未处理", "factoryCount", 30);
       doc.addColumn("核收未核实", "dutyCount", 30);
       doc.addColumn("分局未处理", "substationCount", 30);
        return doc.exportExcel("企业异常情况汇总");
    }

    @PostMapping("/town") //街镇异常汇总
    public ResultEntity townAbnormalList(@RequestBody GCGatherEntity entity) {
        return ResultEntity.success(gatherService.getTownAbnormalList(entity));
    }

    @PostMapping("/export_town")    //  /guocheng/abnormal/export_town
    public AjaxResult townAbnormal(@RequestBody GCGatherEntity entity) {
        List<GCExportGatherEntity> data = gatherService.getTownAbnormalList(entity);
        DocumentUtil doc = new DocumentUtil(data);
        doc.addColumn("街镇", "townName", 30);
        doc.addColumn("异常等级", "levelName", 30);
        doc.addColumn("产生异常数", "warnCount", 30);
        doc.addColumn("企业未处理", "factoryCount", 30);
        doc.addColumn("核收未核实", "dutyCount", 30);
        doc.addColumn("分局未处理", "substationCount", 30);
        return doc.exportExcel("街镇异常情况汇总");
    }

    @PostMapping("/sewage") //工业污水排放量统计 /guocheng/abnormal/sewage
    public ResultEntity industrialWastewater(@RequestBody GCGatherEntity entity) {
        return ResultEntity.success(gatherService.industrialWastewater(entity));
    }

    @PostMapping("/export_sewage")    //  /guocheng/abnormal/export_sewage
    public AjaxResult wastewaterSewage(@RequestBody GCGatherEntity entity) {
        List<GCExportGatherEntity> data = gatherService.industrialWastewater(entity);
        DocumentUtil doc = new DocumentUtil(data);
        List<Column> columns = new ArrayList<>();
        doc.addColumn("污染源", "fullName", 30);
        doc.addColumn("所属街镇", "townName", 30);
        doc.addColumn("调研单位", "unitName", 30);
        doc.addColumn("施工单位", "constructionUnit", 30);
        doc.addColumn("日允环评排放(m³)", "dailyAmt", 30);
        doc.addColumn("总排放量(m³)", "totalAmt", 30);
        doc.addColumn("是否超标", "exceedName", 30);
        return doc.exportExcel("工业污水排放量统计");
    }


    @GetMapping("/calculate")  //当月异常统计
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

    @PostMapping("/source")  //企业异常统计
    public TableDataInfo getSourceAbnor(@RequestBody GCSourceAbnorEntity entity) {
        startPage();
        List<GCSourceAbnorEntity> list = gatherService.getSourceAbnor(entity);
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
    @GetMapping("/town_electric")
    public ResultEntity getTownElectric(@RequestParam("startDate") String startDate, @RequestParam("endDate") String endDate) {
        return ResultEntity.success(gatherService.getTownElectric(startDate, endDate));
    }

    @GetMapping("/month_electric")
    public ResultEntity getMonthElectric(@RequestParam("startDate") String startDate, @RequestParam("endDate") String endDate) {
        return ResultEntity.success(gatherService.getMonthElectric(startDate, endDate));
    }

    @GetMapping("/town_month_electric")
    public ResultEntity getTownMonthElectric(@RequestParam Map<String, String> param) {
        return ResultEntity.success(gatherService.getTownMonthElectric(param));
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

    //企业生产用水统计 /guocheng/statistical/product_water
    @GetMapping("/product_water")
    public ResultEntity getProductWaterAmt(@RequestParam Map<String, String> param) {
        if (StringUtils.isEmpty(param.get("sourceId"))) {
            return ResultEntity.error("请先选择企业");
        }
        if (StringUtils.isEmpty(param.get("dataDate"))) {
            return ResultEntity.error("请先选择日期");
        }
        List<Map> list = gatherService.getProductWaterAmt(param);
        return ResultEntity.success(changeGroup(list));
    }

    //企业生产用电统计 /guocheng/statistical/product_elect
    @GetMapping("/product_elect")
    public ResultEntity getProdElectricAmt(@RequestParam Map<String, String> param) {
        if (StringUtils.isEmpty(param.get("sourceId"))) {
            return ResultEntity.error("请先选择企业");
        }
        if (StringUtils.isEmpty(param.get("dataDate"))) {
            return ResultEntity.error("请先选择日期");
        }
        List<Map> list = gatherService.getProdElectricAmt(param);
        return ResultEntity.success(changeGroup(list));
    }

    //企业回用水量统计 /guocheng/statistical/reuse_water
    @GetMapping("/reuse_water")
    public ResultEntity getReuseWaterAmt(@RequestParam Map<String, String> param) {
        if (StringUtils.isEmpty(param.get("sourceId"))) {
            return ResultEntity.error("请先选择企业");
        }
        if (StringUtils.isEmpty(param.get("dataDate"))) {
            return ResultEntity.error("请先选择日期");
        }
        List<Map> list = gatherService.getReuseWaterAmt(param);
        return ResultEntity.success(changeGroup(list));
    }

    //废水治理设施相关设备实时状态统计 /guocheng/statistical/real_elect
    @GetMapping("/real_elect")
    public ResultEntity getRealElectricAmt(@RequestParam Map<String, String> param) {
        if (StringUtils.isEmpty(param.get("sourceId"))) {
            return ResultEntity.error("请先选择企业");
        }
        if (StringUtils.isEmpty(param.get("dataDate"))) {
            return ResultEntity.error("请先选择日期");
        }
        List<Map> list = gatherService.getRealElectricAmt(param);
        return ResultEntity.success(changeGroup(list));
    }

    //企业污水排放流量统计 /guocheng/statistical/sewage_amt
    @GetMapping("/sewage_amt")
    public ResultEntity getSewageDischargeAmt(@RequestParam Map<String, String> param) {
        if (StringUtils.isEmpty(param.get("sourceId"))) {
            return ResultEntity.error("请先选择企业");
        }
        if (StringUtils.isEmpty(param.get("dataDate"))) {
            return ResultEntity.error("请先选择日期");
        }
        List<Map> list = gatherService.getSewageDischargeAmt(param);
        return ResultEntity.success(changeGroup(list));
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
