package com.ruoyi.project.dgom.service.impl;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.HashMapUtils;
import com.ruoyi.framework.aspectj.lang.annotation.DataSource;
import com.ruoyi.framework.aspectj.lang.enums.DataSourceType;
import com.ruoyi.project.dgom.domain.DataReturnEntity;
import com.ruoyi.project.dgom.domain.DataSearchEnt;
import com.ruoyi.project.dgom.domain.DataSearchPar;
import com.ruoyi.project.dgom.domain.DataTableEntity;
import com.ruoyi.project.dgom.mapper.DataSearchMapper;
import com.ruoyi.project.dgom.service.DataSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 【请填写功能名称】Service业务层处理
 *
 * @author ruoyi
 * @date 2020-08-25
 */
@Service("online_monitor2")
@DataSource(value = DataSourceType.HBONLINE)
public class DataSearchImpl implements DataSearchService {
    @Autowired
    private DataSearchMapper dataSearchMapper;

    final List<Integer> ignoreElementIds = Arrays.asList(100029, 100040, 100070);
    final List<Integer> notOverflowElementIds = Arrays.asList(100002, 100003, 100004, 100029, 100040, 100041, 100045/*, 100046*/, 100047, 100058, 100059, 100060, 100063, 100064, 100070, 100071, 100072, 100073, 100074);

    //查找企业列表
    public List<Map> selectEnterpriseList(String sourceId, String fullName) {
        return HashMapUtils.toCamel(dataSearchMapper.selectEnterpriseList(sourceId, fullName));
    }

    //查找排放口
    public List<Map> selectEmissionList(String sourceId) {
        return HashMapUtils.toCamel(dataSearchMapper.selectEmissionList(sourceId));
    }

    //查找因子
    public List<Map> selectElementList() {
        return HashMapUtils.toCamel(dataSearchMapper.selectElementList());
    }

    //查找排放口下的因子
    public List<Map> getElementListByCheckpointId(String checkpointId) {
        return HashMapUtils.toCamel(dataSearchMapper.getElementListByCheckpointId(checkpointId));
    }

    //查找街镇
    public List<Map> selectTownNameList() {
        return HashMapUtils.toCamel(dataSearchMapper.selectTownNameList());
    }

    // 缺失数据(小时和日)
    public List<DataReturnEntity> getMissingHour(DataSearchPar entity) {
        return dataSearchMapper.getMissingHour(entity);
    }

    // 零值数据和超标数据(小时和日)
    public List<DataReturnEntity> getZerovalueHour(DataSearchPar entity) {
        return dataSearchMapper.getZerovalueHour(entity);
    }

    //查找任务列表
    public List<DataSearchPar> selectTasklistByCondition(DataSearchPar entity) {
        return dataSearchMapper.selectTasklistByCondition(entity);
    }

    //查找任务详情
    public Map selectAbnortaskheadInfo(String taskheadid) {
        Map map = HashMapUtils.toCamel(dataSearchMapper.selectAbnortaskheadInfo(taskheadid));
        Set<Map.Entry<String, Object>> entries = map.entrySet();
        entries.stream().filter(e -> e.getValue() != null).forEach(e -> {
            if (e.getValue().getClass().getSimpleName().equals("Timestamp")) {
                map.replace(e.getKey(), new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(e.getValue()));
            }
            if (e.getValue().getClass().getSimpleName().equals("BigDecimal")) {
                map.replace(e.getKey(), e.getValue().toString());
            }
        });
        List<Map> mapList = HashMapUtils.toCamel(dataSearchMapper.selectAbnortaskElementList(taskheadid));
        for (Map noMap : mapList) {
            Set<Map.Entry<String, Object>> noEntries = noMap.entrySet();
            noEntries.stream().filter(e -> e.getValue() != null).forEach(e -> {
                if (e.getValue().getClass().getSimpleName().equals("BigDecimal")) {
                    noMap.replace(e.getKey(), e.getValue().toString());
                }
                if (e.getValue().getClass().getSimpleName().equals("Timestamp")) {
                    noMap.replace(e.getKey(), new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(e.getValue()));
                }
            });
        }
        map.put("element", mapList);
        return map;
    }

    //表头实时数据
    public List<DataTableEntity> getHeaderReal(DataSearchPar entity) {
        return dataSearchMapper.getHeaderReal(entity);
    }

    //表头分钟数据
    public List<DataTableEntity> getHeaderMinute(DataSearchPar entity) {
        return dataSearchMapper.getHeaderMinute(entity);
    }

    // 表头小时数据
    public List<DataTableEntity> getHeaderHour(DataSearchPar entity) {
        return dataSearchMapper.getHeaderHour(entity);
    }

