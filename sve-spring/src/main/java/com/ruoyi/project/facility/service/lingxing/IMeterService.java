package com.ruoyi.project.facility.service.lingxing;

import com.ruoyi.common.domain.ResultEntity;
import com.ruoyi.project.facility.domain.lingxing.meter.MeterEntity;
import com.ruoyi.project.facility.domain.lingxing.meter.MeterParam;
import com.ruoyi.project.facility.domain.lingxing.meter.MeterResult;

import java.util.List;

public interface IMeterService {
    List<MeterResult> getMeterList(MeterParam param);
    ResultEntity addMeter(MeterEntity entity);
    ResultEntity editMeter(MeterEntity entity);
    ResultEntity removeMeter(String[] meterIds);
}
