package com.ruoyi.project.qixiu.service;

import com.ruoyi.project.qixiu.domain.QXCheckPointEntity;
import com.ruoyi.project.qixiu.domain.QXNameIdEntity;
import com.ruoyi.project.qixiu.domain.QXSourceParam;

import java.util.List;
import java.util.Map;

public interface QXCheckPointService {

    public List<QXNameIdEntity> selectSourceList(QXSourceParam entity);

    public List<QXNameIdEntity> selectCPList(String sourceId);

    public int insertCheckPoint(QXCheckPointEntity entity);

    public int modifyPassWord(Map<String,String> param);

    public int updateCheckPoint(QXCheckPointEntity entity);

    public int deleteCheckPoint(String[] collectIds);

    public List<QXCheckPointEntity> selectCheckPoint(QXCheckPointEntity entity);

    public QXNameIdEntity selectBizSourceId(String sourceId);
    public int isExitCon(String sourceId);

    public int checkConstruction(String sourceId);
}
