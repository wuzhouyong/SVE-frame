package com.ruoyi.project.jinshu.service;

import com.ruoyi.project.jinshu.domain.JSCheckPointEntity;
import com.ruoyi.project.jinshu.domain.JSNameIdEntity;
import com.ruoyi.project.jinshu.domain.JSSourceParam;

import java.util.List;
import java.util.Map;

public interface JSCheckPointService {

    public List<JSNameIdEntity> selectSourceList(JSSourceParam entity);

    public List<JSNameIdEntity> selectCPList(String sourceId);

    public int insertCheckPoint(JSCheckPointEntity entity);

    public int modifyPassWord(Map<String,String> param);

    public int updateCheckPoint(JSCheckPointEntity entity);

    public int deleteCheckPoint(String[] collectIds);

    public List<JSCheckPointEntity> selectCheckPoint(JSCheckPointEntity entity);

    public JSNameIdEntity selectBizSourceId(String sourceId);

    public int isExitCon(String sourceId);

    public int checkConstruction(String sourceId);
}
