package com.ruoyi.project.common.domain;

import java.util.LinkedHashMap;
import java.util.Map;

public class TaskPrefix {

    private static Map<String, Integer> prefixTag() {
        Map<String, Integer> map = new LinkedHashMap<>();
        map.put("guocheng", 1002);
        map.put("lingxing", 1003);
        map.put("voc", 1004);
        map.put("youyan", 1006);
        map.put("outwater", 1012);
        map.put("qixiu", 1013);
        map.put("jiayou", 1014);
        map.put("jinshu", 1015);
        map.put("yitihua", 1016);
        map.put("water", 1017);
        map.put("lingxingp", 1018);
        map.put("zaozhi", 1019);
        map.put("gas", 1021);
        map.put("gasv", 1022);
        map.put("waterv", 1023);
        return map;
    }

    public static Integer getTagHeadId(String prefix) {
        return prefixTag().get(prefix);
    }

    public static Map<Integer, String> typePrefix() {
        Map<Integer, String> map = new LinkedHashMap<>();
        map.put(1002, "gc");
        map.put(1003, "lx");
        map.put(1004, "voc");
        map.put(1006, "yy");
        map.put(1012, "psh");
        map.put(1013, "qx");
        map.put(1014, "jy");
        map.put(1015, "js");
        map.put(1016, "yth");
        map.put(1017, "water");
        map.put(1019, "zz");
        map.put(1021, "gas");
        map.put(1022, "gasv");
        map.put(1023, "waterv");
        return map;
    }

    public static String getTypePrefix(Integer tagHeadId) {
        return typePrefix().get(tagHeadId);
    }
}
