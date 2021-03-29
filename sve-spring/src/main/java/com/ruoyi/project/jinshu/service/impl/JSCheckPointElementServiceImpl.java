package com.ruoyi.project.jinshu.service.impl;

import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.framework.aspectj.lang.annotation.DataSource;
import com.ruoyi.framework.aspectj.lang.enums.DataSourceType;
import com.ruoyi.project.jinshu.domain.JSCPElementEntity;
import com.ruoyi.project.jinshu.domain.JSDeleteEntity;
import com.ruoyi.project.jinshu.domain.JSNameIdEntity;
import com.ruoyi.project.jinshu.mapper.JSCheckPointElementMapper;
import com.ruoyi.project.jinshu.service.JSCheckPointElementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@DataSource(value = DataSourceType.JINSHU)
@Service
public class JSCheckPointElementServiceImpl implements JSCheckPointElementService {

    @Autowired
    private JSCheckPointElementMapper cpElementMapper;

    @Override
    public List<JSCPElementEntity> selectCPElement(JSCPElementEntity entity) {
        return cpElementMapper.selectCPElement(entity);
    }

    @Override
    public List<JSNameIdEntity> selectElement(JSNameIdEntity entity) {
        return cpElementMapper.selectElement(entity);
    }

    @Override
    public int insertCPElement(JSCPElementEntity entity) {
        return cpElementMapper.insertCPElement(entity);
    }

    @Override
    public int updateCPElement(JSCPElementEntity entity) {
        return cpElementMapper.updateCPElement(entity);
    }

    @Override
    public int deleteCPElement(String[] cpElemIds) {
        return cpElementMapper.deleteCPElement(new JSDeleteEntity(cpElemIds, SecurityUtils.getUsername()));
    }

    @Override
    public int repeatCheck(JSCPElementEntity entity) {
        return cpElementMapper.repeatCheck(entity);
    }
}
