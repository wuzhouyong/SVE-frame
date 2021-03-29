package com.ruoyi.project.facility.controller;

import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.facility.domain.ElementParam;
import com.ruoyi.project.facility.domain.ElementResult;
import com.ruoyi.project.facility.service.impl.ElementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/{prefix}/element")
public class ElementController extends BaseController {
    @Autowired
    private ElementService service;

    @PostMapping("/list")
    public TableDataInfo getElementList(@PathVariable String prefix, @RequestBody ElementParam param) {
        String sourceId = SecurityUtils.getSourceId();
        if (sourceId != null) {
            param.setSourceId(sourceId);
        }
        startPage();
        List<ElementResult> list;
        switch (prefix) {
            case "lingxing":
                list = service.getElementListForLingXing(param);
                break;
            case "voc":
                list = service.getElementListForVOC(param);
                break;
            default:
                param.setDbName(prefix);
                list = service.getElementList(param);
                break;
        }
        return getDataTable(list);
    }
}
