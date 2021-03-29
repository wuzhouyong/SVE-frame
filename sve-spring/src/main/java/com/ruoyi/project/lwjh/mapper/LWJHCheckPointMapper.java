package com.ruoyi.project.lwjh.mapper;

import com.ruoyi.project.lwjh.domain.LWJHCheckPointEntity;
import com.ruoyi.project.lwjh.domain.CheckPointData;
import com.ruoyi.project.outwater.domain.entity.SqlInEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LWJHCheckPointMapper {

    public int insertCheckPoint(LWJHCheckPointEntity entity);

    public List<LWJHCheckPointEntity> selectCheckPoint(String[] sourceIds);

    public List<CheckPointData> selectCheckPointData(@Param("sourceId") String sourceId, @Param("accessDate") String accessDate);

    public List<LWJHCheckPointEntity> selectCheckPointType(@Param("checkpointId") String checkpointId);

    public int removeCheckPointBySourceId(SqlInEntity entity);

    public int removeCheckPoint(LWJHCheckPointEntity entity);

    public int updateCheckPoint(LWJHCheckPointEntity entity);
}
