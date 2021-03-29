package com.ruoyi.project.jiayou.mapper;

import com.ruoyi.project.jiayou.domain.JYCheckPointEntity;
import com.ruoyi.project.jiayou.domain.JYDeleteEntity;
import com.ruoyi.project.jiayou.domain.JYNameIdEntity;
import com.ruoyi.project.jiayou.domain.JYSourceParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface JYCheckPointMapper {

    public List<JYNameIdEntity> selectSourceList(JYSourceParam entity);

    public List<JYNameIdEntity> selectCPList(@Param("sourceId") String sourceId);

    public int insertCheckPoint(JYCheckPointEntity entity);

    public int modifyPassWord(Map<String,String> param);

    public int updateCheckPoint(JYCheckPointEntity entity);

    public int deleteCheckPoint(JYDeleteEntity entity);

    public List<JYCheckPointEntity> selectCheckPoint(JYCheckPointEntity entity);

    public JYNameIdEntity selectBizSourceId(@Param("sourceId") String sourceId);

    public int isExitCon(String sourceId);

    public int checkConstruction(@Param("sourceId") String sourceId);
}
