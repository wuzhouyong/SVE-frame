package com.ruoyi.project.dgom.domain.abnor_task;

import java.util.LinkedHashMap;
import java.util.Map;

public class AbnorDictType {
    private static Map<Integer, String> abnorReason() {
        Map<Integer, String> map = new LinkedHashMap<>();
        map.put(0, null);
        map.put(1, "断电");
        map.put(2, "通讯中断");
        map.put(3, "采集故障");
        map.put(4, "设备故障");
        map.put(5, "企业停产");
        map.put(6, "停用在线设备");
        map.put(7, "设备维修");
        map.put(8, "设备更换");
        map.put(9, "其他情况");
        map.put(10, "生产排放原因");
        return map;
    }

    public static String getReasonName(Integer key) {
        return abnorReason().get(key);
    }

    private static Map<Integer, String> abnorType() {
        Map<Integer, String> map = new LinkedHashMap<>();
        map.put(0, null);
        map.put(1, "超标");
        map.put(2, "零值");
        map.put(3, "静止");
        map.put(4, "缺失");
        map.put(5, "负值");
        map.put(6, "其他");
        return map;
    }

    public static String getAbnorType(Integer key) {
        return abnorType().get(key);
    }

    private static Map<Integer, String> alarmLevel() {
        Map<Integer, String> map = new LinkedHashMap<>();
        map.put(0, null);
        map.put(1, "白色");
        map.put(2, "黄色");
        map.put(3, "红色");
        return map;
    }

    public static String getAlarmLevel(Integer key) {
        return alarmLevel().get(key);
    }

    private static Map<Integer, String> taskStatus() {
        Map<Integer, String> map = new LinkedHashMap<>();
        map.put(0, null);
        map.put(1, "待初核");
        map.put(2, "待处置");
        map.put(3, "退回初核");
        map.put(4, "退回处置");
        map.put(5, "已完成");
        return map;
    }

    public static String getTaskStatus(Integer key) {
        return taskStatus().get(key);
    }
}
