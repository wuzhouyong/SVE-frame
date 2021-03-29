package com.ruoyi.project.outwater.mapper;

import com.ruoyi.project.enterprise.domain.EnterpriseNetworkEntity;
import com.ruoyi.project.outwater.domain.OWExportGatherEntity;
import com.ruoyi.project.outwater.domain.OWGatherEntity;
import com.ruoyi.project.outwater.domain.PSHSourceAbnorEntity;

import java.util.List;
import java.util.Map;

/**
 * @author solang
 * @date 2020-11-03 10:38
 */

public interface OWGatherMapper {

    List<OWExportGatherEntity> getEnterpriseList(OWGatherEntity entity);

    List<OWExportGatherEntity> getTownAbnormalList(OWGatherEntity entity);

    Map getCalculateHead();

    List<Map> getCalculateBody();

    List<Map> getBrandTransHead();

    List<Map> getBrandTransData();

    List<Map> getConstructionTransHead();

    List<Map> getConstructionTransData();

    List<PSHSourceAbnorEntity> getSourceAbnor(PSHSourceAbnorEntity entity);

    List<Map> getTownNetworkCount();

    List<EnterpriseNetworkEntity> getEnterpriseNetwork(EnterpriseNetworkEntity entity);

}
