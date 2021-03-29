package com.ruoyi.project.qixiu.service;

import com.ruoyi.project.enterprise.domain.EnterpriseNetworkEntity;
import com.ruoyi.project.guocheng.domain.GCSourceAbnorEntity;
import com.ruoyi.project.qixiu.domain.QXExportGatherEntity;
import com.ruoyi.project.qixiu.domain.QXGatherEntity;
import com.ruoyi.project.qixiu.domain.QXSourceAbnorEntity;

import java.util.List;
import java.util.Map;

/**
 * @author solang
 * @date 2020-11-03 10:25
 */
public interface QXGatherService {

    List<QXExportGatherEntity> getEnterpriseList(QXGatherEntity entity);
    List<QXExportGatherEntity> getTownAbnormalList(QXGatherEntity entity);
    List<Map> getCalculate();
    Map getEquipment();
    Map getConstruction();
    List<QXSourceAbnorEntity> getSourceAbnor(QXSourceAbnorEntity entit);

    List<Map> getTownElectric(String startDate, String endDate);
    List<Map> getMonthElectric(String startDate, String endDate);
    List<Map> getTownMonthElectric(Map<String, String> param);

    List<Map> getTownNetworkCount();

    List<EnterpriseNetworkEntity> getEnterpriseNetwork(EnterpriseNetworkEntity entity);

    List<Map> getTownEmissionAmt(String dataDate);
    List<Map> getEnterpriseEmissionAmt(String townName);
    List<Map> getTownCheckpointAmt();

    List<Map> getElementByEndDischarge(String collectId);
    List<Map> getDischargeTrend(Map<String, String> param);
}
