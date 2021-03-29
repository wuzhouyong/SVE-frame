package com.ruoyi.project.zaozhi.mapper;

import com.ruoyi.project.enterprise.domain.EnterpriseNetworkEntity;
import com.ruoyi.project.zaozhi.domain.ZZExportGatherEntity;
import com.ruoyi.project.zaozhi.domain.ZZGatherEntity;
import com.ruoyi.project.zaozhi.domain.ZZSourceAbnorEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author solang
 * @date 2020-11-03 10:38
 */

public interface ZZGatherMapper {

    List<ZZExportGatherEntity> getEnterpriseList(ZZGatherEntity entity);
    List<ZZExportGatherEntity> getTownAbnormalList(ZZGatherEntity entity);

    Map getCalculateHead();
    List<Map> getCalculateBody();

    List<Map> getBrandTransHead();
    List<Map> getBrandTransData();

    List<Map> getConstructionTransHead();
    List<Map> getConstructionTransData();

    List<ZZSourceAbnorEntity> getSourceAbnor(ZZSourceAbnorEntity entity);

    List<Map> getTownWastewater(@Param("startDate") String startDate, @Param("endDate") String endDate);
    List<Map> getMonthWastewater(@Param("startDate") String startDate, @Param("endDate") String endDate);
    List<Map> getTownMonthWastewater(Map<String, String> param);

    List<Map> getTownElectric(@Param("startDate") String startDate, @Param("endDate") String endDate);
    List<Map> getMonthElectric(@Param("startDate") String startDate, @Param("endDate") String endDate);
    List<Map> getTownMonthElectric(Map<String, String> param);

    List<Map> getTownWater(@Param("startDate") String startDate, @Param("endDate") String endDate);
    List<Map> getMonthWater(@Param("startDate") String startDate, @Param("endDate") String endDate);
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
