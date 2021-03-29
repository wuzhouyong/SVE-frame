package com.ruoyi.project.warnmsg.service;

import com.ruoyi.common.domain.ResultEntity;
import com.ruoyi.project.warnmsg.domain.WarnProcessEntity;
import com.ruoyi.project.warnmsg.domain.WarnProcessResult;
import com.ruoyi.project.warnmsg.domain.WarnmsgParam;
import com.ruoyi.project.warnmsg.domain.WarnmsgResult;

import java.util.List;

public interface IWarnmsgService {
    List<WarnmsgResult> getWarnmsgList(String prefix, WarnmsgParam param);
    List<WarnmsgResult> getDutyWarnmsgList(String prefix, WarnmsgParam param);
    List<WarnProcessResult> getProcessList(String prefix, String warnId);
    ResultEntity enterpriseProc(String prefix, WarnProcessEntity entity);
    ResultEntity dutyProc(String prefix, WarnProcessEntity entity);
    ResultEntity subStationProc(String prefix, WarnProcessEntity entity);
}
