package com.ruoyi.project.dgom.service.impl;

import com.ruoyi.common.utils.HashMapUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.aspectj.lang.annotation.DataSource;
import com.ruoyi.framework.aspectj.lang.enums.DataSourceType;
import com.ruoyi.project.dgom.domain.DailyAvgStatistic;
import com.ruoyi.project.dgom.domain.DutyExport;
import com.ruoyi.project.dgom.domain.OutpaceTransStatistic;
import com.ruoyi.project.dgom.mapper.DutyMapper;
import com.ruoyi.project.dgom.service.DutyService;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@DataSource(value = DataSourceType.HBONLINE)
public class DutyServiceImpl implements DutyService {
    private final DutyMapper mapper;

    public DutyServiceImpl(DutyMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public List<OutpaceTransStatistic> getSubStationWeeklyReport(Map<String, String> param) {
        return mapper.getSubStationWeeklyReport(param);
    }

    @Override
    public List<Map> getEnterpriseWeeklyReport(Map<String, String> param) {
        return HashMapUtils.toCamel(mapper.getEnterpriseWeeklyReport(param));
    }

    @Override
    public List<DutyExport> getEnterpriseWeeklyReportExport(Map<String, String> param) {
        List<Map> maps = HashMapUtils.toCamel(mapper.getEnterpriseWeeklyReport(param));
        List<DutyExport> export = HashMapUtils.mapToBean(maps, DutyExport.class);
        return export;
    }

    @Override
    public Map<String, String> getDutyDiary(Map<String, String> param) {
        Map<String, String> result = new LinkedHashMap<>();
        result.put("h_src_num", "0");
        result.put("d_src_num", "0");
        result.put("h_w_num", "0");
        result.put("h_g_num", "0");
        result.put("d_w_num", "0");
        result.put("d_g_num", "0");
        List<Map> srcMap = HashMapUtils.toLower(mapper.getSrcNumByType(param));
        for (Map src : srcMap) {
            String abnorType = src.get("zabnortype").toString();
            result.put(abnorType.equals("1") ? "h_src_num" : "d_src_num", src.get("src_num") + "家");
        }
        //时均值,日均值 -获取水和气的超标数量
        List<Map> overMap = HashMapUtils.toLower(mapper.getOverNumByType(param));
        for (Map over : overMap) {
            String checkpointType = over.get("zcheckpointtype").toString();
            String abnorType = over.get("zabnortype").toString();
            if (checkpointType.equals("1")) {
                result.put(abnorType.equals("1") ? "h_w_num" : "d_w_num", "水:" + over.get("num"));
            } else {
                result.put(abnorType.equals("1") ? "h_g_num" : "d_g_num", "气:" + over.get("num"));
            }
        }
        //小时均值 （水）设备情况
        List<Map> waterHourDevMap = HashMapUtils.toLower(mapper.getWaterHourDevNumByType(param));
        StringBuilder sb = new StringBuilder();
        for (Map waterDev : waterHourDevMap) {
            sb.append(waterDev.get("zelementdesc"))
                    .append(":")
                    .append(waterDev.get("devnum"))
                    .append("\r\n");
        }
        result.put("h_w_dev_info", sb.toString());
        //小时均值 - 获取气的设备数量
        Map gasHourDevMap = HashMapUtils.toLower(mapper.getGasHourDevNumByType(param));
        sb = new StringBuilder();
        sb.append("烟气：").append(gasHourDevMap.get("devnum"));
        result.put("h_g_dev_info", sb.toString());
        //日均值 -获取水的设备数量
        List<Map> waterDayDevMap = HashMapUtils.toLower(mapper.getWaterDayDevNumByType(param));
        sb = new StringBuilder();
        for (Map waterDev : waterDayDevMap) {
            sb.append(waterDev.get("zelementdesc"))
                    .append(":")
                    .append(waterDev.get("devnum"))
                    .append("\r\n");
        }
        result.put("d_w_dev_info", sb.toString());
        //日均值 - 获取气的设备数量
        Map gasDayDev = HashMapUtils.toLower(mapper.getGasDayDevNumByType(param));
        sb = new StringBuilder();
        sb.append("烟气：").append(gasDayDev.get("devnum"));
        result.put("d_g_dev_info", sb.toString());
        //传输率<90% 企业数
        result.put("trans_rate_num", mapper.getSrcNumByTransRate(param) + "家企业");
        //传输率<90% 设备信息
        List<Map> wastewaterDevMap = HashMapUtils.toLower(mapper.getWasteWaterDevNumByTransRate(param));
        sb = new StringBuilder();
        for (Map waterDev : wastewaterDevMap) {
            sb.append(waterDev.get("zelementdesc"))
                    .append(":")
                    .append(waterDev.get("num"))
                    .append("\r\n");
        }
        sb.append("烟气：").append(mapper.getWasteGasDevNumByTransRate(param));
        result.put("trans_rate_info", sb.toString());
        result.put("zero_record_num", mapper.getZeroNumByAbnor(param) + "条零值");
        List<Map> zeroWaterDevMap = HashMapUtils.toLower(mapper.getZeroWasteWaterDev(param));
        sb = new StringBuilder();
        for (Map waterDev : zeroWaterDevMap) {
            sb.append(waterDev.get("zelementdesc"))
                    .append(":")
                    .append(waterDev.get("total"))
                    .append("\r\n");
        }
        sb.append("烟气：").append(mapper.getZeroWasteGasDev(param));
        result.put("zero_record_info", sb.toString());
        return result;
    }

    @Override
    public List<Map> getAbnorProcessResult(Map<String, String> param) {
        return HashMapUtils.toCamel(mapper.getAbnorProcessResult(param));
    }

    @Override
    public List<DutyExport> getAbnorProcessResultExport(Map<String, String> param) {
        List<Map> list = HashMapUtils.toCamel(mapper.getAbnorProcessResult(param));
        List<DutyExport> export = HashMapUtils.mapToBean(list, DutyExport.class);
        return export;
    }

    @Override
    public Map<String, List<Map>> getAbnorSummary(Map<String, String> param) {
        List<Map> cates = new ArrayList<>();
        cates.addAll(HashMapUtils.toLower(mapper.getAbnorSummaryBySubcategory(param)));
        cates.add(HashMapUtils.toLower(mapper.getAbnorSummaryByOther(param)));
        cates.add(HashMapUtils.toLower(mapper.getAbnorSummaryByZhongDianOther(param)));
        cates.add(HashMapUtils.toLower(mapper.getAbnorSummaryAll(param)));
        Map<String, List<Map>> result = new LinkedHashMap<>();
        result.put("cates", cates);
        result.put("towns", HashMapUtils.toLower(mapper.getAbnorSummaryByTown(param)));
        return result;
    }

    @Override
    public List<Map> getAbnorEnterpriseSummary(Map<String, String> param) {
        return HashMapUtils.toCamel(mapper.getAbnorEnterpriseSummary(param));
    }

    @Override
    public List<DutyExport> getAbnorEnterpriseSummaryExport(Map<String, String> param) {
        List<Map> maps = HashMapUtils.toCamel(mapper.getAbnorEnterpriseSummary(param));
        List<DutyExport> export = HashMapUtils.mapToBean(maps, DutyExport.class);
        export.stream().forEach(x -> {
            x.setSourceName(x.getTownName() + "生态环境分局");
            List<String> flag = Arrays.asList(x.getTwoDayCnt(), x.getFourDayCnt(), x.getRateLess90());
            x.setRateLess90(StringUtils.join(flag.stream().filter(Objects::nonNull).collect(Collectors.toList()), ","));
        });
        return export;
    }

    @Override
    public List<Map> getTransRateLess90(Map<String, String> param) {
        return HashMapUtils.toCamel(mapper.getTransRateLess90(param));
    }

    @Override
    public List<DutyExport> getTransRateLess90Export(Map<String, String> param) {
        List<Map> maps = HashMapUtils.toCamel(mapper.getTransRateLess90(param));
        List<DutyExport> export = HashMapUtils.mapToBean(HashMapUtils.toTypeOf(maps), DutyExport.class);
        return export;
    }

    @Override
    public List<DailyAvgStatistic> getDailyAvgStatistic(Map<String, String> param) {
        return mapper.getDailyAvgStatistic(param);
    }
}
