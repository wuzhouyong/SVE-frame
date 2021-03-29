package com.ruoyi.project.guocheng.service;

import com.ruoyi.project.enterprise.domain.EnterpriseNetworkEntity;
import com.ruoyi.project.guocheng.domain.GCExportGatherEntity;
import com.ruoyi.project.guocheng.domain.GCGatherEntity;
import com.ruoyi.project.guocheng.domain.GCSourceAbnorEntity;

import java.util.List;
import java.util.Map;

/**
 * @author solang
 * @date 2020-11-03 10:25
 */
public interface GCGatherService {

    List<GCExportGatherEntity> getEnterpriseList(GCGatherEntity entity);
    List<GCExportGatherEntity> getTownAbnormalList(GCGatherEntity entity);
    List<GCExportGatherEntity> industrialWastewater(GCGatherEntity entity);
    List<Map> getCalculate();
    Map getEquipment();
    Map getConstruction();
    List<GCSourceAbnorEntity> getSourceAbnor(GCSourceAbnorEntity entit);

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

    //企业生产用水统计
    List<Map> getProductWaterAmt(Map<String, String> param);
    List<Map> getProdElectricAmt(Map<String, String> param);
    List<Map> getReuseWaterAmt(Map<String, String> param);
    List<Map> getRealElectricAmt(Map<String, String> param);
    List<Map> getSewageDischargeAmt(Map<String, String> param);

    List<Map> getElementByEndDischarge(String collectId);
    List<Map> getDischargeTrend(Map<String, String> param);
}
