package com.ruoyi.project.outwater.service.impl;

import com.ruoyi.framework.aspectj.lang.annotation.DataSource;
import com.ruoyi.framework.aspectj.lang.enums.DataSourceType;
import com.ruoyi.project.outwater.domain.BasCheckPointElement;
import com.ruoyi.project.outwater.domain.entity.NameAndIdEntity;
import com.ruoyi.project.outwater.mapper.BasCheckPointElementMapper;
import com.ruoyi.project.outwater.service.BasCheckPointElementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@DataSource(value = DataSourceType.OUTWATER)
@Service
public class BasCheckPointElementServiceImpl implements BasCheckPointElementService{

    @Autowired
    private BasCheckPointElementMapper checkPointElementMapper;

    @Override
    public int insertCheckPointElement(BasCheckPointElement entity) {
        return checkPointElementMapper.insertCheckPointElement(entity);
    }

    @Override
    public int deleteCheckPointElementById(String cpElementId, String updatedBy) {
        return checkPointElementMapper.deleteCheckPointElementById(cpElementId, updatedBy);
    }

    @Override
    public int updateCheckPointElement(BasCheckPointElement entity) {
        return checkPointElementMapper.updateCheckPointElement(entity);
    }

    @Override
    public List<NameAndIdEntity> selectElementIdNameListList(String elemTypeDesc, String elemTypeId) {
        return checkPointElementMapper.selectElementIdNameListList(elemTypeDesc, elemTypeId);
    }

    @Override
    public List<BasCheckPointElement> selectCheckPointElementList(BasCheckPointElement entity) {
        return checkPointElementMapper.selectCheckPointElementList(entity);
    }

    @Override
    public int getTotal(BasCheckPointElement entity) {
        return checkPointElementMapper.getTotal(entity);
    }

    @Override
    public int repeatCheck(BasCheckPointElement entity) {
        return checkPointElementMapper.repeatCheck(entity);
    }
}
