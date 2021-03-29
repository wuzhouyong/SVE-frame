package com.ruoyi.project.lingxing.service.impl;

import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.framework.aspectj.lang.annotation.DataSource;
import com.ruoyi.framework.aspectj.lang.enums.DataSourceType;
import com.ruoyi.project.lingxing.domain.LXCPElementEntity;
import com.ruoyi.project.lingxing.domain.LXDeleteEntity;
import com.ruoyi.project.lingxing.domain.LXNameIdEntity;
import com.ruoyi.project.lingxing.mapper.LXCheckPointElementMapper;
import com.ruoyi.project.lingxing.service.LXCheckPointElementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@DataSource(value = DataSourceType.LINGXING)
@Service
public class LXCheckPointElementServiceImpl implements LXCheckPointElementService {

    @Autowired
    private LXCheckPointElementMapper cpElementMapper;

    @Override
    public List<LXCPElementEntity> selectCPElement(LXCPElementEntity entity) {
        return cpElementMapper.selectCPElement(entity);
    }

    @Override
    public List<LXNameIdEntity> selectElement(LXNameIdEntity entity) {
        return cpElementMapper.selectElement(entity);
    }

    @Override
    public int insertCPElement(LXCPElementEntity entity) {
        return cpElementMapper.insertCPElement(entity);
    }

    @Override
    public int updateCPElement(LXCPElementEntity entity) {
        return cpElementMapper.updateCPElement(entity);
    }

    @Override
    public int deleteCPElement(String[] cpElemIds) {
        return cpElementMapper.deleteCPElement(new LXDeleteEntity(cpElemIds, SecurityUtils.getUsername()));
    }

    @Override
    public int repeatCheck(LXCPElementEntity entity) {
        return cpElementMapper.repeatCheck(entity);
    }
}
