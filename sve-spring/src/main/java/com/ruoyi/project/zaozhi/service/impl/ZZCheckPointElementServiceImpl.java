package com.ruoyi.project.zaozhi.service.impl;

import java.util.List;

import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.framework.aspectj.lang.annotation.DataSource;
import com.ruoyi.framework.aspectj.lang.enums.DataSourceType;
import com.ruoyi.project.zaozhi.domain.ZZCPElementEntity;
import com.ruoyi.project.zaozhi.domain.ZZDeleteEntity;
import com.ruoyi.project.zaozhi.domain.ZZNameIdEntity;
import com.ruoyi.project.zaozhi.mapper.ZZCheckPointElementMapper;
import com.ruoyi.project.zaozhi.service.ZZCheckPointElementService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@DataSource(value = DataSourceType.ZAOZHI)
@Service
public class ZZCheckPointElementServiceImpl implements ZZCheckPointElementService {
    @Autowired
    private ZZCheckPointElementMapper cpElementMapper;

    @Override
    public List<ZZCPElementEntity> selectCPElement(ZZCPElementEntity entity) {
        return cpElementMapper.selectCPElement(entity);
    }

    @Override
    public List<ZZNameIdEntity> selectElement(ZZNameIdEntity entity) {
        return cpElementMapper.selectElement(entity);
    }

    @Override
    public int insertCPElement(ZZCPElementEntity entity) {
        return cpElementMapper.insertCPElement(entity);
    }

    @Override
    public int updateCPElement(ZZCPElementEntity entity) {
        return cpElementMapper.updateCPElement(entity);
    }

    @Override
    public int deleteCPElement(String[] cpElemIds) {
        return cpElementMapper.deleteCPElement(new ZZDeleteEntity(cpElemIds, SecurityUtils.getUsername()));
    }

    @Override
    public int repeatCheck(ZZCPElementEntity entity) {
        return cpElementMapper.repeatCheck(entity);
    }
}
