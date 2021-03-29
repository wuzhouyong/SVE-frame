package com.ruoyi.project.facility.service;

import com.ruoyi.project.facility.domain.ElementEntity;
import com.ruoyi.project.facility.domain.ElementParam;
import com.ruoyi.project.facility.domain.ElementResult;

import java.util.List;

public interface IElementService {
    List<ElementResult> getElementList(ElementParam param);
    List<ElementResult> getElementListForLingXing(ElementParam param);
    List<ElementResult> getElementListForVOC(ElementParam param);

    int addElement(String dbName, ElementEntity entity);
    int editElement(String dbName, ElementEntity entity);
    int removeElement(String dbName, List<Integer> cpElemIds);
}
