package com.ruoyi.project.youyan.service;

import java.util.List;

import com.ruoyi.project.youyan.domain.YYCPElementEntity;
import com.ruoyi.project.youyan.domain.YYNameIdEntity;

public interface YYCheckPointElementService {

    public List<YYCPElementEntity> selectCPElement(YYCPElementEntity entity);

    public List<YYNameIdEntity> selectElement(YYNameIdEntity entity);

    public int insertCPElement(YYCPElementEntity entity);

    public int updateCPElement(YYCPElementEntity entity);

    public int deleteCPElement(String[] cpElemIds);

    public int repeatCheck(YYCPElementEntity entity);
}
