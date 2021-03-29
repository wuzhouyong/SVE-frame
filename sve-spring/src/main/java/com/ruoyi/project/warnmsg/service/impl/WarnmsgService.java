package com.ruoyi.project.warnmsg.service.impl;

import com.ruoyi.common.domain.ResultEntity;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.framework.datasource.DynamicDataSourceContextHolder;
import com.ruoyi.project.warnmsg.domain.WarnProcessEntity;
import com.ruoyi.project.warnmsg.domain.WarnProcessResult;
import com.ruoyi.project.warnmsg.domain.WarnmsgParam;
import com.ruoyi.project.warnmsg.domain.WarnmsgResult;
import com.ruoyi.project.warnmsg.mapper.WarnmsgMapper;
import com.ruoyi.project.warnmsg.service.IWarnmsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("default")
public class WarnmsgService implements IWarnmsgService {
    @Autowired
    private WarnmsgMapper mapper;

    @Override
    public List<WarnmsgResult> getWarnmsgList(String prefix, WarnmsgParam param) {
        DynamicDataSourceContextHolder.setDataSourceType(prefix.toUpperCase());
        List<WarnmsgResult> list = mapper.getWarnmsgList(param);
        DynamicDataSourceContextHolder.clearDataSourceType();
        return list;
    }

    @Override
    public List<WarnmsgResult> getDutyWarnmsgList(String prefix, WarnmsgParam param) {
        DynamicDataSourceContextHolder.setDataSourceType(prefix.toUpperCase());
        List<WarnmsgResult> list = mapper.getDutyWarnmsgList(param);
        DynamicDataSourceContextHolder.clearDataSourceType();
        return list;
    }

    @Override
    public List<WarnProcessResult> getProcessList(String prefix, String warnId) {
        DynamicDataSourceContextHolder.setDataSourceType(prefix.toUpperCase());
        List<WarnProcessResult> list = mapper.getProcessList(warnId);
        DynamicDataSourceContextHolder.clearDataSourceType();
        return list;
    }

    @Override
    public ResultEntity enterpriseProc(String prefix, WarnProcessEntity entity) {
        DynamicDataSourceContextHolder.setDataSourceType(prefix.toUpperCase());
        int level = mapper.getWarnLevelById(entity.getWarnId());
        int procState = level >= 3 ? 6 : 3;
        return warnProc(entity, procState);
    }

    @Override
    public ResultEntity dutyProc(String prefix, WarnProcessEntity entity) {
        DynamicDataSourceContextHolder.setDataSourceType(prefix.toUpperCase());
        int level = mapper.getWarnLevelById(entity.getWarnId());
        int procState = entity.getProcResult() == 1 ? (level == 1 ? 5 : 6) : 1;
        return warnProc(entity, procState);
    }

    @Override
    public ResultEntity subStationProc(String prefix, WarnProcessEntity entity) {
        DynamicDataSourceContextHolder.setDataSourceType(prefix.toUpperCase());
        int procState = entity.getProcResult() == 1 ? 6 : 4;
        return warnProc(entity, procState);
    }

    private ResultEntity warnProc(WarnProcessEntity entity, int procState) {
        int res = mapper.updateProcState(entity.getWarnId(), procState, SecurityUtils.getUsername());
        if (res > 0) {
            res = mapper.addWarnProcess(entity);
            if (res > 0) {
                return ResultEntity.success("异常处理成功");
            }
        }
        DynamicDataSourceContextHolder.clearDataSourceType();
        return ResultEntity.error("异常处理失败");
    }
}
