package com.ruoyi.project.warnmsg.mapper;

import com.ruoyi.project.warnmsg.domain.WarnProcessEntity;
import com.ruoyi.project.warnmsg.domain.WarnProcessResult;
import com.ruoyi.project.warnmsg.domain.WarnmsgParam;
import com.ruoyi.project.warnmsg.domain.WarnmsgResult;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface WarnmsgMapper {
    List<WarnmsgResult> getWarnmsgList(WarnmsgParam param);
    List<WarnmsgResult> getDutyWarnmsgList(WarnmsgParam param);
    List<WarnProcessResult> getProcessList(@Param("warnId") String warnId);
    int getWarnLevelById(@Param("warnId") String warnId);
    int updateProcState(@Param("warnId") String warnId, @Param("procState") int procState, @Param("updatedBy") String updatedBy);
    int addWarnProcess(WarnProcessEntity entity);
    List<WarnmsgResult> getWarnmsgListForLingXingP(WarnmsgParam param);
    List<WarnmsgResult> getDutyWarnmsgListForLingXingP(WarnmsgParam param);
    List<WarnProcessResult> getProcessListForLingXingP(@Param("warnId") String warnId);
    int getWarnLevelByIdForLingXingP(@Param("warnId") String warnId);
    int updateProcStateForLingXingP(@Param("warnId") String warnId, @Param("procState") int procState, @Param("updatedBy") String updatedBy);
    int addWarnProcessForLingXingP(WarnProcessEntity entity);
}
