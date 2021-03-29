package com.ruoyi.common.utils;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.ruoyi.common.domain.DataSearchColEntity;

public class DatDayUtils{
    public static List<Map<String, Object>> getTitle(List<DataSearchColEntity> colEntity, String dataType) {
        //BasCheckPointsEntity entity;
        List<Map<String, Object>> tList = new ArrayList<>();
        Map<String, Object> dateMap = new LinkedHashMap<>();
        dateMap.put("dataIndex","dataDate");
        dateMap.put("title","日期");

        dateMap.put("dataIndex",colEntity.get(0).getDataIndex());
        dateMap.put("title",colEntity.get(0).getTitle());
        tList.add(dateMap);
        return tList;
    }
}
