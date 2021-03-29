package com.ruoyi.project.youyan.mapper;

import com.ruoyi.project.youyan.domain.YYCheckPointEntity;
import com.ruoyi.project.youyan.domain.YYDeleteEntity;
import com.ruoyi.project.youyan.domain.YYNameIdEntity;
import com.ruoyi.project.youyan.domain.YYSourceParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface YYCheckPointMapper {

    public List<YYNameIdEntity> selectSourceList(YYSourceParam entity);

    public List<YYNameIdEntity> selectCPList(@Param("sourceId") String sourceId);

    public int insertCheckPoint(YYCheckPointEntity entity);

    public int modifyPassWord(Map<String,String> param);

    public int updateCheckPoint(YYCheckPointEntity entity);

    public int deleteCheckPoint(YYDeleteEntity entity);

    public List<YYCheckPointEntity> selectCheckPoint(YYCheckPointEntity entity);

    public YYNameIdEntity selectBizSourceId(@Param("sourceId") String sourceId);

    public int isExitCon(String sourceId);

    public int checkConstruction(@Param("sourceId") String sourceId);
}
