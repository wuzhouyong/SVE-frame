package com.ruoyi.project.datasearch.service.impl;

import com.ruoyi.project.datasearch.domain.DataElementEntity;
import com.ruoyi.project.datasearch.mapper.DataSearchMapper;
import com.ruoyi.project.datasearch.service.IDataSearchService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DataSearchService implements IDataSearchService {
    @Resource
    private DataSearchMapper mapper;

    private List<Map<String, Object>> getDataCol(String sourceId) {
        List<DataElementEntity> list = mapper.getElementList(sourceId);
        List<Map<String, Object>> result = createDataCols(list);
        result.add(createCol("时间", "date", 200));
        return result;
    }

    private List<Map<String, Object>> createDataCols(List<DataElementEntity> list) {
        List<Map<String, Object>> result = new ArrayList<>();
        list.stream().filter(p -> p.getGroup() == null).forEach(e -> {
            String title = e.getElementDesc();
            if (e.getUnit() != null) {
                title = String.format("%s(%s)", e.getElementDesc(), e.getUnit());
            }
            result.add(createCol(title, e.getElementId()));
        });
        list.stream().filter(p -> p.getGroup() != null)
                .collect(Collectors.groupingBy(DataElementEntity::getGroup, LinkedHashMap::new, Collectors.toList()))
                .forEach((group, elements) -> {
                    result.add(createCol(group, createDataCols(elements)));
                });
        return result;
    }

    private Map<String, Object> createCol(String title, String field) {
        return createCol(title, field, null);
    }

    private Map<String, Object> createCol(String title, List<Map<String, Object>> children) {
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("title", title);
        map.put("children", children);
        return map;
    }

    private Map<String, Object> createCol(String title, String field, Integer width) {
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("title", title);
        map.put("dataIndex", field);
        if (width != null) map.put("width", width);
        else map.put("minWidth", 150);
        return map;
    }
}
