package com.ruoyi.common.utils;

import com.ruoyi.common.domain.DataSearchColEntity;
import com.ruoyi.common.domain.DataSearchEntity;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

public class DataSearchUtils {
    public static <TEntity extends DataSearchColEntity> List<Map<String, Object>> searchDataCol(List<TEntity> list) {
        List<Map<String, Object>> result = new ArrayList<>();
        List<TEntity> noGroupMap = list.stream().filter(p -> p.getGroupName() == null).collect(Collectors.toList());
        for (TEntity entity : noGroupMap) {
            Map<String, Object> map = new LinkedHashMap<>();
            map.put("title", entity.getTitle());
            map.put("dataIndex", entity.getDataIndex());
            map.put("width", entity.getWidth());
            map.put("minWidth", entity.getMinWidth());
            result.add(map);
        }
        LinkedHashMap<String, List<TEntity>> groupMap = list.stream().filter(p -> p.getGroupName() != null)
                .sorted(Comparator.comparingInt(DataSearchColEntity::getOrderBy))
                .collect(Collectors.groupingBy(g -> g.getGroupName(), LinkedHashMap::new, Collectors.toList()));
        for (Map.Entry<String, List<TEntity>> entry : groupMap.entrySet()) {
            Map<String, Object> map = new LinkedHashMap<>();
            map.put("title", entry.getKey());
            entry.getValue().stream().forEach(e -> e.setGroupName(null));
            List<Map<String, Object>> children = searchDataCol(entry.getValue());
            map.put("children", children);
            result.add(map);
        }
        return result;
    }

    public static <TEntity extends DataSearchEntity> List<Map<String, Object>> searchData(List<TEntity> list) {
        return searchData(list, "yyyy-MM-dd HH:mm:ss");
    }

    public static <TEntity extends DataSearchEntity> List<Map<String, Object>> searchData(List<TEntity> list, String format) {
//        List<Map<String, Object>> result = new ArrayList<>();
//        LinkedHashMap<Date, List<TEntity>> countMap = list.stream()
//                .sorted(Comparator.comparingLong(x -> x.getDataDate().getTime()))
//                .collect(Collectors.groupingBy(o -> o.getDataDate(), LinkedHashMap::new, Collectors.toList()));
//        for (Map.Entry<Date, List<TEntity>> entry : countMap.entrySet()) {
//            Map<String, Object> map = new LinkedHashMap<>();
//            map.put("date", DateStringUtils.dateToString(format, entry.getKey()));
//            for (TEntity cp : entry.getValue()) {
//                map.put(cp.getElementCode(), cp.getAmount());
//            }
//            result.add(map);
//        }
        return searchData(list, format, null);
    }

    public static <TEntity extends DataSearchEntity> List<Map<String, Object>> searchData(List<TEntity> list, String format, WorkStatusFunction func) {
        List<Map<String, Object>> result = new ArrayList<>();
        LinkedHashMap<Date, List<TEntity>> countMap = list.stream()
                .sorted(Comparator.comparingLong(x -> x.getDataDate().getTime()))
                .collect(Collectors.groupingBy(o -> o.getDataDate(), LinkedHashMap::new, Collectors.toList()));
        for (Map.Entry<Date, List<TEntity>> entry : countMap.entrySet()) {
            Map<String, Object> map = new LinkedHashMap<>();
            map.put("date", DateStringUtils.dateToString(format, entry.getKey()));
            if (func != null) {
                func.apply(entry.getKey(), map);
//                map.put("work_status", func.apply(entry.getKey(), map));
            }
            for (TEntity cp : entry.getValue()) {
                map.put(cp.getElementCode(), cp.getAmount());
            }
            result.add(map);
        }
        return result;
    }
}
