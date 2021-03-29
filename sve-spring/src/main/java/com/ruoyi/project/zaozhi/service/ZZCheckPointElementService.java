package com.ruoyi.project.zaozhi.service;

import java.util.List;

import com.ruoyi.project.zaozhi.domain.ZZCPElementEntity;
import com.ruoyi.project.zaozhi.domain.ZZNameIdEntity;

public interface ZZCheckPointElementService {
    public List<ZZCPElementEntity> selectCPElement(ZZCPElementEntity entity);

    public List<ZZNameIdEntity> selectElement(ZZNameIdEntity entity);

    public int insertCPElement(ZZCPElementEntity entity);

    public int updateCPElement(ZZCPElementEntity entity);

    public int deleteCPElement(String[] cpElemIds);

    public int repeatCheck(ZZCPElementEntity entity);
}
