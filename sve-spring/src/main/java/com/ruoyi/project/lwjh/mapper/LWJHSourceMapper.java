package com.ruoyi.project.lwjh.mapper;

import com.ruoyi.project.lwjh.domain.LWJHSourceEntity;
import com.ruoyi.project.lwjh.domain.SourceCityData;
import com.ruoyi.project.lwjh.domain.SourceData;
import com.ruoyi.project.lwjh.domain.SourceListEntity;
import com.ruoyi.project.outwater.domain.entity.NameAndIdEntity;
import com.ruoyi.project.outwater.domain.entity.SqlInEntity;

import java.util.List;

public interface LWJHSourceMapper {

    public int insertSource(SourceListEntity entity);

    public int selectSourceCount(LWJHSourceEntity entity);

    public List<SourceListEntity> selectSource(SourceListEntity entity);

    public int getTotal(LWJHSourceEntity entity);

    public int updateSource(SourceListEntity entity);

    public int removeSource(SqlInEntity entity);

    public List<SourceData> selectSourceData(LWJHSourceEntity entity);

    public List<SourceCityData> selectSourceCityData(LWJHSourceEntity entity);

    public List<SourceListEntity> selectSourceInfo(SourceListEntity entity);

    public List<NameAndIdEntity> selectSourceAddList(SourceListEntity entity);
}
