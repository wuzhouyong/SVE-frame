package com.ruoyi.project.qixiu.service.impl;

import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.framework.aspectj.lang.annotation.DataSource;
import com.ruoyi.framework.aspectj.lang.enums.DataSourceType;
import com.ruoyi.project.qixiu.domain.QXCPElementEntity;
import com.ruoyi.project.qixiu.domain.QXDeleteEntity;
import com.ruoyi.project.qixiu.domain.QXNameIdEntity;
import com.ruoyi.project.qixiu.mapper.QXCheckPointElementMapper;
import com.ruoyi.project.qixiu.service.QXCheckPointElementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@DataSource(value = DataSourceType.QIXIU)
@Service
public class QXCheckPointElementServiceImpl implements QXCheckPointElementService {

    @Autowired
    private QXCheckPointElementMapper cpElementMapper;

    @Override
    public List<QXCPElementEntity> selectCPElement(QXCPElementEntity entity) {
        return cpElementMapper.selectCPElement(entity);
    }

    @Override
    public List<QXNameIdEntity> selectElement(QXNameIdEntity entity) {
        return cpElementMapper.selectElement(entity);
    }

    @Override
    public int insertCPElement(QXCPElementEntity entity) {
        return cpElementMapper.insertCPElement(entity);
    }

    @Override
    public int updateCPElement(QXCPElementEntity entity) {
        return cpElementMapper.updateCPElement(entity);
    }

    @Override
    public int deleteCPElement(String[] cpElemIds) {
        return cpElementMapper.deleteCPElement(new QXDeleteEntity(cpElemIds, SecurityUtils.getUsername()));
    }

    @Override
    public int repeatCheck(QXCPElementEntity entity) {
        return cpElementMapper.repeatCheck(entity);
    }
}
