package com.ruoyi.project.outwater.service;

import com.ruoyi.project.outwater.domain.BasCheckPointElement;
import com.ruoyi.project.outwater.domain.entity.NameAndIdEntity;

import java.util.List;


public interface BasCheckPointElementService {
    public int insertCheckPointElement(BasCheckPointElement entity);

    public int deleteCheckPointElementById(String cpElementId, String updatedBy);

    public int updateCheckPointElement(BasCheckPointElement entity);

    public List<NameAndIdEntity> selectElementIdNameListList(String elemTypeDesc, String elemTypeId);

    public List<BasCheckPointElement> selectCheckPointElementList(BasCheckPointElement entity);

    public int getTotal(BasCheckPointElement entity);

    public int repeatCheck(BasCheckPointElement entity);
}
