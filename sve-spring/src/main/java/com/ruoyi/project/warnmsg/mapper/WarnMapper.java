package com.ruoyi.project.warnmsg.mapper;

import com.ruoyi.framework.aspectj.lang.annotation.DataSource;
import com.ruoyi.framework.aspectj.lang.enums.DataSourceType;
import com.ruoyi.project.warnmsg.domain.WarnEntity;
import com.ruoyi.project.warnmsg.domain.WarnParam;
import com.ruoyi.project.warnmsg.domain.WarnProcEntity;
import com.ruoyi.project.warnmsg.domain.WarnProcResult;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface WarnMapper {
    @DataSource(value = DataSourceType.MASTER)
    List<WarnEntity> getWarnList(WarnParam param);
    int getWarnLevelById(@Param("warnId") String warnId);
    List<WarnProcResult> getWarnProcDetail(@Param("warnId") String warnId);
    List<WarnProcResult> getWarnProcHistory(@Param("warnId") String warnId);
    @DataSource(value = DataSourceType.MASTER)
    List<Map> getAttachList(@Param("refType") String refType, @Param("refId") String refId);
    int verifySameWarnType(@Param("warnIds") String[] warnIds);
    int warnProc(WarnProcResult entity);
    int updateWarnStatus(@Param("warnIds") String[] warnIds, @Param("updatedBy") String updatedBy);
}
