package com.ruoyi.project.youyan.mapper;

import com.ruoyi.project.enterprise.domain.EnterpriseNetworkEntity;
import com.ruoyi.project.youyan.domain.YYExportGatherEntity;
import com.ruoyi.project.youyan.domain.YYGatherEntity;
import com.ruoyi.project.youyan.domain.YYSourceAbnorEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author solang
 * @date 2020-11-03 10:38
 */

public interface YYGatherMapper {

    List<YYExportGatherEntity> getEnterpriseList(YYGatherEntity entity);
    List<YYExportGatherEntity> getTownAbnormalList(YYGatherEntity entity);

    Map getCalculateHead();
    List<Map> getCalculateBody();

    List<Map> getBrandTransHead();
    List<Map> getBrandTransData();

    List<Map> getConstructionTransHead();
    List<Map> getConstructionTransData();


    List<YYSourceAbnorEntity> getSourceAbnor(YYSourceAbnorEntity entity);
    List<Map> getTownNetworkCount();
    List<EnterpriseNetworkEntity> getEnterpriseNetwork(EnterpriseNetworkEntity entity);
    List<Map> getTownEmissionAmt(@Param("dataDate") String dataDate);
    List<Map> getEnterpriseEmissionAmt(@Param("townName") String townName);
    List<Map> getTownCheckpointAmt();

    List<Map> getElementByEndDischarge(String collectId);
    List<Map> getDischargeTrend(Map<String, String> param);
}
