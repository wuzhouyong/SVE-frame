package com.ruoyi.project.outwater.service.impl;

import com.google.common.collect.Maps;
import com.ruoyi.common.utils.HashMapUtils;
import com.ruoyi.framework.aspectj.lang.annotation.DataSource;
import com.ruoyi.framework.aspectj.lang.enums.DataSourceType;
import com.ruoyi.project.outwater.mapper.OWMonitorMapper;
import com.ruoyi.project.outwater.service.OWMonitorService;
import org.apache.commons.collections4.map.LinkedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@DataSource(value = DataSourceType.OUTWATER)
public class OWMonitorServiceImpl implements OWMonitorService {

    @Autowired
    private OWMonitorMapper monitorMapper;

    //设备数量
    @Override
    public Map getEquipmentQuantity() {
        return HashMapUtils.toCamel(monitorMapper.getEquipmentQuantity());
    }

    //预警数
    @Override
    public Map getWarningQuantity() {
        return HashMapUtils.toCamel(monitorMapper.getWarningQuantity());
    }

    //近30天预警数
    @Override
    public Map getWarningQuantityMonth() {
        Map map = HashMapUtils.toCamel(monitorMapper.getWarningQuantityMonth());
        LinkedHashMap<String, Object> tempMap = new LinkedHashMap<>();
        tempMap.put("total", map.get("total"));
        List<Map> list = new ArrayList<>();
        map.forEach((k, v) -> {
            if (!k.equals("total")) {
                LinkedHashMap<String, Object> noMap = new LinkedHashMap<>();
                noMap.put("name", k);
                noMap.put("count", v);
                list.add(noMap);
            }
        });
        tempMap.put("rows", list);
        return tempMap;
    }

    //街镇报警排名
    @Override
    public List<Map> getWarningRank() {
        return HashMapUtils.toCamel(monitorMapper.getWarningRank());
    }

    //预警产生趋势
    @Override
    public List<Map> getWarningTrend() {
        return HashMapUtils.toCamel(monitorMapper.getWarningTrend());
    }

    //污染最严重企业top10
    @Override
    public List<Map> getWarningPollution() {
        return HashMapUtils.toCamel(monitorMapper.getWarningPollution());
    }

    //污染程度分析
    @Override
    public Map getPollutionAnalysis() {
        LinkedHashMap<String, Object> tempMap = new LinkedHashMap<>();
        Map temp_map = HashMapUtils.toCamel(monitorMapper.getWarningHour());
        Map mapHour = HashMapUtils.toTypeOf(temp_map);
        Set<Map.Entry<String, String>> entries = mapHour.entrySet();
        Map<String, String> result = Maps.newLinkedHashMap();
        entries.stream().sorted(Map.Entry.<String, String>comparingByValue()).forEachOrdered(e -> result.put(e.getKey(), e.getValue()));
        List<LinkedHashMap<String, Object>> listHour = new ArrayList<>();
        Iterator itHour = result.entrySet().iterator();
        while (itHour.hasNext()) {
            Map.Entry<String, String> entry = (Map.Entry<String, String>) itHour.next();
            LinkedHashMap<String, Object> noMap = new LinkedHashMap<>();
            noMap.put("type", entry.getKey());
            noMap.put("count", entry.getValue());
            listHour.add(noMap);
        }
        tempMap.put("hour", listHour);
        Map mapDay = HashMapUtils.toCamel(monitorMapper.getWarningDay());
        List<LinkedHashMap<String, Object>> listDay = new ArrayList<>();
        Iterator itDay = mapDay.entrySet().iterator();
        while (itDay.hasNext()) {
            Map.Entry<String, String> entry = (Map.Entry<String, String>) itDay.next();
            LinkedHashMap<String, Object> noMap = new LinkedHashMap<>();
            noMap.put("type", entry.getKey());
            noMap.put("count", entry.getValue());
            listDay.add(noMap);
        }
        tempMap.put("week", listDay);
        Map mapMonth = HashMapUtils.toCamel(monitorMapper.getWarningMonth());
        List<LinkedHashMap<String, Object>> listMonth = new ArrayList<>();
        Iterator itMonth = mapMonth.entrySet().iterator();
        while (itMonth.hasNext()) {
            Map.Entry<String, String> entry = (Map.Entry<String, String>) itMonth.next();
            LinkedHashMap<String, Object> noMap = new LinkedHashMap<>();
            noMap.put("type", entry.getKey());
            noMap.put("count", entry.getValue());
            listMonth.add(noMap);
        }
        tempMap.put("month", listMonth);
        return tempMap;
    }

}