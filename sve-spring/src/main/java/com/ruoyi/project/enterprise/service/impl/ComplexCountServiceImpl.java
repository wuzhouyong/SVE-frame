package com.ruoyi.project.enterprise.service.impl;

import com.ruoyi.common.utils.HashMapUtils;
import com.ruoyi.project.enterprise.mapper.ComplexCountMapper;
import com.ruoyi.project.enterprise.service.ComplexCountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author solang
 * @date 2020-12-03 18:06
 */
@Service
public class ComplexCountServiceImpl implements ComplexCountService {

    @Autowired
    private ComplexCountMapper mapper;

    @Override
    public List<Map> getTownComplexCount(String tagHeadId) {
        return HashMapUtils.toCamel(mapper.getTownComplexCount(tagHeadId));
    }
}
