package com.ruoyi.project.qixiu.controller;

import com.ruoyi.common.domain.ResultEntity;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.common.poi.DocumentUtil;
import com.ruoyi.project.enterprise.domain.EnterpriseNetworkEntity;
import com.ruoyi.project.qixiu.domain.QXExportGatherEntity;
import com.ruoyi.project.qixiu.domain.QXGatherEntity;
import com.ruoyi.project.qixiu.domain.QXSourceAbnorEntity;
import com.ruoyi.project.qixiu.service.QXGatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author solang
 * @date 2020-11-03 10:13
 */
@RestController
@RequestMapping("/qixiu/statistical")
public class QXGatherController extends BaseController {

    @Autowired
    private QXGatherService gatherService;

    @PostMapping("/enterprise")
    public ResultEntity enterpriseList(@RequestBody QXGatherEntity entity) {
        return ResultEntity.success(gatherService.getEnterpriseList(entity));
    }

    @PostMapping("/export_enterprise")
    public AjaxResult AbnormalEnterprise(@RequestBody QXGatherEntity entity) {
        List<QXExportGatherEntity> data = gatherService.getEnterpriseList(entity);
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
    public ResultEntity townAbnormalList(@RequestBody QXGatherEntity entity) {
        return ResultEntity.success(gatherService.getTownAbnormalList(entity));
    }

    @PostMapping("/export_town")
    public AjaxResult townAbnormal(@RequestBody QXGatherEntity entity) {
        List<QXExportGatherEntity> data = gatherService.getTownAbnormalList(entity);
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
    public TableDataInfo getSourceAbnor(@RequestBody QXSourceAbnorEntity entity) {
        startPage();
        List<QXSourceAbnorEntity> list = gatherService.getSourceAbnor(entity);
        return getDataTable(list);
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

    //排放统计头 qixiu/statistical/town_emission
    @GetMapping("/town_emission")
    public ResultEntity getTownEmissionAmt(@RequestParam String dataDate) {
        if (StringUtils.isEmpty(dataDate)) return ResultEntity.error("请先选择日期");
        List<Map> list = gatherService.getTownEmissionAmt(dataDate);
        return ResultEntity.success(list);
    }

    //排放统计 qixiu/statistical/enterprise_emission
    @GetMapping("/enterprise_emission")
    public ResultEntity getEnterpriseEmissionAmt(@RequestParam (value = "townName" ,required = false)String townName) {
        List<Map> list = gatherService.getEnterpriseEmissionAmt(townName);
        return ResultEntity.success(list);
    }

    //数采在/离线状态统计 qixiu/statistical/town_checkpoint
    @GetMapping("/town_checkpoint")
    public ResultEntity getTownCheckpointAmt() {
        List<Map> list = gatherService.getTownCheckpointAmt();
        return ResultEntity.success(list);
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
