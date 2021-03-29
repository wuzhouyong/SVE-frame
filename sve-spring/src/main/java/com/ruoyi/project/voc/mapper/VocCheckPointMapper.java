package com.ruoyi.project.voc.mapper;

import com.ruoyi.project.voc.domain.VocCheckPointEntity;
import com.ruoyi.project.voc.domain.VocDeleteEntity;
import com.ruoyi.project.voc.domain.VocNameIdEntity;
import com.ruoyi.project.voc.domain.VocSourceParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface VocCheckPointMapper {

    public List<VocNameIdEntity> selectSourceList(VocSourceParam entity);

    public List<VocNameIdEntity> selectCPList(@Param("sourceId") String sourceId);

    public int insertCheckPoint(VocCheckPointEntity entity);

    public int modifyPassWord(Map<String,String> param);

    public int updateCheckPoint(VocCheckPointEntity entity);

    public int deleteCheckPoint(VocDeleteEntity entity);

    public List<VocCheckPointEntity> selectCheckPoint(VocCheckPointEntity entity);

    public VocNameIdEntity selectBizSourceId(@Param("sourceId") String sourceId);

    public int isExitCon(String sourceId);

    public int checkConstruction(@Param("sourceId") String sourceId);
}
