package com.ruoyi.project.jiayou.mapper;

import com.ruoyi.project.jiayou.domain.JYSourceAbnorEntity;

import java.util.List;
import java.util.Map;

/**
 * @author solang
 * @date 2020-11-03 10:38
 */

public interface JYGatherMapper {

    Map getCalculateHead();
    List<Map> getCalculateBody();

    List<Map> getBrandTransHead();
    List<Map> getBrandTransData();

    List<Map> getConstructionTransHead();
    List<Map> getConstructionTransData();

    List<JYSourceAbnorEntity> getSourceAbnor(JYSourceAbnorEntity entity);


}
