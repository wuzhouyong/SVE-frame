package com.ruoyi.project.enterprise.service.impl;

import com.ruoyi.common.utils.HashMapUtils;
import com.ruoyi.project.enterprise.domain.dashboard.DashboardParam;
import com.ruoyi.project.enterprise.domain.dashboard.SourceSearchParam;
import com.ruoyi.project.enterprise.domain.dashboard.SourceSearchResult;
import com.ruoyi.project.enterprise.mapper.DashboardMapper;
import com.ruoyi.project.enterprise.service.IDashboardService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DashboardService implements IDashboardService {
    @Resource
    private DashboardMapper mapper;

    @Override
    public List<SourceSearchResult> getSourceList(SourceSearchParam param) {
        return mapper.getSourceList(param);
    }

    @Override
    public Map<String, Object> getSourceStatistics(DashboardParam param) {
        Map<String, Object> map = new LinkedHashMap<>();
        List<Map> list = HashMapUtils.toLower(mapper.getSourceStatistics(param));
        double total = list.stream().mapToDouble(m -> Double.parseDouble(m.get("total").toString())).sum();
        list = list.stream().filter(p -> p.get("name") != null).collect(Collectors.toList());
        list.forEach(e -> {
            double count = Double.parseDouble(e.get("total").toString());
            e.put("rate", Math.round(count / total * 10000.0) / 100.0);
        });
        map.put("total", total);
        map.put("list", list.stream().filter(p -> p.get("name") != null).collect(Collectors.toList()));
        return map;
    }

    @Override
    public Map<String, Object> getWarnStatistics(DashboardParam param) {
        Map<String, Object> map = new LinkedHashMap<>();
        List<Map> list = HashMapUtils.toLower(mapper.getWarnStatistics(param));
        double total = list.stream().mapToDouble(m -> Double.parseDouble(m.get("total").toString())).sum();
        LinkedHashMap<String, List<Map>> level_group = list.stream()
                .collect(Collectors.groupingBy(g -> g.get("warn_level").toString(), LinkedHashMap::new, Collectors.toList()));
        for (Map.Entry<String, List<Map>> entry : level_group.entrySet()) {
            double level_total = entry.getValue().stream().mapToDouble(m -> Double.parseDouble(m.get("total").toString())).sum();
            List<Map> level_list = entry.getValue().stream()
                    .sorted(Comparator.comparing((Map m) -> Integer.parseInt(m.get("total").toString())).reversed()).limit(4)
                    .collect(Collectors.toList());
            level_list.forEach(e -> {
                e.remove("level");
                double count = Double.parseDouble(e.get("total").toString());
                e.put("rate", Math.round(count / level_total * 10000.0) / 100.0);
            });
            double other_total = entry.getValue().stream()
                    .sorted(Comparator.comparing((Map m) -> Integer.parseInt(m.get("total").toString())).reversed()).skip(4)
                    .mapToDouble(m -> Double.parseDouble(m.get("total").toString())).sum();
            Map<String, Object> other = new LinkedHashMap<>();
            other.put("name", "其他");
            other.put("total", other_total);
            other.put("rate", Math.round(other_total / level_total * 10000.0) / 100.0);
            level_list.add(other);
            Map<String, Object> level_map = new LinkedHashMap<>();
            level_map.put("total", level_total);
            level_map.put("list", level_list);
            map.put(entry.getKey(), level_map);
        }
        map.put("total", total);
        return map;
    }
}
