package com.ruoyi.project.facility.service.impl;

import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.framework.datasource.DynamicDataSourceContextHolder;
import com.ruoyi.project.facility.domain.ElementEntity;
import com.ruoyi.project.facility.domain.ElementParam;
import com.ruoyi.project.facility.domain.ElementResult;
import com.ruoyi.project.facility.mapper.ElementMapper;
import com.ruoyi.project.facility.service.IElementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ElementService implements IElementService {
    @Autowired
    private ElementMapper mapper;

    @Override
    public List<ElementResult> getElementList(ElementParam param) {
        DynamicDataSourceContextHolder.setDataSourceType(param.getDbName().toUpperCase());
        List<ElementResult> list = mapper.getElementList(param);
        DynamicDataSourceContextHolder.clearDataSourceType();
        return list;
    }

    @Override
    public List<ElementResult> getElementListForLingXing(ElementParam param) {
        List<ElementResult> list = mapper.getElementListForLingXing(param);
        list.forEach(e -> {
            if (e.getSrcElemId() != null) {
                ElementParam elementParam = new ElementParam();
                elementParam.setDbName("guocheng");
                elementParam.setCpElemId(e.getSrcElemId());
                List<ElementResult> elementList = getElementList(elementParam);
                if (elementList.size() > 0) {
                    ElementResult elementResult = elementList.get(0);
                    e.setSrcCollectName(elementResult.getCollectName());
                    e.setSrcElemName(elementResult.getElementDesc());
                }
            }
        });
        return list;
    }

    @Override
    public List<ElementResult> getElementListForVOC(ElementParam param) {
        return mapper.getElementListForVOC(param);
    }

    @Override
    public int addElement(String dbName, ElementEntity entity) {
        DynamicDataSourceContextHolder.setDataSourceType(dbName.toUpperCase());
        int res = mapper.addElement(entity);
        DynamicDataSourceContextHolder.clearDataSourceType();
        return res;
    }

    @Override
    public int editElement(String dbName, ElementEntity entity) {
        DynamicDataSourceContextHolder.setDataSourceType(dbName.toUpperCase());
        int res = mapper.editElement(entity);
        DynamicDataSourceContextHolder.clearDataSourceType();
        return res;
    }

    @Override
    public int removeElement(String dbName, List<Integer> cpElemIds) {
        DynamicDataSourceContextHolder.setDataSourceType(dbName.toUpperCase());
        int res = mapper.removeElement(cpElemIds, SecurityUtils.getUsername());
        DynamicDataSourceContextHolder.clearDataSourceType();
        return res;
    }
}
