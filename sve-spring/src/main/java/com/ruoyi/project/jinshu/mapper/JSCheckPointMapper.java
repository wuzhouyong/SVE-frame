package com.ruoyi.project.jinshu.mapper;

import com.ruoyi.project.jinshu.domain.JSCheckPointEntity;
import com.ruoyi.project.jinshu.domain.JSDeleteEntity;
import com.ruoyi.project.jinshu.domain.JSNameIdEntity;
import com.ruoyi.project.jinshu.domain.JSSourceParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface JSCheckPointMapper {

    public List<JSNameIdEntity> selectSourceList(JSSourceParam entity);

    public List<JSNameIdEntity> selectCPList(@Param("sourceId") String sourceId);

    public int insertCheckPoint(JSCheckPointEntity entity);

    public int modifyPassWord(Map<String,String> param);

    public int updateCheckPoint(JSCheckPointEntity entity);

    public int deleteCheckPoint(JSDeleteEntity entity);

    public List<JSCheckPointEntity> selectCheckPoint(JSCheckPointEntity entity);

    public JSNameIdEntity selectBizSourceId(@Param("sourceId") String sourceId);

    public int isExitCon(String sourceId);

    public int checkConstruction(@Param("sourceId") String sourceId);
}
