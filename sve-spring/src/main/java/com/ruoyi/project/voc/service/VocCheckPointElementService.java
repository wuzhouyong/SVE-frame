package com.ruoyi.project.voc.service;

import com.ruoyi.project.voc.domain.VocCPElementEntity;
import com.ruoyi.project.voc.domain.VocNameIdEntity;

import java.util.List;

public interface VocCheckPointElementService {

    public List<VocCPElementEntity> selectCPElement(VocCPElementEntity entity);

    public List<VocNameIdEntity> selectElement(VocNameIdEntity entity);

    public int insertCPElement(VocCPElementEntity entity);

    public int updateCPElement(VocCPElementEntity entity);

    public int deleteCPElement(String[] cpElemIds);

    public int repeatCheck(VocCPElementEntity entity);
}
