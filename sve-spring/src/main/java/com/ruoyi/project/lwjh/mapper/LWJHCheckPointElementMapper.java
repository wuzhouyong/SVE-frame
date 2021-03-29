package com.ruoyi.project.lwjh.mapper;

import com.ruoyi.project.lwjh.domain.LWJHCheckPointElementEntity;
import com.ruoyi.project.lwjh.domain.ElementData;
import com.ruoyi.project.outwater.domain.entity.SqlInEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LWJHCheckPointElementMapper {

    public int insertCheckPointElement(LWJHCheckPointElementEntity entity);

    public List<LWJHCheckPointElementEntity> selectCheckPointElement(@Param("checkpointId") String checkpointId);

    public List<ElementData> selectElementData(@Param("checkpointId") String checkpointId,@Param("accessDate") String accessDate);

    public int removeCheckPointElement(SqlInEntity entity);

    public int removeCPE (LWJHCheckPointElementEntity entity);

    public int updateCheckPointElement (LWJHCheckPointElementEntity entity);
}
