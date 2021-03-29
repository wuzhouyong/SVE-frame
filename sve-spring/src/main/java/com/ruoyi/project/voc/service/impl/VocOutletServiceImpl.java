package com.ruoyi.project.voc.service.impl;

import com.ruoyi.framework.aspectj.lang.annotation.DataSource;
import com.ruoyi.framework.aspectj.lang.enums.DataSourceType;
import com.ruoyi.project.voc.domain.VocDeleteEntity;
import com.ruoyi.project.voc.domain.VocOutletEntity;
import com.ruoyi.project.voc.mapper.VocOutletMapper;
import com.ruoyi.project.voc.service.VocOutletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@DataSource(value = DataSourceType.VOC)
@Service
public class VocOutletServiceImpl implements VocOutletService {

    @Autowired
    private VocOutletMapper outletMapper;

    @Override
    public List<VocOutletEntity> selectOutlet(VocOutletEntity entity) {
        return outletMapper.selectOutlet(entity);
    }

    @Override
    public int insertOutlet(VocOutletEntity entity) {
        return outletMapper.insertOutlet(entity);
    }

    @Override
    public int updateOutlet(VocOutletEntity entity) {
        return outletMapper.updateOutlet(entity);
    }

    @Override
    public int deleteOutlet(VocDeleteEntity entity) {
        return outletMapper.deleteOutlet(entity);
    }
}
