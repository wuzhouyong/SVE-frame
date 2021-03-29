package com.ruoyi.project.zaozhi.service.impl;

import com.ruoyi.common.utils.HashMapUtils;
import com.ruoyi.framework.aspectj.lang.annotation.DataSource;
import com.ruoyi.framework.aspectj.lang.enums.DataSourceType;
import com.ruoyi.project.enterprise.domain.EnterpriseNetworkEntity;
import com.ruoyi.project.guocheng.domain.GCSourceAbnorEntity;
import com.ruoyi.project.zaozhi.domain.ZZExportGatherEntity;
import com.ruoyi.project.zaozhi.domain.ZZGatherEntity;
import com.ruoyi.project.zaozhi.domain.ZZSourceAbnorEntity;
import com.ruoyi.project.zaozhi.mapper.ZZGatherMapper;
import com.ruoyi.project.zaozhi.service.ZZGatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author solang
 * @date 2020-11-03 10:37
 */
@Service
@DataSource(value = DataSourceType.ZAOZHI)
public class ZZGatherServiceImpl implements ZZGatherService {

    @Autowired
    private ZZGatherMapper gatherMapper;

    @Override
    public List<ZZExportGatherEntity> getEnterpriseList(ZZGatherEntity entity) {
        return gatherMapper.getEnterpriseList(entity);
    }

    @Override
    public List<ZZExportGatherEntity> getTownAbnormalList(ZZGatherEntity entity) {
        return gatherMapper.getTownAbnormalList(entity);
    }

    @Override
    public List<Map> getCalculate() {
        //当月异常统计(级别,异常分类,异常企业数(占比:企业数/总企业数),异常数(占比:异常数/总异常数))
        Map map = HashMapUtils.toCamel(gatherMapper.getCalculateHead());
        List<Map> maps = HashMapUtils.toCamel(gatherMapper.getCalculateBody());
        maps.forEach(x -> {
            Double sourceCnt_x = Double.valueOf(String.valueOf(x.get("sourceCnt")));
            Double sourceCnt_m = Double.valueOf(String.valueOf(map.get("sourceCnt")));
            double v = Double.parseDouble(String.format("%.2f", sourceCnt_x / sourceCnt_m * 100));
            Double cnt_m = Double.valueOf(String.valueOf(map.get("cnt")));
            Double cnt_x = Double.valueOf(String.valueOf(x.get("cnt")));
            double m = Double.parseDouble(String.format("%.2f", cnt_x / cnt_m * 100));
            x.put("sourceCntRate", v + "%");
            x.put("cntRate", m + "%");
        });
        return HashMapUtils.orderValue(maps, "warnLevel");
    }

    @Override
    public Map getEquipment() {
        Map map = new HashMap();
        List<Map> body = HashMapUtils.toLower(gatherMapper.getBrandTransData());
        List<Map> head = HashMapUtils.toCamel(gatherMapper.getBrandTransHead());
        Map t_map = new LinkedHashMap();
        head.forEach(x -> {
            switch (x.get("rn").toString()) {
                case "1":
                    t_map.put("one", x.get("acquisitionBrand"));
                    break;
                case "2":
                    t_map.put("two", x.get("acquisitionBrand"));
                    break;
                case "3":
                    t_map.put("three", x.get("acquisitionBrand"));
                    break;
                case "4":
                    t_map.put("four", x.get("acquisitionBrand"));
                    break;
                case "5":
                    t_map.put("five", x.get("acquisitionBrand"));
                    break;
                case "6":
                    t_map.put("six", x.get("acquisitionBrand"));
                    break;
                case "7":
                    t_map.put("seven", x.get("acquisitionBrand"));
                    break;
                case "8":
                    t_map.put("eight", x.get("acquisitionBrand"));
                    break;
                case "9":
                    t_map.put("nine", x.get("acquisitionBrand"));
                    break;
            }
        });
        t_map.put("other", "其他");

        map.put("head", t_map);
        map.put("data", body);
        return map;
    }

    @Override
    public Map getConstruction() {
        Map map = new HashMap();
        List<Map> body = HashMapUtils.toLower(gatherMapper.getConstructionTransData());
        List<Map> head = HashMapUtils.toCamel(gatherMapper.getConstructionTransHead());
        Map t_map = new LinkedHashMap();
        head.forEach(x -> {
            switch (x.get("rn").toString()) {
                case "1":
                    t_map.put("one", x.get("shortName"));
                    break;
                case "2":
                    t_map.put("two", x.get("shortName"));
                    break;
                case "3":
                    t_map.put("three", x.get("shortName"));
                    break;
                case "4":
                    t_map.put("four", x.get("shortName"));
                    break;
                case "5":
                    t_map.put("five", x.get("shortName"));
                    break;
                case "6":
                    t_map.put("six", x.get("shortName"));
                    break;
                case "7":
                    t_map.put("seven", x.get("shortName"));
                    break;
                case "8":
                    t_map.put("eight", x.get("shortName"));
                    break;
                case "9":
                    t_map.put("nine", x.get("shortName"));
                    break;
            }
        });
        t_map.put("other", "其他");

        map.put("head", t_map);
        map.put("data", body);
        return map;
    }

