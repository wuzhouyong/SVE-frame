package com.ruoyi.project.jinshu.service;

import com.ruoyi.project.jinshu.domain.JSCPElementEntity;
import com.ruoyi.project.jinshu.domain.JSNameIdEntity;

import java.util.List;

public interface JSCheckPointElementService {

    public List<JSCPElementEntity> selectCPElement(JSCPElementEntity entity);

    public List<JSNameIdEntity> selectElement(JSNameIdEntity entity);

    public int insertCPElement(JSCPElementEntity entity);

    public int updateCPElement(JSCPElementEntity entity);

    public int deleteCPElement(String[] cpElemIds);

    public int repeatCheck(JSCPElementEntity entity);
}
