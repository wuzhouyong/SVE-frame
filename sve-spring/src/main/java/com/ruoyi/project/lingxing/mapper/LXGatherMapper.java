package com.ruoyi.project.lingxing.mapper;

import com.ruoyi.project.enterprise.domain.EnterpriseNetworkEntity;
import com.ruoyi.project.lingxing.domain.LXExportGatherEntity;
import com.ruoyi.project.lingxing.domain.LXGatherEntity;
import com.ruoyi.project.lingxing.domain.LXSourceAbnorEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author solang
 * @date 2020-11-03 10:38
 */

public interface LXGatherMapper {

    List<LXExportGatherEntity> getEnterpriseList(LXGatherEntity entity);
    List<LXExportGatherEntity> getTownAbnormalList(LXGatherEntity entity);

    Map getCalculateHead();
    List<Map> getCalculateBody();

    List<Map> getBrandTransHead();
    List<Map> getBrandTransData();

    List<Map> getConstructionTransHead();
    List<Map> getConstructionTransData();

    List<LXSourceAbnorEntity> getSourceAbnor(LXSourceAbnorEntity entity);

    List<Map> getTownWastewater(@Param("startDate") String startDate, @Param("endDate") String endDate);
    List<Map> getMonthWastewater(@Param("startDate") String startDate, @Param("endDate") String endDate);
    List<Map> getTownMonthWastewater(Map<String, String> param);

    List<Map> getTownWater(@Param("startDate") String startDate, @Param("endDate") String endDate);
    List<Map> getMonthWater(@Param("startDate") String startDate, @Param("endDate") String endDate);
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