    // 表头日数据
    public List<DataTableEntity> getHeaderDay(DataSearchPar entity) {
        return dataSearchMapper.getHeaderDay(entity);
    }

    // 实时数据
    public List<Map<String, Object>> getReal(DataSearchPar entity) {
        List<DataSearchEnt> list = dataSearchMapper.getReal(entity);
        LinkedHashMap<Date, List<DataSearchEnt>> collect = list.stream().collect(Collectors.groupingBy(DataSearchEnt::getDatadate,
                LinkedHashMap::new, Collectors.toList()));
        List<Map<String, Object>> tempList = new ArrayList();
        List<Integer> al = Arrays.asList(100002, 100040, 100046);
        collect.forEach((x, y) -> {
            Map<String, Object> returnMap = new LinkedHashMap<>();
            returnMap.put("date", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(x));
            y.stream().filter(f -> !al.contains(f.getElementid())).forEach(z ->
                    returnMap.put("val_" + z.getElementid(), z.getAmount() != null ? z.getAmount() : 0)
            );
            tempList.add(returnMap);
        });
        return tempList;
    }

    // 分钟数据
    public List<Map<String, Object>> getMinute(DataSearchPar entity) {
        entity.setSourceId(dataSearchMapper.getSourceId(entity.getCheckpointid()));
        List<Map<String, Object>> result = new ArrayList<>();// 返回数据
        Date start = DateUtils.dateTime(entity.getStartDate(), "yyyy-MM-dd");// 开始时间
        Date end = Date.from(DateUtils.dateTime(entity.getStartDate(), "yyyy-MM-dd").toInstant().plusSeconds(24 * 60 * 60));// 结束时间
        entity.setEndDate(DateUtils.dateTime(end));
        long diff = end.getTime() - start.getTime();
        int minutes = Math.toIntExact(diff / (1000 * 60 * 10)); //10分钟
        //根据开始和结束时间获取区间每个小时的时间数组
        List<Date> dates = IntStream.range(0, minutes).mapToObj(m -> Date.from(start.toInstant().plusSeconds(m * 60 * 10))).sorted((x, y) -> Math.toIntExact(y.getTime() - x.getTime())).collect(Collectors.toList());
        List<DataSearchEnt> list = dataSearchMapper.getMinute(entity);
        List<DataTableEntity> elements = dataSearchMapper.getHeaderHour(entity);// 需要显示的因子列表
        List<Date> stops = dataSearchMapper.getStopTime(entity);// 停运时间列表
        Map<Date, List<DataSearchEnt>> groups = list.stream().collect(Collectors.groupingBy(DataSearchEnt::getDatadate, LinkedHashMap::new, Collectors.toList()));
        for (Date date : dates) {
            if (!(date.after(new Date()))) {
                Map<String, Object> item = new HashMap<>();
                item.put("date", DateUtils.parseDate(date, "yyyy-MM-dd HH:mm"));
                for (DataTableEntity element : elements) {
                    Integer elementId = element.getElementid();
//                int digit = element.getDecimaldigit();
                    Double minValue = element.getMinvalue();
                    Double maxValue = element.getMaxvalue();
                    if (ignoreElementIds.contains(elementId)) continue;
                    int dataElementId = 0;
                    switch (elementId) {
                        case 100002:
                            dataElementId = elementId + 14000;  //14000  34000
                            break;
                        case 100041:
                            elementId = 100040;
                            dataElementId = 114040;
                            break;
                        default:
                            dataElementId = elementId + 13000;
                            break;
                    }
                    if (stops.stream().anyMatch(p -> p.getTime() == date.getTime())) {
                        item.put("val_" + elementId, new HashMap<String, Object>() {
                            {
                                put("value", "停运");
                                put("flag", 2);
                            }
                        });
                        continue;
                    }
                    int finalDataElementId = dataElementId;
                    List<DataSearchEnt> data = groups.getOrDefault(date, new ArrayList<>());
                    Optional<DataSearchEnt> optional = data.stream().filter(p -> p.getElementid() == finalDataElementId).findFirst();
                    if (optional.isPresent()) {
                        DataSearchEnt searchEntity = optional.get();
                        Double value = searchEntity.getAmount();
                        Integer flag = searchEntity.getFlag();
                        if (value != null) {
                            // 根据digit对value进行截取
                            if (flag.equals(1)) {
                                item.put("val_" + elementId, new HashMap<String, Object>() {
                                    {
                                        put("value", value);
                                        put("flag", 1);
                                    }
                                });// {value: value, flag: 1}
                            } else {
                                if (!notOverflowElementIds.contains(elementId) && (minValue > value || value > maxValue)) {
                                    item.put("val_" + elementId, new HashMap<String, Object>() {
                                        {
                                            put("value", value);
                                            put("flag", 3);
                                        }
                                    });// {value: value, flag: 3}
                                } else {
                                    item.put("val_" + elementId, new HashMap<String, Object>() {
                                        {
                                            put("value", value);
                                            put("flag", 0);
                                        }
                                    });// {value: value, flag: 0}
                                }
                            }
                        } else {
                            item.put("val_" + elementId, new HashMap<String, Object>() {
                                {
                                    put("value", "");
                                    put("flag", 0);
                                }
                            });// {value: "null", flag: 1}
                        }
                    } else {
                        item.put("val_" + elementId, new HashMap<String, Object>() {
                            {
                                put("value", "");
                                put("flag", -1);
                            }
                        });// {value: "", flag: -1}
                    }
                }
                result.add(item);
            }
        }
        return result;
    }

