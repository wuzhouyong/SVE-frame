package com.ruoyi.project.qixiu.service;

import com.ruoyi.project.qixiu.domain.QXCPElementEntity;
import com.ruoyi.project.qixiu.domain.QXNameIdEntity;

import java.util.List;

public interface QXCheckPointElementService {

    public List<QXCPElementEntity> selectCPElement(QXCPElementEntity entity);

    public List<QXNameIdEntity> selectElement(QXNameIdEntity entity);

    public int insertCPElement(QXCPElementEntity entity);

    public int updateCPElement(QXCPElementEntity entity);

    public int deleteCPElement(String[] cpElemIds);

    public int repeatCheck(QXCPElementEntity entity);
}
