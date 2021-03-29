package com.ruoyi.project.outwater.service.impl;

import com.ruoyi.common.domain.ResultEntity;
import com.ruoyi.common.utils.CollectUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.aspectj.lang.annotation.DataSource;
import com.ruoyi.framework.aspectj.lang.enums.DataSourceType;
import com.ruoyi.project.outwater.domain.ExceptformulaEntity;
import com.ruoyi.project.outwater.domain.SpecwarnmsgEntity;
import com.ruoyi.project.outwater.domain.entity.SpecwarnmsgExport;
import com.ruoyi.project.outwater.mapper.BoundaryExceptMapper;
import com.ruoyi.project.outwater.service.BoundaryExceptService;
import com.sun.jna.platform.unix.solaris.LibKstat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author solang
 * @date 2020-11-23 9:31
 */

@Service
@DataSource(value = DataSourceType.OUTWATER)
public class BoundaryExceptServiceImpl implements BoundaryExceptService {

    @Autowired
    private BoundaryExceptMapper mapper;

    @Override
    public Map selectBoundaryExceptList(ExceptformulaEntity entity) {
        List<ExceptformulaEntity> list = mapper.selectBoundaryExceptList(entity);
        LinkedHashMap<Object, List<ExceptformulaEntity>> listMap = CollectUtils.groupBy(list, "getWarnLevel");
        Map<String, Object> re_map = new HashMap<>();
        for (Object o : listMap.keySet()) {
            Map<String, Object> map_t = new HashMap<>();
            List<ExceptformulaEntity> single = mapper.selectBoundarySingle(o.toString());
            map_t.put("single", single);
            List<ExceptformulaEntity> group = mapper.selectBoundarygGroup(o.toString());
            LinkedHashMap<Object, List<ExceptformulaEntity>> T_map = CollectUtils.groupBy(group, "getTermsGroup");
            List<Map> l = new ArrayList<>();
            for (Map.Entry<Object, List<ExceptformulaEntity>> obj : T_map.entrySet()) {
                Map<String, Object> map = new HashMap();
                map.put("list", obj.getValue());
                ExceptformulaEntity e = obj.getValue().get(0);
                map.put("id", e.getWarnLevel() + e.getWarnType() + e.getTermsGroup());
                map.put("warnDesc", e.getWarnDesc());
                map.put("warnLevel", e.getWarnLevel());
                map.put("termsGroup", e.getTermsGroup());
                l.add(map);
            }
            map_t.put("group", l);
            re_map.put(o.toString(), map_t);
        }
        return re_map;
    }

    @Override
    public Map selectBoundaryExceptList1(ExceptformulaEntity entity) {
        List<ExceptformulaEntity> list = mapper.selectBoundaryExceptList(entity);
        LinkedHashMap<Object, List<ExceptformulaEntity>> listMap = CollectUtils.groupBy(list, "getWarnLevel");
        Map<String, Object> re_map = new HashMap<>();
        for (Map.Entry<Object, List<ExceptformulaEntity>> entry : listMap.entrySet()) {
            LinkedHashMap<Object, List<ExceptformulaEntity>> map = CollectUtils.groupBy(entry.getValue(), "getTermsGroup");
            Map<String, Object> map_t = new HashMap<>();
            if (map.keySet().contains(0)) map_t.put("single", map.get(0));
            List<Map> l = new ArrayList<>();
            for (Map.Entry<Object, List<ExceptformulaEntity>> o : map.entrySet()) {
                if (0 != Integer.parseInt(o.getKey().toString())) {
                    Map<String, Object> map_g = new HashMap<>();
                    map_g.put("list", o.getValue());
                    ExceptformulaEntity t = o.getValue().get(0);
                    map_g.put("id", t.getWarnLevel() + t.getWarnType() + t.getTermsGroup());
                    map_g.put("warnDesc", t.getWarnDesc());
                    map_g.put("termsGroup", t.getTermsGroup());
                    map_g.put("warnLevel", t.getWarnLevel());
                    List<Integer> collect = o.getValue().stream().map(ExceptformulaEntity::getTermsType).distinct().collect(Collectors.toList());
                    if (StringUtils.isNotEmpty(collect)) {
                        map_g.put("termsType", StringUtils.join(collect, ","));
                    }
                    l.add(map_g);
                }
            }
            map_t.put("group", l);
            re_map.put(entry.getKey().toString(), map_t);
        }
        return re_map;
    }

    @Override
    public ResultEntity editBoundaryExcept(List<ExceptformulaEntity> entity) {
        if (entity == null || entity.isEmpty()) return ResultEntity.error("请选择异常等级信息");
        int acc = 0;
        for (ExceptformulaEntity e : entity) {
            acc = mapper.editBoundaryExcept(e);
            if (acc < 1) return ResultEntity.error("编辑排水户阀值异常信息失败");
            acc++;
        }
        return ResultEntity.success("成功编辑排水户阀值异常" + acc + "条");
    }

    @Override
    public List<SpecwarnmsgEntity> selectSpecwarnmsgList(SpecwarnmsgEntity entity) {
        return mapper.selectSpecwarnmsgList(entity);
    }

    @Override
    public List<SpecwarnmsgExport> selectSpecwarnmsgExport(SpecwarnmsgEntity entity) {
        return mapper.selectSpecwarnmsgExport(entity);
    }


}
