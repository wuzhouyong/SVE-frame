package com.ruoyi.project.guocheng.service;

import com.ruoyi.project.guocheng.domain.CPElementEntity;
import com.ruoyi.project.guocheng.domain.GCNameIdEntity;

import java.util.List;

public interface CheckPointElementService {

    public List<CPElementEntity> selectCPElement(CPElementEntity entity);

    public List<GCNameIdEntity> selectElement(GCNameIdEntity entity);

    public int insertCPElement(CPElementEntity entity);

    public int updateCPElement(CPElementEntity entity);

    public int deleteCPElement(String[] cpElemIds);

    public int repeatCheck(CPElementEntity entity);
}
