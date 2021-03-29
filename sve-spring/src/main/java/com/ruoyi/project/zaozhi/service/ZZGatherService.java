package com.ruoyi.project.zaozhi.service;

import com.ruoyi.project.enterprise.domain.EnterpriseNetworkEntity;
import com.ruoyi.project.guocheng.domain.GCSourceAbnorEntity;
import com.ruoyi.project.zaozhi.domain.ZZExportGatherEntity;
import com.ruoyi.project.zaozhi.domain.ZZGatherEntity;
import com.ruoyi.project.zaozhi.domain.ZZSourceAbnorEntity;

import java.util.List;
import java.util.Map;

/**
 * @author solang
 * @date 2020-11-03 10:25
 */
public interface ZZGatherService {

    List<ZZExportGatherEntity> getEnterpriseList(ZZGatherEntity entity);
    List<ZZExportGatherEntity> getTownAbnormalList(ZZGatherEntity entity);
    List<Map> getCalculate();
    Map getEquipment();
    Map getConstruction();
    List<ZZSourceAbnorEntity> getSourceAbnor(ZZSourceAbnorEntity entit);

    List<Map> getTownWastewater(String startDate, String endDate);
    List<Map> getMonthWastewater(String startDate, String endDate);
    List<Map> getTownMonthWastewater(Map<String, String> param);

    List<Map> getTownElectric(String startDate, String endDate);
    List<Map> getMonthElectric(String startDate, String endDate);
    List<Map> getTownMonthElectric(Map<String, String> param);

    List<Map> getTownWater(String startDate, String endDate);
    List<Map> getMonthWater(String startDate, String endDate);
    List<Map> getTownMonthWater(Map<String, String> param);

    List<Map> getTownNetworkCount();
    List<EnterpriseNetworkEntity> getEnterpriseNetwork(EnterpriseNetworkEntity entity);

    List<Map> getProductWaterAmt(Map<String, String> param);
    List<Map> getProdElectricAmt(Map<String, String> param);
    List<Map> getReuseWaterAmt(Map<String, String> param);
    List<Map> getRealElectricAmt(Map<String, String> param);
    List<Map> getSewageDischargeAmt(Map<String, String> param);
    List<Map> getElementByEndDischarge(String collectId);
    List<Map> getDischargeTrend(Map<String, String> param);
}
