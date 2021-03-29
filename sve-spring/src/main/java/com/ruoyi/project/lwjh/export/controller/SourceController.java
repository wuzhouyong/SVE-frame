package com.ruoyi.project.lwjh.export.controller;

import com.ruoyi.common.utils.BreakUpUtils;
import com.ruoyi.common.utils.DateStringUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.aspectj.lang.annotation.DataScope;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.common.poi.DocumentUtil;
import com.ruoyi.project.lwjh.domain.*;
import com.ruoyi.project.lwjh.service.LWJHCheckPointElementService;
import com.ruoyi.project.lwjh.service.LWJHCheckPointService;
import com.ruoyi.project.lwjh.service.LWJHSourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/base/source")
public class SourceController extends BaseController {

    @Autowired
    private LWJHSourceService sourceService;

    @Autowired
    private LWJHCheckPointService checkPointService;

    @Autowired
    private LWJHCheckPointElementService elementService;

    @PostMapping("/export")
    @DataScope(regionAlias = "s")
    public AjaxResult export(@RequestBody LWJHSourceEntity entity) {
        List<SourceData> data = sourceService.selectSourceData(entity);
        for (int i = 0; i < data.size(); i++) {
            data.get(i).setNumber(i + 1 + "");
            data.get(i).setCity("东莞市");
            data.get(i).setPolluteType(BreakUpUtils.getPolluteType(data.get(i).getPolluteType()));
            List<CheckPointData> cPDataList = checkPointService.selectCheckPointData(data.get(i).getSourceId(), entity.getAccessDate());
            for (CheckPointData c : cPDataList) {
                List<ElementData> elementDataList = elementService.selectElementData(c.getCheckpointId(), entity.getAccessDate());
                c.setChildrens(elementDataList);
            }
            data.get(i).setChildrens(cPDataList);
        }
        String fileName = "";
        if (StringUtils.isEmpty(entity.getAccessDate()))
            fileName = DateStringUtils.dateToString("yyyy-MM-dd", new Date()) + "-省厅监控企业名录";
        else
            fileName = entity.getAccessDate() + "年-省厅监控企业名录";
        DocumentUtil doc = new DocumentUtil(data);
        doc.addColumn("序号", "number", 10);
        doc.addColumn("地市", "city", 20);
        doc.addColumn("重点排污单位名称", "fullName", 50);
        doc.addColumn("排污许可证编码", "emissionPermitCode", 30);
        doc.addColumn("重点类型", "polluteType", 50);
        doc.addColumn("企业工作负责人", "envContactName", 15);
        doc.addColumn("负责人有效手机号码", "envContactPhone", 20);
        doc.addColumn("所属国民经济行业类型", "industryName", 30);
        doc.addColumn("产污工艺/设备名称", "processFlow", 50);
        doc.addColumn("排污许可证排放口编码", "emissionCode", 20);
        doc.addColumn("监控点名称", "checkpointDesc", 15);
        doc.addColumn("监控污染物", "elementName", 15);
        doc.addColumn("执行标准", "lawStandard", 15);
        doc.addColumn("备注", "memo", 50);
        return doc.exportExcel(fileName);
    }

    @PostMapping("/exportCity")
    @DataScope(regionAlias = "s")
    public AjaxResult exportCity(@RequestBody LWJHSourceEntity entity) {
        List<SourceCityData> data = sourceService.selectSourceCityData(entity);
        for (int i = 0; i < data.size(); i++) {
            List<ElementData> eList = new ArrayList<>();
            List<CheckPointData> cpList = checkPointService.selectCheckPointData(data.get(i).getSourceId(), entity.getAccessDate());
            for (CheckPointData c : cpList) {
                eList.addAll(elementService.selectElementData(c.getCheckpointId(), entity.getAccessDate()));
            }
            data.get(i).setNumber(i + 1 + "");
            if (!StringUtils.isEmpty(data.get(i).getHasVideo()) && data.get(i).getHasVideo().equals("1"))
                data.get(i).setHasVideo("√");
            List<String> pType = BreakUpUtils.breakUp(data.get(i).getPolluteType());
            for (String s : pType) {
                switch (s) {
                    case "1":
                        data.get(i).setWater("√");
                        break;
                    case "2":
                        data.get(i).setAir("√");
                        break;
                    case "4":
                        data.get(i).setSoil("√");
                        break;
                    case "8":
                        data.get(i).setVoice("√");
                        break;
                    case "16":
                        data.get(i).setOther("√");
                        break;
                    default:
                        break;
                }
            }
            for (ElementData e : eList) {
                switch (e.getElementName()) {
                    case "pH":
                        data.get(i).setPH("√");
                        break;
                    case "COD":
                        data.get(i).setCOD("√");
                        break;
                    case "氨氮":
                        data.get(i).setNH3("√");
                        break;
                    case "总磷":
                        data.get(i).setTotalP("√");
                        break;
                    case "总氮":
                        data.get(i).setTotalN("√");
                        break;
                    case "烟尘":
                        data.get(i).setSmoke("√");
                        break;
                    case "二氧化硫":
                        data.get(i).setSO2("√");
                        break;
                    case "氮氧化物":
                        data.get(i).setNOX("√");
                        break;
                    default:
                        break;
                }
            }
        }
        String fileName = "";
        if (StringUtils.isEmpty(entity.getAccessDate()))
            fileName = DateStringUtils.dateToString("yyyy-MM-dd", new Date()) + "-市局监控企业名录";
        else
            fileName = entity.getAccessDate() + "年-市局监控企业名录";
        DocumentUtil doc = new DocumentUtil(data);
        doc.addColumn("序号", "number", 10);
        doc.addColumn("企业详细名称", "fullName", 50);
        doc.addColumn("镇(街)", "townName", 15);
        doc.addColumn("安装因子",
                doc.createColumn("站房和排放口视频", "hasVideo", 20),
                doc.createColumn("Ph", "PH", 10),
                doc.createColumn("COD", "COD", 10),
                doc.createColumn("氨氮", "NH3", 10),
                doc.createColumn("总磷", "totalP", 10),
                doc.createColumn("总氮", "totalN", 10),
                doc.createColumn("烟尘", "smoke", 10),
                doc.createColumn("二氧化硫", "SO2", 10),
                doc.createColumn("氮氧化物", "NOX", 10));
        doc.addColumn("社会信用代码", "socialCreditCode", 20);
        doc.addColumn("国家排污许可证编码", "emissionPermitCode", 20);
        doc.addColumn("行业代码", "industryCode", 20);
        doc.addColumn("行业类别", "industryName", 30);
        doc.addColumn("环保联系人", "envContactName", 10);
        doc.addColumn("联系方式", "envContactPhone", 20);
        doc.addColumn("重点排污单位类型",
                doc.createColumn("水环境重点排污单位", "water", 20),
                doc.createColumn("大气环境重点排污单位", "air", 20),
                doc.createColumn("土壤环境污染重点监管单位", "soil", 25),
                doc.createColumn("声环境重点排污单位", "voice", 20),
                doc.createColumn("其他重点排污单位", "other", 20));
        doc.addColumn("备注", "memo", 50);

        return doc.exportExcel(fileName);
    }
}
