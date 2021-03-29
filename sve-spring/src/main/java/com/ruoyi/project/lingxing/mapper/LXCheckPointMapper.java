package com.ruoyi.project.lingxing.mapper;

import com.ruoyi.framework.aspectj.lang.annotation.DataSource;
import com.ruoyi.framework.aspectj.lang.enums.DataSourceType;
import com.ruoyi.project.lingxing.domain.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface LXCheckPointMapper {

    public List<LXNameIdEntity> selectSourceList(LXSourceParam entity);

    public List<LXNameIdEntity> selectCPList(@Param("sourceId") String sourceId);

    public int insertCheckPoint(LXCheckPointEntity entity);

    public int modifyPassWord(Map<String,String> param);

    public int updateCheckPoint(LXCheckPointEntity entity);

    public int deleteCheckPoint(LXDeleteEntity entity);

    public List<LXCheckPointEntity> selectCheckPoint(LXCheckPointEntity entity);

    public LXNameIdEntity selectBizSourceId(@Param("sourceId") String sourceId);

    public int isExitCon(String sourceId);

    public int checkConstruction(@Param("sourceId") String sourceId);

    List<LXTransbillinfo> selectTransbillinfoList(LXTransbillinfo info);
}
