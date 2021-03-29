package com.ruoyi.common.utils;


import com.google.common.util.concurrent.AtomicDouble;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

public class HashMapUtils {

    private static final char UNDERLINE = '_';

    /**
     * @param maps
     * @return
     * @description 把map的key转换成驼峰命名
     */
    public static List<Map> toCamel(List<Map> maps) {
        List<Map> re_maps = new ArrayList<Map>();
        for (Map map : maps) {

            Map re_map = new HashMap();
            if (re_map != null) {
                Iterator var2 = map.entrySet().iterator();

                while (var2.hasNext()) {
                    String key = ((Map.Entry) var2.next()).getKey().toString();
                    re_map.put(underlineToCamel(key), map.get(key));
                }
            }
            re_maps.add(re_map);
        }
        maps.clear();
        return re_maps;
    }

    /**
     * @param maps
     * @return
     * @description 把map的key变成小写
     */
    public static List<Map> toLower(List<Map> maps) {
        List<Map> re_maps = new ArrayList<Map>();
        for (Map map : maps) {
            Map re_map = new HashMap();
            if (re_map != null) {
                Iterator var2 = map.entrySet().iterator();
                while (var2.hasNext()) {
                    String key = ((Map.Entry) var2.next()).getKey().toString();
                    re_map.put(underlineToLower(key), map.get(key));
                }
            }
            re_maps.add(re_map);
        }
        maps.clear();
        return re_maps;
    }

    /**
     * @param map
     * @return
     * @description 把map的key变成小写
     */
    public static Map toCamel(Map map) {
        Map re_map = new HashMap();
        if (re_map != null) {
            Iterator var2 = map.entrySet().iterator();
            while (var2.hasNext()) {
                String key = ((Map.Entry) var2.next()).getKey().toString();
                re_map.put(underlineToCamel(key), map.get(key));
            }
            map.clear();
        }
        return re_map;
    }

    /**
     * @param map
     * @return
     * @description 把map的key转换成驼峰命名
     */
    public static Map toLower(Map map) {
        Map re_map = new HashMap();
        Iterator var2 = map.entrySet().iterator();
        while (var2.hasNext()) {
            String key = ((Map.Entry) var2.next()).getKey().toString();
            re_map.put(underlineToLower(key), map.get(key));
        }
        map.clear();
        return re_map;
    }

