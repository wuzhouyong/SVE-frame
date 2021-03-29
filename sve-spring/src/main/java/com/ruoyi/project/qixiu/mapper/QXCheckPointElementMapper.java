package com.ruoyi.project.qixiu.mapper;

import com.ruoyi.project.qixiu.domain.QXCPElementEntity;
import com.ruoyi.project.qixiu.domain.QXDeleteEntity;
import com.ruoyi.project.qixiu.domain.QXNameIdEntity;

import java.util.List;

public interface QXCheckPointElementMapper {

    public List<QXCPElementEntity> selectCPElement(QXCPElementEntity entity);

    public List<QXNameIdEntity> selectElement(QXNameIdEntity entity);

    public int insertCPElement(QXCPElementEntity entity);

    public int updateCPElement(QXCPElementEntity entity);

    public int deleteCPElement(QXDeleteEntity entity);

    public int repeatCheck(QXCPElementEntity entity);
}
