package com.ruoyi.project.lwjh.service.impl;

import com.ruoyi.framework.aspectj.lang.annotation.DataSource;
import com.ruoyi.framework.aspectj.lang.enums.DataSourceType;
import com.ruoyi.project.lwjh.domain.ElementData;
import com.ruoyi.project.lwjh.mapper.LWJHCheckPointElementMapper;
import com.ruoyi.project.lwjh.service.LWJHCheckPointElementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@DataSource(value = DataSourceType.LWJH)
@Service
public class LWJHCheckPointElementServiceImpl implements LWJHCheckPointElementService {

    @Autowired
    private LWJHCheckPointElementMapper checkPointElementMapper;

    @Override
    public List<ElementData> selectElementData(String checkpointId, String accessDate) {
        return checkPointElementMapper.selectElementData(checkpointId, accessDate);
    }
}
