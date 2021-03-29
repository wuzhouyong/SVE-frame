package com.ruoyi.project.youyan.service;

import com.ruoyi.project.enterprise.domain.EnterpriseNetworkEntity;
import com.ruoyi.project.guocheng.domain.GCSourceAbnorEntity;
import com.ruoyi.project.youyan.domain.YYExportGatherEntity;
import com.ruoyi.project.youyan.domain.YYGatherEntity;
import com.ruoyi.project.youyan.domain.YYSourceAbnorEntity;

import java.util.List;
import java.util.Map;

/**
 * @author solang
 * @date 2020-11-03 10:25
 */
public interface YYGatherService {

    List<YYExportGatherEntity> getEnterpriseList(YYGatherEntity entity);
    List<YYExportGatherEntity> getTownAbnormalList(YYGatherEntity entity);
    List<Map> getCalculate();
    Map getEquipment();
    Map getConstruction();
    List<YYSourceAbnorEntity> getSourceAbnor(YYSourceAbnorEntity entit);

    List<Map> getTownNetworkCount();

    List<EnterpriseNetworkEntity> getEnterpriseNetwork(EnterpriseNetworkEntity entity);
    List<Map> getTownEmissionAmt(String dataDate);
    List<Map> getEnterpriseEmissionAmt(String townName);
    List<Map> getTownCheckpointAmt();

    List<Map> getElementByEndDischarge(String collectId);
    List<Map> getDischargeTrend(Map<String, String> param);
}
