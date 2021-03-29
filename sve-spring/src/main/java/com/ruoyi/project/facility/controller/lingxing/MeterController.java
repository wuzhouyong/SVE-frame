package com.ruoyi.project.facility.controller.lingxing;

import com.ruoyi.common.domain.ResultEntity;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.facility.domain.lingxing.meter.MeterEntity;
import com.ruoyi.project.facility.domain.lingxing.meter.MeterParam;
import com.ruoyi.project.facility.domain.lingxing.meter.MeterResult;
import com.ruoyi.project.facility.service.lingxing.IMeterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lingxing/meter")
public class MeterController extends BaseController {
    @Autowired
    private IMeterService service;

    @PostMapping("/list")
    public TableDataInfo getMeterList(@RequestBody MeterParam param) {
        String sourceId = SecurityUtils.getSourceId();
        if (sourceId != null) {
            param.setSourceId(sourceId);
        }
        startPage();
        List<MeterResult> list = service.getMeterList(param);
        return getDataTable(list);
    }

    @PostMapping("/add")
    public ResultEntity addMeter(@RequestBody MeterEntity entity) {
        String sourceId = SecurityUtils.getSourceId();
        if (sourceId == null) return ResultEntity.error("企业未关联或关联错误");
        entity.setSourceId(sourceId);
        return service.addMeter(entity);
    }

    @PostMapping("/edit")
    public ResultEntity editMeter(@RequestBody MeterEntity entity) {
        return service.editMeter(entity);
    }

    @DeleteMapping("/remove/{meterIds}")
    public ResultEntity removeMeter(@PathVariable String[] meterIds) {
        return service.removeMeter(meterIds);
    }
}
