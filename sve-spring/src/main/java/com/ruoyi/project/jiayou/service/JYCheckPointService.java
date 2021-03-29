package com.ruoyi.project.jiayou.service;

import com.ruoyi.project.jiayou.domain.JYCheckPointEntity;
import com.ruoyi.project.jiayou.domain.JYNameIdEntity;
import com.ruoyi.project.jiayou.domain.JYSourceParam;

import java.util.List;
import java.util.Map;

public interface JYCheckPointService {

    public List<JYNameIdEntity> selectSourceList(JYSourceParam entity);

    public List<JYNameIdEntity> selectCPList(String sourceId);

    public int insertCheckPoint(JYCheckPointEntity entity);

    public int modifyPassWord(Map<String,String> param);

    public int updateCheckPoint(JYCheckPointEntity entity);

    public int deleteCheckPoint(String[] collectIds);

    public List<JYCheckPointEntity> selectCheckPoint(JYCheckPointEntity entity);

    public JYNameIdEntity selectBizSourceId(String sourceId);

    public int isExitCon(String sourceId);

    public int checkConstruction(String sourceId);
}
