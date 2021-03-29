package com.ruoyi.project.outwater.controller;

import com.ruoyi.common.domain.ResultEntity;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.common.poi.DocumentUtil;
import com.ruoyi.project.enterprise.domain.EnterpriseNetworkEntity;
import com.ruoyi.project.outwater.domain.OWExportGatherEntity;
import com.ruoyi.project.outwater.domain.OWGatherEntity;
import com.ruoyi.project.outwater.domain.PSHSourceAbnorEntity;
import com.ruoyi.project.outwater.service.OWGatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author solang
 * @date 2020-11-03 10:13
 */
@RestController
@RequestMapping("/outwater/statistical")
public class OWGatherController extends BaseController {

    @Autowired
    private OWGatherService gatherService;

    @PostMapping("/enterprise")
    public ResultEntity enterpriseList(@RequestBody OWGatherEntity entity) {
        return ResultEntity.success(gatherService.getEnterpriseList(entity));
    }

    @PostMapping("/export_enterprise")
    public AjaxResult AbnormalEnterprise(@RequestBody OWGatherEntity entity) {
        List<OWExportGatherEntity> data = gatherService.getEnterpriseList(entity);
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

    @PostMapping("/town") //街镇异常汇总
    public ResultEntity townAbnormalList(@RequestBody OWGatherEntity entity) {
        return ResultEntity.success(gatherService.getTownAbnormalList(entity));
    }

    @PostMapping("/export_town")
    public AjaxResult townAbnormal(@RequestBody OWGatherEntity entity) {
        List<OWExportGatherEntity> data = gatherService.getTownAbnormalList(entity);
        DocumentUtil doc = new DocumentUtil(data);
        doc.addColumn("街镇", "townName", 15);
        doc.addColumn("异常等级", "levelName", 15);
        doc.addColumn("产生异常数", "warnCount", 15);
        doc.addColumn("企业未处理", "factoryCount", 15);
        doc.addColumn("核收未核实", "dutyCount", 15);
        doc.addColumn("分局未处理", "substationCount", 15);
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
    public TableDataInfo getSourceAbnor(@RequestBody PSHSourceAbnorEntity entity) {
        startPage();
        List<PSHSourceAbnorEntity> list = gatherService.getSourceAbnor(entity);
        return getDataTable(list);
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

}
