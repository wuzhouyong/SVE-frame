package com.ruoyi.project.voc.service.impl;

import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.framework.aspectj.lang.annotation.DataSource;
import com.ruoyi.framework.aspectj.lang.enums.DataSourceType;
import com.ruoyi.project.voc.domain.VocCPElementEntity;
import com.ruoyi.project.voc.domain.VocDeleteEntity;
import com.ruoyi.project.voc.domain.VocNameIdEntity;
import com.ruoyi.project.voc.mapper.VocCheckPointElementMapper;
import com.ruoyi.project.voc.service.VocCheckPointElementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@DataSource(value = DataSourceType.VOC)
@Service
public class VocCheckPointElementServiceImpl implements VocCheckPointElementService {

    @Autowired
    private VocCheckPointElementMapper cpElementMapper;

    @Override
    public List<VocCPElementEntity> selectCPElement(VocCPElementEntity entity) {
        return cpElementMapper.selectCPElement(entity);
    }

    @Override
    public List<VocNameIdEntity> selectElement(VocNameIdEntity entity) {
        return cpElementMapper.selectElement(entity);
    }

    @Override
    public int insertCPElement(VocCPElementEntity entity) {
        return cpElementMapper.insertCPElement(entity);
    }

    @Override
    public int updateCPElement(VocCPElementEntity entity) {
        return cpElementMapper.updateCPElement(entity);
    }

    @Override
    public int deleteCPElement(String[] cpElemIds) {
        return cpElementMapper.deleteCPElement(new VocDeleteEntity(cpElemIds, SecurityUtils.getUsername()));
    }

    @Override
    public int repeatCheck(VocCPElementEntity entity) {
        return cpElementMapper.repeatCheck(entity);
    }
}
