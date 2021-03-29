package com.ruoyi.project.lwjh.service;

import com.ruoyi.common.domain.ResultEntity;
import com.ruoyi.project.lwjh.domain.LWJHCheckPointEntity;
import com.ruoyi.project.lwjh.domain.CheckPointData;

import java.util.List;

public interface LWJHCheckPointService {

    public ResultEntity selectCheckPoint(String sourceId);

    public ResultEntity insertCheckPoint(LWJHCheckPointEntity entity);

    public ResultEntity updateCheckPoint(LWJHCheckPointEntity entity);

    public ResultEntity removeCheckPoint(LWJHCheckPointEntity entity);

    public List<CheckPointData> selectCheckPointData(String sourceId, String accessDate);
}
