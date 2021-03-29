package com.ruoyi.project.construction.service;

import java.util.Map;

public interface IConstructionElementService {
    Map getNoFacilityElementForLingXing(String tagHeadId, String sourceId);
    Map getNoFacilityElementForVOC(String tagHeadId, String sourceId);
    Map getNoFacilityElementForYouYan(String tagHeadId, String sourceId);
    Map getNoFacilityElementForQiXiu(String tagHeadId, String sourceId);

    Map getNoDataElementForGuoCheng(String tagHeadId, String sourceId);
    Map getNoDataElementForLingXing(String tagHeadId, String sourceId);
    Map getNoDataElementForVOC(String tagHeadId, String sourceId);
    Map getNoDataElementForYouYan(String tagHeadId, String sourceId);
    Map getHasDataElementForPaiShuiHu(String tagHeadId, String sourceId);
    Map getNoDataElementForPaiShuiHu(String tagHeadId, String sourceId);
    Map getNoDataElementForQiXiu(String tagHeadId, String sourceId);
    Map getNoDataElementForJiaYou(String tagHeadId, String sourceId);
    Map getNoDataElementForJinShu(String tagHeadId, String sourceId);
    Map getNoDataElementForZaoZhi(String tagHeadId, String sourceId);
}
