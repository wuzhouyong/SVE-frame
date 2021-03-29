package com.ruoyi.project.lingxing.service;

import com.ruoyi.project.lingxing.domain.LXCPElementEntity;
import com.ruoyi.project.lingxing.domain.LXNameIdEntity;

import java.util.List;

public interface LXCheckPointElementService {

    public List<LXCPElementEntity> selectCPElement(LXCPElementEntity entity);

    public List<LXNameIdEntity> selectElement(LXNameIdEntity entity);

    public int insertCPElement(LXCPElementEntity entity);

    public int updateCPElement(LXCPElementEntity entity);

    public int deleteCPElement(String[] cpElemIds);

    public int repeatCheck(LXCPElementEntity entity);
}
