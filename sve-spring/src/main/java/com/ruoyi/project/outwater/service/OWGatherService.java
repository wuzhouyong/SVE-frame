package com.ruoyi.project.outwater.service;

import com.ruoyi.project.enterprise.domain.EnterpriseNetworkEntity;
import com.ruoyi.project.guocheng.domain.GCSourceAbnorEntity;
import com.ruoyi.project.outwater.domain.OWExportGatherEntity;
import com.ruoyi.project.outwater.domain.OWGatherEntity;
import com.ruoyi.project.outwater.domain.PSHSourceAbnorEntity;

import java.util.List;
import java.util.Map;

/**
 * @author solang
 * @date 2020-11-03 10:25
 */
public interface OWGatherService {

    List<OWExportGatherEntity> getEnterpriseList(OWGatherEntity entity);
    List<OWExportGatherEntity> getTownAbnormalList(OWGatherEntity entity);
    List<Map> getCalculate();
    Map getEquipment();
    Map getConstruction();
    List<PSHSourceAbnorEntity> getSourceAbnor(PSHSourceAbnorEntity entit);

    List<Map> getTownNetworkCount();

    List<EnterpriseNetworkEntity> getEnterpriseNetwork(EnterpriseNetworkEntity entity);

}
