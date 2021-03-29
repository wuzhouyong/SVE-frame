package com.ruoyi.project.voc.service;

import com.ruoyi.project.voc.domain.VocCheckPointEntity;
import com.ruoyi.project.voc.domain.VocNameIdEntity;
import com.ruoyi.project.voc.domain.VocSourceParam;

import java.util.List;
import java.util.Map;

public interface VocCheckPointService {

    public List<VocNameIdEntity> selectSourceList(VocSourceParam entity);

    public List<VocNameIdEntity> selectCPList(String sourceId);

    public int insertCheckPoint(VocCheckPointEntity entity);

    public int modifyPassWord(Map<String,String> param);

    public int updateCheckPoint(VocCheckPointEntity entity);

    public int deleteCheckPoint(String[] collectIds);

    public List<VocCheckPointEntity> selectCheckPoint(VocCheckPointEntity entity);

    public VocNameIdEntity selectBizSourceId(String sourceId);

    public int isExitCon(String sourceId);

    public int checkConstruction(String sourceId);
}
