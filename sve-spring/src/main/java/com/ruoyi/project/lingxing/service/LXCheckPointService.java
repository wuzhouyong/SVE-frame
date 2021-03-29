package com.ruoyi.project.lingxing.service;

import com.ruoyi.project.lingxing.domain.LXCheckPointEntity;
import com.ruoyi.project.lingxing.domain.LXNameIdEntity;
import com.ruoyi.project.lingxing.domain.LXSourceParam;
import com.ruoyi.project.lingxing.domain.LXTransbillinfo;

import java.util.List;
import java.util.Map;

public interface LXCheckPointService {

    public List<LXNameIdEntity> selectSourceList(LXSourceParam entity);

    public List<LXTransbillinfo> selectTransbillinfoList(LXTransbillinfo info);

    public List<LXNameIdEntity> selectCPList(String sourceId);

    public int insertCheckPoint(LXCheckPointEntity entity);

    public int modifyPassWord(Map<String,String> param);

    public int updateCheckPoint(LXCheckPointEntity entity);

    public int deleteCheckPoint(String[] collectIds);

    public List<LXCheckPointEntity> selectCheckPoint(LXCheckPointEntity entity);

    public LXNameIdEntity selectBizSourceId(String sourceId);

    public int isExitCon(String sourceId);

    public int checkConstruction(String sourceId);
}
