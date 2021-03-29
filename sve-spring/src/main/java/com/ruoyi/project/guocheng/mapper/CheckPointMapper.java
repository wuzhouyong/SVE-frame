package com.ruoyi.project.guocheng.mapper;

import com.ruoyi.project.guocheng.domain.CheckPointEntity;
import com.ruoyi.project.guocheng.domain.DeleteEntity;
import com.ruoyi.project.guocheng.domain.GCNameIdEntity;
import com.ruoyi.project.guocheng.domain.GCSourceParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface CheckPointMapper {

    public List<GCNameIdEntity> selectSourceList(GCSourceParam entity);

    public List<GCNameIdEntity> selectCPList(@Param("sourceId") String sourceId);

    public int insertCheckPoint(CheckPointEntity entity);

    public int modifyPassWord(Map<String,String> param);

    public int updateCheckPoint(CheckPointEntity entity);

    public int deleteCheckPoint(DeleteEntity entity);

    public List<CheckPointEntity> selectCheckPoint(CheckPointEntity entity);

    public GCNameIdEntity selectBizSourceId(@Param("sourceId") String sourceId);

    public int isExitCon(String sourceId);

    public int checkConstruction(@Param("sourceId") String sourceId);
}
