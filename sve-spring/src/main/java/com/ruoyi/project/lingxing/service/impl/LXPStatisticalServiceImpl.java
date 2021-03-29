package com.ruoyi.project.lingxing.service.impl;

import com.ruoyi.common.utils.HashMapUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.aspectj.lang.annotation.DataSource;
import com.ruoyi.framework.aspectj.lang.enums.DataSourceType;
import com.ruoyi.project.enterprise.domain.DisallowanceEntity;
import com.ruoyi.project.lingxing.domain.LXConstructionEntity;
import com.ruoyi.project.lingxing.mapper.LXPStatisticalMapper;
import com.ruoyi.project.lingxing.service.LXPStatisticalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@DataSource(value = DataSourceType.LINGXING)
@Service
public class LXPStatisticalServiceImpl implements LXPStatisticalService {

    @Autowired
    private LXPStatisticalMapper mapper;

    @Override
    public List<LXConstructionEntity> getDatConstructionList(String sourceId) {
        List<LXConstructionEntity> list = mapper.getDatConstructionList(sourceId);
        List<DisallowanceEntity> disaList = mapper.getDisallowanceList(sourceId);
        for (LXConstructionEntity entity : list) {
            if (StringUtils.isNotEmpty(entity.getAcceptDate()) && StringUtils.isNotEmpty(disaList)) {
                entity.setFlag(true);
            } else entity.setFlag(false);
        }
        return list;
    }

    @Override
    public List<Map> getTransBillReceiveAmt(Map<String, String> param) {
        return HashMapUtils.toLower(mapper.getTransBillReceiveAmt(param));
    }

    @Override
    public List<Map> getInflowAmt(Map<String, String> param) {
        return HashMapUtils.toLower(mapper.getInflowAmt(param));
    }

    @Override
    public List<Map> getEmissionsAmt(Map<String, String> param) {
        return HashMapUtils.toLower(mapper.getEmissionsAmt(param));
    }

    @Override
    public List<Map> getElectricityAmt(Map<String, String> param) {
        return HashMapUtils.toLower(mapper.getElectricityAmt(param));
    }

    @Override
    public List<Map> getCarWaterAmt(Map<String, String> param) {
        return HashMapUtils.toLower(mapper.getCarWaterAmt(param));
    }

    @Override
    public List<Map> getWaterQualityAmt(Map<String, String> param) {
        return HashMapUtils.toLower(mapper.getWaterQualityAmt(param));
    }

}
