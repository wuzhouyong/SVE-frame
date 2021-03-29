package com.ruoyi.project.dgom.service.impl;

import com.ruoyi.common.utils.HashMapUtils;
import com.ruoyi.framework.aspectj.lang.annotation.DataSource;
import com.ruoyi.framework.aspectj.lang.enums.DataSourceType;
import com.ruoyi.project.dgom.mapper.WaterStatisticalMapper;
import com.ruoyi.project.dgom.service.WaterStatisticalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author solang
 * @date 2020-12-04 10:32
 */
@Service
@DataSource(value = DataSourceType.HBONLINE)
public class WaterStatisticalServiceImpl implements WaterStatisticalService {

    @Autowired
    private WaterStatisticalMapper mapper;


    @Override
    public List<Map> getTownWastewater(String startDate, String endDate) {
        return HashMapUtils.toCamel(mapper.getTownWastewater(startDate, endDate));
    }

    @Override
    public List<Map> getMonthWastewater(String startDate, String endDate) {
        return HashMapUtils.toCamel(mapper.getMonthWastewater(startDate, endDate));
    }

    @Override
    public List<Map> getTownMonthWastewater(Map<String, String> param) {
        List<Map> list = HashMapUtils.toCamel(mapper.getTownMonthWastewater(param));
        return HashMapUtils.toTypeOf(list);
    }

}
