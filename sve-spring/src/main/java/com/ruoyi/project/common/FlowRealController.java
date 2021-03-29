package com.ruoyi.project.common;

import com.ruoyi.common.utils.HashMapUtils;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.qixiu.service.QXOutletService;
import com.ruoyi.project.voc.service.VocBasFacilityService;
import com.ruoyi.project.youyan.service.YYFacilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


//流程图数据
@RestController
@RequestMapping("/flowreal")
public class FlowRealController extends BaseController {

    @Autowired
    private YYFacilityService yyFacilityService;

    @Autowired
    private QXOutletService outletService;

    @Autowired
    private VocBasFacilityService vocFacilityService;

    @GetMapping("/chart")
    public AjaxResult flowChartTree(@RequestParam Map<String, Object> keys) {
        String name = keys.get("platform").toString();
        switch (name) {
            case "youyan":
                return AjaxResult.success(yyFacilityService.selectBasFacilityBySourceId(keys.get("key").toString()));
            case "qixiu":
                return AjaxResult.success(outletService.bulidOutletTreeBySourceId(keys.get("key").toString()));
            case "voc":
                return AjaxResult.success(vocFacilityService.getFlowFacilityBySourceId(keys.get("key").toString()));
            //return null;
            default:
                return AjaxResult.error();
        }
    }

    @GetMapping("/realdata")
    public AjaxResult flowTagsReal(@RequestParam Map<String, Object> keys) {

        String type = keys.get("platform").toString();
        List<Map> tags;
        List<Map> runStatus;
        if (type.equals("youyan")) {
            tags = yyFacilityService.selectFacilityRealElementData(Integer.valueOf(keys.get("key").toString()));
            runStatus = yyFacilityService.selectFacilityRunStatus(Integer.valueOf(keys.get("key").toString()));
        } else if (type.equals("qixiu")) {
            tags = outletService.selectCheckElemntTagVal(keys.get("key").toString());
            runStatus = outletService.getPointRunStatus(tags);
        } else if (type.equals("voc")) {
            tags = vocFacilityService.selectVocElemntTagVal(keys.get("key").toString());
            runStatus = vocFacilityService.getVocFacilityRunStatus(keys.get("key").toString());
        } else
            return AjaxResult.error();

        return AjaxResult.success(new HashMap<String, Object>() {
            {
                put("tags", HashMapUtils.toCamel(tags));
                put("runStatus", HashMapUtils.toCamel(runStatus));
            }
        });
    }
}
