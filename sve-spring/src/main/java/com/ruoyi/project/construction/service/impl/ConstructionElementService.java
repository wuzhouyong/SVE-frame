package com.ruoyi.project.construction.service.impl;

import com.ruoyi.common.utils.HashMapUtils;
import com.ruoyi.framework.aspectj.lang.annotation.DataSource;
import com.ruoyi.framework.aspectj.lang.enums.DataSourceType;
import com.ruoyi.project.construction.mapper.ConstructionElementMapper;
import com.ruoyi.project.construction.service.IConstructionElementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ConstructionElementService implements IConstructionElementService {
    @Autowired
    private ConstructionElementMapper mapper;

    @Override
    @DataSource(value = DataSourceType.LINGXING)
    public Map getNoFacilityElementForLingXing(String tagHeadId, String sourceId) {
        return mapper.getNoFacilityElement(tagHeadId, sourceId);
    }

    @Override
    public Map getNoFacilityElementForVOC(String tagHeadId, String sourceId) {
        return HashMapUtils.toLower(mapper.getNoFacilityElementForVOC(tagHeadId, sourceId));
    }

    @Override
    @DataSource(value = DataSourceType.YOUYAN)
    public Map getNoFacilityElementForYouYan(String tagHeadId, String sourceId) {
        return HashMapUtils.toLower(mapper.getNoFacilityElement(tagHeadId, sourceId));
    }

    @Override
    @DataSource(value = DataSourceType.QIXIU)
    public Map getNoFacilityElementForQiXiu(String tagHeadId, String sourceId) {
        return HashMapUtils.toLower(mapper.getNoFacilityElementForQiXiu(tagHeadId, sourceId));
    }

    @Override
    @DataSource(value = DataSourceType.GUOCHENG)
    public Map getNoDataElementForGuoCheng(String tagHeadId, String sourceId) {
        return HashMapUtils.toLower(mapper.getNoDataElement(tagHeadId, sourceId));
    }

    @Override
    @DataSource(value = DataSourceType.LINGXING)
    public Map getNoDataElementForLingXing(String tagHeadId, String sourceId) {
        return HashMapUtils.toLower(mapper.getNoDataElement(tagHeadId, sourceId));
    }

    @Override
    @DataSource(value = DataSourceType.VOC)
    public Map getNoDataElementForVOC(String tagHeadId, String sourceId) {
        return HashMapUtils.toLower(mapper.getNoDataElement(tagHeadId, sourceId));
    }

    @Override
    @DataSource(value = DataSourceType.YOUYAN)
    public Map getNoDataElementForYouYan(String tagHeadId, String sourceId) {
        return HashMapUtils.toLower(mapper.getNoDataElement(tagHeadId, sourceId));
    }

    @Override
    @DataSource(value = DataSourceType.OUTWATER)
    public Map getNoDataElementForPaiShuiHu(String tagHeadId, String sourceId) {
        return HashMapUtils.toLower(mapper.getNoDataElement(tagHeadId, sourceId));
    }

    @Override
    @DataSource(value = DataSourceType.OUTWATER)
    public Map getHasDataElementForPaiShuiHu(String tagHeadId, String sourceId) {
        return HashMapUtils.toLower(mapper.getHasDataElement(tagHeadId, sourceId));
    }

    @Override
    @DataSource(value = DataSourceType.QIXIU)
    public Map getNoDataElementForQiXiu(String tagHeadId, String sourceId) {
        return HashMapUtils.toLower(mapper.getNoDataElement(tagHeadId, sourceId));
    }

    @Override
    @DataSource(value = DataSourceType.JIAYOU)
    public Map getNoDataElementForJiaYou(String tagHeadId, String sourceId) {
        return HashMapUtils.toLower(mapper.getNoDataElement(tagHeadId, sourceId));
    }

    @Override
    @DataSource(value = DataSourceType.JINSHU)
    public Map getNoDataElementForJinShu(String tagHeadId, String sourceId) {
        return HashMapUtils.toLower(mapper.getNoDataElement(tagHeadId, sourceId));
    }

    @Override
    @DataSource(value = DataSourceType.ZAOZHI)
    public Map getNoDataElementForZaoZhi(String tagHeadId, String sourceId) {
        return HashMapUtils.toLower(mapper.getNoDataElement(tagHeadId, sourceId));
    }
}
