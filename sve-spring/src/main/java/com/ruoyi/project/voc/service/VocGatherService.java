package com.ruoyi.project.voc.service;

import com.ruoyi.project.enterprise.domain.EnterpriseNetworkEntity;
import com.ruoyi.project.guocheng.domain.GCSourceAbnorEntity;
import com.ruoyi.project.voc.domain.VOCSourceAbnorEntity;
import com.ruoyi.project.voc.domain.VocExportGatherEntity;
import com.ruoyi.project.voc.domain.VocGatherEntity;

import java.util.List;
import java.util.Map;

/**
 * @author solang
 * @date 2020-11-03 10:25
 */
public interface VocGatherService {

    List<VocExportGatherEntity> getEnterpriseList(VocGatherEntity entity);

    List<VocExportGatherEntity> getTownAbnormalList(VocGatherEntity entity);

    List<Map> getCalculate();

    Map getEquipment();

    Map getConstruction();

    List<VOCSourceAbnorEntity> getSourceAbnor(VOCSourceAbnorEntity entit);

    List<Map> getTownElectric(String startDate, String endDate);

    List<Map> getMonthElectric(String startDate, String endDate);

    List<Map> getTownMonthElectric(Map<String, String> param);

    List<Map> getTownNetworkCount();

    List<Map> getTownWarnAmt(Map<String, String> param);
    List<Map> getTownWarnEnterprise(Map<String, String> param);

    List<EnterpriseNetworkEntity> getEnterpriseNetwork(EnterpriseNetworkEntity entity);

    List<Map> getTownEmissionAmt(String dataDate);
    List<Map> getEnterpriseEmissionAmt(String townName);
    List<Map> getTownCheckpointAmt();

    List<Map> getElementByEndDischarge(String collectId);
    List<Map> getDischargeTrend(Map<String, String> param);
}
