package com.ruoyi.project.warnmsg.service.impl;

import com.ruoyi.common.domain.ResultEntity;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.framework.aspectj.lang.annotation.DataSource;
import com.ruoyi.framework.aspectj.lang.enums.DataSourceType;
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

@Service("lingxingp")
@DataSource(value = DataSourceType.LINGXING)
public class LXPWarnmsgService implements IWarnmsgService {
    @Autowired
    private WarnmsgMapper mapper;

    @Override
    public List<WarnmsgResult> getWarnmsgList(String prefix, WarnmsgParam param) {
        return mapper.getWarnmsgListForLingXingP(param);
    }

    @Override
    public List<WarnmsgResult> getDutyWarnmsgList(String prefix, WarnmsgParam param) {
        return mapper.getDutyWarnmsgListForLingXingP(param);
    }

    @Override
    public List<WarnProcessResult> getProcessList(String prefix, String warnId) {
        return mapper.getProcessListForLingXingP(warnId);
    }

    @Override
    public ResultEntity enterpriseProc(String prefix, WarnProcessEntity entity) {
        int level = mapper.getWarnLevelByIdForLingXingP(entity.getWarnId());
        int procState = level >= 3 ? 6 : 3;
        return warnProc(entity, procState);
    }

    @Override
    public ResultEntity dutyProc(String prefix, WarnProcessEntity entity) {
        int level = mapper.getWarnLevelByIdForLingXingP(entity.getWarnId());
        int procState = entity.getProcResult() == 1 ? (level == 1 ? 5 : 6) : 1;
        return warnProc(entity, procState);
    }

    @Override
    public ResultEntity subStationProc(String prefix, WarnProcessEntity entity) {
        int procState = entity.getProcResult() == 1 ? 6 : 4;
        return warnProc(entity, procState);
    }

    private ResultEntity warnProc(WarnProcessEntity entity, int procState) {
        int res = mapper.updateProcStateForLingXingP(entity.getWarnId(), procState, SecurityUtils.getUsername());
        if (res > 0) {
            res = mapper.addWarnProcessForLingXingP(entity);
            if (res > 0) {
                return ResultEntity.success("异常处理成功");
            }
        }
        return ResultEntity.error("异常处理失败");
    }
}
