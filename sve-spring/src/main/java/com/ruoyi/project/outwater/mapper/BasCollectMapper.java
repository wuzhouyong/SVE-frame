package com.ruoyi.project.outwater.mapper;

import com.ruoyi.project.outwater.domain.BasCheckPoint;
import com.ruoyi.project.outwater.domain.entity.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface BasCollectMapper {

    public BasCollectEntity getMN();

    public List<BasCollectEntity> checkMN(String mn);

    public int insertBasCollect(BasCollectEntity entity);

    public int updateBasCollect(BasCollectEntity entity);

    public String getSourceId(@Param("collectId")String collectId);

    public List<NameAndIdEntity> selectBasCollectIdNameList(@Param("collectName")String collectName, @Param("collectId")String collectId);

    public int deleteBasCollectById (@Param("collectId")String collectId, @Param("updatedBy")String updatedBy);

    public int getTotal(BasCollectEntity entity);

    public int insertCollect(CollectCheckPointEntity entity);

    public int modifyPassWord(Map<String,String> param);

    public int updateCollect(CollectCheckPointEntity entity);

    public int insertBasCheckPoint(CollectCheckPointEntity entity);

    public int updateBasCheckPoint(CollectCheckPointEntity entity);

    public List<CheckPointElementEntity> selectCheckPointElement(@Param("checkPointId")String checkPointId);

    public List<CheckPointElementEntity> selectCpElementId(SqlInEntity entity);

    public int insertCheckPointElementEa70(CollectCheckPointEntity entity);

    public int updateCheckPointElement(CheckPointElementEntity entity);

    public int insertCheckPointElementEa71(CollectCheckPointEntity entity);

    public int insertCheckPointElementEa73(CollectCheckPointEntity entity);

    public int insertCheckPointElementEa74(CollectCheckPointEntity entity);

    public int insertCheckPointElementPs00(CollectCheckPointEntity entity);

    public List<BasCheckPoint> selectCheckPointType(@Param("checkPointId")String checkPointId);

    public List<BasCollectEntity> selectBasCollectList(BasCollectEntity entity);
}
