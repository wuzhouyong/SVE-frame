package com.ruoyi.common.utils;

import com.google.common.util.concurrent.AtomicDouble;

import java.lang.reflect.Method;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author solang
 * @date 2020-09-19 23:17
 */
public class CollectUtils {

    /**
     * @param oldArray 旧的关联关系,数据库中原本存在的关联关系,需要拿到父的id去找子的id数组
     * @param newArray 前段传递过来的关联关系,父id下新的多个子id数组
     * @return Map key:add 新增关联关系,新增之前要判断是否存在已经被删除了,存在就更新,不存在就添加,key:delete 删除关联关系
     * 取到value,不用判断,直接遍历
     * @description 更新关联关系表中关联数据 父id对多个子id 注意:此方法用在有关联关系表,只操作关联关系表
     */
    public static Map<String, List<Integer>> toOperate(Integer[] newArray, Integer[] oldArray) {
        Map<String, List<Integer>> map = new LinkedHashMap<>();
        List<Integer> listNew = Stream.of(newArray).collect(Collectors.toList()); //旧关联关系数组
        List<Integer> listOld = Stream.of(oldArray).collect(Collectors.toList()); //新关联关系数组
        //交集
        List<Integer> tempList = listNew.stream().filter(num -> listOld.contains(num)).collect(Collectors.toList());
        //差集 listNew - tempList -> 新增清除关联关系,新增之前需要拿父id和子id 去数据库中查询,如果存在就更新,如果不存在就添加,避免垃圾数据一直增多
        List<Integer> addList = listNew.stream().filter(num -> !tempList.contains(num)).collect(Collectors.toList());
        //差集 listOld - tempList -> 清除关联关系,就是删除
        List<Integer> modifyList = listOld.stream().filter(num -> !tempList.contains(num)).collect(Collectors.toList());
        map.put("add", addList);
        map.put("delete", modifyList);
        return map;
    }

