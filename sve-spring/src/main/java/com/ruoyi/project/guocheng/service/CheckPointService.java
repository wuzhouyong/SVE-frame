package com.ruoyi.project.guocheng.service;

import com.ruoyi.project.guocheng.domain.CheckPointEntity;
import com.ruoyi.project.guocheng.domain.GCNameIdEntity;
import com.ruoyi.project.guocheng.domain.GCSourceParam;

import java.util.List;
import java.util.Map;

public interface CheckPointService {

    public List<GCNameIdEntity> selectSourceList(GCSourceParam entity);

    public List<GCNameIdEntity> selectCPList(String sourceId);

    public int insertCheckPoint(CheckPointEntity entity);

    public int modifyPassWord(Map<String,String> param);

    public int updateCheckPoint(CheckPointEntity entity);

    public int deleteCheckPoint(String[] collectIds);

    public List<CheckPointEntity> selectCheckPoint(CheckPointEntity entity);

    public GCNameIdEntity selectBizSourceId(String sourceId);

    public int isExitCon(String sourceId);

    public int checkConstruction(String sourceId);
}
