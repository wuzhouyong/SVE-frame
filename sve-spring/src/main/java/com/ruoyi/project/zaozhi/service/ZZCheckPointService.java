package com.ruoyi.project.zaozhi.service;

import com.ruoyi.project.zaozhi.domain.ZZCheckPointEntity;
import com.ruoyi.project.zaozhi.domain.ZZNameIdEntity;
import com.ruoyi.project.zaozhi.domain.ZZSourceParam;

import java.util.List;
import java.util.Map;

public interface ZZCheckPointService {
    public List<ZZNameIdEntity> selectSourceList(ZZSourceParam entity);

    public List<ZZNameIdEntity> selectCPList(String sourceId);

    public int insertCheckPoint(ZZCheckPointEntity entity);

    public int modifyPassWord(Map<String,String> param);

    public int updateCheckPoint(ZZCheckPointEntity entity);

    public int deleteCheckPoint(String[] collectIds);

    public List<ZZCheckPointEntity> selectCheckPoint(ZZCheckPointEntity entity);

    public ZZNameIdEntity selectBizSourceId(String sourceId);

    public int isExitCon(String sourceId);

    public int checkConstruction(String sourceId);
}