    /**
     * @param oldList       旧的关联关系,数据库中原本存在的关联关系,需要拿到父的id去找子id的实体类集合
     * @param newList       前段传递过来的关联关系,父id实体类下新的多个子id的实体类集合
     * @param getMethodName 实体类中获取id的get方法名 eg:getTechId,如果方法名不对就会报异常
     * @return Map key:add 新增, key:modify 修改,key:delete 删除
     * 取到value,不用判断,直接遍历
     * @description 关联关系是一个实体类对应多个实体类, 父id对多个子id, 前端页面是编辑时候父id是一个实体类, 子id也是一个实体类, 而且是一个实体类集合
     * 注意:此方法一对多的实体类,无论新增的实体类是否有id,如选择下拉框的实体类是不不需要新增id,都可以用这方法
     * 如果报错->getTechId,id空指针异常,则用toOperateN方法,很少报错 techId属性值不能为null,为null就会报异常
     */
    public static <T> Map<String, List<T>> toOperate(List<T> newList, List<T> oldList, String getMethodName) {
        Map<String, List<T>> map = new LinkedHashMap<>();
        List<T> modify = newList.stream().filter(p -> oldList.stream().anyMatch(a -> {
            try {
                Class<T> aClass = (Class<T>) a.getClass();
                Method method_a = aClass.getDeclaredMethod(getMethodName);
                String invoke_a = method_a.invoke(a).toString();
                Class<T> pClass = (Class<T>) p.getClass();
                Method method_p = pClass.getDeclaredMethod(getMethodName);
                String invoke_p = method_p.invoke(p).toString();
                if (!invoke_a.equals(invoke_p)) return false;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return true;
        })).collect(Collectors.toList());  //新集合中找到条件为旧集合与新集合id属性相等的集合 -> 修改
        map.put("modify", modify);
        List<T> add = newList.stream().filter(p -> !oldList.stream().anyMatch(a -> {
            try {
                Class<T> aClass = (Class<T>) a.getClass();
                Method method_a = aClass.getDeclaredMethod(getMethodName);
                String invoke_a = method_a.invoke(a).toString();
                Class<T> pClass = (Class<T>) p.getClass();
                Method method_p = pClass.getDeclaredMethod(getMethodName);
                String invoke_p = method_p.invoke(p).toString();
                if (!invoke_a.equals(invoke_p)) return false;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return true;
        })).collect(Collectors.toList());//新集合中找到条件为旧集合与新集合id属性不相等的集合 -> 新增
        map.put("add", add);
        List<T> delete = oldList.stream().filter(p -> !newList.stream().filter(f -> {
            try {
                Class<T> aClass = (Class<T>) f.getClass();
                Method method_a = aClass.getDeclaredMethod(getMethodName);
                if (StringUtils.isNull(method_a.invoke(f))) return false;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return true;
        }).anyMatch(a -> {
            try {
                Class<T> aClass = (Class<T>) a.getClass();
                Method method_a = aClass.getDeclaredMethod(getMethodName);
                String invoke_a = method_a.invoke(a).toString();
                Class<T> pClass = (Class<T>) p.getClass();
                Method method_p = pClass.getDeclaredMethod(getMethodName);
                String invoke_p = method_p.invoke(p).toString();
                if (!invoke_a.equals(invoke_p)) return false;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return true;
        })).collect(Collectors.toList());//旧集合中找到 条件为(新集合中条件为条件为id属性不为空集合)与旧集合id属性相等的集合 -> 删除
        map.put("delete", delete);
        return map;
    }

    /**
     * @param oldList       旧的关联关系,数据库中原本存在的关联关系,需要拿到父的id去找子id的实体类集合
     * @param newList       前段传递过来的关联关系,父id实体类下新的多个子id的实体类集合
     * @param getMethodName 实体类中获取id的get方法名 eg:getTechId,如果方法名不对就会报异常
     * @return Map key:add 新增, key:modify 修改,key:delete 删除
     * 取到value,不用判断,直接遍历
     * @description 关联关系是一个实体类对应多个实体类, 父id对多个子id, 前端页面是编辑时候父id是一个实体类, 子id也是一个实体类, 而且是一个实体类集合
     * 注意:此方法一对多的实体类,过滤了getTechId->id空指针异常 techId属性不能为null,为null就会报异常
     */
    public static <T> Map<String, List<T>> toOperateN(List<T> newList, List<T> oldList, String getMethodName) {
        Map<String, List<T>> map = new LinkedHashMap<>();
        List<T> add = newList.stream().filter(p -> {
            try {
                Class<T> pClass = (Class<T>) p.getClass();
                Method method_p = pClass.getDeclaredMethod(getMethodName);
                if (StringUtils.isNull(method_p.invoke(p))) return false;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return true;
        }).collect(Collectors.toList()); //新集合中找到条件id属性为null的集合 -> 新增
        map.put("add", add);
        List<T> noadd = newList.stream().filter(p -> {
            try {
                Class<T> pClass = (Class<T>) p.getClass();
                Method method_p = pClass.getDeclaredMethod(getMethodName);
                if (StringUtils.isNull(method_p.invoke(p))) return false;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return true;
        }).collect(Collectors.toList()); //新集合中找到条件id属性不为null的集合
        List<T> modify = noadd.stream().filter(p -> oldList.stream().anyMatch(a -> {
            try {
                Class<T> aClass = (Class<T>) a.getClass();
                Method method_a = aClass.getDeclaredMethod(getMethodName);
                String invoke_a = method_a.invoke(a).toString();
                Class<T> pClass = (Class<T>) p.getClass();
                Method method_p = pClass.getDeclaredMethod(getMethodName);
                String invoke_p = method_p.invoke(p).toString();
                if (!invoke_a.equals(invoke_p)) return false;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return true;
        })).collect(Collectors.toList());//新集合中找到条件为旧集合与新集合id属性不相等的集合 -> 修改
        map.put("modify", modify);
        List<T> delete = oldList.stream().filter(p -> !noadd.stream().anyMatch(a -> {
            try {
                Class<T> aClass = (Class<T>) a.getClass();
                Method method_a = aClass.getDeclaredMethod(getMethodName);
                String invoke_a = method_a.invoke(a).toString();
                Class<T> pClass = (Class<T>) p.getClass();
                Method method_p = pClass.getDeclaredMethod(getMethodName);
                String invoke_p = method_p.invoke(p).toString();
                if (!invoke_a.equals(invoke_p)) return false;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return true;
        })).collect(Collectors.toList());//旧集合中找到条件为新集合与旧集合id属性不相等的集合 -> 删除
        map.put("delete", delete);
        return map;
    }

    /**
     * @param groupList     需要分组的实体类集合
     * @param getMethodName 指定实体类需要分组的属性get方法名,比如:getWarnLevel
     * @return LinkedHashMap 分组之后map集合
     * @description 根据指定字段的方法名进行分组, 因指定字段的类型不确定，分组之后为Object类型
     * 注意：1.如果已经过滤分组字段为null值，分组字段值为null,无法分组
     * 2.如果反射执行get方法 getMethodName 该方法没执行，无法分组
     */
    public static <E> LinkedHashMap<Object, List<E>> groupBy(List<E> groupList, String getMethodName) {
        LinkedHashMap<Object, List<E>> map = groupList.stream().filter(p -> {
            try {
                Class<E> pClass = (Class<E>) p.getClass();
                Method method_p = pClass.getDeclaredMethod(getMethodName);
                if (StringUtils.isNull(method_p.invoke(p))) return false;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return true;
        }).collect(Collectors.groupingBy((p -> {
            try {
                Class<E> pClass = (Class<E>) p.getClass();
                Method method_p = pClass.getDeclaredMethod(getMethodName);
                return method_p.invoke(p);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return "分组错误";
        }), LinkedHashMap::new, Collectors.toList()));
        return map;
    }

    /**
     * @param groupList     需要抽取的实体类集合
     * @param getMethodName 指定k作为实体类的唯一标识，代表整个实体类
     *                      需要指定实体类需要分组的属性get方法名,比如:getWarnLevel
     * @return LinkedHashMap 抽取之后map
     * @description 根据指定实体类的属性作为k(这个k代表整个map, 相当于主键)对list赋值主键
     * eg:Map<1, E{getMethodName:1}> 指定实体类中的key当作主键，代表整个map,k为主键，也就是x.getId(),v,为主键对应的实体类,也就是p
     * 注意：1.如果已经过滤分组字段为null值，分组字段值为null,无法分组
     * 2.如果反射执行get方法 getMethodName 该方法没执行，无法分组
     *
     * Map<Long,Person> personMap =list.stream().sorted(Comparator.comparing(x -> x.getDataDate(),
     *      Comparator.nullsFirst(Comparator.reverseOrder())))
     * .collect(Maps::newHashMap,(map,p)->map.put(p.getId().toString(),p),Map::putAll); 和这功能一样
     */
    public static <E> LinkedHashMap<String, E> extractBy(List<E> groupList, String getMethodName) {
        Map<String, E> h_map = groupList.stream().filter(p -> {
            try {
                Class<E> pClass = (Class<E>) p.getClass();
                Method method_p = pClass.getDeclaredMethod(getMethodName);
                if (StringUtils.isNull(method_p.invoke(p))) return false;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return true;
        }).collect(Collectors.toMap((p -> {
            try {
                Class<E> pClass = (Class<E>) p.getClass();
                Method method_p = pClass.getDeclaredMethod(getMethodName);
                return method_p.invoke(p).toString();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return "抽取失败";
        }), p -> p, (key1, key2) -> key2));
        LinkedHashMap map = new LinkedHashMap(h_map);
        return map;
    }

    /**
     * @param sumMap     需要求属性和的分组对象
     * @param MethodName 指定实体类属性，第一个字母大写，也就是set/get方法的后部分
     * @return List<Map < String Object>> 分组求和之后map集合
     * @description 根据指定字段的方法名进行分组之后的求和, 按分组之后求得每个分组的某个属性值的和
     * 注意：1.该方法参数是set/get方法的后面部分setMethodName
     * 2.如果反射执行get方法 getMethodName 该方法没执行，无法分组
     */
    public static <E> List<Map<String, Object>> groupBy(Map<Object, List<E>> sumMap, String MethodName) {
        List<Map<String, Object>> outletList = new ArrayList<>();
        for (Map.Entry<Object, List<E>> entry : sumMap.entrySet()) {
            Map<String, Object> temp = new HashMap<>();
            temp.put("group", entry.getKey());
            List<E> value = entry.getValue();
            temp.put("group", value);
            AtomicDouble sum = new AtomicDouble(0);//求和某个属性的值相加
            value.forEach(x -> {
                try {
                    Class<E> pClass = (Class<E>) x.getClass();
                    Method method_g = pClass.getDeclaredMethod("get" + MethodName);
                    Method method_s = pClass.getDeclaredMethod("set" + MethodName, Object.class);
                    if (StringUtils.isNull(method_g.invoke(x))) {
                        method_s.invoke(x, 0.0);
                    }
                    sum.addAndGet(Double.parseDouble(method_g.invoke(x).toString()));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                sum.addAndGet(0.0);
            });
            temp.put("count", sum.doubleValue());
            outletList.add(temp);
        }
        return outletList;
    }

    /**
     * @param filterList    需要过滤null的数组
     * @param getMethodName 实体类中获取id的get方法名 eg:getTechId,如果方法名不对就会报异常
     * @return List 取得属性为null的集合
     * @description 取对象集合中属性为null的集合
     */
    public static <T> List<T> tofilter(List<T> filterList, String getMethodName) {
        List<T> list_t = filterList.stream().filter(p -> {
            try {
                Class<T> pClass = (Class<T>) p.getClass();
                Method method_p = pClass.getDeclaredMethod(getMethodName);
                if (StringUtils.isNull(method_p.invoke(p))) return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return false;
        }).collect(Collectors.toList());
        return list_t;
    }

    /**
     * @param filterList    需要过滤null的数组
     * @param getMethodName 实体类中获取id的get方法名 eg:getTechId,如果方法名不对就会报异常
     * @return List 取得属性不为null的集合
     * @description 取对象集合中属性不为null的集合
     */
    public static <T> List<T> tofilterN(List<T> filterList, String getMethodName) {
        List<T> list_t = filterList.stream().filter(p -> {
            try {
                Class<T> pClass = (Class<T>) p.getClass();
                Method method_p = pClass.getDeclaredMethod(getMethodName);
                if (StringUtils.isNotNull(method_p.invoke(p))) return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return false;
        }).collect(Collectors.toList());
        return list_t;
    }


}
