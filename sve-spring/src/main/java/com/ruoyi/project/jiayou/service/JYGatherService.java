package com.ruoyi.project.jiayou.service;


import com.ruoyi.project.guocheng.domain.GCSourceAbnorEntity;
import com.ruoyi.project.jiayou.domain.JYSourceAbnorEntity;

import java.util.List;
import java.util.Map;

/**
 * @author solang
 * @date 2020-11-03 10:25
 */
public interface JYGatherService {

    List<Map> getCalculate();
    Map getEquipment();
    Map getConstruction();
    List<JYSourceAbnorEntity> getSourceAbnor(JYSourceAbnorEntity entit);
}
