package com.ruoyi.project.youyan.service.impl;

import java.util.List;

import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.framework.aspectj.lang.annotation.DataSource;
import com.ruoyi.framework.aspectj.lang.enums.DataSourceType;
import com.ruoyi.project.youyan.domain.YYCPElementEntity;
import com.ruoyi.project.youyan.domain.YYDeleteEntity;
import com.ruoyi.project.youyan.domain.YYNameIdEntity;
import com.ruoyi.project.youyan.mapper.YYCheckPointElementMapper;
import com.ruoyi.project.youyan.service.YYCheckPointElementService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@DataSource(value = DataSourceType.YOUYAN)
@Service
public class YYCheckPointElementServiceImpl implements YYCheckPointElementService {

    @Autowired
    private YYCheckPointElementMapper cpElementMapper;

    @Override
    public List<YYCPElementEntity> selectCPElement(YYCPElementEntity entity) {
        return cpElementMapper.selectCPElement(entity);
    }

    @Override
    public List<YYNameIdEntity> selectElement(YYNameIdEntity entity) {
        return cpElementMapper.selectElement(entity);
    }

    @Override
    public int insertCPElement(YYCPElementEntity entity) {
        return cpElementMapper.insertCPElement(entity);
    }

    @Override
    public int updateCPElement(YYCPElementEntity entity) {
        return cpElementMapper.updateCPElement(entity);
    }

    @Override
    public int deleteCPElement(String[] cpElemIds) {
        return cpElementMapper.deleteCPElement(new YYDeleteEntity(cpElemIds, SecurityUtils.getUsername()));
    }

    @Override
    public int repeatCheck(YYCPElementEntity entity) {
        return cpElementMapper.repeatCheck(entity);
    }
}
