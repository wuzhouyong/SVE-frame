package com.ruoyi.project.youyan.service;

import com.ruoyi.project.youyan.domain.YYCheckPointEntity;
import com.ruoyi.project.youyan.domain.YYNameIdEntity;
import com.ruoyi.project.youyan.domain.YYSourceParam;

import java.util.List;
import java.util.Map;

public interface YYCheckPointService {

    public List<YYNameIdEntity> selectSourceList(YYSourceParam entity);

    public List<YYNameIdEntity> selectCPList(String sourceId);

    public int insertCheckPoint(YYCheckPointEntity entity);

    public int modifyPassWord(Map<String,String> param);

    public int updateCheckPoint(YYCheckPointEntity entity);

    public int deleteCheckPoint(String[] collectIds);

    public List<YYCheckPointEntity> selectCheckPoint(YYCheckPointEntity entity);

    public YYNameIdEntity selectBizSourceId(String sourceId);

    public int isExitCon(String sourceId);

    public int checkConstruction(String sourceId);
}
