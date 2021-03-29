package com.ruoyi.project.jiayou.service;

import com.ruoyi.project.jiayou.domain.JYCPElementEntity;
import com.ruoyi.project.jiayou.domain.JYNameIdEntity;

import java.util.List;

public interface JYCheckPointElementService {

    public List<JYCPElementEntity> selectCPElement(JYCPElementEntity entity);

    public List<JYNameIdEntity> selectElement(JYNameIdEntity entity);

    public int insertCPElement(JYCPElementEntity entity);

    public int updateCPElement(JYCPElementEntity entity);

    public int deleteCPElement(String[] cpElemIds);

    public int repeatCheck(JYCPElementEntity entity);
}
