package com.ruoyi.common.utils;

import java.util.ArrayList;
import java.util.List;

import com.ruoyi.common.domain.DataSearchColEntity;

public class DataMapUtils {
    public static DataSearchColEntity createColumn(String title, String field) {
        DataSearchColEntity entity = new DataSearchColEntity();
        entity.setTitle(title);
        entity.setDataIndex(field);
        return entity;
    }

    public static List<DataSearchColEntity> getTitle(String checkPointType, String dataType) {
        List<DataSearchColEntity> list = new ArrayList<>();
        list.add(createColumn("日期", dataType.equals("DAY") ? "dataDateD" : "dataDate"));
        if (dataType.equals("HOUR")) {
            list.add(createColumn("标记状态", "workStatusName"));
        }
        if (checkPointType.equals("1")) {
            list.add(createColumn("污水检测井pH(无量纲)", "pH"));
            list.add(createColumn("污水检测井电导率(mS/m)", "conductivity"));
        } else {
            list.add(createColumn("雨水监测井pH值(无量纲)", "pH"));
            list.add(createColumn("雨水监测井电导率(mS/m)", "conductivity"));
        }
        list.add(createColumn("WQI", "wqi"));
        return list;
    }
}
