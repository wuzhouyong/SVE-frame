package com.ruoyi.project.qixiu.mapper;

import com.ruoyi.project.qixiu.domain.QXCheckPointEntity;
import com.ruoyi.project.qixiu.domain.QXDeleteEntity;
import com.ruoyi.project.qixiu.domain.QXNameIdEntity;
import com.ruoyi.project.qixiu.domain.QXSourceParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface QXCheckPointMapper {

    public List<QXNameIdEntity> selectSourceList(QXSourceParam entity);

    public List<QXNameIdEntity> selectCPList(@Param("sourceId") String sourceId);

    public int insertCheckPoint(QXCheckPointEntity entity);

    public int modifyPassWord(Map<String,String> param);

    public int updateCheckPoint(QXCheckPointEntity entity);

    public int deleteCheckPoint(QXDeleteEntity entity);

    public List<QXCheckPointEntity> selectCheckPoint(QXCheckPointEntity entity);

    public QXNameIdEntity selectBizSourceId(@Param("sourceId") String sourceId);

    public int isExitCon(String sourceId);

    public int checkConstruction(@Param("sourceId") String sourceId);
}
