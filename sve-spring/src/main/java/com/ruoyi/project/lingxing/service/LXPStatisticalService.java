package com.ruoyi.project.lingxing.service;

import com.ruoyi.project.lingxing.domain.LXConstructionEntity;

import java.util.List;
import java.util.Map;

public interface LXPStatisticalService {

    public List<LXConstructionEntity> getDatConstructionList(String sourceId);

    public List<Map> getTransBillReceiveAmt(Map<String, String> param);
    public List<Map> getInflowAmt(Map<String, String> param);
    public List<Map> getEmissionsAmt(Map<String, String> param);
    public List<Map> getElectricityAmt(Map<String, String> param);
    public List<Map> getCarWaterAmt(Map<String, String> param);
    public List<Map> getWaterQualityAmt(Map<String, String> param);
}
