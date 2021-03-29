package com.ruoyi.project.outwater.mapper;

import com.ruoyi.project.outwater.domain.BasCheckPoint;
import com.ruoyi.project.outwater.domain.entity.CollectCheckPointEntity;
import com.ruoyi.project.outwater.domain.entity.NameAndIdEntity;
import com.ruoyi.project.outwater.domain.entity.SqlInEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface BasCheckPointMapper {

    public int insertCheckPoint(BasCheckPoint entity);

    public int deleteCheckPointById(SqlInEntity entity);

    public int updateCheckPoint(CollectCheckPointEntity entity);

    public List<NameAndIdEntity> selectCheckPointIdNameList(@Param("checkPointName")String checkPointName,
                                                            @Param("checkPointId")String checkPointId,
                                                            @Param("sourceId")String sourceId);

    public int getTotal(CollectCheckPointEntity entity);

    public List<BasCheckPoint> getCollectIdList(SqlInEntity entity);

    public List<CollectCheckPointEntity> selectCheckPointList(CollectCheckPointEntity entity);

    public int isExitCon(String sourceId);

    public int checkConstruction(@Param("sourceId") String sourceId);
}
