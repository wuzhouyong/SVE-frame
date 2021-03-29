package com.ruoyi.project.guocheng.service.impl;

import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.framework.aspectj.lang.annotation.DataSource;
import com.ruoyi.framework.aspectj.lang.enums.DataSourceType;
import com.ruoyi.project.guocheng.domain.CPElementEntity;
import com.ruoyi.project.guocheng.domain.DeleteEntity;
import com.ruoyi.project.guocheng.domain.GCNameIdEntity;
import com.ruoyi.project.guocheng.mapper.CheckPointElementMapper;
import com.ruoyi.project.guocheng.service.CheckPointElementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@DataSource(value = DataSourceType.GUOCHENG)
@Service
public class CheckPointElementServiceImpl implements CheckPointElementService{

    @Autowired
    private CheckPointElementMapper cpElementMapper;

    @Override
    public List<CPElementEntity> selectCPElement(CPElementEntity entity) {
        return cpElementMapper.selectCPElement(entity);
    }

    @Override
    public List<GCNameIdEntity> selectElement(GCNameIdEntity entity) {
        return cpElementMapper.selectElement(entity);
    }

    @Override
    public int insertCPElement(CPElementEntity entity) {
        return cpElementMapper.insertCPElement(entity);
    }

    @Override
    public int updateCPElement(CPElementEntity entity) {
        return cpElementMapper.updateCPElement(entity);
    }

    @Override
    public int deleteCPElement(String[] cpElemIds) {
        return cpElementMapper.deleteCPElement(new DeleteEntity(cpElemIds, SecurityUtils.getUsername()));
    }

    @Override
    public int repeatCheck(CPElementEntity entity) {
        return cpElementMapper.repeatCheck(entity);
    }
}