    // 小时数据
    public List<Map<String, Object>> getHour(DataSearchPar entity) {
        entity.setSourceId(dataSearchMapper.getSourceId(entity.getCheckpointid()));
        List<Map<String, Object>> result = new ArrayList<>();// 返回数据
        Date start = DateUtils.dateTime(entity.getStartDate(), "yyyy-MM-dd");// 开始时间
        Date end = Date.from(DateUtils.dateTime(entity.getStartDate(), "yyyy-MM-dd").toInstant().plusSeconds(24 * 60 * 60));// 结束时间
        entity.setEndDate(DateUtils.dateTime(end));
        long diff = end.getTime() - start.getTime();
        int hour = Math.toIntExact(diff / (1000 * 60 * 60)); //1个小时
        //根据开始和结束时间获取区间每个小时的时间数组
        List<Date> dates = IntStream.range(0, hour).mapToObj(m -> Date.from(start.toInstant().plusSeconds(m * 60 * 60))).sorted((x, y) -> Math.toIntExact(y.getTime() - x.getTime())).collect(Collectors.toList());
        List<DataSearchEnt> list = dataSearchMapper.getHour(entity);
        List<DataTableEntity> elements = dataSearchMapper.getHeaderHour(entity);// 需要显示的因子列表
        List<Date> stops = dataSearchMapper.getStopTime(entity);// 停运时间列表
        Map<Date, List<DataSearchEnt>> groups = list.stream().collect(Collectors.groupingBy(DataSearchEnt::getDatadate, LinkedHashMap::new, Collectors.toList()));
        for (Date date : dates) {
            if (!(date.after(new Date()))) {
                Map<String, Object> item = new HashMap<>();
                item.put("date", DateUtils.parseDate(date, "yyyy-MM-dd HH:mm"));
                for (DataTableEntity element : elements) {
                    Integer elementId = element.getElementid();
//                int digit = element.getDecimaldigit();
                    Double minValue = element.getMinvalue();
                    Double maxValue = element.getMaxvalue();
                    if (ignoreElementIds.contains(elementId)) continue;
                    int dataElementId = 0;
                    switch (elementId) {
                        case 100002:
                            dataElementId = elementId + 24000;  //14000  34000
                            break;
                        case 100041:
                            elementId = 100040;
                            dataElementId = 124040;
                            break;
                        default:
                            dataElementId = elementId + 23000;
                            break;
                    }
                    if (stops.stream().anyMatch(p -> p.getTime() == date.getTime())) {
                        item.put("val_" + elementId, new HashMap<String, Object>() {
                            {
                                put("value", "停运");
                                put("flag", 2);
                            }
                        });
                        continue;
                    }
                    int finalDataElementId = dataElementId;
                    List<DataSearchEnt> data = groups.getOrDefault(date, new ArrayList<>());
                    Optional<DataSearchEnt> optional = data.stream().filter(p -> p.getElementid() == finalDataElementId).findFirst();
                    if (optional.isPresent()) {
                        DataSearchEnt searchEntity = optional.get();
                        Double value = searchEntity.getAmount();
                        Integer flag = searchEntity.getFlag();
                        if (value != null) {
                            // 根据digit对value进行截取
                            if (flag.equals(1)) {
                                item.put("val_" + elementId, new HashMap<String, Object>() {
                                    {
                                        put("value", value);
                                        put("flag", 1);
                                    }
                                });// {value: value, flag: 1}
                            } else {
                                if (!notOverflowElementIds.contains(elementId) && (minValue > value || value > maxValue)) {
                                    item.put("val_" + elementId, new HashMap<String, Object>() {
                                        {
                                            put("value", value);
                                            put("flag", 3);
                                        }
                                    });// {value: value, flag: 3}
                                } else {
                                    item.put("val_" + elementId, new HashMap<String, Object>() {
                                        {
                                            put("value", value);
                                            put("flag", 0);
                                        }
                                    });// {value: value, flag: 0}
                                }
                            }
                        } else {
                            item.put("val_" + elementId, new HashMap<String, Object>() {
                                {
                                    put("value", "");
                                    put("flag", 0);
                                }
                            });// {value: "null", flag: 1}
                        }
                    } else {
                        item.put("val_" + elementId, new HashMap<String, Object>() {
                            {
                                put("value", "");
                                put("flag", -1);
                            }
                        });// {value: "", flag: -1}
                    }
                }
                result.add(item);
            }
        }
        return result;
    }

