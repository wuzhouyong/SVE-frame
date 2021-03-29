package com.ruoyi.project.lingxing.controller;

import com.ruoyi.common.domain.ResultEntity;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.lingxing.domain.LXConstructionEntity;
import com.ruoyi.project.lingxing.service.LXPStatisticalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/lingxingp")
public class LXPStatisticalController extends BaseController {
    @Autowired
    private LXPStatisticalService service;

//    @GetMapping("/construction/model/{sourceId}")
//    public AjaxResult model(@PathVariable String sourceId) {
//        LXConstructionEntity entity = new LXConstructionEntity();
//        if (!StringUtils.isEmpty(SecurityUtils.getLoginUser().getUser().getSourceId()))
//            sourceId = SecurityUtils.getLoginUser().getUser().getSourceId();
//        if (sourceId != null) {
//            List<LXConstructionEntity> list = service.getDatConstructionList(sourceId);
//            if (list.size() > 0) {
//                entity = list.get(0);
//            }
//        }
//        return AjaxResult.success(entity);
//    }


    @GetMapping("/statistical/trans_bill_receive")
    public ResultEntity getTransBillReceiveAmt(@RequestParam Map<String, String> param) {
        return ResultEntity.success(service.getTransBillReceiveAmt(param));
    }

    //处理单位进水量统计
    @GetMapping("/statistical/inflow_amt")
    public ResultEntity getInflowAmt(@RequestParam Map<String, String> param) {
        if (StringUtils.isEmpty(param.get("sourceId"))) {
            return ResultEntity.error("请先选择企业");
        }
        if (StringUtils.isEmpty(param.get("dataDate"))) {
            return ResultEntity.error("请先选择日期");
        }
        List<Map> list = service.getInflowAmt(param);
        return ResultEntity.success(changeGroup(list));
    }

    //处理单位排放量统计
    @GetMapping("/statistical/emissions_amt")
    public ResultEntity getEmissionsAmt(@RequestParam Map<String, String> param) {
        if (StringUtils.isEmpty(param.get("sourceId"))) {
            return ResultEntity.error("请先选择企业");
        }
        if (StringUtils.isEmpty(param.get("dataDate"))) {
            return ResultEntity.error("请先选择日期");
        }
        List<Map> list = service.getEmissionsAmt(param);
        return ResultEntity.success(changeGroup(list));
    }

    //处理单位用电量统计
    @GetMapping("/statistical/elect_amt")
    public ResultEntity getElectricityAmt(@RequestParam Map<String, String> param) {
        if (StringUtils.isEmpty(param.get("sourceId"))) {
            return ResultEntity.error("请先选择企业");
        }
        if (StringUtils.isEmpty(param.get("dataDate"))) {
            return ResultEntity.error("请先选择日期");
        }
        List<Map> list = service.getElectricityAmt(param);
        return ResultEntity.success(changeGroup(list));
    }

    //处理单位运输车辆实时水量统计  /lingxingp/statistical/car_water_amt
    @PostMapping("/statistical/car_water_amt")
    public ResultEntity getCarWaterAmt(@RequestBody Map<String, String> param) {
        if (StringUtils.isEmpty(param.get("sourceId"))) {
            return ResultEntity.error("请先选择企业");
        }
        if (StringUtils.isEmpty(param.get("dataDate"))) {
            return ResultEntity.error("请先选择日期");
        }
        List<Map<String, Object>> result = new ArrayList<>();
        List<Map> list = service.getCarWaterAmt(param);
//        LinkedHashMap<String, List<Map>> group = list.stream().collect(
//                Collectors.groupingBy(g -> g.get("car_tag").toString(), LinkedHashMap::new, Collectors.toList()));
//        for (Map.Entry<String, List<Map>> entry : group.entrySet()) {
        if (list.size() > 0) {
            String name = list.stream().map(m -> m.get("car_tag").toString()).findFirst().get();
            for (Map map : list) {
                String date = DateUtils.dateTimeS((Date) map.get("datadate"));
                BigDecimal cou_amt = (BigDecimal) map.get("amt");
                double amt = 0.0;
                if (cou_amt != null) amt = Double.parseDouble(cou_amt.toString());
                Map<String, Object> m = new LinkedHashMap<>();
                Optional<Map<String, Object>> optional = result.stream().filter(a -> a.get("日期").equals(date)).findFirst();
                if (optional.isPresent()) {
                    m = optional.get();
                } else {
                    m.put("日期", date);
                    result.add(m);
                }
                String key = name;
                if (map.get("car_type") != null) {
                    key += String.format("(%s)", map.get("car_type"));
                }
                m.put(key, amt);
            }
        }
        return ResultEntity.success(result);
    }

    //处理单位用电量统计
    @GetMapping("/statistical/water_quality_amt")
    public ResultEntity getWaterQualityAmt(@RequestParam Map<String, String> param) {
        if (StringUtils.isEmpty(param.get("sourceId"))) {
            return ResultEntity.error("请先选择企业");
        }
        if (StringUtils.isEmpty(param.get("dataDate"))) {
            return ResultEntity.error("请先选择日期");
        }
        List<Map> list = service.getWaterQualityAmt(param);
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
                if (cou_amt == null) {
                    cou_amt = (BigDecimal) map.get("avg_amt");
                }
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

}
