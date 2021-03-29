package com.ruoyi.project.enterprise.service.impl;

import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.framework.aspectj.lang.annotation.DataSource;
import com.ruoyi.framework.aspectj.lang.enums.DataSourceType;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.enterprise.domain.SourceModifyEntity;
import com.ruoyi.project.enterprise.domain.SourceOtherColumn;
import com.ruoyi.project.enterprise.domain.SourceVocsParamEntity;
import com.ruoyi.project.enterprise.mapper.SourceModifyMapper;
import com.ruoyi.project.enterprise.service.SourceModifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@DataSource(value = DataSourceType.MASTER)
public class SourceModifyServiceImpl implements SourceModifyService {

    @Autowired
    private SourceModifyMapper sourceModifyMapper;

    @Override
    public List<SourceModifyEntity> selectSourceModify(SourceModifyEntity entity) {
        return sourceModifyMapper.selectSourceModify(entity);
    }

    @Override
    public AjaxResult insertSourceModify(SourceModifyEntity entity) {
        int i = sourceModifyMapper.checkForPresence(entity.getSourceId());
        int res = 0;
        if (i > 0) {
            SourceModifyEntity sourceModify = sourceModifyMapper.getSourceEntity(entity.getSourceId());
            entity.setModifyId(sourceModify.getModifyId());
            res = sourceModifyMapper.updateSourceModify(entity);
            if (res < 0) return AjaxResult.error("修改企业信息失败");
        } else {
            entity.setModifyId(java.util.UUID.randomUUID().toString());
            entity.setUpdatedBy(SecurityUtils.getUsername());
            res = sourceModifyMapper.insertSourceModify(entity);
            if (res > 0) {
                String originName = sourceModifyMapper.getOriginSourceName(entity.getSourceId());
                if (originName.equals(entity.getFullName())) {
                    entity.setAuditState("1");
                    agree(entity);
                }
            }
        }
        if (res > 0)
            return AjaxResult.success("数据提交成功", entity.getModifyId());
        else
            return AjaxResult.error("数据提交失败");
    }

    @Override
    public int agree(SourceModifyEntity entity) {
        entity.setUpdatedBy(SecurityUtils.getUsername());
        if (entity.getAuditState().equals("1")) {
            sourceModifyMapper.updateModify(entity.getModifyId(), SecurityUtils.getUsername());
            entity.setUpdatedBy(SecurityUtils.getUsername());
            sourceModifyMapper.updateSourceUser(entity);
        }
        return sourceModifyMapper.agree(entity);
    }

    @Override
    public int updateBasSource(SourceOtherColumn entity) {
        return sourceModifyMapper.updateBasSource(entity);
    }

    @Override
    public List<SourceModifyEntity> selectModifyHistory(String sourceId) {
        return sourceModifyMapper.selectModifyHistory(sourceId);
    }

    @Override
    public int updateVocs(SourceVocsParamEntity entity) {
        return sourceModifyMapper.updateVocs(entity);
    }

    @Override
    public SourceModifyEntity getSourceEntity(String sourceId) {
        return sourceModifyMapper.getSourceEntity(sourceId);
    }
}
