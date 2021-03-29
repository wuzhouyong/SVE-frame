package com.ruoyi.project.outwater.service;

import com.ruoyi.project.outwater.domain.BasCheckPoint;
import com.ruoyi.project.outwater.domain.entity.CollectCheckPointEntity;
import com.ruoyi.project.outwater.domain.entity.NameAndIdEntity;

import java.util.List;


public interface BasCheckPointService {

    public int insertCheckPoint(BasCheckPoint entity);

    public int deleteCheckPointById(String[] checkPointIds);

    public int updateCheckPoint(CollectCheckPointEntity entity);

    public List<NameAndIdEntity> selectCheckPointIdNameList(String checkPointName, String checkPointId, String sourceId);

    public int getTotal(CollectCheckPointEntity entity);

    public List<CollectCheckPointEntity> selectCheckPointList(CollectCheckPointEntity entity);

    public int isExitCon(String sourceId);

    public int checkConstruction(String sourceId);
}
