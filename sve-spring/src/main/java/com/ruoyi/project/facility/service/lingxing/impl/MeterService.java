package com.ruoyi.project.facility.service.lingxing.impl;

import com.ruoyi.common.domain.ResultEntity;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.project.facility.domain.ElementEntity;
import com.ruoyi.project.facility.domain.ElementParam;
import com.ruoyi.project.facility.domain.ElementResult;
import com.ruoyi.project.facility.domain.lingxing.meter.MeterEntity;
import com.ruoyi.project.facility.domain.lingxing.meter.MeterParam;
import com.ruoyi.project.facility.domain.lingxing.meter.MeterResult;
import com.ruoyi.project.facility.mapper.ElementRefMapper;
import com.ruoyi.project.facility.mapper.lingxing.MeterMapper;
import com.ruoyi.project.facility.service.impl.ElementService;
import com.ruoyi.project.facility.service.lingxing.IMeterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class MeterService implements IMeterService {
    @Autowired
    private MeterMapper meterMapper;

    @Autowired
    private ElementService elementService;

    @Autowired
    private ElementRefMapper elementRefMapper;

    @Override
    public List<MeterResult> getMeterList(MeterParam param) {
        List<MeterResult> list = meterMapper.getMeterList(param);
        list.forEach(e -> {
            ElementParam elementParam = new ElementParam();
            elementParam.setFacilityId(e.getMeterId());
            List<ElementResult> elementList = elementService.getElementListForLingXing(elementParam);
            e.setElements(elementList);
        });
        return list;
    }

    @Override
    public ResultEntity addMeter(MeterEntity entity) {
        entity.setMeterId(UUID.randomUUID().toString());
        int res = meterMapper.addMeter(entity);
        if (res > 0) {
            for (ElementEntity element : entity.getElements()) {
                element.setFacilityId(entity.getMeterId());
                res = elementService.addElement("lingxing", element);
                if (res <= 0) {
                    break;
                }
                if (entity.getIsOther()) {
                    element.setSrcTagHeadId(1002);
                    element.setDstElemId(String.valueOf(element.getCpElemId()));
                    element.setDstTagHeadId(1003);
                    res = elementRefMapper.addElementRef(element);
                    if (res <= 0) {
                        break;
                    }
                }
            }
            return ResultEntity.success("水表添加成功");
        }
        return ResultEntity.error("水表添加失败");
    }

    @Override
    public ResultEntity editMeter(MeterEntity entity) {
        int res = meterMapper.editMeter(entity);
        if (res > 0) {
            for (ElementEntity element : entity.getElements()) {
                res = elementService.editElement("lingxing", element);
                if (res <= 0) {
                    break;
                }
            }
            return ResultEntity.success("水表修改成功");
        }
        return ResultEntity.error("水表修改失败");
    }

    @Override
    public ResultEntity removeMeter(String[] meterIds) {
        ElementParam param = new ElementParam();
        param.setFacilityIds(meterIds);
        List<ElementResult> list = elementService.getElementListForLingXing(param);
        List<Integer> elementIds = list.stream().map(ElementResult::getCpElemId).collect(Collectors.toList());
        int res;
        if (elementIds.size() > 0) {
            res = elementRefMapper.removeElementRef(1003, elementIds, SecurityUtils.getUsername());
            if (res < 0) {
                return ResultEntity.error("水表删除失败");
            }
            res = elementService.removeElement("lingxing", elementIds);
            if (res < 0) {
                return ResultEntity.error("水表删除失败");
            }
        }
        res = meterMapper.removeMeter(meterIds, SecurityUtils.getUsername());
        if (res > 0) {
            return ResultEntity.success("水表删除成功");
        }
        return ResultEntity.error("水表删除失败");
    }
}
