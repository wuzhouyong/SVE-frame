package com.ruoyi.project.enterprise.service;

import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.enterprise.domain.SourceModifyEntity;
import com.ruoyi.project.enterprise.domain.SourceOtherColumn;
import com.ruoyi.project.enterprise.domain.SourceVocsParamEntity;

import java.util.List;

public interface SourceModifyService {

    public List<SourceModifyEntity> selectSourceModify(SourceModifyEntity entity);

    public AjaxResult insertSourceModify(SourceModifyEntity entity);

    public int agree(SourceModifyEntity entity);

    public int updateBasSource(SourceOtherColumn entity);

    public List<SourceModifyEntity> selectModifyHistory(String sourceId);

    public int updateVocs(SourceVocsParamEntity entity);

    public SourceModifyEntity getSourceEntity(String sourceId);
}
