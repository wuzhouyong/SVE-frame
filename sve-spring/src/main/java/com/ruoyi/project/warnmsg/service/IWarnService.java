package com.ruoyi.project.warnmsg.service;

import com.ruoyi.project.warnmsg.domain.WarnEntity;
import com.ruoyi.project.warnmsg.domain.WarnParam;
import com.ruoyi.project.warnmsg.domain.WarnProcEntity;
import com.ruoyi.project.warnmsg.domain.WarnProcResult;

import java.util.List;
import java.util.Map;

public interface IWarnService {
    List<WarnEntity> getWarnList(WarnParam param);
    List<WarnProcResult> getWarnProcDetail(String warnId);
    List<WarnProcResult> getWarnProcHistory(String warnId);
    List<Map> getAttachList(String refType, String refId);
    int warnProc(WarnProcEntity entity);
    int upgradeLevel(String warnId);
}
