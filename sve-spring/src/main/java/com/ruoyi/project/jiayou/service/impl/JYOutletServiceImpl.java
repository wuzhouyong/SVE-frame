package com.ruoyi.project.jiayou.service.impl;

import com.ruoyi.framework.aspectj.lang.annotation.DataSource;
import com.ruoyi.framework.aspectj.lang.enums.DataSourceType;
import com.ruoyi.project.jiayou.domain.JYDeleteEntity;
import com.ruoyi.project.jiayou.domain.JYOutletEntity;
import com.ruoyi.project.jiayou.mapper.JYOutletMapper;
import com.ruoyi.project.jiayou.service.JYOutletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@DataSource(value = DataSourceType.JIAYOU)
@Service
public class JYOutletServiceImpl implements JYOutletService {

    @Autowired
    private JYOutletMapper outletMapper;

    @Override
    public List<JYOutletEntity> selectOutlet(JYOutletEntity entity) {
        return outletMapper.selectOutlet(entity);
    }

    @Override
    public int insertOutlet(JYOutletEntity entity) {
        return outletMapper.insertOutlet(entity);
    }

    @Override
    public int updateOutlet(JYOutletEntity entity) {
        return outletMapper.updateOutlet(entity);
    }

    @Override
    public int deleteOutlet(JYDeleteEntity entity) {
        return outletMapper.deleteOutlet(entity);
    }

    @Override
    public List<JYOutletEntity> selectOutletList(String sourceId) {
        return outletMapper.selectOutletList(sourceId);
    }
}