    public static String underlineToLower(String param) {
        if (param == null || "".equals(param.trim())) {
            return "";
        }
        int len = param.length();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            sb.append(Character.toLowerCase(param.charAt(i)));
        }
        return sb.toString();
    }

    public static String underlineToCamel(String param) {
        if (param == null || "".equals(param.trim())) {
            return "";
        }
        int len = param.length();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            char c = param.charAt(i);
            if (c == UNDERLINE) {
                if (++i < len) {
                    sb.append(Character.toUpperCase(param.charAt(i)));
                }
            } else {
                sb.append(Character.toLowerCase(param.charAt(i)));
            }
        }
        return sb.toString();
    }

    /**
     * @param map
     * @return Map
     * @description 把map的value中BigDecimal, Timestamp转换成string类型且根据k排序 正序
     */
    public static Map toTypeOf(Map map) {
        Set<Map.Entry<String, Object>> entries = map.entrySet();
        entries.stream().filter(e -> e.getValue() != null).forEach(e -> {
            if (e.getValue().getClass().getSimpleName().equals("BigDecimal")) {
                map.put(e.getKey(), e.getValue().toString());
            }
            if (e.getValue().getClass().getSimpleName().equals("Timestamp")) {
                map.replace(e.getKey(), DateUtils.dateTimeS((Date) e.getValue()));
            }
        });
        return sortByKey(map, true);
    }

    /**
     * @param mapList
     * @return
     * @description 把map的value中BigDecimal, Timestamp转换成string类型
     * 且map根据k排序 正序 最简单的排序 Map re_map=new TreeMap(map);
     */
    public static List<Map> toTypeOf(List<Map> mapList) {
        List<Map> list = new ArrayList<>();
        for (Map noMap : mapList) {
            Set<Map.Entry<String, Object>> noEntries = noMap.entrySet();
            noEntries.stream().filter(e -> e.getValue() != null).forEach(e -> {
                if (e.getValue().getClass().getSimpleName().equals("BigDecimal")) {
                    noMap.replace(e.getKey(), e.getValue().toString());
                }
                if (e.getValue().getClass().getSimpleName().equals("Timestamp")) {
                    noMap.replace(e.getKey(), DateUtils.dateTimeS((Date) e.getValue()));
                }
            });
            list.add(sortByKey(noMap, true));
        }
        return list;
    }

    /**
     * @param mapList 需要转换的map集合,format按照指定的时间格式(yyyy-MM-dd HH:mm)去转换
     * @return
     * @description 把map的value中BigDecimal, Timestamp转换成string类型
     * 且map根据k排序 正序 最简单的排序 Map re_map=new TreeMap(map);
     */
    public static List<Map> toTypeOf(List<Map> mapList, String format) {
        List<Map> list = new ArrayList<>();
        for (Map noMap : mapList) {
            Set<Map.Entry<String, Object>> noEntries = noMap.entrySet();
            noEntries.stream().filter(e -> e.getValue() != null).forEach(e -> {
                if (e.getValue().getClass().getSimpleName().equals("BigDecimal")) {
                    noMap.replace(e.getKey(), e.getValue().toString());
                }
                if (e.getValue().getClass().getSimpleName().equals("Timestamp")) {
                    noMap.replace(e.getKey(), DateUtils.parseDate((Date) e.getValue(), format));
                }
                if (e.getValue().getClass().getSimpleName().equals("Date")) {
                    noMap.replace(e.getKey(), DateUtils.parseDate((Date) e.getValue(), format));
                }
            });
            list.add(sortByKey(noMap, true));
        }
        return list;
    }

    /**
     * @param mapList List<Map<String, Object>> 转换成List<Map>
     */
    public static List<Map> mapReMap(List<Map<String, Object>> mapList) {
        List<Map> list = new ArrayList<>();
        for (Map<String, Object> noMap : mapList) {
            Map map = new HashMap(noMap);
            list.add(map);
        }
        return list;
    }

    /**
     * @param mapList List<Map> 转换成List<Map<String, Object>>
     */
    public static List<Map<String, Object>> mapToMap(List<Map> mapList) {
        List<Map<String, Object>> list = new ArrayList<>();
        for (Map noMap : mapList) {
            Map<String, Object> map = new HashMap(noMap);
            list.add(map);
        }
        return list;
    }

    /**
     * @param mapList
     * @param orderKey 根据key排序
     * @return
     * @description 把map的value中BigDecimal, Timestamp转换成string类型
     * 且根据指定map的k,获取value,根据value 对list排序 正序
     */
    public static List<Map> orderValue(List<Map> mapList, String orderKey) {
        List<Map> list = new ArrayList<>();
        List<Map> tempList = toTypeOf(mapList);
        tempList.stream().filter(e -> e.get(orderKey) != null).sorted(new Comparator<Map>() {
            @Override
            public int compare(Map o1, Map o2) {
                return (o1.get(orderKey).toString()).compareTo(o2.get(orderKey).toString());
            }
        }).forEach(e -> list.add(e));
        tempList.stream().filter(e -> e.get(orderKey) == null).forEach(e -> {
            Map<String, Object> map = new HashMap();
            Set<Map.Entry<String, Object>> entry = e.entrySet();
            for (Map.Entry<String, Object> x : entry) {
                if (x.getValue() == null) {
                    map.put(x.getKey(), null);
                }
                map.put(x.getKey(), x.getValue());
            }
            list.add(map);
        });
        return list;
    }

    /**
     * @param mapList
     * @param orderKey 根据key排序
     * @return
     * @description 把map的value中BigDecimal, Timestamp转换成string类型
     * 且根据指定map的k,获取value,根据value 对list排序 倒序
     */
    public static List<Map> orderValueDesc(List<Map> mapList, String orderKey) {
        List<Map> list = new ArrayList<>();
        List<Map> tempList = toTypeOf(mapList);
        tempList.stream().filter(e -> e.get(orderKey) != null).sorted(new Comparator<Map>() {
            @Override
            public int compare(Map o1, Map o2) {
                return (o1.get(orderKey).toString()).compareTo(o2.get(orderKey).toString());
            }
        }.reversed()).forEach(e -> list.add(e));
        tempList.stream().filter(e -> e.get(orderKey) == null).forEach(e -> {
            Map<String, Object> map = new HashMap();
            Set<Map.Entry<String, Object>> entry = e.entrySet();
            for (Map.Entry<String, Object> x : entry) {
                if (x.getValue() == null) {
                    map.put(x.getKey(), null);
                }
                map.put(x.getKey(), x.getValue());
            }
            list.add(map);
        });
        return list;
    }

    /**
     * @param map    待排序的map,
     * @param isDesc 是否降序，true：升序，false：降序
     * @return
     * @description 根据map的key排序
     */
    public static <K extends Comparable<? super K>, V> Map<K, V> sortByKey(Map<K, V> map, boolean isDesc) {
        // Map t_map=new TreeMap(map); return t_map; 最简单的排序
        LinkedHashMap<K, V> result = new LinkedHashMap();
        if (isDesc) {
            map.entrySet().stream().sorted(Map.Entry.<K, V>comparingByKey())
                    .forEachOrdered(e -> result.put(e.getKey(), e.getValue()));
        } else {
            map.entrySet().stream().sorted(Map.Entry.<K, V>comparingByKey().reversed())
                    .forEachOrdered(e -> result.put(e.getKey(), e.getValue()));
        }
        return result;
    }

    /**
     * @param map    待排序的map
     * @param isDesc 是否降序，true：升序，false：降序,
     * @return
     * @description 把map的value中BigDecimal, Timestamp转换成string类型 如果value为null则排在最后
     */
    public static Map sortByValue(Map map, boolean isDesc) {
        Set<Map.Entry<String, Object>> entry = map.entrySet();
        List<Map.Entry<String, Object>> list = new ArrayList<>();
        list.addAll(entry);
        Map<String, Object> re_map = new LinkedHashMap<>();
        if (isDesc) {
            list.stream().filter(e -> e.getValue() != null).sorted(Comparator.comparing(e -> e.getValue().toString()))
                    .forEach(e -> re_map.put(e.getKey(), e.getValue()));
            list.stream().filter(e -> e.getValue() == null)
                    .sorted(Comparator.comparing(Map.Entry::getKey))
                    .forEach(e -> re_map.put(e.getKey(), null));
        } else {
            list.stream().filter(e -> e.getValue() != null)
                    .sorted(Collections.reverseOrder(Comparator.comparing(e -> e.getValue().toString())))
                    .forEach(e -> re_map.put(e.getKey(), e.getValue()));
            list.stream().filter(e -> e.getValue() == null)
                    .sorted(Comparator.comparing(Map.Entry::getKey))
                    .forEach(e -> re_map.put(e.getKey(), null));
        }
        return re_map;
    }

    /**
     * @param mapList  需要分组的map
     * @param orderKey 需要分组的key 转换为string在分组
     * @return
     * @description 把k转为小写.根据指定map的k对list分组, 并进行类型转换
     */
    public static Map<String, List<Map>> groupValue(List<Map> mapList, String orderKey) {
        List<Map> list = toTypeOf(toCamel(mapList));
        LinkedHashMap<String, List<Map>> collect = list.stream().filter(e -> e.get(orderKey) != null)
                .collect(Collectors.groupingBy(e -> e.get(orderKey).toString(), LinkedHashMap::new, Collectors.toList()));
        return collect;
    }

    /**
     * @param mapList  需要抽取的map集合
     * @param orderKey 指定k作为v的唯一标识，代表整个map
     * @return
     * @description 根据指定map的k(这个k代表整个map, 相当于主键)对list赋值主键 eg:Map<1, Map{orderKey,1}>
     * 指定map中的key当作主键，代表整个map,k为主键,也就是x.get(orderKey),v，为主键对应的map,也就是p
     * <p>
     * Map<Long,Person> personMap =list.stream().sorted(Comparator.comparing(x -> x.get(orderKey),
     * Comparator.nullsFirst(Comparator.reverseOrder())))
     * .collect(Maps::newHashMap,(map,p)->map.put(p.get(orderKey).toString(),p),Map::putAll); 和这功能一样
     */
    public static Map<String, Map> extractValue(List<Map> mapList, String orderKey) {
        Map<String, Map> collect = mapList.stream().filter(e -> e.get(orderKey) != null)
                .collect(Collectors.toMap(x -> x.get(orderKey).toString(), p -> p, (key1, key2) -> key2));
        return collect;
    }

    /**
     * @param mapList  分组之后的Map
     * @param orderKey 根据key对每个分组的求和,注:key得到的vlue不能为非数字类型的string,自动过滤null
     * @return
     * @description 把map的value中BigDecimal, Timestamp转换成string类型
     * 对分组之后的map,对其指定map的k求和,k对应的value为BigDecimal类型,如果不是,则不会分组求和
     * 返回k为data,值为分组之后的List<Map>,需要做数据转换
     * AtomicDouble类 该方法需要加载 com.google.guava包
     */
    public static List<Map<String, Object>> fieldSumValue(Map<String, List<Map>> mapList, String orderKey) {
        List<Map<String, Object>> outletList = new ArrayList<>();
        Set<Map.Entry<String, List<Map>>> entry = mapList.entrySet();
        for (Map.Entry<String, List<Map>> e : entry) {
            Map<String, Object> temp = new LinkedHashMap<>();
            temp.put("group", e.getKey());
            temp.put("data", e.getValue());
            AtomicDouble sum = new AtomicDouble(0);//求和某个属性的值相加
            e.getValue().forEach(x -> {
                        Set<Map.Entry<String, Object>> setList = x.entrySet();
                        for (Map.Entry<String, Object> e_temp : setList) {
                            if (e_temp.getValue() == null) {
                                if (e_temp.getKey().equals(orderKey)) x.replace(e_temp.getKey(), 0.0);
                                continue;
                            }
                            String str = e_temp.getValue().getClass().getSimpleName();
                            if (!str.equals("Integer") && !str.equals("Double") && !str.equals("String"))
                                x.replace(e_temp.getKey(), 0.0);
                        }
                        sum.addAndGet(Double.parseDouble(x.get(orderKey).toString()));
                    }
            );
            temp.put("count", sum.doubleValue());
            outletList.add(temp);
        }
        return outletList;
    }

    /**
     * @param <T> t 需要转换成map的实体类对象
     * @return 返回相对应的 Map
     * @throws
     * @description 对象转Map, map中的key和属性名一样
     */
    public static <T> Map<String, Object> beanToMap(T t) {
        Map<String, Object> map = new HashMap<>();
        //获得类的的属性名 数组
        Field[] fields = t.getClass().getDeclaredFields();
        for (Field field : fields) {
            try {
                boolean flag = field.isAccessible();
                field.setAccessible(true);
                map.put(field.getName(), field.get(t));
                field.setAccessible(flag);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return map;
    }

    /**
     * @param <T> t 把实体类及父类属性转换为map
     * @return 返回相对应的 Map
     * @throws
     * @description 对象转Map, map中的key和属性名一样
     */
    public static <T> Map<String, Object> beanToMapF(T t) {
        Map<String, Object> map = new HashMap<>();
        //获得类的的属性名 数组
        Field[] fields = t.getClass().getDeclaredFields();
        for (Field field : fields) {
            try {
                boolean flag = field.isAccessible();
                field.setAccessible(true);
                map.put(field.getName(), field.get(t));
                field.setAccessible(flag);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        Class<?> aClass = t.getClass().getSuperclass();
        if (aClass != null) {
            try {
                //Object o = aClass.newInstance();不能取父类对象
                Field[] fields_t = aClass.getDeclaredFields();
                for (Field field : fields_t) {
                    boolean flag = field.isAccessible();
                    field.setAccessible(true);
                    map.put(field.getName(), field.get(t)); //要取子类对象
                    field.setAccessible(flag);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return map;
    }

    /**
     * @param <T> t 需要转换成map的实体类对象
     * @return Map 转换之后的map
     * @description: 对象转Map, map中的key和属性名一样，并统一加key前缀
     */
    public static <T> Map<String, Object> beanToMap(T t, String prefix) {
        Map<String, Object> map = new HashMap<>();
        //获得类的的属性名 数组
        Field[] fields = t.getClass().getDeclaredFields();
        for (Field field : fields) {
            try {
                boolean flag = field.isAccessible();
                field.setAccessible(true);
                //  String varName = field.getName().toLowerCase();  // 将key置为小写，默认为对象的属性
                map.put(prefix + field.getName(), field.get(t));
                field.setAccessible(flag);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return map;
    }

    /**
     * @param list<T> t需要转换成map的实体类对象数组
     * @return 返回相对应的 Map
     * @throws
     * @description 对象转Map, map中的key和属性名一样
     */
    public static <T> List<Map<String, Object>> beanToMap(List<T> list) {
        if (list == null || list.size() == 0) return new ArrayList<>();
        List<Map<String, Object>> retList = new ArrayList<>();
        for (T t : list) {
            Map<String, Object> map = new HashMap<>();
            //获得类的的属性名 数组
            Field[] fields = t.getClass().getDeclaredFields();
            for (Field field : fields) {
                try {
                    boolean flag = field.isAccessible();
                    field.setAccessible(true);
                    map.put(field.getName(), field.get(t));
                    field.setAccessible(flag);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            retList.add(map);
        }
        return retList;
    }

    /**
     * @param list<T> 把实体类属性及直接父类属性转换为map
     * @return 返回相对应的 Map
     * @throws
     * @description 对象转Map, map中的key和属性名一样
     */
    public static <T> List<Map<String, Object>> beanToMapF(List<T> list) {
        if (list == null || list.size() == 0) return new ArrayList<>();
        List<Map<String, Object>> retList = new ArrayList<>();
        for (T t : list) {
            Map<String, Object> map = new HashMap<>();
            //获得类的的属性名 数组
            Field[] fields = t.getClass().getDeclaredFields();
            for (Field field : fields) {
                try {
                    boolean flag = field.isAccessible();
                    field.setAccessible(true);
                    map.put(field.getName(), field.get(t));
                    field.setAccessible(flag);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            Class<?> aClass = t.getClass().getSuperclass();
            if (aClass != null) {
                try {
                    //Object o = aClass.newInstance();不能取父类对象
                    Field[] fields_t = aClass.getDeclaredFields();
                    for (Field field : fields_t) {
                        boolean flag = field.isAccessible();
                        field.setAccessible(true);
                        map.put(field.getName(), field.get(t)); //要取子类对象
                        field.setAccessible(flag);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            retList.add(map);
        }


        return retList;
    }

    /**
     * @param <T>
     * @param map   需要初始化的数据，key字段必须与实体类的成员名字一样,而且属性类型也要一样,否则赋值为空
     * @param clazz 需要转化成的实体类
     * @return
     * @description: Map转实体类
     */

    public static <T> T mapToBean(Map<String, Object> map, Class<T> clazz) {
        T t = null;
        try {
            t = clazz.newInstance();
            for (Field field : clazz.getDeclaredFields()) {
                if (map.containsKey(field.getName())) {
                    boolean flag = field.isAccessible();
                    field.setAccessible(true);
                    Object object = map.get(field.getName());
                    if (object != null && field.getType().isAssignableFrom(object.getClass())) {
                        field.set(t, object);
                    }
                    field.setAccessible(flag);
                }
            }
            return t;
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return t;
    }

    /**
     * @param <T>
     * @param mapList 需要初始化的数据数组，key字段必须与实体类的成员名字一样,而且属性类型也要一样,否则赋值为空
     * @param clazz   需要转化成的实体类
     * @return
     * @description: List<T>转实体类
     */

    public static <T> List<T> mapToBean(List<Map> mapList, Class<T> clazz) {
        if (mapList == null || mapList.isEmpty()) return new ArrayList<>();
        List<T> list = new ArrayList<>();
        for (Map map : mapList) {
            T t = null;
            try {
                t = clazz.newInstance();
                for (Field field : clazz.getDeclaredFields()) {
                    if (map.containsKey(field.getName())) {
                        boolean flag = field.isAccessible();
                        field.setAccessible(true);
                        Object object = map.get(field.getName());
                        if (object != null && field.getType().isAssignableFrom(object.getClass())) {
                            field.set(t, object);
                        }
                        field.setAccessible(flag);
                    }
                }
                list.add(t);
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

}