package com.ruoyi.project.lwjh.service;

import com.ruoyi.common.domain.ResultEntity;
import com.ruoyi.project.lwjh.domain.LWJHSourceEntity;
import com.ruoyi.project.lwjh.domain.SourceCityData;
import com.ruoyi.project.lwjh.domain.SourceData;
import com.ruoyi.project.lwjh.domain.SourceListEntity;
import com.ruoyi.project.outwater.domain.entity.NameAndIdEntity;

import java.util.List;

public interface LWJHSourceService {

    public ResultEntity insertSource(SourceListEntity entity);

    public List<SourceListEntity> selectSource(SourceListEntity entity);

    public int getTotal(LWJHSourceEntity entity);

    public ResultEntity updateSource(SourceListEntity entity);

    public ResultEntity removeSource(String[] sourceIds);

    public List<SourceData> selectSourceData(LWJHSourceEntity entity);

    public List<SourceCityData> selectSourceCityData(LWJHSourceEntity entity);

    public List<SourceListEntity> selectSourceInfo(SourceListEntity entity);

    public List<NameAndIdEntity> selectSourceAddList(SourceListEntity entity);
}
