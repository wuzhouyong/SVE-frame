package com.ruoyi.project.qixiu.mapper;

import com.ruoyi.project.enterprise.domain.EnterpriseNetworkEntity;
import com.ruoyi.project.qixiu.domain.QXExportGatherEntity;
import com.ruoyi.project.qixiu.domain.QXGatherEntity;
import com.ruoyi.project.qixiu.domain.QXSourceAbnorEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author solang
 * @date 2020-11-03 10:38
 */

public interface QXGatherMapper {

    List<QXExportGatherEntity> getEnterpriseList(QXGatherEntity entity);
    List<QXExportGatherEntity> getTownAbnormalList(QXGatherEntity entity);

    Map getCalculateHead();
    List<Map> getCalculateBody();

    List<Map> getBrandTransHead();
    List<Map> getBrandTransData();

    List<Map> getConstructionTransHead();
    List<Map> getConstructionTransData();


    List<QXSourceAbnorEntity> getSourceAbnor(QXSourceAbnorEntity entity);

    List<Map> getTownElectric(@Param("startDate") String startDate, @Param("endDate") String endDate);
    List<Map> getMonthElectric(@Param("startDate") String startDate, @Param("endDate") String endDate);
    List<Map> getTownMonthElectric(Map<String, String> param);

    List<Map> getTownNetworkCount();

    List<EnterpriseNetworkEntity> getEnterpriseNetwork(EnterpriseNetworkEntity entity);
    List<Map> getTownEmissionAmt(@Param("dataDate") String dataDate);
    List<Map> getEnterpriseEmissionAmt(@Param("townName") String townName);
    List<Map> getTownCheckpointAmt();

    List<Map> getElementByEndDischarge(String collectId);
    List<Map> getDischargeTrend(Map<String, String> param);
}
