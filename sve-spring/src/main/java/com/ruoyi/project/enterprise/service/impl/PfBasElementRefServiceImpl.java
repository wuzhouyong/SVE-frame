package com.ruoyi.project.enterprise.service.impl;

import com.ruoyi.framework.aspectj.lang.annotation.DataSource;
import com.ruoyi.framework.aspectj.lang.enums.DataSourceType;
import com.ruoyi.project.enterprise.domain.PfBasElementRef;
import com.ruoyi.project.enterprise.mapper.PfBasElementRefMapper;
import com.ruoyi.project.enterprise.service.PfBasElementRefService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@DataSource(value = DataSourceType.MASTER)
public class PfBasElementRefServiceImpl implements PfBasElementRefService {

    @Autowired
    private PfBasElementRefMapper pfBasElementRefMapper;

    @Override
    public int insertPfBasElementRef(PfBasElementRef entity) {
        return pfBasElementRefMapper.insertPfBasElementRef(entity);
    }

    @Override
    public int updatePfBasElementRef(PfBasElementRef entity) {
        return pfBasElementRefMapper.updatePfBasElementRef(entity);
    }

    @Override
    public int deletePfBasElementRef(int tagHeadId, int cpElemId) {
        return pfBasElementRefMapper.deletePfBasElementRef(tagHeadId + "", cpElemId + "");
    }

    @Override
    public List<PfBasElementRef> selectPfBasElementRef(PfBasElementRef entity) {
        return pfBasElementRefMapper.selectPfBasElementRef(entity);
    }
}
