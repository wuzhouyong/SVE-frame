package com.ruoyi.project.zaozhi.mapper;

import com.ruoyi.project.zaozhi.domain.ZZCheckPointEntity;
import com.ruoyi.project.zaozhi.domain.ZZDeleteEntity;
import com.ruoyi.project.zaozhi.domain.ZZNameIdEntity;
import com.ruoyi.project.zaozhi.domain.ZZSourceParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ZZCheckPointMapper {
    public List<ZZNameIdEntity> selectSourceList(ZZSourceParam entity);

    public List<ZZNameIdEntity> selectCPList(@Param("sourceId") String sourceId);

    public int insertCheckPoint(ZZCheckPointEntity entity);

    public int modifyPassWord(Map<String,String> param);

    public int updateCheckPoint(ZZCheckPointEntity entity);

    public int deleteCheckPoint(ZZDeleteEntity entity);

    public List<ZZCheckPointEntity> selectCheckPoint(ZZCheckPointEntity entity);

    public ZZNameIdEntity selectBizSourceId(@Param("sourceId") String sourceId);

    public int isExitCon(String sourceId);

    public int checkConstruction(@Param("sourceId") String sourceId);
}