    @Override
    public List<ZZSourceAbnorEntity> getSourceAbnor(ZZSourceAbnorEntity entity) {
        return gatherMapper.getSourceAbnor(entity);
    }

    @Override
    public List<Map> getTownWastewater(String startDate, String endDate) {
        List<Map> list = gatherMapper.getTownWastewater(startDate, endDate);
        return HashMapUtils.toCamel(list);
    }

    @Override
    public List<Map> getMonthWastewater(String startDate, String endDate) {
        return HashMapUtils.toCamel(gatherMapper.getMonthWastewater(startDate, endDate));
    }

    @Override
    public List<Map> getTownMonthWastewater(Map<String, String> param) {
        List<Map> list = HashMapUtils.toCamel(gatherMapper.getTownMonthWastewater(param));
        return HashMapUtils.toTypeOf(list);
    }

    @Override
    public List<Map> getTownElectric(String startDate, String endDate) {
        List<Map> list = gatherMapper.getTownElectric(startDate, endDate);
        return HashMapUtils.toCamel(list);
    }

    @Override
    public List<Map> getMonthElectric(String startDate, String endDate) {
        return HashMapUtils.toCamel(gatherMapper.getMonthElectric(startDate, endDate));
    }

    @Override
    public List<Map> getTownMonthElectric(Map<String, String> param) {
        List<Map> list = HashMapUtils.toCamel(gatherMapper.getTownMonthElectric(param));
        return HashMapUtils.toTypeOf(list);
    }

    @Override
    public List<Map> getTownWater(String startDate, String endDate) {
        List<Map> list = gatherMapper.getTownWater(startDate, endDate);
        return HashMapUtils.toCamel(list);
    }

    @Override
    public List<Map> getMonthWater(String startDate, String endDate) {
        return HashMapUtils.toCamel(gatherMapper.getMonthWater(startDate, endDate));
    }

    @Override
    public List<Map> getTownMonthWater(Map<String, String> param) {
        List<Map> list = HashMapUtils.toCamel(gatherMapper.getTownMonthWater(param));
        return HashMapUtils.toTypeOf(list);
    }

    @Override
    public List<Map> getTownNetworkCount() {
        return HashMapUtils.toLower(gatherMapper.getTownNetworkCount());
    }

    @Override
    public List<EnterpriseNetworkEntity> getEnterpriseNetwork(EnterpriseNetworkEntity entity) {
        List<EnterpriseNetworkEntity> list = gatherMapper.getEnterpriseNetwork(entity);
        list.stream().forEach(x -> {
            if (x.getStatus() != null) {
                //施工状态:0:未开展,1:施工中,2:已接入,3:接入完成,4:待验收,5:"",6:已验收
                switch (x.getStatus()) {
                    case 0:
                        x.setStatusName("未开展");
                        break;
                    case 1:
                        x.setStatusName("施工中");
                        break;
                    case 2:
                        x.setStatusName("已接入");
                        break;
                    case 3:
                        x.setStatusName("接入完成");
                        break;
                    case 4:
                        x.setStatusName("待验收");
                        break;
                    case 6:
                        x.setStatusName("已验收");
                        break;
                    default:
                        x.setStatusName("");
                        break;
                }
            }
        });
        return list;
    }

    //企业生产用水统计
    @Override
    public List<Map> getProductWaterAmt(Map<String, String> param) {
        return HashMapUtils.toLower(gatherMapper.getProductWaterAmt(param));
    }

    //企业生产用电统计
    @Override
    public List<Map> getProdElectricAmt(Map<String, String> param) {
        return HashMapUtils.toLower(gatherMapper.getProdElectricAmt(param));
    }

    //企业回用水量统计
    @Override
    public List<Map> getReuseWaterAmt(Map<String, String> param) {
        return HashMapUtils.toLower(gatherMapper.getReuseWaterAmt(param));
    }

    //废水治理设施相关设备实时状态统计
    @Override
    public List<Map> getRealElectricAmt(Map<String, String> param) {
        return HashMapUtils.toLower(gatherMapper.getRealElectricAmt(param));
    }

    //企业污水排放流量统计
    @Override
    public List<Map> getSewageDischargeAmt(Map<String, String> param) {
        return HashMapUtils.toLower(gatherMapper.getSewageDischargeAmt(param));
    }

    // 末端排放因子
    @Override
    public List<Map> getElementByEndDischarge(String collectId) {
        return HashMapUtils.toLower(gatherMapper.getElementByEndDischarge(collectId));
    }

    //因子排放趋势分析
    @Override
    public List<Map> getDischargeTrend(Map<String, String> param) {
        return HashMapUtils.toLower(gatherMapper.getDischargeTrend(param));
    }
}
