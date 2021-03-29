package com.ruoyi.project.lingxing.service;

import com.ruoyi.project.enterprise.domain.EnterpriseNetworkEntity;
import com.ruoyi.project.guocheng.domain.GCSourceAbnorEntity;
import com.ruoyi.project.lingxing.domain.LXExportGatherEntity;
import com.ruoyi.project.lingxing.domain.LXGatherEntity;
import com.ruoyi.project.lingxing.domain.LXSourceAbnorEntity;

import java.util.List;
import java.util.Map;

/**
 * @author solang
 * @date 2020-11-03 10:25
 */
public interface LXGatherService {

    List<LXExportGatherEntity> getEnterpriseList(LXGatherEntity entity);
    List<LXExportGatherEntity> getTownAbnormalList(LXGatherEntity entity);
    List<Map> getCalculate();
    Map getEquipment();
    Map getConstruction();
    List<LXSourceAbnorEntity> getSourceAbnor(LXSourceAbnorEntity entit);

    List<Map> getTownWastewater(String startDate, String endDate);
    List<Map> getMonthWastewater(String startDate, String endDate);
    List<Map> getTownMonthWastewater(Map<String, String> param);

    List<Map> getTownWater(String startDate, String endDate);
    List<Map> getMonthWater(String startDate, String endDate);
    List<Map> getTownMonthWater(Map<String, String> param);

    List<Map> getTownNetworkCount();
    List<EnterpriseNetworkEntity> getEnterpriseNetwork(EnterpriseNetworkEntity entity);

    //企业生产用水统计
    List<Map> getEnterpriseWater(Map<String, String> param);
    List<Map> getStorageRate(Map<String, String> param);
    List<LXSourceAbnorEntity> getTransferAmount(LXSourceAbnorEntity entity);

    List<Map> getElementByEndDischarge(String collectId);
    List<Map> getDischargeTrend(Map<String, String> param);
}
