package com.ruoyi.project.jiayou.service.impl;

import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.framework.aspectj.lang.annotation.DataSource;
import com.ruoyi.framework.aspectj.lang.enums.DataSourceType;
import com.ruoyi.project.jiayou.domain.JYCPElementEntity;
import com.ruoyi.project.jiayou.domain.JYDeleteEntity;
import com.ruoyi.project.jiayou.domain.JYNameIdEntity;
import com.ruoyi.project.jiayou.mapper.JYCheckPointElementMapper;
import com.ruoyi.project.jiayou.service.JYCheckPointElementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@DataSource(value = DataSourceType.JIAYOU)
@Service
public class JYCheckPointElementServiceImpl implements JYCheckPointElementService {

    @Autowired
    private JYCheckPointElementMapper cpElementMapper;

    @Override
    public List<JYCPElementEntity> selectCPElement(JYCPElementEntity entity) {
        return cpElementMapper.selectCPElement(entity);
    }

    @Override
    public List<JYNameIdEntity> selectElement(JYNameIdEntity entity) {
        return cpElementMapper.selectElement(entity);
    }

    @Override
    public int insertCPElement(JYCPElementEntity entity) {
        return cpElementMapper.insertCPElement(entity);
    }

    @Override
    public int updateCPElement(JYCPElementEntity entity) {
        return cpElementMapper.updateCPElement(entity);
    }

    @Override
    public int deleteCPElement(String[] cpElemIds) {
        return cpElementMapper.deleteCPElement(new JYDeleteEntity(cpElemIds, SecurityUtils.getUsername()));
    }

    @Override
    public int repeatCheck(JYCPElementEntity entity) {
        return cpElementMapper.repeatCheck(entity);
    }
}
