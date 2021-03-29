package com.ruoyi.project.dgom.service.impl;

import com.ruoyi.common.utils.HashMapUtils;
import com.ruoyi.framework.aspectj.lang.annotation.DataSource;
import com.ruoyi.framework.aspectj.lang.enums.DataSourceType;
import com.ruoyi.project.dgom.mapper.WorkPlaceMapper;
import com.ruoyi.project.dgom.service.WorkPlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author solang
 * @date 2020-10-12 15:32
 */

@Service
@DataSource(value = DataSourceType.HBONLINE)
public class WorkPlaceServiceImpl implements WorkPlaceService {

    @Autowired
    private WorkPlaceMapper placeMapper;

    @Override
    public List<Map> getSourceNameList() {
        List<Map> mapList = HashMapUtils.toCamel(placeMapper.getSourceNameList());
        List<Map> list = new ArrayList<>();
        mapList.stream().forEach(x -> {
            Map map = HashMapUtils.sortByKey(x, true);
            list.add(map);
        });
        return list;
    }


}
