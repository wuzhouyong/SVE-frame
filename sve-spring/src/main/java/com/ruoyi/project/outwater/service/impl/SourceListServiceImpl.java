package com.ruoyi.project.outwater.service.impl;

import java.util.List;

import com.ruoyi.framework.aspectj.lang.annotation.DataSource;
import com.ruoyi.framework.aspectj.lang.enums.DataSourceType;
import com.ruoyi.project.outwater.domain.OWNameIdEntity;
import com.ruoyi.project.outwater.domain.OWSourceParam;
import com.ruoyi.project.outwater.mapper.SourceListMapper;
import com.ruoyi.project.outwater.service.SourceListService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@DataSource(value = DataSourceType.OUTWATER)
@Service
public class SourceListServiceImpl implements SourceListService {

    // private static final Logger log = LoggerFactory.getLogger(SourceListServiceImpl.class);

    @Autowired
    private SourceListMapper sourceListMapper;

    @Override
    public List<OWNameIdEntity> selectSourceList(OWSourceParam entity) {
        return sourceListMapper.selectSourceList(entity);
    }


}
