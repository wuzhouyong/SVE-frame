package com.ruoyi.project.lingxing.mapper;

import com.ruoyi.framework.aspectj.lang.annotation.DataSource;
import com.ruoyi.framework.aspectj.lang.enums.DataSourceType;
import com.ruoyi.project.enterprise.domain.DisallowanceEntity;
import com.ruoyi.project.lingxing.domain.LXConstructionEntity;

import java.util.List;
import java.util.Map;

public interface LXPStatisticalMapper {


    @DataSource(value = DataSourceType.LINGXING)
    public List<LXConstructionEntity> getDatConstructionList(String sourceId);

    @DataSource(value = DataSourceType.MASTER)
    List<DisallowanceEntity> getDisallowanceList(String sourceId);

    List<Map> getTransBillReceiveAmt(Map<String, String> param);
    List<Map> getInflowAmt(Map<String, String> param);
    List<Map> getEmissionsAmt(Map<String, String> param);
    List<Map> getElectricityAmt(Map<String, String> param);
    List<Map> getCarWaterAmt(Map<String, String> param);
    List<Map> getWaterQualityAmt(Map<String, String> param);
}