    // 日数据
    public List<Map<String, Object>> getDay(DataSearchPar entity) {
        entity.setSourceId(dataSearchMapper.getSourceId(entity.getCheckpointid()));
        List<Map<String, Object>> result = new ArrayList<>();// 返回数据
        Date start = DateUtils.dateTime(entity.getStartDate(), "yyyy-MM-dd");// 开始时间
        Date end = Date.from(DateUtils.dateTime(entity.getEndDate(), "yyyy-MM-dd").toInstant().plusSeconds(24 * 60 * 60));// 结束时间
        long diff = end.getTime() - start.getTime();
        int day = Math.toIntExact(diff / (1000 * 60 * 60 * 24)); //1天
        //根据开始和结束时间获取区间每个小时的时间数组
        List<Date> dates = IntStream.range(0, day).mapToObj(m -> Date.from(start.toInstant().plusSeconds(m * 60 * 60 * 24))).sorted((x, y) -> Math.toIntExact(y.getTime() - x.getTime())).collect(Collectors.toList());
        List<DataSearchEnt> list = dataSearchMapper.getDay(entity);
        List<DataTableEntity> elements = dataSearchMapper.getHeaderDay(entity);// 需要显示的因子列表
        List<Date> stops = dataSearchMapper.getStopTime(entity);// 停运时间列表
        Map<Date, List<DataSearchEnt>> groups = list.stream().collect(Collectors.groupingBy(DataSearchEnt::getDatadate, LinkedHashMap::new, Collectors.toList()));
        for (Date date : dates) {
            if (!(date.after(new Date()))) {
                Map<String, Object> item = new HashMap<>();
                item.put("date", DateUtils.parseDate(date, "yyyy-MM-dd"));
                for (DataTableEntity element : elements) {
                    Integer elementId = element.getElementid();
//                int digit = element.getDecimaldigit();
                    Double minValue = element.getMinvalue();
                    Double maxValue = element.getMaxvalue();
                    if (ignoreElementIds.contains(elementId)) continue;
                    int dataElementId = 0;
                    switch (elementId) {
                        case 100002:
                            dataElementId = elementId + 34000;  //14000  34000
                            break;
                        case 100041:
                            elementId = 100040;
                            dataElementId = 134040;
                            break;
                        default:
                            dataElementId = elementId + 33000;
                            break;
                    }
                    if (stops.stream().anyMatch(p -> p.getTime() == date.getTime())) {
                        item.put("val_" + elementId, new HashMap<String, Object>() {
                            {
                                put("value", "停运");
                                put("flag", 2);
                            }
                        });
                        continue;
                    }
                    int finalDataElementId = dataElementId;
                    List<DataSearchEnt> data = groups.getOrDefault(date, new ArrayList<>());
                    Optional<DataSearchEnt> optional = data.stream().filter(p -> p.getElementid() == finalDataElementId).findFirst();
                    if (optional.isPresent()) {
                        DataSearchEnt searchEntity = optional.get();
                        Double value = searchEntity.getAmount();
                        Integer flag = searchEntity.getFlag();
                        if (value != null) {
                            // 根据digit对value进行截取
                            if (flag.equals(1)) {
                                item.put("val_" + elementId, new HashMap<String, Object>() {
                                    {
                                        put("value", value);
                                        put("flag", 1);
                                    }
                                });// {value: value, flag: 1}
                            } else {
                                if (!notOverflowElementIds.contains(elementId) && (minValue > value || value > maxValue)) {
                                    item.put("val_" + elementId, new HashMap<String, Object>() {
                                        {
                                            put("value", value);
                                            put("flag", 3);
                                        }
                                    });// {value: value, flag: 3}
                                } else {
                                    item.put("val_" + elementId, new HashMap<String, Object>() {
                                        {
                                            put("value", value);
                                            put("flag", 0);
                                        }
                                    });// {value: value, flag: 0}
                                }
                            }
                        } else {
                            item.put("val_" + elementId, new HashMap<String, Object>() {
                                {
                                    put("value", "");
                                    put("flag", 0);
                                }
                            });// {value: "null", flag: 1}
                        }
                    } else {
                        item.put("val_" + elementId, new HashMap<String, Object>() {
                            {
                                put("value", "");
                                put("flag", -1);
                            }
                        });// {value: "", flag: -1}
                    }
                }
                result.add(item);
            }
        }
        return result;
    }


}
