package com.ruoyi.project.warnmsg.service.impl;

import com.ruoyi.common.utils.HashMapUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.framework.aspectj.lang.annotation.DataSource;
import com.ruoyi.framework.aspectj.lang.enums.DataSourceType;
import com.ruoyi.project.warnmsg.domain.WarnEntity;
import com.ruoyi.project.warnmsg.domain.WarnParam;
import com.ruoyi.project.warnmsg.domain.WarnProcEntity;
import com.ruoyi.project.warnmsg.domain.WarnProcResult;
import com.ruoyi.project.warnmsg.mapper.WarnMapper;
import com.ruoyi.project.warnmsg.service.IWarnService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
@DataSource(value = DataSourceType.MASTER)
public class WarnService implements IWarnService {
    @Resource
    private WarnMapper mapper;

    @Override
    public List<WarnEntity> getWarnList(WarnParam param) {
        return mapper.getWarnList(param);
    }

    @Override
    public List<WarnProcResult> getWarnProcDetail(String warnId) {
        List<WarnProcResult> list = mapper.getWarnProcDetail(warnId);
        return list;
    }

    @Override
    public List<WarnProcResult> getWarnProcHistory(String warnId) {
        List<WarnProcResult> list = mapper.getWarnProcHistory(warnId);
        return list;
    }

    @Override
    public List<Map> getAttachList(String refType, String refId) {
        return HashMapUtils.toCamel(mapper.getAttachList(refType, refId));
    }

    @Override
    public int warnProc(WarnProcEntity entity) {
        if (mapper.verifySameWarnType(entity.getWarnIds()) == 1) {
            int res = 0;
            for (String warnId : entity.getWarnIds()) {
                try {
                    WarnProcResult result = (WarnProcResult) entity.clone();
                    result.setWarnId(warnId);
                    result.setProcUser(SecurityUtils.getDisplayName());
                    res += mapper.warnProc(result);
                } catch (CloneNotSupportedException ignored) {

                }
            }
            if (res > 0) {
                String type = entity.getUserType();
                int level = entity.getWarnLevel();
                if (entity.getProcStatus() == 0 &&
                        (
                                (type.equals("enterprise") && level == 4) ||
                                        (type.equals("duty") && level >= 3) ||
                                        (type.equals("sub") && level >= 2) ||
                                        (type.equals("city"))
                        )
                ) res = mapper.updateWarnStatus(entity.getWarnIds(), SecurityUtils.getUsername());
            }
            return res;
        }
        return -1;
    }

    @Override
    public int upgradeLevel(String warnId) {
        int level = mapper.getWarnLevelById(warnId);
        if (level > 1) {
            return mapper.upgradeLevel(warnId, SecurityUtils.getUsername());
        }
        return 0;
    }
}
