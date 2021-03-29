package com.ruoyi.project.voc.mapper;

import com.ruoyi.project.enterprise.domain.EnterpriseNetworkEntity;
import com.ruoyi.project.voc.domain.VOCSourceAbnorEntity;
import com.ruoyi.project.voc.domain.VocExportGatherEntity;
import com.ruoyi.project.voc.domain.VocGatherEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author solang
 * @date 2020-11-03 10:38
 */

public interface VocGatherMapper {

    List<VocExportGatherEntity> getEnterpriseList(VocGatherEntity entity);
    List<VocExportGatherEntity> getTownAbnormalList(VocGatherEntity entity);

    Map getCalculateHead();
    List<Map> getCalculateBody();

    List<Map> getBrandTransHead();
    List<Map> getBrandTransData();

    List<Map> getConstructionTransHead();
    List<Map> getConstructionTransData();

    List<VOCSourceAbnorEntity> getSourceAbnor(VOCSourceAbnorEntity entity);

    List<Map> getTownElectric(@Param("startDate") String startDate, @Param("endDate") String endDate);
    List<Map> getMonthElectric(@Param("startDate") String startDate, @Param("endDate") String endDate);
    List<Map> getTownMonthElectric(Map<String, String> param);

    List<Map> getTownNetworkCount();

    List<Map> getTownWarnAmt(Map<String, String> param);
    List<Map> getTownWarnEnterprise(Map<String, String> param);
    List<EnterpriseNetworkEntity> getEnterpriseNetwork(EnterpriseNetworkEntity entity);
    List<Map> getTownEmissionAmt(@Param("dataDate") String dataDate);
    List<Map> getEnterpriseEmissionAmt(@Param("townName") String townName);
    List<Map> getTownCheckpointAmt();

    List<Map> getElementByEndDischarge(String collectId);
    List<Map> getDischargeTrend(Map<String, String> param);
}
