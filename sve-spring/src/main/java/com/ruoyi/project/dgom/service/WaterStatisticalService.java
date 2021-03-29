package com.ruoyi.project.dgom.service;

import java.util.List;
import java.util.Map;

/**
 * @author solang
 * @date 2020-12-04 10:32
 */
public interface WaterStatisticalService {

    List<Map> getTownWastewater(String startDate, String endDate);
    List<Map> getMonthWastewater(String startDate, String endDate);
    List<Map> getTownMonthWastewater(Map<String, String> param);

}
